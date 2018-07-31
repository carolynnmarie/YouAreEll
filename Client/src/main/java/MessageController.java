import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.ArrayList;

public class MessageController implements MessageInterface {

    private YouAreEll youAreEll = new YouAreEll();
    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public String get_messages(ArrayList<String> list)  {
        String payload = "";
        Message message = new Message("-", "","","");
        try {
            payload = objectMapper.writeValueAsString(message);
        } catch(JsonProcessingException e){
            e.printStackTrace();
        }
        return youAreEll.makeURLCall("/messages", "GET", payload);
    }

    @Override
    public String get_from_friend(ArrayList<String> list) {
//         /ids/:mygithubid/from/:friendgithubid
        return null;
    }

    @Override
    public String get_my_messages(ArrayList<String> list) {
       String payload = "";
        String me = list.get(1);
        Message message = new Message("-", me, "", "");
        String url = "/ids/:" + me + "/messages";
        try {
            payload = objectMapper.writeValueAsString(message);
        } catch(JsonProcessingException e){
            e.printStackTrace();
        }
        return youAreEll.makeURLCall(url, "GET", payload);
    }

    @Override
    public String post_world(ArrayList<String> list){

        String mess = list.get(1);
        String me = list.get(2);
        String payload = "";
        Message message = new Message("-", me, "", mess);
        String url = "/ids/:" + me + "/messages";
        try {
            payload = objectMapper.writeValueAsString(message);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e){
            e.printStackTrace();
        }
        return youAreEll.makeURLCall(url, "POST", payload);
    }

    @Override
    public String post_friend(ArrayList<String> list) {
        String mess = list.get(1);
        String me = list.get(2);
        String friend = list.get(3);
        String payload = "";
        Message message = new Message("-", me, friend, mess);
        try {
            payload = objectMapper.writeValueAsString(message);
        } catch(JsonProcessingException e){
            e.printStackTrace();
        }
        return youAreEll.makeURLCall("/messages","POST", payload);
    }
}
