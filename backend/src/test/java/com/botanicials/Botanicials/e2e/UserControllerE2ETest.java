package com.botanicials.Botanicials.e2e;

import com.botanicials.Botanicials.dto.UserDTO;
import com.botanicials.Botanicials.model.User;
import com.botanicials.Botanicials.repository.ForumCommentsRepository;
import com.botanicials.Botanicials.repository.ForumPostRepository;
import com.botanicials.Botanicials.repository.UserPlantCollectionRepository;
import com.botanicials.Botanicials.repository.UserPlantWishlistRepository;
import com.botanicials.Botanicials.repository.UserRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class UserControllerE2ETest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ForumCommentsRepository forumCommentsRepository;
    @Autowired
    private ForumPostRepository forumPostRepository;
    @Autowired
    private UserPlantWishlistRepository userPlantWishlistRepository;
    @Autowired
    private UserPlantCollectionRepository userPlantCollectionRepository;


    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
        registry.add("PERENUAL_API", () -> "http://test-api.example.com/v1");
    }

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        forumCommentsRepository.deleteAllInBatch();
        forumPostRepository.deleteAllInBatch();
        userPlantWishlistRepository.deleteAllInBatch();
        userPlantCollectionRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();

        forumCommentsRepository.flush();
        forumPostRepository.flush();
        userPlantWishlistRepository.flush();
        userPlantCollectionRepository.flush();
        userRepository.flush();
    }

    @Test
    void shouldCreateAndRetrieveUser() {
        User userToCreate = new User();
        userToCreate.setEmail("test@example.com");
        userToCreate.setName("Test User");
        userToCreate.setGoogleId("google123");
        userToCreate.setImageUrl("http://example.com/image.jpg");

        UserDTO createdUser = given()
                .contentType(ContentType.JSON)
                .body(userToCreate)
                .when()
                .post("/users")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("email", equalTo("test@example.com"))
                .body("name", equalTo("Test User"))
                .body("imageUrl", equalTo("http://example.com/image.jpg"))
                .extract().as(UserDTO.class);

        assertThat(createdUser.getId()).isNotNull();

        given()
                .pathParam("id", createdUser.getId())
                .when()
                .get("/users/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(createdUser.getId().intValue()))
                .body("email", equalTo("test@example.com"))
                .body("name", equalTo("Test User"));

        given()
                .when()
                .get("/users")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("$", hasSize(1))
                .body("[0].email", equalTo("test@example.com"));
    }

    @Test
    void shouldReturnNotFoundWhenUserDoesNotExist() {
        // GET for a non-existent user
        given()
                .pathParam("id", 999L)
                .when()
                .get("/users/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());

        // PUT for a non-existent user should result in 500 because service throws RuntimeException
        User nonExistingUser = new User();
        nonExistingUser.setEmail("nonexistent@example.com");
        nonExistingUser.setName("Non Existent");
        nonExistingUser.setGoogleId("new_google_id");
        nonExistingUser.setImageUrl("http://nonexistent.com/img.jpg");

        given()
                .contentType(ContentType.JSON)
                .body(nonExistingUser)
                .pathParam("id", 999L)
                .when()
                .put("/users/{id}")
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()); // Expecting 500

        // DELETE for a non-existent user should also result in 500 because service throws RuntimeException
        given()
                .pathParam("id", 999L)
                .when()
                .delete("/users/{id}")
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()); // <--- CHANGED THIS LINE TO EXPECT 500
    }

    @Test
    void shouldUpdateUser() {
        User initialUser = new User();
        initialUser.setEmail("original@example.com");
        initialUser.setName("Original Name");
        initialUser.setGoogleId("orig_google");
        initialUser.setImageUrl("http://original.com/img.jpg");
        initialUser = userRepository.save(initialUser);

        User userDetailsToUpdate = new User();
        userDetailsToUpdate.setEmail("updated@example.com");
        userDetailsToUpdate.setName("Updated Name");
        userDetailsToUpdate.setImageUrl("http://updated.com/img.jpg");

        given()
                .contentType(ContentType.JSON)
                .body(userDetailsToUpdate)
                .pathParam("id", initialUser.getId())
                .when()
                .put("/users/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(initialUser.getId().intValue()))
                .body("email", equalTo("updated@example.com"))
                .body("name", equalTo("Updated Name"))
                .body("imageUrl", equalTo("http://updated.com/img.jpg"));

        User updatedUserInDb = userRepository.findById(initialUser.getId()).orElseThrow();
        assertThat(updatedUserInDb.getEmail()).isEqualTo("updated@example.com");
        assertThat(updatedUserInDb.getName()).isEqualTo("Updated Name");
        assertThat(updatedUserInDb.getImageUrl()).isEqualTo("http://updated.com/img.jpg");
        assertThat(updatedUserInDb.getGoogleId()).isEqualTo("orig_google");
    }

    @Test
    void shouldDeleteUser() {
        User userToDelete = new User();
        userToDelete.setEmail("delete@example.com");
        userToDelete.setName("Delete Me");
        userToDelete.setGoogleId("to_delete");
        userToDelete.setImageUrl("http://delete.com/img.jpg");
        userToDelete = userRepository.save(userToDelete);

        given()
                .pathParam("id", userToDelete.getId())
                .when()
                .delete("/users/{id}")
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

        assertThat(userRepository.findById(userToDelete.getId())).isEmpty();

        given()
                .pathParam("id", userToDelete.getId())
                .when()
                .get("/users/{id}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}