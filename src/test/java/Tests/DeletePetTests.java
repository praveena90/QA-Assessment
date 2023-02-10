package Tests;

import Base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeletePetTests extends TestBase {
    public DeletePetTests() throws IOException {
    }

    String baseUrl = prop.getProperty("URL");
    String serviceURL = prop.getProperty("serviceURL");
    int invalidPetID = Integer.parseInt(prop.getProperty("invalidPetID"));
    int unsuccessCode = Integer.parseInt(prop.getProperty("PETNOTFOUND_CODE"));

    String url = baseUrl+serviceURL+invalidPetID;

    @Test
    public void deleteInvalidPet()
    {
        //Starting the post request
        RequestSpecification httpRequest = RestAssured.given();

        //Saving the response in a response object
        Response response = httpRequest.request(Method.DELETE,url);

        int statusCode = response.statusCode();

        //Validate status code
        Assert.assertEquals(statusCode,unsuccessCode);
    }
}
