package Tests;

import Base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CreatePetTests extends TestBase {

    public CreatePetTests() throws IOException {
    }

    String baseUrl = prop.getProperty("URL");
    String serviceURL = prop.getProperty("serviceURL");
    int successCode = Integer.parseInt(prop.getProperty("SUCCESS_CODE"));
    String jbody= prop.getProperty("newPetJsonBody");
    int petID = Integer.parseInt(prop.getProperty("petID"));
    String petName = prop.getProperty("petName");

    String url = baseUrl+serviceURL;
    String getPetByIDUrl = baseUrl+serviceURL+petID;

    @Test
    public void createNewPet(){

        //Starting the post request
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.body(jbody);
        httpRequest.contentType("application/json");

        //Saving the response in a response object
        Response response = httpRequest.request(Method.POST,url);
        int statusCode = response.statusCode();

        //Validate status code
        Assert.assertEquals(statusCode,successCode);

        //--Validate the pet object created successfully--
        Response resPetByID = httpRequest.request(Method.GET,getPetByIDUrl);

        //Get the response body
        JsonPath jsonPath = resPetByID.jsonPath();
        String name = jsonPath.get("name");

        //Validate pet name
        Assert.assertEquals(name,petName);


    }
}
