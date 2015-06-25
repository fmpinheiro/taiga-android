package com.devmonsters.taigamobile.activities.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.devmonsters.taigamobile.R;
import com.devmonsters.taigamobile.classes.login.SignInStatus;
import com.devmonsters.taigamobile.service.LoginService;

public class LoginActivity extends Activity {

    private static final String TAG = LoginActivity.class.getName();

    private String taigaUrl = "http://taiga.io/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.change_taiga_url);

            // Set up the input
            final EditText input = new EditText(this);
            input.setText(taigaUrl);
            input.setSelectAllOnFocus(true);
            builder.setView(input);

            // Set up the buttons
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.i(TAG, String.format("Changing URL from %s to %s", taigaUrl, input.getText().toString()));
                    taigaUrl = input.getText().toString();
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.i(TAG, String.format("Cancel URL change, keeping as %s", taigaUrl));
                    dialog.cancel();
                }
            });

            builder.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void doSignIn(View view) {
        String username = ((EditText) findViewById(R.id.username_or_email)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();

        SignInStatus signInStatus = new LoginService(taigaUrl).doLogin(username, password);

        if (signInStatus != SignInStatus.OK) {
            Toast.makeText(getApplicationContext(), getString(signInStatus.getToastMessage()), Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, ProjectsActivity.class);
        startActivity(intent);
    }
}
