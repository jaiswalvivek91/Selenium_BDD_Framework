package com.test.stepDefinitions;


import com.test.Hooks.ReadConfig;
import com.test.createRequestBody.ChatApi;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;


import java.util.List;
import java.util.Map;


public class TestNew extends ReadConfig {

    private static String BASE_URL ;

    private static String message;
    private static String user_name;
    private static String user_id;
    private static Response response;
    private static String jsonString;


    @Before
    public void readDefaultValues() {
        message=prop.getProperty("message");
        user_name=prop.getProperty("user_name")+java.time.LocalDateTime.now().toString();
        user_id=prop.getProperty("user_id")+java.time.LocalDateTime.now().toString();
    }


    @Given("I have Chat Bot API")
    public void iAmAnAuthorizedUser() {
        BASE_URL=prop.getProperty("CHAT_API_BASE_URL");
    }


    @Given("I have Healthily Chat Bot")
    public void iHaveHealthilyChatBot() {
        BASE_URL=prop.getProperty("CHAT_API_BASE_URL");
    }


    @When("I get response for Chat Bot API")
    public void iGetResponseForChatBotAPI() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        ChatApi chatApi = new ChatApi(prop.getProperty("message"),prop.getProperty("user_name"), prop.getProperty("user_id"));

        response = request
                .header("Content-type",prop.getProperty("Content-type"))
                .header("x-api-key",prop.getProperty("x-api-key"))
                .body(chatApi)
                .when()
                .post("/vhi")
                .then()
               // .log().all()
                .extract().response();

//        String jsonString = response.asString();
//        //token = JsonPath.from(jsonString).get("token");
//
//        List<Map<String, String>> question = JsonPath.from(jsonString).get("question");
//        Assert.assertEquals(0, question.size());



    }

    @When("I enter user name as {string}")
    public void iEnterUserNameAs(String username) {
        user_name = username;
        user_id = username;
    }

    @Then("I select an option or enter text as")
    public void iSelectAnOptionOrEnterTextAs(DataTable options) {


        for (Map<Object, Object> data : options.asMaps(String.class, String.class)) {

            RestAssured.baseURI = BASE_URL;
            RequestSpecification request = RestAssured.given();

            ChatApi chatApi = new ChatApi((String) data.get("options"),user_name, user_id);

            response = request
                    .header("Content-type",prop.getProperty("Content-type"))
                    .header("x-api-key",prop.getProperty("x-api-key"))
                    .body(chatApi)
                    .when()
                    .post("/vhi")
                    .then()
                    // .log().all()
                    .extract().response();
        }




    }

    @Then("I verify the Response code")
    public void iVerifyTheResponseCode() {
        Assert.assertEquals(200, response.getStatusCode());
    }


    @Then("I interact with chat bot for {int} times")
    public void iInteractWithChatBotForTimes(int noOfTimes) {

        //Assign the default value of message for request body as "Hello"
        message  = prop.getProperty("message") ;

        for (int i = 0; i < noOfTimes; i++) {
            System.out.println(i);

            RestAssured.baseURI = BASE_URL;
            RequestSpecification request = RestAssured.given();

            ChatApi chatApi = new ChatApi(message,user_name, user_name);

            response = request
                    .header("Content-type",prop.getProperty("Content-type"))
                    .header("x-api-key",prop.getProperty("x-api-key"))
                    .body(chatApi)
                    .when()
                    .post("/vhi")
                    .then()
                    // .log().all()
                    .extract().response();



            //String jsonString = response.asString();
            JSONObject json = new JSONObject(response.asString());

            //Check if We have option to select of need to enter text
            String event_name = "default";
            if(json.has("chat_events")){
                JSONArray arrchat_events = json.getJSONArray("chat_events");
                JSONObject chat_events = arrchat_events.getJSONObject(0);
                event_name = chat_events.get("event_name").toString();

            }
           
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

        }

    }
}
