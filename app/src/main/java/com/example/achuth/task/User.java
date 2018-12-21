package com.example.achuth.task;

public class User {
    private String uuid,firstName,lastName,loginId,password;
    private boolean isUser;
    User()
    {
        uuid=firstName=lastName=loginId=password=" ";isUser=true;
    }
    User(String loginId ,String firstName,Boolean isUser)
    {
        this.loginId=loginId;
        this.firstName=firstName;
        this.isUser=isUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setUser(boolean user) {
        isUser = user;
    }

    public boolean isUser() {
        return isUser;
    }
}
