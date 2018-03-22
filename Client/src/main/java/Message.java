

public class Message {

    private String sequence;
    private String timestamp;
    private String fromid;
    private String toid;
    private String message;

    public Message(){
    }

    public Message(String sequence, String fromid, String toid, String message) {
        this.sequence = sequence;
        this.timestamp = "2018-03-22T01:00:00.0Z";
        this.fromid = fromid;
        this.toid = toid;
        this.message = message;
    }

    public Message(String fromid, String toid, String message){
        this.fromid = fromid;
        this.toid = toid;
        this.message = message;
    }

    public Message(String fromid, String message){
        this.fromid = fromid;
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

    @Override
    public String toString(){
        return "sequence: " + getSequence() + ", timestamp: " + getTimestamp()+ ", from: " + getFromid() + ", to: " + getToid() + ", message: " + getMessage();
    }
}


