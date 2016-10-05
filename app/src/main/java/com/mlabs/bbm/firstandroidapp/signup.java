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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * Created by androidstudio on 17/09/16.
 */
public class signup extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        final DBHelper mydb = new DBHelper(this) ;
        Button btnSignUp ;
        final EditText etsignEmail, etsignPass, etsignConPass, etsignUsernmae, etsignFname, etsignLname;

        etsignEmail = (EditText)findViewById(R.id.etsignEmail);
        etsignPass = (EditText)findViewById(R.id.etsignPass);
        etsignConPass = (EditText)findViewById(R.id.etsignConPass);
        etsignUsernmae = (EditText)findViewById(R.id.etsignUser);
        etsignFname = (EditText)findViewById(R.id.etsignFirstName);
        etsignLname = (EditText)findViewById(R.id.etsignLastName);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String strPass = etsignPass.getText().toString();
                final String strConPass = etsignPass.getText().toString();

                if(android.util.Patterns.EMAIL_ADDRESS.matcher(etsignEmail.getText().toString()).matches()){
                    if(Pattern.compile("^([A-Z][a-z]+ ?)+$").matcher(etsignFname.getText()).matches()){
                        if(Pattern.compile("^([A-Z][a-z]+ ?)+$").matcher(etsignLname.getText()).matches()){
                            if(!(etsignPass.length()== 0)){
                                if(etsignPass.length()>8){
                                    if(strPass.equals(strConPass)){
                                        Calendar c = Calendar.getInstance();
                                        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                                        String formattedDate = df.format(c.getTime());
                                        String res = mydb.insertAccount(etsignEmail.getText().toString(), etsignPass.getText().toString(),etsignUsernmae.getText().toString(),etsignFname.getText().toString(),etsignLname.getText().toString(),formattedDate);
                                        if(res=="True")
                                        {
                                            Toast.makeText(getBaseContext(),"You have succesfully registered!", Toast.LENGTH_SHORT).show();
                                            Toast.makeText(getBaseContext(),"Created at: " + formattedDate, Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(signup.this, login.class);
                                            startActivity(intent);
                                            finish();

                                        }
                                        else
                                        {
                                            Toast.makeText(getBaseContext(),res, Toast.LENGTH_SHORT).show();
                                        }
                                    }else Toast.makeText(getBaseContext(),"Password does not match", Toast.LENGTH_SHORT).show();
                                } else Toast.makeText(getBaseContext(),"Password too short", Toast.LENGTH_SHORT).show();
                            }else Toast.makeText(getBaseContext(),"Password field is empty", Toast.LENGTH_SHORT).show();
                        }else Toast.makeText(getBaseContext(),"Invalid Last Name", Toast.LENGTH_SHORT).show();
                    }else Toast.makeText(getBaseContext(),"Invalid First Name", Toast.LENGTH_SHORT).show();

                }else Toast.makeText(getBaseContext(),"Invalid Email Address", Toast.LENGTH_SHORT).show();


                    /*Toast.makeText(getBaseContext(),, Toast.LENGTH_SHORT).show();*/
            }
        });


    }

}
