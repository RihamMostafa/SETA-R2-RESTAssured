package objects;

public class UserObject {
    String name;
    String job;
    String username;
    String password;

    public UserObject setUpdateData(String name, String job) {
        this.name = name;
        this.job = job;
        return this;
    }


    public UserObject setLoginData(String username, String password) {
        this.username = username;
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
