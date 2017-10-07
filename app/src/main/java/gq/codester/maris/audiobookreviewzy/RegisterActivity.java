package gq.codester.maris.audiobookreviewzy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private Button registerBtn;
    private EditText edEmail,edPass, edLogin, edName, edName2, edDOBD, edDOBM, edDOBY;
    String email,login, pass, fname, sname, dobd, dobm, doby,dob;
    private TextView tv1;
    private ProgressDialog bar;
    SharedPreferences sp;

    private static final String REGISTER_URL = "http://codester.gq/book/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bar = new ProgressDialog(this);

        checkUser();

        registerBtn = (Button) findViewById(R.id.registerBtn);
        edEmail = (EditText) findViewById(R.id.edEmail);
        edLogin = (EditText) findViewById(R.id.edLogin);
        edPass = (EditText) findViewById(R.id.edPass);
        edName = (EditText) findViewById(R.id.edName);
        edName2 = (EditText) findViewById(R.id.edName2);
        edDOBD = (EditText) findViewById(R.id.edDOBD);
        edDOBM = (EditText) findViewById(R.id.edDOBM);
        edDOBY = (EditText) findViewById(R.id.edDOBY);



        tv1 = (TextView) findViewById(R.id.tv1);

        registerBtn.setOnClickListener(this);
        tv1.setOnClickListener(this);
    }//end onCreate methods



    private void registerUser(){
        email = edEmail.getText().toString().trim();
        pass = edPass.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            //if email is empty
            Toast.makeText(this,"Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(pass)){//if password is empty
            Toast.makeText(this,"Please enter pass", Toast.LENGTH_SHORT).show();
            return;
        }


        fname = edName.getText().toString().trim();
        sname = edName2.getText().toString().trim();
        login = edLogin.getText().toString().trim();
        dobd = edDOBD.getText().toString().trim();
        dobm = edDOBM.getText().toString().trim();
        doby = edDOBY.getText().toString().trim();

        dob = doby+"/"+dobm+"/"+dobd;

        bar.setMessage("Registering user...");
        bar.show();

        register(fname,sname,email,pass,login,dob);


    }//end RegisterUser method

    @Override
    public void onClick(View view) {
        if(registerBtn == view){
            registerUser();
        }

        if(view == tv1){
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
        }
    }//end OnClick method

    private void checkUser() {//method that checks if there is user data saved just log him in then

    }

    private void register(String fname, String sname, String email, String login, String pass, String dob) {
        String urlSuffix = "?sname="+fname+"&fname="+sname+"&email="+email+"&login="+login+"&password="+pass+"&dob="+dob;
        class RegisterUser extends AsyncTask<String, Void, String> {

            //ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //loading = ProgressDialog.show(RegisterActivity.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                bar.dismiss();

                String succ ="successfully registered";

                if(s.equals(succ)){
                    SharedPreferences.Editor editor = sp.edit();
                    //editor.putString("login", login);
                    //editor.putString("pass", pass);
                    editor.commit();

                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(REGISTER_URL+s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String result;

                    result = bufferedReader.readLine();

                    return result;
                }catch(Exception e){
                    return null;
                }
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(urlSuffix);
    }

}

