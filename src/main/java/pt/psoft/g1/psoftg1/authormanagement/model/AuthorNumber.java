package pt.psoft.g1.psoftg1.authormanagement.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;


public class AuthorNumber implements Serializable {

    private String authorNumber;

    public AuthorNumber(String authorNumber) {
        setAuthorNumber(authorNumber);
    }

    public void setAuthorNumber(String authorNumber) {
        if(authorNumber == null)
            throw new IllegalArgumentException("Author Number cannot be null");
        if(authorNumber.isBlank())
            throw new IllegalArgumentException("Author Number cannot be blank");
        this.authorNumber = authorNumber;
    }

    /**Protected empty constructor for ORM only.*/
    public AuthorNumber() {}

    public String toString() {
        return this.authorNumber;
    }

}
