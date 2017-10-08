package gq.codester.maris.audiobookreviewzy;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sp;
    String fname, sname, email, login, pass, dob, dobd, dobm, doby;
    Button btn_save;
    EditText edEmail,edPass, edLogin, edName, edName2, edDOBD, edDOBM, edDOBY;
    ImageView img_profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sp = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        login =sp.getString("login",null);
        pass = sp.getString("pass",null);
        email = sp.getString("email",null);
        fname = sp.getString("fname",null);
        sname = sp.getString("Sname",null);
        dob = sp.getString("dob",null);

        btn_save = (Button) findViewById(R.id.btn_save);
        //btn_save.setOnClickListener(this);

        img_profile = (ImageView) findViewById(R.id.img_profile);
        img_profile.setImageResource(R.mipmap.logo1);

        edEmail = (EditText) findViewById(R.id.edEmail);
        edLogin = (EditText) findViewById(R.id.edLogin);
        edPass = (EditText) findViewById(R.id.edPass);
        edName = (EditText) findViewById(R.id.edName);
        edName2 = (EditText) findViewById(R.id.edName2);
        edDOBD = (EditText) findViewById(R.id.edDOBD);
        edDOBM = (EditText) findViewById(R.id.edDOBM);
        edDOBY = (EditText) findViewById(R.id.edDOBY);

        edEmail.setText(email);
        edPass.setText(pass);
        edLogin.setText(login);
        edName.setText(fname);
        edName2.setText(sname);



    }


    @Override
    public void onClick(View v) {
        if(v == btn_save){
            saveDate();
        }


    }

    private void saveDate() {
        email = edEmail.getText().toString().trim();
        pass = edPass.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
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

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("login", login);
        editor.putString("pass", pass);
        editor.putString("email", email);
        editor.putString("fname", fname);
        editor.putString("sname", sname);
        editor.putString("dob", dob);
        editor.commit();
    }
}
