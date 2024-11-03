package pt.psoft.g1.psoftg1.authormanagement.model;

import lombok.Getter;
import pt.psoft.g1.psoftg1.authormanagement.dto.request.UpdateAuthorRequest;
import pt.psoft.g1.psoftg1.authormanagement.factories.AuthorNumberFactory;
import pt.psoft.g1.psoftg1.authormanagement.factories.BioFactory;
import pt.psoft.g1.psoftg1.shared.factories.NameFactory;
import pt.psoft.g1.psoftg1.shared.factories.PhotoFactory;
import pt.psoft.g1.psoftg1.shared.model.EntityWithPhoto;
import pt.psoft.g1.psoftg1.shared.model.Name;

public class Author extends EntityWithPhoto {

    private final AuthorNumberFactory authorNumberFactory;
    private final NameFactory nameFactory;
    private final BioFactory bioFactory;
    private final PhotoFactory photoFactory;

    @Getter
    private AuthorNumber authorNumber;

    private Name name;

    private Bio bio;

    public void setAuthorNumber(String authorNumber) {
        this.authorNumber = this.authorNumberFactory.newAuthorNumber(authorNumber);
    }

    public void setName(String name) {
        this.name = this.nameFactory.newName(name);
    }

    public void setBio(String bio) {
        this.bio = this.bioFactory.newBio(bio);
    }

    public void setPhoto(String photoURI) {
        this.photo = this.photoFactory.newPhoto(photoURI);
    }

    public Author(String authorNumber, String name, String bio, String photoURI, AuthorNumberFactory authorNumberFactory, NameFactory nameFactory, BioFactory bioFactory, PhotoFactory photoFactory) {
        this.authorNumberFactory = authorNumberFactory;
        this.nameFactory = nameFactory;
        this.bioFactory = bioFactory;
        this.photoFactory = photoFactory;
        setAuthorNumber(authorNumber);
        setName(name);
        setBio(bio);
        if (photoURI != null){
            setPhoto(photoURI);
        }
    }

    public void applyPatch(final UpdateAuthorRequest request) {
        if (request.getName() != null)
            setName(request.getName());
        if (request.getBio() != null)
            setBio(request.getBio());
        if(request.getPhotoURI() != null)
            setPhoto(request.getPhotoURI());
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

