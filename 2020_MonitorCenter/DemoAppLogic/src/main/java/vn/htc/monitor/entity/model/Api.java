package vn.htc.monitor.entity.model;

public class Api {

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Api() {
    }

    public Api(String user, String pass, String type, String content) {
        this.user = user;
        this.pass = pass;
        this.type = type;
        this.content = content;
    }

    String user;
    String pass;
    String type;
    String content;
}
