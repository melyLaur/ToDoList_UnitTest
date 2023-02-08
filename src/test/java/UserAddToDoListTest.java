import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class UserAddToDoListTest {

    private User user;
    private Item item;


    @BeforeEach
    void setUp3() {
        //User valid
        this.user = new User("Nawel", "Mougni", "n.mougni@myges.fr", LocalDate.now(),
                "123AhBBBBBBBgdfuyzge_è-(-è_");
        user.setDateOfBirth(LocalDate.of(LocalDate.now().getYear() - User.MAX_AGE, LocalDate.now().getMonthValue(),
                LocalDate.now().getDayOfMonth() - 1));

        //Mock
        this.item = mock(Item.class);
        ToDoList toDoList = mock(ToDoList.class);
        user.setToDoList(toDoList);
    }

    /**
     * Cette fonction test le fonctionnement suivant :
     * Si le nom est unique et qu'il y a bien eu un ajout dans la todolist, la fonction check8item est bien appelé
     */
    @Test
    public void testAddToDoListCorrect() {
        try {
            when(user.getToDoList().addToToDoList(item)).thenReturn(true);
            user.addToDoList(item);
            verify(user.getToDoList(), times(1)).check8item();

        } catch (ExceptionUserAndToDoList e) {
            fail("unexpected exception");
        }
    }

    /**
     * Cette fonction test le fonctionnement suivant :
     * Si le nom est unique et qu'une exception est soulevée dans la fonction addToToDoList,
     * Une exception est levée et la fonction check8item n'est jamais appelée
     */
    @Test
    public void testThrowsExceptionWhenAddToToDoListThrowException() {
        try {
            doThrow(ExceptionUserAndToDoList.class).when(user.getToDoList()).addToToDoList(item);
            verify(user.getToDoList(), never()).check8item();
            assertThrows(ExceptionUserAndToDoList.class, () -> user.addToDoList(item), ExceptionMessageType.TODOLIST_ADD_INVALID.getMessage());
        } catch (ExceptionUserAndToDoList ignored) {

        }

    }
}