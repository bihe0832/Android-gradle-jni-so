package com.bihe0832.gradletest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends Activity {

    private static final String LOG_TAG = "Bihe0832 Gradle";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText md5KeyEdit = (EditText)findViewById(R.id.md5String);
        final TextView md5ResultTextView = (TextView)findViewById(R.id.md5Result);

        Button upperMD5Btn = (Button)findViewById(R.id.UpperMD5);
        upperMD5Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String md5Key = md5KeyEdit.getText().toString();

                if(null != md5Key){
                    Log.d(LOG_TAG,md5Key);
                    if(!md5Key.equals("")){
                        md5ResultTextView.setText("MD5:"+ GradleTestDemoNative.getUpperMD5(md5Key));
                    }else{
                        Toast.makeText(MainActivity.this,"md5 key is enpty!",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"md5 key is bad!",Toast.LENGTH_LONG).show();
                }

            }
        });

        Button lowerMD5Btn = (Button)findViewById(R.id.LowerMD5);
        lowerMD5Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String md5Key = md5KeyEdit.getText().toString();

                if(null != md5Key){
                    Log.d(LOG_TAG,md5Key);
                    if(!md5Key.equals("")){
                        md5ResultTextView.setText("MD5:"+ GradleTestDemoNative.getLowerMD5(md5Key));
                    }else{
                        Toast.makeText(MainActivity.this,"md5 key is enpty!",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"md5 key is bad!",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    static {
        System.loadLibrary("GradleTest");
        Log.d(LOG_TAG,"System.loadLibrary");
    }
}
