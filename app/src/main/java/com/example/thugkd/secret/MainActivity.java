package com.example.thugkd.secret;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.thugkd.secret.atys.AtyTimeLine;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String token = Config.getCachedToken(this);
        startActivity(new Intent(this, AtyTimeLine.class));
//        if (token != null){
//            Intent intent = new Intent(this, AtyTimeLine.class);
//            intent.putExtra(Config.KEY_TOKEN,token);
//            startActivity(intent);
//        }else {
//            startActivity(new Intent(this, AtyLogin.class));
//        }

        finish();
    }
}
