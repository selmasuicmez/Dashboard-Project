package API_Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AssertionStatusCode {

    @Test
    public void assertStatusCodeProject() throws IOException {
        //1)Set the url
        String url = "https://huteam01.atlassian.net/rest/api/2/project/";
        //2)Type automation script to send GET Request and to get response
        Response response =
                given()
                        .auth()
                        .basic("@gmail.com", "")
                        .when()
                        .get(url);

        response.prettyPrint();
        //3)Do assertion
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("id", equalTo(10000)).
                body("key", equalTo("DAR")).
                body("name", equalTo("DarmaTest"));


        //Print status code, status line, content type, etc. on the console
        System.out.println("Status Code is :" + response.getStatusCode());
        System.out.println("Status Line is :" + response.getStatusLine());
        System.out.println("Content Type is :" + response.getContentType());
        System.out.println("Time is :" + response.getTime());
        System.out.println("Headers are :\n" + response.getHeaders());
        System.out.println("Via is :" + response.getHeader("Via"));
    }

}