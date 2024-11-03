package pt.psoft.g1.psoftg1.genremanagement.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pt.psoft.g1.psoftg1.genremanagement.dto.*;
import pt.psoft.g1.psoftg1.genremanagement.dto.response.GenreBookCountResponseDTO;
import pt.psoft.g1.psoftg1.genremanagement.dto.response.GenreLendingsAvgPerMonthResponseDTO;
import pt.psoft.g1.psoftg1.genremanagement.dto.response.GenreLendingsCountPerMonthResponseDTO;
import pt.psoft.g1.psoftg1.genremanagement.dto.response.GenreLendingsResponseDTO;
import pt.psoft.g1.psoftg1.genremanagement.model.Genre;
import pt.psoft.g1.psoftg1.bookmanagement.dto.request.GenreBookCountDTO;
import pt.psoft.g1.psoftg1.shared.api.MapperInterface;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class GenreMapper extends MapperInterface{

    @Mapping(target = "genre", source = "genre")
    public abstract GenreDTO toGenreView(Genre genre);

    public abstract GenreDTO mapStringToGenreView(String genre);

    @Mapping(target = "genreDTO", source = "genre")
    public abstract GenreBookCountResponseDTO toGenreBookCountResponseDTO(GenreBookCountDTO genreBookCountView);
    public abstract List<GenreBookCountResponseDTO> toGenreBookCountResponseDTO(List<GenreBookCountDTO> genreBookCountView);

    public abstract GenreLendingsResponseDTO toGenreAvgLendingsResponseDTO(GenreLendingsDTO dto);
    public abstract List<GenreLendingsResponseDTO> toGenreAvgLendingsResponseDTO(List<GenreLendingsDTO> dtos);

    @Mapping(target = "lendingsCount", source = "values")
    public abstract GenreLendingsCountPerMonthResponseDTO toGenreLendingsCountPerMonthView(GenreLendingsPerMonthDTO dto);
    public abstract List<GenreLendingsCountPerMonthResponseDTO> toGenreLendingsCountPerMonthView(List<GenreLendingsPerMonthDTO> dtos);


    @Mapping(target = "durationAverages", source = "values")
    public abstract GenreLendingsAvgPerMonthResponseDTO toGenreLendingsAveragePerMonthResponseDTO(GenreLendingsPerMonthDTO dto);
    public abstract List<GenreLendingsAvgPerMonthResponseDTO> toGenreLendingsAveragePerMonthResponseDTO(List<GenreLendingsPerMonthDTO> dtos);


}
