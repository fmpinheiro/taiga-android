package com.devmonsters.taigamobile.classes.login;

import com.devmonsters.taigamobile.R;

public enum SignInStatus {
    UNAVAILABLE(R.string.application_unavailable),
    INVALID_CREDENTIALS(R.string.invalid_credentials),
    OK(null);

    private final Integer toastMessage;

    SignInStatus(Integer toastMessage) {
        this.toastMessage = toastMessage;
    }

    public int getToastMessage() {
        return toastMessage;
    }
}
