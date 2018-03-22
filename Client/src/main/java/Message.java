

public class Message {

    private String sequence;
    private String timestamp;
    private String fromid;
    private String toid;
    private String message;

    public Message(){
        this.sequence = "-";
        this.timestamp = "2018-03-21T01:00:00.0Z";
        this.fromid = "";
        this.toid = "";
        this.message = "";
    }

    public Message(String sequence, String timestamp, String fromid, String toid, String message) {
        this.sequence = sequence;
        timestamp = "2018-03-21T01:00:00.0Z";
        this.timestamp = timestamp;
        this.fromid = fromid;
        this.toid = toid;
        this.message = message;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getSequence() {
        return sequence;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setFromid(String fromid) {
        this.fromid = fromid;
    }

    public String getFromid() {
        return fromid;
    }

    public void setToid(String toid) {
        this.toid = toid;
    }

    public String getToid() {
        return toid;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}



//timestamp: