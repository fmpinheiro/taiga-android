package com.devmonsters.taigamobile.service;

import com.devmonsters.taigamobile.classes.login.SignInStatus;

import org.apache.commons.lang3.StringUtils;

public class LoginService {

    private final String taigaUrl;

    public LoginService(String taigaUrl) {
        this.taigaUrl = taigaUrl;
    }

    public SignInStatus doLogin(String username, String password) {
        if (StringUtils.isBlank(username) && StringUtils.isBlank(password)) {
            return SignInStatus.UNAVAILABLE;
        }
        if (!username.equals(password)) {
            return SignInStatus.INVALID_CREDENTIALS;
        }
        return SignInStatus.OK;
    }
}
