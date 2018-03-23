import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import okhttp3.*;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;



public class YouAreEll {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    YouAreEll() {
    }

    public static void main(String[] args) {
        YouAreEll urlhandler = new YouAreEll();
        ObjectMapper objectMapper = new ObjectMapper();
        String payload = "";
        Id id = new Id("-", "Carolynn", "me");
        try {
            payload = objectMapper.writeValueAsString(id);
            urlhandler.makeURLCall("/ids", "POST", payload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        System.out.println(urlhandler.makeURLCall("/ids", "POST", payload));
//        System.out.println(urlhandler.makeURLCall("/messages", "GET", ""));
    }


    public String makeURLCall(String mainurl, String method, String jpayload) {
        OkHttpClient okHttpClient = new OkHttpClient();
        String fullUrl = "http://zipcode.rocks:8085" + mainurl;
        Request request = null;
        if (method.equalsIgnoreCase("get")) {
            request = new Request.Builder().url(fullUrl).build();
        }
        if (method.equalsIgnoreCase("post")) {
            RequestBody requestBody = RequestBody.create(JSON, jpayload);
            request = new Request.Builder().url(fullUrl).post(requestBody).build();
        }

        if (request != null) {
            try (Response response = okHttpClient.newCall(request).execute()) {
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "" + method;
    }
}