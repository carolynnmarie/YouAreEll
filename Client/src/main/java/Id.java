

public class Id {

    private String id;
    private String name;
    private String githubid;

    public Id(){
    }

    public Id(String id, String name, String githubid){
        this.id = id;
        this.name = name;
        this.githubid = githubid;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGithubid(String githubid) {
        this.githubid = githubid;
    }

    public String getGithubid() {
        return githubid;
    }

    @Override
    public String toString(){
        return "id: " + getId() + ", name: " + getName() + ", githubid: " + getGithubid();
    }

}

