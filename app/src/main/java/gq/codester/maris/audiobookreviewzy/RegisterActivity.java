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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private Button registerBtn;
    private EditText edEmail,edPass, edName, edName2, edDOBD, edDOBM, edDOBY;
    String email, pass, name1, name2, dobd, dobm, doby;
    private TextView tv1;
    private ProgressDialog bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bar = new ProgressDialog(this);

        checkUser();

        registerBtn = (Button) findViewById(R.id.registerBtn);
        edEmail = (EditText) findViewById(R.id.edEmail);
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


        name1 = edName.getText().toString().trim();
        name2 = edName2.getText().toString().trim();
        dobd = edDOBD.getText().toString().trim();
        dobm = edDOBM.getText().toString().trim();
        doby = edDOBY.getText().toString().trim();

        bar.setMessage("Registering user...");
        bar.show();



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

}

