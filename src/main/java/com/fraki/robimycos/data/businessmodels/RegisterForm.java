package com.fraki.robimycos.data.businessmodels;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by bambo on 09.10.2017.
 */
public class RegisterForm {

    @NotNull
    @Size(min=3, max=15)
    private String login;

    @NotNull
    @Size(min=8, max=20)
    private String password;

    @NotNull
    @Size(min=9, max=12)
    private String phone;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
