package testlib.utils.handlers;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

/**
 * APIHandler нужен только для того, чтобы скрывать логику получения
 * аутентификационного токена и сразу подставлять его в запросы.
 */
public class ApiHandler {
    private static Logger log= LoggerFactory.getLogger(ApiHandler.class);

    public static String getToken(String apiName,String accLogin,String accPassword){
        String url="/"+apiName+"/auth";
        Response response=given()
                .body("{\n" +
                        "    \"username\": \""+accLogin+"\",\n" +
                        "    \"password\": \""+accPassword+"\"\n" +
                        "}")
                .contentType("application/json")
                .post(PropertyHandler.getProperty("base.URL")+url)
                .then()
                .extract().response();
        log.info("Авторизация api " + apiName + " выполнена успешно. Получаем токен для дальнейшей работы");
        return response.body().jsonPath().getString("token");
    }
}
