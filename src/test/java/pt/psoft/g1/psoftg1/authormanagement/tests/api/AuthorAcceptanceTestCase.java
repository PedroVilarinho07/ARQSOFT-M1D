package pt.psoft.g1.psoftg1.authormanagement.tests.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.request.WebRequest;
import pt.psoft.g1.psoftg1.authormanagement.dto.request.CreateAuthorRequest;
import pt.psoft.g1.psoftg1.usermanagement.services.UserService;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthorAcceptanceTestCase {



    @MockBean
    private UserService userService;

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.basePath = "/api/authors";
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void testCreateAuthorSuccessfully() {
        // Simulando uma requisição válida para criar um autor
        CreateAuthorRequest request = new CreateAuthorRequest();
        request.setName("Author Name");
        request.setBio("This is a test bio for the author.");
        request.setPhoto(null); // Sem foto para simplificar

        given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("name", equalTo("Author Name"))
                .body("bio", equalTo("This is a test bio for the author"));
    }

    @Test
    public void testCreateAuthorFailsWithoutName() {
        // Simulando uma requisição inválida (sem nome)
        CreateAuthorRequest request = new CreateAuthorRequest();
        request.setBio("This is a test bio for the author.");
        request.setPhoto(null);

        given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", containsString("Name cannot be null"));
    }

    @Test
    public void testUpdateAuthorSuccessfully() {
        // Primeiro, criar um autor
        CreateAuthorRequest createRequest = new CreateAuthorRequest();
        createRequest.setName("Author Name");
        createRequest.setBio("This is a test bio for the author.");
        createRequest.setPhoto(null);

        Response createResponse = given()
                .contentType("application/json")
                .body(createRequest)
                .when()
                .post("/")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract().response();

        String authorNumber = createResponse.path("authorNumber");

        // Atualizar o autor criado
        var updateRequest = new pt.psoft.g1.psoftg1.authormanagement.dto.request.UpdateAuthorRequest();
        updateRequest.setBio("Updated Bio");
        updateRequest.setName("Updated Name");
        updateRequest.setPhoto(null);

        given()
                .contentType("application/json")
                .header("If-Match", "1") // Simulando controle de concorrência
                .body(updateRequest)
                .when()
                .patch("/{authorNumber}", authorNumber)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo("Updated Name"))
                .body("bio", equalTo("Updated Bio"));
    }

    @Test
    public void testGetAuthorByNumber() {
        // Primeiro, criar um autor
        CreateAuthorRequest createRequest = new CreateAuthorRequest();
        createRequest.setName("Author Name");
        createRequest.setBio("This is a test bio for the author.");
        createRequest.setPhoto(null);

        Response createResponse = given()
                .contentType("application/json")
                .body(createRequest)
                .when()
                .post("/")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract().response();

        String authorNumber = createResponse.path("authorNumber");

        // Buscar o autor criado
        given()
                .contentType("application/json")
                .when()
                .get("/{authorNumber}", authorNumber)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", equalTo("Author Name"))
                .body("bio", equalTo("This is a test bio for the author."));
    }

    @Test
    public void testDeleteAuthorPhoto() {
        // Primeiro, criar um autor com foto
        CreateAuthorRequest createRequest = new CreateAuthorRequest();
        createRequest.setName("Author Name");
        createRequest.setBio("This is a test bio for the author.");
        createRequest.setPhotoURI("example_photo_uri.jpg");

        Response createResponse = given()
                .contentType("application/json")
                .body(createRequest)
                .when()
                .post("/")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract().response();

        String authorNumber = createResponse.path("authorNumber");

        // Deletar a foto do autor criado
        given()
                .contentType("application/json")
                .when()
                .delete("/{authorNumber}/photo", authorNumber)
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
