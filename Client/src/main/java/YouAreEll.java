import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.client.fluent.*;
import com.fasterxml.jackson.databind.*;
import java.io.IOException;


public class YouAreEll {

    YouAreEll() {
    }

    public static void main(String[] args) {
        YouAreEll urlhandler = new YouAreEll();
        ObjectMapper objectMapper = new ObjectMapper();
        String payload = "";
        Id id = new Id("-","Carolynn","me");
        try {
            payload = objectMapper.writeValueAsString(id);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(urlhandler.makeURLCall("/ids", "GET", payload));
//        System.out.println(urlhandler.makeURLCall("/messages", "GET", ""));
    }

    public String makeURLCall(String mainurl, String method, String jpayload) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        String fullUrl = "http://zipcode.rocks:8085" + mainurl;
        String requestReturn = "";
        if(mainurl.equalsIgnoreCase("/ids") && method.equalsIgnoreCase("get")){
            try {
                requestReturn = Request.Get(fullUrl).execute().returnContent().asString();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(mainurl.equalsIgnoreCase("/ids") && method.equalsIgnoreCase("post")){
            requestReturn = Request.Post(jpayload).toString();
        }
        if(mainurl.equalsIgnoreCase("/messages")){
            try {
                Request.Get(fullUrl).execute().returnContent();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return requestReturn;
    }

}
