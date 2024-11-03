package pt.psoft.g1.psoftg1.genremanagement.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "A Genre and its lendings count per month.")
@AllArgsConstructor
public class GenreLendingsCountPerMonthResponseDTO {
    private Integer year;
    private Integer month;
    private List<GenreLendingsResponseDTO> lendingsCount;
}
