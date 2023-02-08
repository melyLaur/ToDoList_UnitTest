import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ToDoList8ItemSendMailTest {
    private ToDoList toDoList;
    private EmailSenderService emailSenderService;

    @BeforeEach
    void setUp4(){
        this.toDoList = new ToDoList();
        this.emailSenderService = mock(EmailSenderService.class);
        this.toDoList.setEmailSenderService(emailSenderService);

    }

    /**
     * Ce test vérifie le focntionnement suivant :
     * Si la toDolist contient 8 elements, un mail est envoyé
     * */
    @Test
    void testWhenAdd8itemSendEmail(){
        toDoList.setSize(8);
        toDoList.check8item();
        verify(emailSenderService, times(1)).pushEmail();
    }

}