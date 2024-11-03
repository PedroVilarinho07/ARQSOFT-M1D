package pt.psoft.g1.psoftg1.shared.model;


import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class PhotoTest {

    @Test
    public void ensurePathMustNotBeNullTest() {
        assertThrows(NullPointerException.class, () -> new Photo(null));
    }

    @Test
    public void ensurePathIsValidToLocalFileTest() {
        Path fileStorageLocation = Paths.get("uploads-psoft-g1").toAbsolutePath().normalize();
        assertNotEquals(null, fileStorageLocation.toString());

        Photo photo = new Photo(Paths.get("photoTest.jpg"));
        assertEquals(photo.getPhotoFile(), "photoTest.jpg");
    }


}
