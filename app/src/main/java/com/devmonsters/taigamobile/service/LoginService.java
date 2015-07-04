package com.devmonsters.taigamobile.service;

import android.os.AsyncTask;

import com.devmonsters.taigamobile.classes.login.SignInStatus;
import com.devmonsters.taigamobile.endpoints.AuthEndpoint;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.auth.AUTH;

public class LoginService {

    private static final String TAG = LoginService.class.getName();
    private final String taigaUrl;

    public LoginService(String taigaUrl) {
        this.taigaUrl = taigaUrl;
    }

    public SignInStatus doLogin(String username, String password) {
        if (StringUtils.isBlank(username) && StringUtils.isBlank(password)) {
            return SignInStatus.INVALID_CREDENTIALS;
        }
        try {
            AuthEndpoint authEndpoint = new AuthEndpoint();
            authEndpoint.execute(taigaUrl, username, password);
            if (authEndpoint.get()) {
                return SignInStatus.OK;
            }
            if (authEndpoint.isThrownAnError()) {
                return SignInStatus.UNAVAILABLE;
            }
            return SignInStatus.INVALID_CREDENTIALS;
        } catch (Exception e) {
            return SignInStatus.UNAVAILABLE;
        }
    }
}
