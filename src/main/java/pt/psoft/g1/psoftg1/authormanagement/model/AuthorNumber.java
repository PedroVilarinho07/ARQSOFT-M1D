package pt.psoft.g1.psoftg1.authormanagement.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;


public class AuthorNumber implements Serializable {

    @NotNull
    @NotBlank
    private String authorNumber;

    public AuthorNumber(String authorNumber) {
        this.authorNumber = authorNumber;
    }

    /**Protected empty constructor for ORM only.*/
    public AuthorNumber() {}

    public String toString() {
        return this.authorNumber;
    }

}
