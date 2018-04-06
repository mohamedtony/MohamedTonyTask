package com.example.tony.mohamedtonytask.webServices.request;

public class CreateUserModel {

    private String firstname;
    private String midname;
    private String lastname;
    private String username;
    private String password;
    private String confirm_password;
    private String email;
    private String mobile_number;
    private int fk_gender_id;

    public CreateUserModel(){

    }

    public CreateUserModel(String firstname, String midname, String lastname, String username, String password, String confirm_password, String email, String mobile_number, int fk_gender_id) {
        this.firstname = firstname;
        this.midname = midname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.confirm_password = confirm_password;
        this.email = email;
        this.mobile_number = mobile_number;
        this.fk_gender_id = fk_gender_id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setMidname(String midname) {
        this.midname = midname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public void setFk_gender_id(int fk_gender_id) {
        this.fk_gender_id = fk_gender_id;
    }
}
