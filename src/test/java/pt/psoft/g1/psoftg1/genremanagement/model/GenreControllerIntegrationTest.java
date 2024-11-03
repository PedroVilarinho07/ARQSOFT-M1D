/*package pt.psoft.g1.psoftg1.genremanagement.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pt.psoft.g1.psoftg1.PsoftG1Application;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(classes = PsoftG1Application.class)
public class GenreControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetTopGenres() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/genres/top5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isNotEmpty());
    }

    @WithMockUser
    @Test
    void testGetAverageLendings() throws Exception {
        String requestBody = """
            {
                "query": { "year": 2023, "month": 5 },
                "page": { "pageNumber": 1, "pageSize": 5 }
            }
            """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/genres/avgLendingsPerGenre")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isNotEmpty());
    }
    @Test
    void testGetLendingsPerMonthLastYearByGenre() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/genres/lendingsPerMonthLastTwelveMonths")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isNotEmpty());
    }

    @Test
    void testGetLendingsAverageDurationPerMonth() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/genres/lendingsAverageDurationPerMonth")
                        .param("startDate", "2023-01-01")
                        .param("endDate", "2023-12-31")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isNotEmpty());
    }
}
*/