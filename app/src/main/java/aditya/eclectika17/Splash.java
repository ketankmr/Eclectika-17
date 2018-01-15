package aditya.eclectika17;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Splash extends AppCompatActivity {
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        preferences= getSharedPreferences("mypref", Context.MODE_PRIVATE);

        if(preferences.getString("ec_id","").isEmpty()){
            Intent i = new Intent(Splash.this,Register.class);
            startActivity(i);

            finish();
        }

        else {
            Intent i = new Intent(Splash.this,MainActivity.class);
            startActivity(i);
            finish();
        }

    }
}
