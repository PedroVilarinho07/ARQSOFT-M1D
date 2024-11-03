package pt.psoft.g1.psoftg1.genremanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@Schema(description = "A Genre")
public class GenreDTO {
    @NotNull
    private String genre;
}