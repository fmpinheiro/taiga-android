package com.devmonsters.taigamobile.endpoints;

import android.os.AsyncTask;
import android.util.Log;

import com.devmonsters.taigamobile.classes.response.AuthResponse;
import com.google.gson.GsonBuilder;

import org.apache.commons.io.IOUtils;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class AuthEndpoint extends AsyncTask<String, Void, AuthResponse> {

    private static final String TAG = AuthEndpoint.class.getName();

    private final String taigaUrl;
    private final String username;
    private final String password;

    public AuthEndpoint(String taigaUrl, String username, String password) {
        this.taigaUrl = taigaUrl;
        this.username = username;
        this.password = password;
    }

    @Override
    protected AuthResponse doInBackground(String... params) {
        try {
            URL url = new URL(taigaUrl + "/api/v1/auth");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            String postData = String.format("type=normal&username=%s&password=%s",
                    URLEncoder.encode(username, "UTF-8"), URLEncoder.encode(password, "UTF-8"));

            try (OutputStream os = conn.getOutputStream();
                 OutputStreamWriter out = new OutputStreamWriter(os, "UTF-8");
                 BufferedWriter writer = new BufferedWriter(out)) {
                writer.write(postData);
                writer.flush();
            }
            int responseCode = conn.getResponseCode();

            Log.w(TAG, "Login response code: " + responseCode);
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String jsonResponse = IOUtils.toString(conn.getInputStream());
                Log.d(TAG, "Login response: " + jsonResponse);

                return new GsonBuilder().create().fromJson(jsonResponse, AuthResponse.class);
            }
            throw new IllegalStateException(String.valueOf(responseCode));
        } catch (Exception e) {
            Log.e(TAG, "Error requesting login", e);
            return null;
        }
    }
}
