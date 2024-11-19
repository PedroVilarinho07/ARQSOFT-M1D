/*package pt.psoft.g1.psoftg1.bookmanagement.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles({"sql", "idGeneratorHash", "recommendationY"})
@SpringBootTest
public class IsbnIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientRegistrationRepository clientRegistrationRepository; // Adiciona mock para o repositório OAuth2

    @Autowired
    private ObjectMapper objectMapper;

    private static String token;

    @BeforeAll
    public static void authenticate(@Autowired MockMvc mockMvc) throws Exception {
        // Obtenha o token de autenticação (substitua por um mock se necessário)
        MvcResult loginResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/public/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"usuario\", \"password\": \"senha\"}"))
                .andExpect(status().isOk())
                .andReturn();

        token = loginResult.getResponse().getHeader(HttpHeaders.AUTHORIZATION);
    }

    @Test
    void ensureValidIsbnIsCreatedSuccessfully() throws Exception {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("isbn", "9783161484100");
        requestBody.put("title", "A Valid Book");
        requestBody.put("genre", "Fiction");
        requestBody.put("description", "A detailed description.");
        requestBody.put("authors", List.of("Author1"));

        String jsonParams = objectMapper.writeValueAsString(requestBody);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/books/9783161484100")
                        .header("Authorization", token) // Passa o token de autorização
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonParams))
                .andExpect(status().isCreated());
    }
}
*/