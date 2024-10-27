package pt.psoft.g1.psoftg1.authormanagement.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pt.psoft.g1.psoftg1.authormanagement.dto.response.AuthorResponseDTO;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
public class AuthorCoAuthorBooksView {
    private AuthorResponseDTO author;
    private List<CoAuthorView> coauthors;

}
