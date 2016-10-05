package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * Created by Little Garden on 7/20/2016.
 */
public class login extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        Button btnlogin, btnshow;
        final EditText etEmail, etPass;
        final TextView lblSignUp;
        SQLiteDatabase mydatabase = openOrCreateDatabase("dbAccounts",MODE_PRIVATE,null);
        final DBHelper mydb = new DBHelper(this) ;
        etEmail = (EditText)findViewById(R.id.etsignEmail);
        etPass = (EditText)findViewById(R.id.etPass);
        btnlogin = (Button)findViewById(R.id.btnLogin);
        btnshow = (Button)findViewById(R.id.btnShow);
        lblSignUp = (TextView)findViewById((R.id.lblSignUp));


        lblSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,signup.class );
                startActivity(intent);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //if(Pattern.compile("([a-zA-Z0-9]+_?)+@[a-zA-Z0-9]+\\.com").matcher(etEmail.getText()).matches()){
                        if(!(etPass.length()== 0)){
                            if(etPass.length()>8){
                                if (mydb.validateUser(etEmail.getText().toString(), etPass.getText().toString())=="True" || mydb.validateUsername(etEmail.getText().toString(), etPass.getText().toString())=="True") {
                                    Intent intent = new Intent(login.this, MainActivity.class);
                                    intent.putExtra("Username",etEmail.getText().toString());
                                    startActivity(intent);
                                    //for disposing
                                    finish();
                                }else Toast.makeText(getBaseContext(),"Incorrect email or password", Toast.LENGTH_SHORT).show();
                            } else Toast.makeText(getBaseContext(),"Password too short", Toast.LENGTH_SHORT).show();
                        }else Toast.makeText(getBaseContext(),"Password field is empty", Toast.LENGTH_SHORT).show();
                    //}else Toast.makeText(getBaseContext(),"Invalid Email Address", Toast.LENGTH_SHORT).show();


                    /*Toast.makeText(getBaseContext(),, Toast.LENGTH_SHORT).show();*/
                }
            });

        btnshow.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent motion){
                final int cursor = etPass.getSelectionStart();
                switch(motion.getAction()){
                    case MotionEvent.ACTION_DOWN: {
                        etPass.setInputType(InputType.TYPE_CLASS_TEXT);
                        etPass.setSelection(cursor);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        etPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        etPass.setSelection(cursor);
                        break;
                    }
                }
                return true;
            }

        });

    }
}
