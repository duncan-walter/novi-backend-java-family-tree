package FamilyTree;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    @Test
    public void simpleGettersReturnExpectedValueWhenInitializedThroughConstructor() {
        // Arrange
        var person = new Person("Henk", "von","Baron", 100, Gender.Male);

        // Assert
        assertEquals("Henk", person.getName());
        assertEquals("von", person.getMiddleName());
        assertEquals("Baron", person.getLastName());
        assertEquals(100, person.getAge());
        assertEquals(Gender.Male, person.getGender());
    }

    @Test
    public void getGrandchildrenShouldReturnAllGrandchildrenWhenPresent() {
        // Arrange
        var grandfather = new Person("Grandfather", "Last name", 100, Gender.Male);
        var father1 = new Person("Father 1", "Last name", 50, Gender.Male);
        var father2 = new Person("Father 2", "Last name", 50, Gender.Male);
        var child11 = new Person("Child 11", "Last name", 25, Gender.Male);
        var child12 = new Person("Child 12", "Last name", 25, Gender.Male);
        var child13 = new Person("Child 13", "Last name", 25, Gender.Male);
        var child21 = new Person("Child 21", "Last name", 25, Gender.Male);
        var child22 = new Person("Child 22", "Last name", 25, Gender.Male);
        var child23 = new Person("Child 23", "Last name", 25, Gender.Male);

        grandfather.addChild(father1);
        grandfather.addChild(father2);
        father1.addChild(child11);
        father1.addChild(child12);
        father1.addChild(child13);
        father2.addChild(child21);
        father2.addChild(child22);
        father2.addChild(child23);

        // Act
        var grandchildren = grandfather.getGrandchildren();

        // Assert
        assertEquals(6, grandchildren.size());
    }

    @Test
    public void simpleSettersAssignExpectedValuesToFields() {
        // Arrange
        var person = new Person("Henk", "von","Baron", 100, Gender.Male);

        // Act
        person.setName("Honk");
        person.setMiddleName("van");
        person.setLastName("Bonk");
        person.setAge(1000);
        person.setGender(Gender.Female);

        // Assert
        assertEquals("Honk", person.getName());
        assertEquals("van", person.getMiddleName());
        assertEquals("Bonk", person.getLastName());
        assertEquals(1000, person.getAge());
        assertEquals(Gender.Female, person.getGender());
    }

    @Test
    public void setParentsShouldSetBothParentsWhenMotherAndFatherAreProvided() {
        // Arrange
        var mother = new Person("Parent", "Last name", 50, Gender.Female);
        var father = new Person("Parent", "Last name", 50, Gender.Male);
        var child = new Person("Child", "Last name", 25, Gender.Male);

        // Act
        child.setParents(mother, father);

        // Assert
        assertNotNull(child.getMother());
        assertNotNull(child.getFather());
    }

    @Test
    public void addChildShouldAddChildToParent() {
        // Arrange
        var parent = new Person("Parent", "Last name", 50, Gender.Male);
        var child = new Person("Child", "Last name", 25, Gender.Male);

        // Act
        parent.addChild(child);

        // Assert
        assertEquals(1, parent.getChildren().size());
    }

    @Test
    public void addChildShouldSetParentOnChild() {
        // Arrange
        var mother = new Person("Parent", "Last name", 50, Gender.Female);
        var father = new Person("Parent", "Last name", 50, Gender.Male);
        var child = new Person("Child", "Last name", 25, Gender.Male);

        // Act
        mother.addChild(child);
        father.addChild(child);

        // Assert
        assertNotNull(child.getMother());
        assertNotNull(child.getFather());
    }

    @Test
    public void addPetShouldAddPetToPerson() {
        // Arrange
        var person = new Person("Person", "Last name", 50, Gender.Male);
        var pet = new Pet ("Cat", 10, "Cat");

        // Act
        person.addPet(pet);

        // Assert
        assertEquals(1, person.getPets().size());
    }

    @Test
    public void addPetShouldAddOwnerToPet() {
        // Arrange
        var person = new Person("Person", "Last name", 50, Gender.Male);
        var pet = new Pet ("Cat", 10, "Cat");

        // Act
        person.addPet(pet);

        // Assert
        assertEquals(person.getName(), pet.getOwner().getName());
    }

    @Test
    public void addSiblingShouldAddSiblingToPerson() {
        // Arrange
        var brother = new Person("Brother", "Last name", 50, Gender.Male);
        var sister = new Person("Sister", "Last name", 50, Gender.Female);

        // Act
        brother.addSibling(sister);

        // Assert
        assertEquals(sister.getName(), brother.getSiblings().getFirst().getName());
    }

    @Test
    public void addSiblingShouldAddSiblingToBothPersons() {
        // Arrange
        var brother = new Person("Brother", "Last name", 50, Gender.Male);
        var sister = new Person("Sister", "Last name", 50, Gender.Female);

        // Act
        brother.addSibling(sister);

        // Assert
        assertEquals(sister.getName(), brother.getSiblings().getFirst().getName());
        assertEquals(brother.getName(), sister.getSiblings().getFirst().getName());
    }
}