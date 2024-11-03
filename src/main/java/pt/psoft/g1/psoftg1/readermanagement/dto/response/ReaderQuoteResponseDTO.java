package pt.psoft.g1.psoftg1.readermanagement.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Data
@EqualsAndHashCode(callSuper = true)
@Setter
public class ReaderQuoteResponseDTO extends ReaderResponseDTO {
    private String quote;
}
