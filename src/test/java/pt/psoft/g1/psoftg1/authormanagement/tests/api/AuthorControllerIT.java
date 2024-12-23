package pt.psoft.g1.psoftg1.authormanagement.tests.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pt.psoft.g1.psoftg1.authormanagement.dto.request.CreateAuthorRequest;
import pt.psoft.g1.psoftg1.authormanagement.dto.request.UpdateAuthorRequest;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should create a new author")
    void createAuthor() throws Exception {
        CreateAuthorRequest request = new CreateAuthorRequest();
        request.setName("John Doe");
        request.setBio("An accomplished author of various novels.");
        request.setPhotoURI(null);

        mockMvc.perform(post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.bio").value("An accomplished author of various novels."))
                .andExpect(jsonPath("$._links.author").exists());
    }

    @Test
    @DisplayName("Should update an existing author")
    void updateAuthor() throws Exception {
        // Creating a new author first
        CreateAuthorRequest createRequest = new CreateAuthorRequest();
        createRequest.setName("Jane Doe");
        createRequest.setBio("A promising author.");
        createRequest.setPhotoURI(null);

        mockMvc.perform(post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated());

        UpdateAuthorRequest updateRequest = new UpdateAuthorRequest();
        updateRequest.setBio("A promising author with several new books.");

        mockMvc.perform(patch("/api/authors/1")
                        .header("If-Match", "1") // Simulando controle de concorrência
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bio").value("A promising author with several new books."));
    }

    @Test
    @DisplayName("Should fetch an author by author number")
    void fetchAuthorByNumber() throws Exception {
        // Creating a new author first
        CreateAuthorRequest request = new CreateAuthorRequest();
        request.setName("John Smith");
        request.setBio("A well-known author.");
        request.setPhotoURI(null);

        mockMvc.perform(post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/authors/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Smith"))
                .andExpect(jsonPath("$.bio").value("A well-known author."));
    }

    @Test
    @DisplayName("Should return top 5 authors by lending stats")
    void fetchTop5AuthorsByLending() throws Exception {
        mockMvc.perform(get("/api/authors/top5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(org.hamcrest.Matchers.lessThanOrEqualTo(5)));
    }

    @Test
    @DisplayName("Should fetch books of a specific author")
    void fetchBooksByAuthor() throws Exception {
        // Simulate existing author and books
        mockMvc.perform(get("/api/authors/1/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").exists());
    }

    @Test
    @DisplayName("Should delete an author's photo")
    void deleteAuthorPhoto() throws Exception {
        // Assuming an author with a photo exists
        mockMvc.perform(delete("/api/authors/1/photo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
