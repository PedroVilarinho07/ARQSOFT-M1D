package pt.psoft.g1.psoftg1.shared.factories;

import org.springframework.stereotype.Component;
import pt.psoft.g1.psoftg1.shared.model.Photo;

import java.nio.file.Path;

@Component
public class PhotoFactory {

    public Photo newPhoto(String photo)
    {
        return new Photo(Path.of(photo));
    }
}
