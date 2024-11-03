package pt.psoft.g1.psoftg1.bookmanagement.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Books with lending count")
public class BookCountResponseDTO {
    @NotNull
    private BookResponseDTO bookResponseDTO;

    private Long lendingCount;
}
