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

    public void addChild(Person child) {
        this.children.add(child);

        if (this.gender.equals(Gender.Male)) {
            child.setParents(child.getMother(), this);
        } else {
            child.setParents(this, child.getFather());
        }
    }

    public void addPet(Pet pet) {
        this.pets.add(pet);
    }

    public void addSibling(Person sibling) {
        this.siblings.add(sibling);
    }

    public List<Person> getGrandchildren() {
        List<Person> grandchildren = new ArrayList<>();

        for (Person child : this.getChildren()) {
            grandchildren.addAll(child.getChildren());
        }

        return grandchildren;
    }
}