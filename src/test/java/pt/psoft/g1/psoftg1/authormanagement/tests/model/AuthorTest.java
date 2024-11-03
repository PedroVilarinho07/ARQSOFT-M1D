package pt.psoft.g1.psoftg1.authormanagement.tests.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.psoft.g1.psoftg1.authormanagement.dto.request.CreateAuthorRequest;
import pt.psoft.g1.psoftg1.authormanagement.dto.request.UpdateAuthorRequest;
import pt.psoft.g1.psoftg1.authormanagement.factories.AuthorNumberFactory;
import pt.psoft.g1.psoftg1.authormanagement.factories.BioFactory;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;
import pt.psoft.g1.psoftg1.authormanagement.model.AuthorNumber;
import pt.psoft.g1.psoftg1.authormanagement.model.Bio;
import pt.psoft.g1.psoftg1.shared.factories.NameFactory;
import pt.psoft.g1.psoftg1.shared.factories.PhotoFactory;
import pt.psoft.g1.psoftg1.shared.model.Name;
import pt.psoft.g1.psoftg1.shared.model.Photo;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthorTest {
    private final String validName = "João Alberto";
    private final String validAuthorNumber = "AuthorNumber";
    private final String validPhotoUri = "photoTest.jpg";
    private final String validBio = "O João Alberto nasceu em Chaves e foi pedreiro a maior parte da sua vida.";


    @Test
    public void ensureAuthorNumberNotNullTest(){
        //Arrange
        String expectedMessage = "Author Number cannot be null";
        AuthorNumberFactory doubleAuthorNumberFactory = mock(AuthorNumberFactory.class);
        NameFactory doubleNameFactory = mock(NameFactory.class);
        BioFactory doubleBioFactory = mock(BioFactory.class);
        PhotoFactory doublePhotoFactory = mock(PhotoFactory.class);

        Bio bioDouble = Mockito.mock(Bio.class);
        Name nameDouble = mock(Name.class);
        Photo photoDouble = mock(Photo.class);

        when(doubleAuthorNumberFactory.newAuthorNumber(null)).thenThrow(new IllegalArgumentException(expectedMessage));
        when(doubleNameFactory.newName(validName)).thenReturn(nameDouble);
        when(doubleBioFactory.newBio(validBio)).thenReturn(bioDouble);
        when(doublePhotoFactory.newPhoto(validPhotoUri)).thenReturn(photoDouble);

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Author(null,validName,validBio, null, doubleAuthorNumberFactory, doubleNameFactory, doubleBioFactory, doublePhotoFactory));

        //Assert
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ensureNameNotNullTest(){
        //Arrange
        String expectedMessage = "Name cannot be null";
        AuthorNumberFactory doubleAuthorNumberFactory = mock(AuthorNumberFactory.class);
        NameFactory doubleNameFactory = mock(NameFactory.class);
        BioFactory doubleBioFactory = mock(BioFactory.class);
        PhotoFactory doublePhotoFactory = mock(PhotoFactory.class);

        AuthorNumber authorNumberDouble = Mockito.mock(AuthorNumber.class);
        Bio bioDouble = Mockito.mock(Bio.class);
        Photo photoDouble = mock(Photo.class);

        when(doubleAuthorNumberFactory.newAuthorNumber(validAuthorNumber)).thenReturn(authorNumberDouble);
        when(doubleNameFactory.newName(null)).thenThrow(new IllegalArgumentException(expectedMessage));
        when(doubleBioFactory.newBio(validBio)).thenReturn(bioDouble);
        when(doublePhotoFactory.newPhoto(validPhotoUri)).thenReturn(photoDouble);

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Author(validAuthorNumber,null,validBio, null, doubleAuthorNumberFactory, doubleNameFactory, doubleBioFactory, doublePhotoFactory));

        //Assert
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void ensureBioNotNullTest(){
        //Arrange
        String expectedMessage = "Bio cannot be null";
        AuthorNumberFactory doubleAuthorNumberFactory = mock(AuthorNumberFactory.class);
        NameFactory doubleNameFactory = mock(NameFactory.class);
        BioFactory doubleBioFactory = mock(BioFactory.class);
        PhotoFactory doublePhotoFactory = mock(PhotoFactory.class);

        AuthorNumber authorNumberDouble = Mockito.mock(AuthorNumber.class);
        Name nameDouble = mock(Name.class);
        Photo photoDouble = mock(Photo.class);

        when(doubleAuthorNumberFactory.newAuthorNumber(validAuthorNumber)).thenReturn(authorNumberDouble);
        when(doubleNameFactory.newName(null)).thenReturn(nameDouble);
        when(doubleBioFactory.newBio(null)).thenThrow(new IllegalArgumentException(expectedMessage));
        when(doublePhotoFactory.newPhoto(validPhotoUri)).thenReturn(photoDouble);

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Author(validAuthorNumber,validName,null, null, doubleAuthorNumberFactory, doubleNameFactory, doubleBioFactory, doublePhotoFactory));

        //Assert
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testCreateAuthorWithoutPhotoTest() {
        //Arrange
        AuthorNumberFactory doubleAuthorNumberFactory = mock(AuthorNumberFactory.class);
        NameFactory doubleNameFactory = mock(NameFactory.class);
        BioFactory doubleBioFactory = mock(BioFactory.class);
        PhotoFactory doublePhotoFactory = mock(PhotoFactory.class);

        AuthorNumber authorNumberDouble = Mockito.mock(AuthorNumber.class);
        Name nameDouble = mock(Name.class);
        Bio bioDouble = Mockito.mock(Bio.class);

        when(doubleAuthorNumberFactory.newAuthorNumber(validAuthorNumber)).thenReturn(authorNumberDouble);
        when(doubleNameFactory.newName(validName)).thenReturn(nameDouble);
        when(doubleBioFactory.newBio(validBio)).thenReturn(bioDouble);
        when(doublePhotoFactory.newPhoto(null)).thenReturn(null);

        //Act
        Author author = new Author(validAuthorNumber, validName, validBio, null, doubleAuthorNumberFactory, doubleNameFactory, doubleBioFactory, doublePhotoFactory);

        //Assert
        assertNotNull(author);
        assertNotNull(author.getAuthorNumber());
        assertNotNull(author.getName());
        assertNotNull(author.getBio());
        assertNull(author.getPhoto());
    }

    @Test
    public void testCreateAuthorRequestWithPhotoTest() {
        //Arrange
        AuthorNumberFactory doubleAuthorNumberFactory = mock(AuthorNumberFactory.class);
        NameFactory doubleNameFactory = mock(NameFactory.class);
        BioFactory doubleBioFactory = mock(BioFactory.class);
        PhotoFactory doublePhotoFactory = mock(PhotoFactory.class);

        AuthorNumber authorNumberDouble = Mockito.mock(AuthorNumber.class);
        Name nameDouble = mock(Name.class);
        Bio bioDouble = Mockito.mock(Bio.class);
        Photo photoDouble = mock(Photo.class);

        when(doubleAuthorNumberFactory.newAuthorNumber(validAuthorNumber)).thenReturn(authorNumberDouble);
        when(doubleNameFactory.newName(validName)).thenReturn(nameDouble);
        when(doubleBioFactory.newBio(validBio)).thenReturn(bioDouble);
        when(doublePhotoFactory.newPhoto(validPhotoUri)).thenReturn(photoDouble);
        CreateAuthorRequest request = new CreateAuthorRequest(validName, validBio, null, validPhotoUri);

        //Act
        Author author = new Author(validAuthorNumber, request.getName(), request.getBio(), validPhotoUri, doubleAuthorNumberFactory, doubleNameFactory, doubleBioFactory, doublePhotoFactory);

        //Assert
        assertNotNull(author);
        assertNotNull(author.getAuthorNumber());
        assertNotNull(author.getName());
        assertNotNull(author.getBio());
        assertNotNull(author.getPhoto());
    }

    @Test
    public void testCreateAuthorRequestWithoutPhotoTest() {
        //Arrange
        AuthorNumberFactory doubleAuthorNumberFactory = mock(AuthorNumberFactory.class);
        NameFactory doubleNameFactory = mock(NameFactory.class);
        BioFactory doubleBioFactory = mock(BioFactory.class);
        PhotoFactory doublePhotoFactory = mock(PhotoFactory.class);

        AuthorNumber authorNumberDouble = Mockito.mock(AuthorNumber.class);
        Name nameDouble = mock(Name.class);
        Bio bioDouble = Mockito.mock(Bio.class);

        when(doubleAuthorNumberFactory.newAuthorNumber(validAuthorNumber)).thenReturn(authorNumberDouble);
        when(doubleNameFactory.newName(validName)).thenReturn(nameDouble);
        when(doubleBioFactory.newBio(validBio)).thenReturn(bioDouble);
        when(doublePhotoFactory.newPhoto(null)).thenReturn(null);

        CreateAuthorRequest request = new CreateAuthorRequest(validName, validBio, null, null);

        //Act
        Author author = new Author(validAuthorNumber, request.getName(), request.getBio(), null, doubleAuthorNumberFactory, doubleNameFactory, doubleBioFactory, doublePhotoFactory);

        //Assert
        assertNotNull(author);
        assertNotNull(author.getAuthorNumber());
        assertNotNull(author.getName());
        assertNotNull(author.getBio());
        assertNull(author.getPhoto());
    }

    @Test
    public void ensureBioIsUpdatedTest(){
        AuthorNumberFactory doubleAuthorNumberFactory = mock(AuthorNumberFactory.class);
        NameFactory doubleNameFactory = mock(NameFactory.class);
        BioFactory doubleBioFactory = mock(BioFactory.class);
        PhotoFactory doublePhotoFactory = mock(PhotoFactory.class);

        AuthorNumber authorNumberDouble = Mockito.mock(AuthorNumber.class);
        Bio bioDouble = Mockito.mock(Bio.class);
        Name nameDouble = mock(Name.class);
        Photo photoDouble = mock(Photo.class);

        String newBio = "biooo";
        Bio newBioDouble = Mockito.mock(Bio.class);
        when(newBioDouble.toString()).thenReturn(newBio);
        when(doubleBioFactory.newBio(newBio)).thenReturn(newBioDouble);

        when(doubleAuthorNumberFactory.newAuthorNumber(validAuthorNumber)).thenReturn(authorNumberDouble);
        when(doubleNameFactory.newName(validName)).thenReturn(nameDouble);
        when(doubleBioFactory.newBio(validBio)).thenReturn(bioDouble);
        when(doublePhotoFactory.newPhoto(validPhotoUri)).thenReturn(photoDouble);

        Author author = new Author(validAuthorNumber,validName,validBio, null, doubleAuthorNumberFactory, doubleNameFactory, doubleBioFactory, doublePhotoFactory);
        UpdateAuthorRequest update = new UpdateAuthorRequest(newBio, null, null, null);
        author.applyPatch(update);
        assertEquals(newBio, author.getBio());
    }

    @Test
    public void ensureNameIsUpdatedTest(){
        AuthorNumberFactory doubleAuthorNumberFactory = mock(AuthorNumberFactory.class);
        NameFactory doubleNameFactory = mock(NameFactory.class);
        BioFactory doubleBioFactory = mock(BioFactory.class);
        PhotoFactory doublePhotoFactory = mock(PhotoFactory.class);

        AuthorNumber authorNumberDouble = Mockito.mock(AuthorNumber.class);
        Bio bioDouble = Mockito.mock(Bio.class);
        Name nameDouble = mock(Name.class);
        Photo photoDouble = mock(Photo.class);

        String newName = "nameee";
        Name newNameDouble = mock(Name.class);
        when(newNameDouble.toString()).thenReturn(newName);
        when(doubleNameFactory.newName(newName)).thenReturn(newNameDouble);

        when(doubleAuthorNumberFactory.newAuthorNumber(validAuthorNumber)).thenReturn(authorNumberDouble);
        when(doubleNameFactory.newName(validName)).thenReturn(nameDouble);
        when(doubleBioFactory.newBio(validBio)).thenReturn(bioDouble);
        when(doublePhotoFactory.newPhoto(validPhotoUri)).thenReturn(photoDouble);

        Author author = new Author(validAuthorNumber,validName,validBio, null, doubleAuthorNumberFactory, doubleNameFactory, doubleBioFactory, doublePhotoFactory);
        UpdateAuthorRequest update = new UpdateAuthorRequest(null, newName, null, null);
        author.applyPatch(update);
        assertEquals(newName, author.getName());
    }
    @Test
    public void ensureGetNameIsValidTest() {
        AuthorNumberFactory doubleAuthorNumberFactory = mock(AuthorNumberFactory.class);
        NameFactory doubleNameFactory = mock(NameFactory.class);
        BioFactory doubleBioFactory = mock(BioFactory.class);
        PhotoFactory doublePhotoFactory = mock(PhotoFactory.class);

        AuthorNumber authorNumberDouble = Mockito.mock(AuthorNumber.class);
        Bio bioDouble = Mockito.mock(Bio.class);
        Name nameDouble = mock(Name.class);
        Photo photoDouble = mock(Photo.class);

        when(nameDouble.toString()).thenReturn(validName);

        when(doubleAuthorNumberFactory.newAuthorNumber(validAuthorNumber)).thenReturn(authorNumberDouble);
        when(doubleNameFactory.newName(validName)).thenReturn(nameDouble);
        when(doubleBioFactory.newBio(validBio)).thenReturn(bioDouble);
        when(doublePhotoFactory.newPhoto(validPhotoUri)).thenReturn(photoDouble);

        Author author = new Author(validAuthorNumber,validName,validBio, null, doubleAuthorNumberFactory, doubleNameFactory, doubleBioFactory, doublePhotoFactory);
        assertEquals(validName, author.getName());
    }

    @Test
    public void ensurePhotoIsRemovedTest(){
        AuthorNumberFactory doubleAuthorNumberFactory = mock(AuthorNumberFactory.class);
        NameFactory doubleNameFactory = mock(NameFactory.class);
        BioFactory doubleBioFactory = mock(BioFactory.class);
        PhotoFactory doublePhotoFactory = mock(PhotoFactory.class);

        AuthorNumber authorNumberDouble = Mockito.mock(AuthorNumber.class);
        Bio bioDouble = Mockito.mock(Bio.class);
        Name nameDouble = mock(Name.class);
        Photo photoDouble = mock(Photo.class);

        when(doubleAuthorNumberFactory.newAuthorNumber(validAuthorNumber)).thenReturn(authorNumberDouble);
        when(doubleNameFactory.newName(validName)).thenReturn(nameDouble);
        when(doubleBioFactory.newBio(validBio)).thenReturn(bioDouble);
        when(doublePhotoFactory.newPhoto(validPhotoUri)).thenReturn(photoDouble);

        Author author = new Author(validAuthorNumber,validName,validBio, validPhotoUri, doubleAuthorNumberFactory, doubleNameFactory, doubleBioFactory, doublePhotoFactory);
        author.removePhoto();
        assertNull(author.getPhoto());
    }

    @Test
    void applyPatchTransparentTest() {
        AuthorNumberFactory doubleAuthorNumberFactory = mock(AuthorNumberFactory.class);
        NameFactory doubleNameFactory = mock(NameFactory.class);
        BioFactory doubleBioFactory = mock(BioFactory.class);
        PhotoFactory doublePhotoFactory = mock(PhotoFactory.class);

        AuthorNumber authorNumberDouble = Mockito.mock(AuthorNumber.class);
        Bio bioDouble = Mockito.mock(Bio.class);
        Name nameDouble = mock(Name.class);
        Photo photoDouble = mock(Photo.class);

        String newName = "nameee";
        String newBio = "newBio";
        Name newNameDouble = mock(Name.class);
        when(doubleNameFactory.newName(newName)).thenReturn(newNameDouble);

        when(doubleAuthorNumberFactory.newAuthorNumber(validAuthorNumber)).thenReturn(authorNumberDouble);
        when(doubleNameFactory.newName(validName)).thenReturn(nameDouble);
        when(doubleBioFactory.newBio(validBio)).thenReturn(bioDouble);
        when(doublePhotoFactory.newPhoto(validPhotoUri)).thenReturn(photoDouble);

        Author authorSpy = spy(new Author(validAuthorNumber,validName,validBio, null, doubleAuthorNumberFactory, doubleNameFactory, doubleBioFactory, doublePhotoFactory));
        UpdateAuthorRequest update = new UpdateAuthorRequest(null, newName, null, null);
        authorSpy.applyPatch(update);

        verify(authorSpy, times(1)).setName(newName);
        verify(authorSpy, never()).setBio(anyString());
        verify(authorSpy, never()).setPhoto(anyString());

        update = new UpdateAuthorRequest(newBio, null, null, null);
        authorSpy.applyPatch(update);

        verify(authorSpy).setBio(newBio);
        verify(authorSpy, times(1)).setName(newName);
        verify(authorSpy, never()).setPhoto(anyString());
    }

    @Test
    void setBioTransparentTest() {
        AuthorNumberFactory doubleAuthorNumberFactory = mock(AuthorNumberFactory.class);
        NameFactory doubleNameFactory = mock(NameFactory.class);
        BioFactory doubleBioFactory = mock(BioFactory.class);
        PhotoFactory doublePhotoFactory = mock(PhotoFactory.class);

        AuthorNumber authorNumberDouble = Mockito.mock(AuthorNumber.class);
        Bio bioDouble = Mockito.mock(Bio.class);
        Name nameDouble = mock(Name.class);
        Photo photoDouble = mock(Photo.class);

        String newBio = "newBio";

        when(doubleAuthorNumberFactory.newAuthorNumber(validAuthorNumber)).thenReturn(authorNumberDouble);
        when(doubleNameFactory.newName(validName)).thenReturn(nameDouble);
        when(doubleBioFactory.newBio(validBio)).thenReturn(bioDouble);
        when(doublePhotoFactory.newPhoto(validPhotoUri)).thenReturn(photoDouble);

        Author authorSpy = spy(new Author(validAuthorNumber,validName,validBio, null, doubleAuthorNumberFactory, doubleNameFactory, doubleBioFactory, doublePhotoFactory));
        authorSpy.setBio(newBio);

        verify(doubleBioFactory, times(1)).newBio(newBio);
    }

    @Test
    void setNameTransparentTest() {
        AuthorNumberFactory doubleAuthorNumberFactory = mock(AuthorNumberFactory.class);
        NameFactory doubleNameFactory = mock(NameFactory.class);
        BioFactory doubleBioFactory = mock(BioFactory.class);
        PhotoFactory doublePhotoFactory = mock(PhotoFactory.class);

        AuthorNumber authorNumberDouble = Mockito.mock(AuthorNumber.class);
        Bio bioDouble = Mockito.mock(Bio.class);
        Name nameDouble = mock(Name.class);
        Photo photoDouble = mock(Photo.class);

        String newName = "newName";

        when(doubleAuthorNumberFactory.newAuthorNumber(validAuthorNumber)).thenReturn(authorNumberDouble);
        when(doubleNameFactory.newName(validName)).thenReturn(nameDouble);
        when(doubleBioFactory.newBio(validBio)).thenReturn(bioDouble);
        when(doublePhotoFactory.newPhoto(validPhotoUri)).thenReturn(photoDouble);

        Author authorSpy = spy(new Author(validAuthorNumber,validName,validBio, null, doubleAuthorNumberFactory, doubleNameFactory, doubleBioFactory, doublePhotoFactory));
        authorSpy.setName(newName);

        verify(doubleNameFactory, times(1)).newName(newName);
    }

    @Test
    void setAuthorNumberTransparentTest() {
        AuthorNumberFactory doubleAuthorNumberFactory = mock(AuthorNumberFactory.class);
        NameFactory doubleNameFactory = mock(NameFactory.class);
        BioFactory doubleBioFactory = mock(BioFactory.class);
        PhotoFactory doublePhotoFactory = mock(PhotoFactory.class);

        AuthorNumber authorNumberDouble = Mockito.mock(AuthorNumber.class);
        Bio bioDouble = Mockito.mock(Bio.class);
        Name nameDouble = mock(Name.class);
        Photo photoDouble = mock(Photo.class);

        String newNumber = "newNumber";

        when(doubleAuthorNumberFactory.newAuthorNumber(validAuthorNumber)).thenReturn(authorNumberDouble);
        when(doubleNameFactory.newName(validName)).thenReturn(nameDouble);
        when(doubleBioFactory.newBio(validBio)).thenReturn(bioDouble);
        when(doublePhotoFactory.newPhoto(validPhotoUri)).thenReturn(photoDouble);

        Author authorSpy = spy(new Author(validAuthorNumber,validName,validBio, null, doubleAuthorNumberFactory, doubleNameFactory, doubleBioFactory, doublePhotoFactory));
        authorSpy.setAuthorNumber(newNumber);

        verify(doubleAuthorNumberFactory, times(1)).newAuthorNumber(newNumber);
    }

    @Test
    void setPhotoTransparentTest() {
        AuthorNumberFactory doubleAuthorNumberFactory = mock(AuthorNumberFactory.class);
        NameFactory doubleNameFactory = mock(NameFactory.class);
        BioFactory doubleBioFactory = mock(BioFactory.class);
        PhotoFactory doublePhotoFactory = mock(PhotoFactory.class);

        AuthorNumber authorNumberDouble = Mockito.mock(AuthorNumber.class);
        Bio bioDouble = Mockito.mock(Bio.class);
        Name nameDouble = mock(Name.class);
        Photo photoDouble = mock(Photo.class);

        String newPhoto = "newPhoto";

        when(doubleAuthorNumberFactory.newAuthorNumber(validAuthorNumber)).thenReturn(authorNumberDouble);
        when(doubleNameFactory.newName(validName)).thenReturn(nameDouble);
        when(doubleBioFactory.newBio(validBio)).thenReturn(bioDouble);
        when(doublePhotoFactory.newPhoto(validPhotoUri)).thenReturn(photoDouble);

        Author authorSpy = spy(new Author(validAuthorNumber,validName,validBio, null, doubleAuthorNumberFactory, doubleNameFactory, doubleBioFactory, doublePhotoFactory));
        authorSpy.setPhoto(newPhoto);

        verify(doublePhotoFactory, times(1)).newPhoto(newPhoto);
    }
}
