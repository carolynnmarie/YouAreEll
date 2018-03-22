import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

public class MessageController implements MessageInterface {

    private YouAreEll youAreEll = new YouAreEll();
    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public String get_messages(ArrayList<String> list) throws com.fasterxml.jackson.core.JsonProcessingException {
        Message message = new Message("-", "","","");
        String payload = objectMapper.writeValueAsString(message);
        return youAreEll.makeURLCall("/messages", "GET", payload);
    }

    @Override
    public String get_from_friend(ArrayList<String> list) throws com.fasterxml.jackson.core.JsonProcessingException  {
        return null;
    }

    @Override
    public String get_my_messages(ArrayList<String> list)throws com.fasterxml.jackson.core.JsonProcessingException  {
        String me = list.get(1);
        Message message = new Message("-", me,"","");
        String payload = objectMapper.writeValueAsString(message);
        return youAreEll.makeURLCall("/messages", "GET", payload);
    }

    @Override
    public String post_world(ArrayList<String> list) throws com.fasterxml.jackson.core.JsonProcessingException  {
        String mess = list.get(1);
        String me = list.get(2);
        Message message = new Message("",me, "", mess);
        String payload = objectMapper.writeValueAsString(message);
        return youAreEll.makeURLCall("/messages","POST", payload);
    }

    @Override
    public String post_friend(ArrayList<String> list) throws com.fasterxml.jackson.core.JsonProcessingException  {
        String mess = list.get(1);
        String me = list.get(2);
        String friend = list.get(3);
        Message message = new Message("", me, friend, mess);
        String payload = objectMapper.writeValueAsString(message);
        return youAreEll.makeURLCall("/messages","POST", payload);
    }
}
