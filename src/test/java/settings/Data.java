package settings;

import org.testng.annotations.DataProvider;

public class Data {

    @DataProvider(name = "url")
    private Object[][] url() {
        String vacancy = "/vacancies";
        String languages = "/languages";
        String employers = "/employers";
        String areas = "/areas";
        String specializations = "/specializations";

        return new Object[][] {{vacancy}, {languages}, {employers}, {areas}, {specializations}};
    }

    @DataProvider(name = "vacancy")
    private Object[][] vacancy() {
        String manager = "Менеджер";
        String seller = "Продавец";
        String driver = "Водитель";
        String operator = "Оператор";
        String locksmith = "Слесарь";

        return new Object[][] {{manager, "2"}, {seller, "68"}, {driver, "1"}, {operator, "55"}, {locksmith, "3"}};
    }

}
