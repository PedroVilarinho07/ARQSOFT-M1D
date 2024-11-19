package pt.psoft.g1.psoftg1.authormanagement.tests.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import pt.psoft.g1.psoftg1.authormanagement.api.AuthorAPIMapper;
import pt.psoft.g1.psoftg1.authormanagement.services.AuthorService;
import pt.psoft.g1.psoftg1.bookmanagement.api.BookViewMapper;
import pt.psoft.g1.psoftg1.shared.services.FileStorageService;

import static org.mockito.Mockito.mock;


@AutoConfigureMockMvc
@ActiveProfiles({"sql", "idGeneratorHash", "recommendationY"})
@SpringBootTest
public class AuthorServiceImplIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientRegistrationRepository clientRegistrationRepository;


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthorService authorService;

    @MockBean
    private AuthorAPIMapper authorAPIMapper;

    @MockBean
    private FileStorageService fileStorageService;

    @MockBean
    private BookViewMapper bookViewMapper;

    private static String token;
/*
    @BeforeAll
    public static void authenticate(@Autowired MockMvc mockMvc) throws Exception {
        MvcResult loginResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/public/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"pedro@gmail.com\", \"password\": \"Pedrodascenas!123\"}"))
                .andExpect(status().isOk())
                .andReturn();

        // Retrieve the token from the Authorization header
        token = loginResult.getResponse().getHeader(HttpHeaders.AUTHORIZATION);
    }

    @WithMockUser
    @Test
    void ensureAuthorIsCreatedIT() throws Exception {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "AuthorName");
        requestBody.put("bio", "AuthorBio");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonParams = objectMapper.writeValueAsString(requestBody);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/authors")
                        .header("Authorization", "Bearer " + token)
                        .content(jsonParams)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



 AuthorResponseDTO mockAuthorResponseDTO = mock(AuthorResponseDTO.class);

        when(mockAuthorResponseDTO.getAuthorNumber()).thenReturn(authorNumber);
        when(authorService.findByAuthorNumber(authorNumber)).thenReturn(Optional.of(mockAuthor));
        when(authorAPIMapper.toAuthorDTO(mockAuthor)).thenReturn(mockAuthorResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.authorNumber").value(authorNumber));
    void suggestionsShouldReturnSuccess() throws Exception {
        // Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/books/suggestions")
                        .header("Authorization", "Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();

        // Assert
        assertNotNull(resultContent);
    }

    @Test
    void suggestionsShouldReturnAtLeastOne() throws Exception {
        // Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/books/suggestions")
                        .header("Authorization", "Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        ListResponse<BookView> response = objectMapper.readValue(resultContent,
                objectMapper.getTypeFactory().constructParametricType(ListResponse.class, BookView.class));

        // Assert
        assertNotNull(response);
        assertNotNull(response.getItems());
        assertFalse(response.getItems().isEmpty(), "The suggestions list should not be empty");
    }

    public Author createAuthor () {
        String validName = "João Alberto";
        String validAuthorNumber = "AuthorNumber";
        String validPhotoUri = "photoTest.jpg";
        String validBio = "O João Alberto nasceu em Chaves e foi pedreiro a maior parte da sua vida.";

        AuthorNumberFactory doubleAuthorNumberFactory = mock(AuthorNumberFactory.class);
        NameFactory doubleNameFactory = mock(NameFactory.class);
        BioFactory doubleBioFactory = mock(BioFactory.class);
        PhotoFactory doublePhotoFactory = mock(PhotoFactory.class);

        AuthorNumber authorNumberDouble = mock(AuthorNumber.class);
        Name nameDouble = mock(Name.class);
        Bio bioDouble = mock(Bio.class);
        Photo photoDouble = mock(Photo.class);

        when(doubleAuthorNumberFactory.newAuthorNumber(validAuthorNumber)).thenReturn(authorNumberDouble);
        when(doubleNameFactory.newName(validName)).thenReturn(nameDouble);
        when(doubleBioFactory.newBio(validBio)).thenReturn(bioDouble);
        when(doublePhotoFactory.newPhoto(validPhotoUri)).thenReturn(photoDouble);
        CreateAuthorRequest request = new CreateAuthorRequest(validName, validBio, null, validPhotoUri);


        return new Author(validAuthorNumber, request.getName(), request.getBio(), validPhotoUri, doubleAuthorNumberFactory, doubleNameFactory, doubleBioFactory, doublePhotoFactory);
    }

 */
}

