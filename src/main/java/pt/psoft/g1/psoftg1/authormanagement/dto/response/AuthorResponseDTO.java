package pt.psoft.g1.psoftg1.authormanagement.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Data
@Schema(description = "A Lending")
public class AuthorResponseDTO {
    @NotNull
    private String authorNumber;
    @NotNull
    private String name;
    @NotNull
    private String bio;
    private String photo;
    @Setter
    @Getter
    private Map<String, Object> _links = new HashMap<>();


}


