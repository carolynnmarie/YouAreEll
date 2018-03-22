import java.io.IOException;
import java.util.ArrayList;

public interface MessageInterface {

    String get_messages(ArrayList<String> list)throws IOException;
    String get_from_friend(ArrayList<String> list)throws IOException;
    String get_my_messages(ArrayList<String> list)throws IOException;
    String post_world(ArrayList<String> list)throws IOException;
    String post_friend(ArrayList<String> list)throws IOException;

}
