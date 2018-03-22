import org.apache.http.*;

public class YouAreEll {

    YouAreEll() {
    }

    public static void main(String[] args) {
        YouAreEll urlhandler = new YouAreEll();
        System.out.println(urlhandler.MakeURLCall("/ids", "GET", ""));
        System.out.println(urlhandler.MakeURLCall("/messages", "GET", ""));
    }

    public String get_ids() {
        return MakeURLCall("/ids", "GET", "");
    }

    public void post_id(){ }

    public String get_messages() {
        return MakeURLCall("/messages", "GET", "");
    }

    public String get_all_my_messages(){
        return null;
    }

    public String get_my_messages_from_friend(){
        return null;
    }

    public String post_message_to_world(){
        //returns message sequence # & timestamp
        return null;
    }

    public String post_message_to_friend(){
        //returns message sequence # & timestamp
        return null;
    }

    public String MakeURLCall(String mainurl, String method, String jpayload) {
        return "nada";
    }
}
