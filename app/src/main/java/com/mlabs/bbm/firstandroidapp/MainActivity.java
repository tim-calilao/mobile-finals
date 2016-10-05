package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnTouch;
        btnTouch = (Button)findViewById(R.id.btnTouch);
        //Toast.makeText(getBaseContext(),this.getIntent().getStringExtra("Username"), Toast.LENGTH_SHORT).show();

        btnTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,touch.class );
                startActivity(intent);
            }
        });


    }
}
