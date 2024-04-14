package cl.sumativa2.sumativa2.models;

public class LogInModel {
    private String email;
    private String password;

    public LogInModel(String string, String password) {
        this.email = string;
        this.password = password;
    }

    public LogInModel() {
    }

    public String getEmail() {
        return email;
    }

    public void setString(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
