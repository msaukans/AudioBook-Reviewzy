package gq.codester.maris.audiobookreviewzy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loginBtn;
    private EditText email,pass;
    private TextView tv1;

    private ProgressDialog bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginBtn = (Button) findViewById(R.id.loginBtn);

        email = (EditText) findViewById(R.id.editTextEmail);
        pass = (EditText) findViewById(R.id.editTextPass);

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
        String em = email.getText().toString().trim();
        String p = pass.getText().toString().trim();

        if(TextUtils.isEmpty(em)){
            Toast.makeText(this,"Please enter email", Toast.LENGTH_SHORT);
            return;
        }

        if(TextUtils.isEmpty(p)){
            Toast.makeText(this,"Please enter email", Toast.LENGTH_SHORT);
            return;
        }

        bar.setMessage("logging in....");
        bar.show();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        bar.dismiss();



    }


}
