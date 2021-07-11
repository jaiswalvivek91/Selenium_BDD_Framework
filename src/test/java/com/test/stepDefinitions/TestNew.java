package com.test.stepDefinitions;


import com.google.inject.Inject;
import com.test.Hooks.ReadConfig;
import com.test.createRequestBody.ChatApi;

import com.test.utils.Helper;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
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



    @Inject
    Helper Helper;


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
//
//            RestAssured.baseURI = BASE_URL;
//            RequestSpecification request = RestAssured.given();
//
//            ChatApi chatApi = new ChatApi((String) data.get("options"),user_name, user_id);

            System.out.println("Message Value : "+data.get("options").toString());
            response = Helper.getChatApiResponse((String) data.get("options"),user_name, user_id);

//            response = request
//                    .header("Content-type",prop.getProperty("Content-type"))
//                    .header("x-api-key",prop.getProperty("x-api-key"))
//                    .body(chatApi)
//                    .when()
//                    .post("/vhi")
//                    .then()
//                    // .log().all()
//                    .extract().response();
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
        System.out.println("Default Message Value : "+message);
        for (int i = 0; i < noOfTimes; i++) {
//            System.out.println(i);

//            RestAssured.baseURI = BASE_URL;
//            RequestSpecification request = RestAssured.given();
//
//            ChatApi chatApi = new ChatApi(message,user_name, user_name);
//
//            response = request
//                    .header("Content-type",prop.getProperty("Content-type"))
//                    .header("x-api-key",prop.getProperty("x-api-key"))
//                    .body(chatApi)
//                    .when()
//                    .post("/vhi")
//                    .then()
//                    // .log().all()
//                    .extract().response();
//

            response = Helper.getChatApiResponse(message,user_name, user_id);
            JSONObject json = new JSONObject(response.asString());
            String event_name = Helper.getEventName(json);

            //Get value of message parameter for request body
            message = Helper.getRequestMessage(json, event_name );
            System.out.println("Message Value : "+message);

//            //String jsonString = response.asString();
//            JSONObject json = new JSONObject(response.asString());
//
//            //Check if We have option to select of need to enter text
//            String event_name = "default";
//            if(json.has("chat_events")){
//                JSONArray arrchat_events = json.getJSONArray("chat_events");
//                JSONObject chat_events = arrchat_events.getJSONObject(0);
//                event_name = chat_events.get("event_name").toString();
//
//            }
//
//                switch (event_name)
//                {
//                    case "coach_check_symptoms":
//                        message = "fever";
//                        break;
//                    case "onboard_gender":
//                        message = "1991";
//                        break;
//                    default:
//
//                        JSONObject questions = json.getJSONObject("question");
//                        // String ismultiple = questions.get("multiple").toString();
//                        JSONArray choices = questions.getJSONArray("choices");
//
//                        JSONObject choice1 = choices.getJSONObject(0);
//                        String  strchoice1 = choice1.get("label").toString();
//
//                        //overwriting the message for next conversation
//                        message = strchoice1;
//                }

        }

    }

    @Then("I Type {string}")
    public void iType(String strMessage) {

        response = Helper.getChatApiResponse(strMessage,user_name, user_id);

//        JSONObject json = new JSONObject(response.asString());
//        String event_name = Helper.getEventName(json);
//
//        //Get value of message paratmeter for request body
//        message = Helper.getRequestMessage(json, event_name );


    }


    @And("I click on {string} for GDPR")
    public void iClickOnForGDPR(String arg0) {
        response = Helper.getChatApiResponse(arg0,user_name, user_name);
    }

    @And("I click on {string} the GDPR terms")
    public void iClickOnTheGDPRTerms(String arg0) {
        response = Helper.getChatApiResponse(arg0,user_name, user_name);
    }

    @And("I click on {string} to acknowledge that the information was useful")
    public void iClickOnToAcknowledgeThatTheInformationWasUseful(String arg0) {

        JSONObject json = new JSONObject(response.asString());
        JSONArray messages = json.getJSONArray("messages");

        JSONObject Message1 = messages.getJSONObject(0);
        String  value = Message1.get("value").toString();

       //Very confirmation message
        Assert.assertEquals(value, "No problem. Was this information useful?");

        //Respond with Yes
        response = Helper.getChatApiResponse(arg0,user_name, user_name);

    }

    @And("I click on {string} to confirm")
    public void iClickOnToConfirm(String arg0) {
        response = Helper.getChatApiResponse(arg0,user_name, user_name);
    }

    @And("I select {string} in response to What would you like to do?")
    public void iSelectInResponseToWhatWouldYouLikeToDo(String arg0) {
        response = Helper.getChatApiResponse(arg0,user_name, user_name);
    }

    @And("I select {string} in response to Who is the symptom check for?")
    public void iSelectInResponseToWhoIsTheSymptomCheckFor(String arg0) {
        response = Helper.getChatApiResponse(arg0,user_name, user_name);
    }

    @And("I select my gender as {string}")
    public void iSelectMyGenderAs(String arg0) {
        response = Helper.getChatApiResponse(arg0,user_name, user_name);
    }

    @And("I select my year of birth as {string}")
    public void iSelectMyYearOfBirthAs(String arg0) {
        response = Helper.getChatApiResponse(arg0,user_name, user_name);
    }

    @And("I select {string} in response to What would help you most")
    public void iSelectInResponseToWhatWouldHelpYouMost(String arg0) {
        response = Helper.getChatApiResponse(arg0,user_name, user_name);
    }

    @And("I click on {string} after I get useful information")
    public void iClickOnAfterIGetUsefulInformation(String arg0) {
        response = Helper.getChatApiResponse(arg0,user_name, user_name);
    }

    @And("I type my symptons as {string} in text box")
    public void iTypeMySymptonsAsInTextBox(String arg0) {
        response = Helper.getChatApiResponse(arg0,user_name, user_name);
    }


    @Then("I select {string} in response")
    public void iSelectInResponse(String arg0) {
        response = Helper.getChatApiResponse(arg0,user_name, user_name);
    }

    @Then("I exit the consulation and go back to What would you like to do? page")
    public void iExitTheConsulationAndGoBackToWhatWouldYouLikeToDoPage() {

        JSONObject json = new JSONObject(response.asString());
        JSONArray available_commands = json.getJSONArray("available_commands");

        JSONObject available_commands1 = available_commands.getJSONObject(0);
        String  command = available_commands1.get("command").toString();

        //Very confirmation message
        Assert.assertEquals(command, "STOP_CS");

        System.out.println("Stopping the current consulation : ");

        // Send command to stop the consulation and go back to What would you like to do next page
        response = Helper.getChatApiResponse("STOP_CS",user_name, user_name);

    }
}
