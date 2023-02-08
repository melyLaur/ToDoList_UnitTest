import java.time.LocalDate;

public class User {

    private String name;
    private String firstname;
    private LocalDate dateOfBirth;
    private String password;
    private String mail;

    private ToDoList toDoList;

    public static final int MAX_AGE = 13;

    //Natural Constructor
    public User(String firstname, String name, String mail, LocalDate dateOfBirth, String password) {
        this.firstname = firstname;
        this.name = name;
        this.mail = mail;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.toDoList = new ToDoList();
    }

    /**
     * Cette fonction permet d'ajouter un item dans la ToDoList de l'user
     * Si le nom n'est pas unique, une exception est retournée
     * Si la fonction addToToDoList retourne une exception alors une exception est levée
     * @param item : item
     * */
    public void addToDoList(Item item) throws ExceptionUserAndToDoList{
        try {
            isValid();
            this.toDoList.addToToDoList(item);
        } catch (ExceptionUserAndToDoList e) {
            throw new ExceptionUserAndToDoList(ExceptionMessageType.AGE_INVALID);
        }
        this.toDoList.check8item();
    }

    /**
     * Cette fonction vérifie si l'utilisateur est valide ( age > 13, mail contient @ et aucun param null)
     *
     * @return true si les attributs de l'user sont corrects
     */
    public boolean isValid() throws ExceptionUserAndToDoList {
        LocalDate nowDate = LocalDate.now();
        LocalDate validDate = LocalDate.of(nowDate.getYear() - MAX_AGE, nowDate.getMonthValue(), nowDate.getDayOfMonth());

        if (this.mail == null || this.firstname == null || this.name == null || this.dateOfBirth == null || this.password == null
                || this.mail.isBlank() || this.firstname.isBlank() || this.name.isBlank() || this.password.isBlank()) {
            throw new ExceptionUserAndToDoList(ExceptionMessageType.PARAMETERS_MISSING);
        } else if (!isValidEmail(this.mail)) {
            throw new ExceptionUserAndToDoList(ExceptionMessageType.EMAIL_INVALID);
        } else if (!this.dateOfBirth.isBefore(validDate)) {
            throw new ExceptionUserAndToDoList(ExceptionMessageType.AGE_INVALID);
        } else if (!isValidPassword(this.password)) {
            throw new ExceptionUserAndToDoList(ExceptionMessageType.PASSWORD_INVALID);
        }
        return true;
    }

    /**
     * Cette fonction vérifie si le format du mail est correct
     *
     * @param mail : mail de l'utilisateur
     */
    private boolean isValidEmail(final String mail) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return mail.matches(regexPattern);
    }

    /**
     * Cette fonction vérifie si le mot de passe est correct
     * (mot de passe avec une MAJ, une min, un chiffre ainsi qu'une longueur entre 8 et 40)
     *
     * @param password : mot de passe de l'utlisateur
     */
    private boolean isValidPassword(final String password) {
        char ch;
        boolean isNumber = false;
        boolean isUpperCase = false;
        boolean isLowerCase = false;

        if (!(password.length() >= 8 && password.length() <= 40)) return false;

        for (int i = 0; i < password.length(); i++) {
            ch = password.charAt(i);
            if (Character.isDigit(ch)) {
                isNumber = true;
            } else if (Character.isUpperCase(ch)) {
                isUpperCase = true;
            } else if (Character.isLowerCase(ch)) {
                isLowerCase = true;
            }

            if (isNumber && isUpperCase && isLowerCase) return true;
        }
        return false;

    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public ToDoList getToDoList() {
        return toDoList;
    }

    public void setToDoList(ToDoList toDoList) {
        this.toDoList = toDoList;
    }


}
