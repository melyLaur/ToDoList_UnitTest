import java.time.LocalDate;

public class Item {
    private String name;
    private String description;
    private LocalDate dateCreation;

    /**
     * Natural Constructor
     * */
    public Item(final String name, final String description, final LocalDate dateCreation){
        verifyParameters(name, description, dateCreation);
        this.name = name;
        this.description = description;
        this.dateCreation = dateCreation;
    }

    /**
     * Cette fonction vérifie si l'item a des paramètres corrects
     *
     * @throws IllegalArgumentException
     */
    public void verifyParameters(final String name, final String description, final LocalDate dateCreation) throws IllegalArgumentException{
        if(name.isBlank() || description.isBlank() || dateCreation == null)
            throw new IllegalArgumentException(ExceptionMessageType.PARAMETERS_MISSING.getMessage());
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }
}
