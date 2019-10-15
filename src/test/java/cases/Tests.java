package cases;

import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import settings.Backend;
import settings.Data;

import java.util.HashMap;
import java.util.List;

public class Tests extends Backend {

    private final static String URL = "https://api.hh.ru";

    @Test(dataProvider = "url", dataProviderClass = Data.class)
    @Story("Проверка корректного ответа кода сервера")
    public void checkSuccessStatusCode(String url) {
        Response answer = Backend.get(URL + url);
        Assert.assertEquals(answer.getStatusCode(), 401, "Отображен не корректный статус!");
    }

    @Test(dataProvider = "url", dataProviderClass = Data.class)
    @Story("Проверка некорректного ответа кода сервера")
    public void checkFailedStatusCode(String url) {
        Response answer = Backend.get(URL + url + "error");
        Assert.assertEquals(answer.getStatusCode(), 404, "Отображен не корректный статус!");
    }

    @Test(dataProvider = "vacancy", dataProviderClass = Data.class)
    @Story("Проверка корректного поиска вакансий")
    public void checkSearchVacancies(String vacancy, String city) {
        String request = URL + "/vacancies?text=" + vacancy + "&search_field=name&area=" + city + "";
        Response answer = Backend.get(request);

        List<HashMap> vacancies = answer.jsonPath().getList("items");
        for (HashMap listVacancy : vacancies) {
            String name = listVacancy.get("name").toString().toLowerCase();
            Assert.assertTrue(name.contains(vacancy.toLowerCase()), "Не совпадает название вакансии!");
        }
    }

    @Test(dataProvider = "url", dataProviderClass = Data.class)
    @Story("Проверка корректного заголовка")
    public void checkHeaders(String url) {
        Response answer = Backend.get(URL + url);
        String getContentType = answer.header("Content-Type");
        String getServerType =  answer.header("Server");
        String getAcceptLanguage = answer.header("Content-Encoding");

        Assert.assertTrue(getAcceptLanguage.contains("gzip") &
                                   getContentType.contains("application/json") &
                                   getServerType.contains("nginx"), "Не совпадает тело заголовка!");
    }

}
