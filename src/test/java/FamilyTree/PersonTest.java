package FamilyTree;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
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
}