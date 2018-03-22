import org.apache.http.*;

public class YouAreEll {

    YouAreEll() {
    }

    public static void main(String[] args) {
        YouAreEll urlhandler = new YouAreEll();
        System.out.println(urlhandler.makeURLCall("/ids", "GET", ""));
        System.out.println(urlhandler.makeURLCall("/messages", "GET", ""));
    }



    public String get_messages() {
        return makeURLCall("/messages", "GET", "");
    }

    public String post_message_to_world(){
        //POST
        //returns message sequence # & timestamp
        return null;
    }

    public String post_message_to_friend(){
        //POST
        //returns message sequence # & timestamp
        return null;
    }



    public String makeURLCall(String mainurl, String method, String jpayload) {

        return null;
    }
}
