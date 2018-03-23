

public class Id {

    private String id;
    private String name;
    private String github;

    public Id(){
    }

    public Id(String id, String name, String github){
        this.id = id;
        this.name = name;
        this.github = github;
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

    public void setGithub(String github) {
        this.github = github;
    }

    public String getGithub() {
        return github;
    }

    @Override
    public String toString(){
        return "id: " + getId() + ", name: " + getName() + ", github: " + getGithub();
    }

}

