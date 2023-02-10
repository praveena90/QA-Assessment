package Tests;

import Base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import java.io.IOException;

public class GetPetDetailsTests extends TestBase {

    public GetPetDetailsTests() throws IOException {
    }

    String baseUrl = prop.getProperty("URL");
    String serviceURL = prop.getProperty("statusServiceURL");
    String petStatus = prop.getProperty("petStatus");

    String url = baseUrl+serviceURL;

    @Test
    public void getPetDetailsByStatus(){

        //Starting the get request
        RequestSpecification httpRequest = RestAssured.given();

        //Saving the response in a response object
        httpRequest.queryParam("status",petStatus);
        Response response = httpRequest.request(Method.GET,url);

        //Get the response body
        JsonPath jsonPath = new JsonPath(response.asString());
        int soldPetCount = jsonPath.get("id.size()");

        System.out.println("Sold pet count is : "+soldPetCount);

    }



}
