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

public class UpdatePetTests extends TestBase {

    public UpdatePetTests() throws IOException {
    }

    String baseUrl = prop.getProperty("URL");
    String serviceURL = prop.getProperty("serviceURL");
    int successCode = Integer.parseInt(prop.getProperty("SUCCESS_CODE"));
    String jbody= prop.getProperty("updatePetJsonBody");
    int petID = Integer.parseInt(prop.getProperty("petID"));
    String petName = prop.getProperty("updatePetName");

    String getPetByIDUrl = baseUrl+serviceURL+petID;
    String url = baseUrl+serviceURL;

    @Test
    public void updatePetDetails()
    {
        //Starting the post request
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.body(jbody);
        httpRequest.contentType("application/json");

        //Saving the response in a response object
        Response response = httpRequest.request(Method.PUT,url);
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
