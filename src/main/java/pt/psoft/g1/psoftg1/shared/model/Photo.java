package pt.psoft.g1.psoftg1.shared.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;

@Setter
@Getter
public class Photo {

    @NotNull
    private String photoFile;

    protected Photo (){}

    public Photo (Path photoPath){
        setPhotoFile(photoPath.toString());
    }
}

