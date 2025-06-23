import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class Base {

    HashMap <String, String > storeData;
//    Response response =null;
    public Response getAPITest() {
        Map<String,String > header = new HashMap<>();
        header.put("x-api-key", "reqres-free-v1");
        Response response = RestAssured.given().queryParam("page",2).headers(header).get("https://reqres.in/api/users");
        System.out.println(response.asString());
        return response;
    }
//    public static void main (String[] args) {
//        Base bc =new Base();
//        bc.getAPITest();
//    }

    public String readJson (Response response, String key) {
        JsonObject jsonObject = new Gson().fromJson(response.asString(),JsonObject.class);
        return jsonObject.get(key).toString();
    }
//    public String writePayload () {
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("name", "morpheus");
//        jsonObject.addProperty("job", "job");
//        return jsonObject.toString();
//    }

    public String writePayload () {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "morpheus");
        jsonObject.addProperty("job", "job");
        if(storeData != null) {
            for (Map.Entry<String, String> each : storeData.entrySet()) {
                jsonObject.addProperty(each.getKey(), each.getValue());
            }
        }
        return jsonObject.toString();
    }


    public Response postAPITest() {
        Map<String,String > header = new HashMap<>();
        header.put("x-api-key", "reqres-free-v1");
        Response response = RestAssured.given().headers(header).body("{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}").post("https://reqres.in/api/users");
        System.out.println(response.asString());
        return response;
    }

    public Response postAPIWithPayloadTest() {
        Map<String,String > header = new HashMap<>();
        header.put("x-api-key", "reqres-free-v1");
        System.out.println(writePayload ());
        Response response = RestAssured.given().headers(header).body(writePayload ()).post("https://reqres.in/api/users");
        System.out.println(response.asString());
        return response;
    }

    public void storeObject(String key, String value) {
        storeData = new HashMap<>();
        storeData.put(key, value);
    }
}
