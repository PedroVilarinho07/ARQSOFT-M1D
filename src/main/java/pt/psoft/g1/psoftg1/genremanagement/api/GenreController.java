package pt.psoft.g1.psoftg1.genremanagement.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pt.psoft.g1.psoftg1.exceptions.NotFoundException;
import pt.psoft.g1.psoftg1.genremanagement.dto.response.GenreLendingsResponseDTO;
import pt.psoft.g1.psoftg1.genremanagement.dto.response.GenreBookCountResponseDTO;
import pt.psoft.g1.psoftg1.genremanagement.dto.response.GenreLendingsAvgPerMonthResponseDTO;
import pt.psoft.g1.psoftg1.genremanagement.dto.response.GenreLendingsCountPerMonthResponseDTO;
import pt.psoft.g1.psoftg1.genremanagement.mappers.GenreMapper;
import pt.psoft.g1.psoftg1.genremanagement.services.IGenreService;
import pt.psoft.g1.psoftg1.genremanagement.dto.request.GetAverageLendingsQuery;
import pt.psoft.g1.psoftg1.shared.api.ListResponse;
import pt.psoft.g1.psoftg1.shared.services.SearchRequest;

@Tag(name = "Genres", description = "Endpoints for managing Genres")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genres")
public class GenreController {

    private final IGenreService IGenreService;
    private final GenreMapper genreMapper;

    @PostMapping(value="/avgLendingsPerGenre")
    public ListResponse<GenreLendingsResponseDTO> getAverageLendings(
            @Valid @RequestBody final SearchRequest<GetAverageLendingsQuery> query){
        final var list = IGenreService.getAverageLendings(query.getQuery(), query.getPage());
        return new ListResponse<>(genreMapper.toGenreAvgLendingsResponseDTO(list));
    }

    @GetMapping("/top5")
    public ListResponse<GenreBookCountResponseDTO> getTop() {
        final var list = IGenreService.findTopGenreByBooks();

        if(list.isEmpty())
            throw new NotFoundException("No genres to show");

        return new ListResponse<>(genreMapper.toGenreBookCountResponseDTO(list));
    }

    @GetMapping("/lendingsPerMonthLastTwelveMonths")
    public ListResponse<GenreLendingsCountPerMonthResponseDTO> getLendingsPerMonthLastYearByGenre() {
        final var list = IGenreService.getLendingsPerMonthLastYearByGenre();

        if(list.isEmpty())
            throw new NotFoundException("No genres to show");

        final var viewList = genreMapper.toGenreLendingsCountPerMonthView(list);

        return new ListResponse<>(viewList);
    }

    @GetMapping("/lendingsAverageDurationPerMonth")
    public ListResponse<GenreLendingsAvgPerMonthResponseDTO> getLendingsAverageDurationPerMonth(
            @RequestParam("startDate") final String start,
            @RequestParam("endDate") final String end) {
        final var list = IGenreService.getLendingsAverageDurationPerMonth(start, end);

        if(list.isEmpty())
            throw new NotFoundException("No genres to show");

        final var responseList = genreMapper.toGenreLendingsAveragePerMonthResponseDTO(list);

        return new ListResponse<>(responseList);
    }
}
