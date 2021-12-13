package com.example.rentcars;

public class NewClient {
    private String email;
    private String password;
    private String nameC;
    private String surName;
    private String patronymic;
    private String passport;
    private String address;
    private String telephone;


    public NewClient() {
    }

    public NewClient(String email,String password, String nameC, String surName, String patronymic, String passport,
                     String address, String telephone ) {
        this.email = email;
        this.password = password;
        this.nameC = nameC;
        this.surName = surName;
        this.patronymic = patronymic;
        this.passport = passport;
        this.address = address;
        this.telephone = telephone;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getNameC() {
        return nameC;
    }

    public void setNameC(String nameC) {
        this.nameC = nameC;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


}
