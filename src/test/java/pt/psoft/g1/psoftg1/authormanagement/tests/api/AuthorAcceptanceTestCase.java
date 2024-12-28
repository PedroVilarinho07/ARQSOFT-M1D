package pt.psoft.g1.psoftg1.authormanagement.tests.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import pt.psoft.g1.psoftg1.authormanagement.factories.AuthorNumberFactory;
import pt.psoft.g1.psoftg1.authormanagement.factories.BioFactory;
import pt.psoft.g1.psoftg1.authormanagement.model.Author;
import pt.psoft.g1.psoftg1.authormanagement.repositories.AuthorRepository;

import org.junit.jupiter.api.Tag;
import pt.psoft.g1.psoftg1.shared.factories.NameFactory;
import pt.psoft.g1.psoftg1.shared.factories.PhotoFactory;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Tag("IntegrationTest")
class AuthorAcceptanceTestCase {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthorRepository authorRepository;

    private Author author;

    @BeforeEach
    public void setUp() {

        AuthorNumberFactory authorNumberFactory = new AuthorNumberFactory();
        NameFactory nameFactory = new NameFactory();
        BioFactory bioFactory = new BioFactory();
        PhotoFactory photoFactory = new PhotoFactory();

        author = new Author(
                "1",
                "Madalena Fernandes",
                "Excelente Cronista",
                "photoTest.jpg",
                authorNumberFactory,
                nameFactory,
                bioFactory,
                photoFactory
        );

        authorRepository.save(author);
    }

    @AfterEach
    public void tearDown() {
        authorRepository.delete(author);
    }

    @Test
    @WithMockUser(username = "librarian@email.pt", authorities = { "ROLE_LIBRARIAN" })
    public void whenGetAuthorByName_thenReturnAuthor() throws Exception {
        mockMvc.perform(get("/api/authors")
                        .param("name", author.getName())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items[0].name").value("Madalena Fernandes"))
                .andExpect(jsonPath("$.items[0].bio").value("Excelente Cronista"));
    }

    @Test
    @WithMockUser(username = "reader@email.pt", authorities = { "ROLE_READER" })
    public void whenGetAuthorByNumber_thenReturnAuthorBooksNumber() throws Exception {
        mockMvc.perform(get("/api/authors/" + author.getAuthorNumber() + "/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items").isArray())
                .andExpect(jsonPath("$.items").isEmpty());
    }

    @Test
    @WithMockUser(username = "reader@email.pt", authorities = { "ROLE_READER" })
    public void whenGetAuthorByAuthorNumber_thenReturnAuthorDetails() throws Exception {
        mockMvc.perform(get("/api/authors/" + author.getAuthorNumber())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Madalena Fernandes"))
                .andExpect(jsonPath("$.bio").value("Excelente Cronista"));
    }
}
