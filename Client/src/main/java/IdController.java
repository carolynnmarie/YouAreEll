import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

public class IdController implements IdInterface {
    ObjectMapper objectMapper = new ObjectMapper();
    YouAreEll youAreEll = new YouAreEll();

    @Override
    public String get_ids(ArrayList<String> list) throws com.fasterxml.jackson.core.JsonProcessingException {
        Id id = new Id("-","","");
        String payload = objectMapper.writeValueAsString(id);
        return youAreEll.makeURLCall("/ids", "GET", payload);
    }

    @Override
    public String saveId(ArrayList<String> list) throws com.fasterxml.jackson.core.JsonProcessingException {
        String name = list.get(1);
        String github = list.get(2);
        Id id = new Id("-", name, github);
        String payload = objectMapper.writeValueAsString(id);
       return youAreEll.makeURLCall("/ids", "POST", payload);
    }

}
