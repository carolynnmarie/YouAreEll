

public class Id {

    private String userid;
    private String name;
    private String github;

    public Id(){
    }

    public Id(String userid, String name, String github){
        this.userid = userid;
        this.name = name;
        this.github = github;
    }


    public void setId(String id) {
        this.userid = id;
    }

    public String getuserid() {
        return userid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getGithub() {
        return github;
    }

    @Override
    public String toString(){
        return "userid: " + getuserid() + ", name: " + getName() + ", github: " + getGithub();
    }

}

