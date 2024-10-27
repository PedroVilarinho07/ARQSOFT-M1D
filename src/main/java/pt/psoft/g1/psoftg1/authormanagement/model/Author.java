package pt.psoft.g1.psoftg1.authormanagement.model;

import lombok.Getter;
import pt.psoft.g1.psoftg1.authormanagement.dto.request.UpdateAuthorRequest;
import pt.psoft.g1.psoftg1.shared.model.EntityWithPhoto;
import pt.psoft.g1.psoftg1.shared.model.Name;

public class Author extends EntityWithPhoto {

    @Getter
    private AuthorNumber authorNumber;

    private Name name;

    private Bio bio;

    public void setAuthorNumber(String authorNumber) {
        this.authorNumber = new AuthorNumber(authorNumber);
    }

    public void setName(String name) {
        this.name = new Name(name);
    }

    public void setBio(String bio) {
        this.bio = new Bio(bio);
    }

    public Author(String authorNumber, String name, String bio, String photoURI) {
        setAuthorNumber(authorNumber);
        setName(name);
        setBio(bio);
        setPhotoInternal(photoURI);
    }

    protected Author() {}


    public void applyPatch(final UpdateAuthorRequest request) {
        if (request.getName() != null)
            setName(request.getName());
        if (request.getBio() != null)
            setBio(request.getBio());
        if(request.getPhotoURI() != null)
            setPhotoInternal(request.getPhotoURI());
    }

    public void removePhoto() {
        setPhotoInternal(null);
    }

    public String getName() {
        return this.name.toString();
    }

    public String getBio() {
        return this.bio.toString();
    }
}

