package aditya.eclectika17;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class qr extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr);

        SharedPreferences preferences= getSharedPreferences("mypref", Context.MODE_PRIVATE);

        if(preferences.getString("logged_in","false").equalsIgnoreCase("false")){
            setContentView(R.layout.fragment_guest__mode);

            Button button = (Button) findViewById(R.id.button4);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(qr.this,Register.class);
                    startActivity(i);
                }
            });
        }
        else {ImageView imageView=(ImageView)findViewById(R.id.imageView);
            Glide
                    .with(this)
                    .load("http://eclectika.org/apiv2/public/img/qr/"+preferences.getString("ec_id","")+"_"+preferences.getString("contact","")+".png")
                    .diskCacheStrategy( DiskCacheStrategy.SOURCE )
                    .override(450,450)
                    .fitCenter()
                    .into(imageView);
        }
    }
}
