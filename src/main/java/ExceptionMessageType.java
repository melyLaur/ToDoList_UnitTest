public enum ExceptionMessageType {
    PARAMETERS_MISSING("Parameters are missing"),
    AGE_INVALID("You're too young"),
    EMAIL_INVALID("Mail is not good. Please verify if mail contains @ or your domain extension"),
    PASSWORD_INVALID("Format of your password is invalid, please make sure you have one upperCase, lowerCase and " +
            "a number"),

    ITEM_UNIQUE_NAME("Name existing !"),
    TODOLIST_LONGER("Max 10 items"),
    TODOLIST_ADD_ITEM_TOO_EARLY("You cannot add an item. It's too early !"),
    TODOLIST_ADD_INVALID("We cannot add your item");


    private String message;

    ExceptionMessageType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
