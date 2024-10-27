package pt.psoft.g1.psoftg1.authormanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
public class AuthorCoAuthorBooksResponseDTO {
    private AuthorResponseDTO author;
    private List<CoAuthorResponseDTO> coauthors;

}
