import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    private long startTime = 0;
    private long stopTime = 0;
    private long elapsed = 0;
    private boolean running = false;

    private List<Item> items;
    private int size;

    private EmailSenderService emailSenderService;

    public ToDoList() {
        this.emailSenderService = new EmailSenderService();
        this.items = new ArrayList<>();
    }

    /**
     * Cette fonction permet d'ajouter un item à une toDoList
     * @param item : item qu'on veut ajouter à une todoList
     * */
    public boolean addToToDoList(Item item) throws ExceptionUserAndToDoList {
        checkName(item.getName());
        if (running) {
            stop();
            elapsed = (System.currentTimeMillis() - startTime);
        }

        if (this.getSize() < 10) {
            if (elapsed >= 1800000 || items.size() == 0) {
                item.setDateCreation(LocalDate.from(LocalDateTime.now()));
                items.add(item);
                this.size++;
                start();
                return true;
            } else {
                throw new ExceptionUserAndToDoList(ExceptionMessageType.TODOLIST_ADD_ITEM_TOO_EARLY);
            }

        } else {
            throw new ExceptionUserAndToDoList(ExceptionMessageType.TODOLIST_LONGER);
        }

    }
    /**
     * Cette fonction vérifie si la todoList contient 8 objets
     * */
    public void check8item() {
        if (this.getSize() == 8) {
            emailSenderService.pushEmail();
        }
    }

    private boolean checkName(String name) throws ExceptionUserAndToDoList {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName() == name) {
                throw new ExceptionUserAndToDoList(ExceptionMessageType.ITEM_UNIQUE_NAME);
            }
        }
        return true;

    }

    private void start() {
        this.startTime = System.currentTimeMillis();
        this.running = true;
    }


    private void stop() {
        this.stopTime = System.currentTimeMillis();
        this.running = false;
    }


    //Getters and Setters
    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getStopTime() {
        return stopTime;
    }

    public void setStopTime(long stopTime) {
        this.stopTime = stopTime;
    }

    public long getElapsed() {
        return elapsed;
    }

    public void setElapsed(long elapsed) {
        this.elapsed = elapsed;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public EmailSenderService getEmailSenderService() {
        return emailSenderService;
    }

    public void setEmailSenderService(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }
}
