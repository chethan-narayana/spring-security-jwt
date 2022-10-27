package io.javabrains.springsecurityjwt.model;

/**
 * Input Authenticate method post request pojo
 */
public class AutheticationRequest {

    private String userName;
    private String password;

    public AutheticationRequest() {
    }

    public AutheticationRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
