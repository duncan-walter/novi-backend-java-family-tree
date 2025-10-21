package FamilyTree;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private String middleName;
    private String lastName;
    private Gender gender;
    private int age;
    private Person mother;
    private Person father;
    private final List<Person> siblings = new ArrayList<>();
    private final List<Person> children = new ArrayList<>();
    private final List<Pet> pets = new ArrayList<>();

    public Person(String name, String lastName, int age, Gender gender) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    public Person(String name, String middleName, String lastName, int age, Gender gender) {
        this(name, lastName, age, gender);
        this.middleName = middleName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person getMother() {
        return mother;
    }

    public Person getFather() {
        return father;
    }

    public void setParents(Person mother, Person father) {
        this.mother = mother;
        this.father = father;
    }

    public List<Person> getSiblings() {
        return this.siblings;
    }

    public List<Person> getChildren() {
        return this.children;
    }

    public List<Pet> getPets() {
        return this.pets;
    }

    public void addChild(Person childToAdd) {
        if (this.children.contains(childToAdd)) return;

        this.children.add(childToAdd);

        if (this.gender.equals(Gender.Male)) {
            childToAdd.setParents(childToAdd.getMother(), this);
        } else {
            childToAdd.setParents(this, childToAdd.getFather());
        }

        for (Person child : this.children) {
            if (child == childToAdd) continue;
            if (child.getSiblings().contains(childToAdd)) continue;

            child.addSibling(childToAdd);
        }
    }

    public void addPet(Pet pet) {
        this.pets.add(pet);
        pet.setOwner(this);
    }

    public void addSibling(Person sibling) {
        if (siblings.contains(sibling)) return;

        this.siblings.add(sibling);
        sibling.addSibling(this);
    }

    public List<Person> getGrandchildren() {
        List<Person> grandchildren = new ArrayList<>();

        for (Person child : this.getChildren()) {
            grandchildren.addAll(child.getChildren());
        }

        return grandchildren;
    }

    public List<Pet> getGrandchildrenPets() {
        List<Pet> grandchildrenPets = new ArrayList<>();

        for (Person grandchild : this.getGrandchildren()) {
            grandchildrenPets.addAll(grandchild.getPets());
        }

        return grandchildrenPets;
    }

    public List<Person> getCousins() {
        return this.getCousins(null);
    }

    public List<Person> getCousins(Gender gender) {
        List<Person> cousins = new ArrayList<>();
        List<Person> parentSiblings = new ArrayList<>();
        var mother = this.getMother();
        var father = this.getFather();

        if (father != null) {
            parentSiblings.addAll(father.getSiblings());
        }

        if (mother != null) {
            parentSiblings.addAll(mother.getSiblings());
        }

        for (Person parentSibling : parentSiblings) {
            var parentSiblingChildren = parentSibling.getChildren()
                    .stream()
                    .filter(parentChild -> gender == null || parentChild.getGender() == gender)
                    .toList();
            cousins.addAll(parentSiblingChildren);
        }

        return cousins;
    }
}