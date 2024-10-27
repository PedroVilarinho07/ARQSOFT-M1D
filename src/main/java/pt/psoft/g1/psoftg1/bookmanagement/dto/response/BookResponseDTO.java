package pt.psoft.g1.psoftg1.bookmanagement.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Schema(description = "A Book")
public class BookResponseDTO {
    @NotNull
    private String title;

    @NotNull
    private List<String> authors;

    @NotNull
    private String genre;

    private String description;

    @NotNull
    private String isbn;

    @Setter
    @Getter
    private Map<String, Object> _links = new HashMap<>();
}
