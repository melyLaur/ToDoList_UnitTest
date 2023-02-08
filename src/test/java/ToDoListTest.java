import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListTest {
    private Item item;
    private ToDoList toDoList;

    @BeforeEach
    void setVariable() {
        LocalDate date = LocalDate.now();
        item = new Item("name", "la description", date);
        toDoList = new ToDoList();
    }


    /**
     * Cette fonction teste le fonctionnement suivant :
     * L'ajout d'un item valide dans la ToDolist doit return True
     */
    @Test
    public void testAddToToListValid() {
        try {
            assertTrue(toDoList.addToToDoList(item));
            assertEquals(1, toDoList.getSize());
            assertEquals(item, toDoList.getItems().get(0));

        } catch (ExceptionUserAndToDoList e) {
            fail("unexpected exception");
        }
    }

    /**
     * Cette fonction teste le fonctionnement suivant :
     * L'ajout d'un item ayant déjà un nom dans la toDoList doit lever une exception
     */
    @Test
    public void testAddToToDoListThrowExceptionCauseNotUniqueName() {
        Item itemParameter = new Item(item.getName(), "Je dois faire la vaisselle", LocalDate.now());
        try {
            toDoList.addToToDoList(item);
            assertThrows(ExceptionUserAndToDoList.class, () -> toDoList.addToToDoList(itemParameter), ExceptionMessageType.ITEM_UNIQUE_NAME.getMessage());
        } catch (ExceptionUserAndToDoList e) {
            fail("unexpected exception");

        }
    }

    /**
     * Cette fonction teste le fonctionnement suivant :
     * L'ajout d'un item en moins de 30 min doit lever une exception
     */
    @Test
    public void testAddToToDoListThrowExceptionCauseTimeLessThan30Minutes() {
        Item itemParameter = new Item("Item2", "Je dois faire la vaisselle", LocalDate.now());
        Item itemParameter2 = new Item("Item3", "Je dois faire la vaisselle", LocalDate.now());
        try {
            toDoList.addToToDoList(itemParameter);
        } catch (ExceptionUserAndToDoList e) {
            fail("unexpected exception");
        }
        assertThrows(ExceptionUserAndToDoList.class, () -> toDoList.addToToDoList(itemParameter2), ExceptionMessageType.TODOLIST_ADD_ITEM_TOO_EARLY.getMessage());
    }

    /**
     * Cette fonction teste le fonctionnement suivant :
     * Si on ajoute un item dans une todoList de 10 items, une exception est lancée
     */
    @Test
    public void testAddToToDolistThrowExceptionCauseToDolistIsTooLonger() {
        toDoList.setSize(10);
        assertThrows(ExceptionUserAndToDoList.class, () -> toDoList.addToToDoList(item), ExceptionMessageType.TODOLIST_LONGER.getMessage());
    }
}