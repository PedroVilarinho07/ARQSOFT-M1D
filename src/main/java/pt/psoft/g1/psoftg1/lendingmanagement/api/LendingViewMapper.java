package pt.psoft.g1.psoftg1.lendingmanagement.api;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pt.psoft.g1.psoftg1.lendingmanagement.dto.response.LendingResponseDTO;
import pt.psoft.g1.psoftg1.lendingmanagement.dto.response.LendingsAverageDurationResponseDTO;
import pt.psoft.g1.psoftg1.lendingmanagement.model.Lending;
import pt.psoft.g1.psoftg1.shared.api.MapperInterface;

import java.util.*;

/**
 * Brief guides:
 * <a href="https://www.baeldung.com/mapstruct">https://www.baeldung.com/mapstruct</a>
 * <p>
 * <a href="https://medium.com/@susantamon/mapstruct-a-comprehensive-guide-in-spring-boot-context-1e7202da033e">https://medium.com/@susantamon/mapstruct-a-comprehensive-guide-in-spring-boot-context-1e7202da033e</a>
 * */
@Mapper(componentModel = "spring")
public abstract class LendingViewMapper extends MapperInterface {

    @Mapping(target = "lendingNumber", source = "lendingNumber")
    @Mapping(target = "isbn", source = "isbn")
    @Mapping(target = "fineValueInCents", expression = "java(lending.getFineValueInCents().orElse(null))")
    @Mapping(target = "returnedDate", source = "returnedDate")
    @Mapping(target = "_links", ignore = true)
    public abstract LendingResponseDTO toLendingView(Lending lending);

    public abstract List<LendingResponseDTO> toLendingView(List<Lending> lendings);

    public abstract LendingsAverageDurationResponseDTO toLendingsAverageDurationView(Double lendingsAverageDuration);
}
