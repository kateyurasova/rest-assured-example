import io.restassured.response.Response;
import models.Pet;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PetsExampleTest extends TestBase {
    @Test
    public void shouldHaveStatus200ForAllPetsList() {
        REQUEST.get("/pet/1").then().statusCode(200);
    }

    @Test
    public void shouldCreateNewPet() {
        String status = "available";
        Pet newPet = generatePet(status);
        Response response = REQUEST.body(newPet).post("/pet");
        Integer petId = response.body().jsonPath().getInt("id");
        String petName = response.body().jsonPath().getString("name");
        String actualStatus = response.body().jsonPath().getString("status");

        assertEquals(petName, newPet.name);
        assertEquals(actualStatus, status);

    }

    private Pet generatePet(String status) {
        String uniqueName = FAKER.crypto().md5();
        return new Pet( uniqueName, status);
    }

}
