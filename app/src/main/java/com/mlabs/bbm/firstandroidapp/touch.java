package com.mlabs.bbm.firstandroidapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * Created by Little Garden on 7/20/2016.
 */
public class touch extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch);
        final ImageView imgTouch;

        imgTouch = (ImageView) findViewById(R.id.imgTouch);

        imgTouch.setOnTouchListener(new View.OnTouchListener(){
            float cursorX=0;
            float cursorY=0;
            float cursorX2=0;
            float cursorY2=0;
            String message="";
            public boolean onTouch(View v, MotionEvent motion){


                switch(motion.getAction()){
                    case MotionEvent.ACTION_DOWN: {
                        cursorX = motion.getX(); cursorY = motion.getY();

                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        cursorX2 = motion.getX(); cursorY2 = motion.getY();
                        message = message +"X: "+ cursorX2; message = message +" Y: "+cursorY2;
                        if (cursorX < cursorX2){

                            message = message+"Swiped Right ";
                        }
                        if (cursorX2 < cursorX){
                            message = message+"Swiped Left ";
                        }

                        if (cursorY < cursorY2){

                            message = message+"Swiped Down ";
                        }
                        if (cursorY2 < cursorY){
                            message = message+"Swiped Up ";
                        }



                        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
                        message= "";

                        break;
                    }
                }


                return true;
            }

        });




    }
}
