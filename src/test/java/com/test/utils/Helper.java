package com.test.utils;

import com.test.Hooks.ReadConfig;
import com.test.createRequestBody.ChatApi;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

public class Helper extends ReadConfig {

    private static String BASE_URL ;

    private static String message;
    private static String user_name;
    private static String user_id;
    private static Response response;

    public Response getChatApiResponse(String message, String user_name, String user_id) {

        BASE_URL=prop.getProperty("CHAT_API_BASE_URL");

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        //Get request body in json format
        ChatApi chatApi = new ChatApi(message,user_name, user_id);

        response = request
                .header("Content-type",prop.getProperty("Content-type"))
                .header("x-api-key",prop.getProperty("x-api-key"))
                .body(chatApi)
                .when()
                .post("/vhi")
                .then()
                // .log().all()
                .extract().response();

        return  response;

    }

    public String getEventName(JSONObject json) {

        String event_name = "default";

        if(json.has("chat_events")){
            JSONArray arrchat_events = json.getJSONArray("chat_events");
            JSONObject chat_events = arrchat_events.getJSONObject(0);
            event_name = chat_events.get("event_name").toString();

        }
        return event_name;
    }

    public String getRequestMessage(JSONObject json, String event_name) {


        switch (event_name)
        {
            case "coach_check_symptoms":
                message = "fever";
                break;
            case "onboard_gender":
                message = "1991";
                break;
            default:

                JSONObject questions = json.getJSONObject("question");
                // String ismultiple = questions.get("multiple").toString();
                JSONArray choices = questions.getJSONArray("choices");

                JSONObject choice1 = choices.getJSONObject(0);
                String  strchoice1 = choice1.get("label").toString();

                //overwriting the message for next conversation
                message = strchoice1;
        }


        return message;
    }




}
