public class ExceptionUserAndToDoList extends Exception {

    /**
     * @param messageType ExceptionMessageType
     */
    public ExceptionUserAndToDoList(ExceptionMessageType messageType) {
        super(messageType.getMessage());

    }

    public ExceptionUserAndToDoList(String message) {
        super(message);
    }
}
