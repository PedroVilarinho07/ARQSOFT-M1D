package pt.psoft.g1.psoftg1.genremanagement.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import pt.psoft.g1.psoftg1.genremanagement.dto.GenreDTO;

@Data
@Schema(description = "A Genre with book count")
public class GenreBookCountResponseDTO {
    @NotNull
    private GenreDTO genreDTO;

    private Long bookCount;
}