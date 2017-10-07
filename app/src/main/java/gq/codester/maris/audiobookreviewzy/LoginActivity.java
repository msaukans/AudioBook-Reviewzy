package gq.codester.maris.audiobookreviewzy;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loginBtn;
    private EditText ed_login,ed_pass;
    private TextView tv1;
    String pass, login;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sp;
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;

    private ProgressDialog bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        checkUser();


        loginBtn = (Button) findViewById(R.id.loginBtn);

        ed_login = (EditText) findViewById(R.id.edLogin);
        ed_pass = (EditText) findViewById(R.id.edPass);

        tv1 = (TextView) findViewById(R.id.tv1);

        loginBtn.setOnClickListener(this);
        tv1.setOnClickListener(this);

        bar = new ProgressDialog(this);



    }

    @Override
    public void onClick(View v) {
        if(v == loginBtn){
            userLogin();
        }

        if(v == tv1){
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        }
    }

    public void userLogin(){
        login = ed_login.getText().toString().trim();
        pass = ed_pass.getText().toString().trim();

        if(TextUtils.isEmpty(login)){
            Toast.makeText(this,"Please enter login", Toast.LENGTH_SHORT).show();
            return;
        }

            if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        new AsyncLogin().execute(login,pass);

    }

    private class AsyncLogin extends AsyncTask<String, String, String>{
        ProgressDialog pdLoading = new ProgressDialog(LoginActivity.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }
        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL("http://codester.gq/book/login.php");

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("username", params[0])
                        .appendQueryParameter("password", params[1]);
                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return(result.toString());

                }else{

                    return("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            pdLoading.dismiss();

            if(result.equalsIgnoreCase("true"))
            {
                //if log in exists & correct save log in details in sp
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("login", login);
                editor.putString("pass", pass);
                editor.commit();

                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();

            }else if (result.equalsIgnoreCase("false")){

                // If username and password does not match display a error message
                Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_LONG).show();

            } else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {

                Toast.makeText(LoginActivity.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

            }
        }

    }

    public void checkUser(){ //check if user's data is saved, if so just log them in and go to main activity
        Toast.makeText(this, "checking user", Toast.LENGTH_SHORT).show();

        sp = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String l =sp.getString("login",null);
        String p = sp.getString("pass",null);

        //Toast.makeText(this, l + "  " + p, Toast.LENGTH_SHORT).show();

        if(!TextUtils.isEmpty(l) && !TextUtils.isEmpty(p)){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }



    }


}
