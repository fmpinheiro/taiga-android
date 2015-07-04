package com.devmonsters.taigamobile.endpoints;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class AuthEndpoint extends AsyncTask<String, Void, Boolean> {

    private static final String TAG = AuthEndpoint.class.getName();

    private boolean thrownAnError = false;

    @Override
    protected Boolean doInBackground(String... params) {
        thrownAnError = false;
        try {
            return this.requestLogin(params[0], params[1], params[2]);
        } catch (Exception e) {
            Log.e(TAG, "Error requesting login", e);
            return false;
        }
    }

    private boolean requestLogin(String taigaUrl, String username, String password) throws Exception {
        try {
            URL url = new URL(taigaUrl + "/api/v1/auth");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            Map<String, String> postDataParams = new HashMap<>();
            postDataParams.put("type", "normal");
            postDataParams.put("username", username);
            postDataParams.put("password", password);

            try (OutputStream os = conn.getOutputStream();
                 OutputStreamWriter out = new OutputStreamWriter(os, "UTF-8");
                 BufferedWriter writer = new BufferedWriter(out)) {
                writer.write(getPostDataString(postDataParams));
                writer.flush();
            }
            int responseCode = conn.getResponseCode();

            String response = "";
            Log.w(TAG, "Login response code: " + responseCode);
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                try (InputStream is = conn.getInputStream();
                     InputStreamReader isr = new InputStreamReader(is);
                     BufferedReader br = new BufferedReader(isr)) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        response += line;
                    }
                }

                Log.d(TAG, "Login response: " + response);
                return true;
            }
            return false;
        } catch (Exception e) {
            thrownAnError = true;
            Log.e(TAG, "Error requesting login", e);
            return false;
        }
    }

    private String getPostDataString(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    public boolean isThrownAnError() {
        return thrownAnError;
    }
}
