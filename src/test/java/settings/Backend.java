package settings;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class Backend {

    /* Метод получения параметров из апи */
    @Step("Получение параметров в API : ")
    public static Response get(final String url) {
        Response responseGet = given().get(url);
        System.out.println(responseGet.asString());
        return responseGet;
    }

    /* Метод передачи параметров в апи */
    @Step("Передача параметров в API : ")
    public static Response post(final String url, final String request) {
        Response responsePOST = given().contentType(JSON).body(request).when().post(url);
        System.out.println(responsePOST.asString());
        return responsePOST;
    }

}
