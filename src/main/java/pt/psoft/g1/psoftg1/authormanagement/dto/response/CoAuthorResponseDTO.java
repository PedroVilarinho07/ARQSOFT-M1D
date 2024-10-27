package pt.psoft.g1.psoftg1.authormanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pt.psoft.g1.psoftg1.bookmanagement.dto.response.BookShortResponseDTO;

import java.util.List;
import java.util.Map;
@Data
@Getter
@Setter
@AllArgsConstructor
public class CoAuthorResponseDTO {
    private String name;
    private Map<String, Object> _links;
    private List<BookShortResponseDTO> books;
}
