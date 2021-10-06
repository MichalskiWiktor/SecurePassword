package Modules;

public class LoginData {
    private int id;
    private String name;
    private String login;
    private String password;
    private String deCodedPassword;
    public LoginData(int id, String name, String login, String password){
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.deCodedPassword = password;
    }
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getLogin(){
        return this.login;
    }
    public String getPassword(){
        return this.password;
    }
    public String getDeCodedPassword(){
        return this.deCodedPassword;
    }
    public void setDeCodedPassword(String deCodedPassword){
        this.deCodedPassword = deCodedPassword;
    }
}

