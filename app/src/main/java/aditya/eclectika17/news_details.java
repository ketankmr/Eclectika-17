package aditya.eclectika17;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class news_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        TextView title,description;
        ImageView imageView;
        Window window = this.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        title= (TextView)findViewById(R.id.news_title);
        description= (TextView)findViewById(R.id.news_description);
        imageView = (ImageView)findViewById(R.id.news_image);


       if( getIntent().getExtras().getString("url").equalsIgnoreCase("false")){
           imageView.setVisibility(View.GONE);
       }else {  Glide
               .with(this)
               .load(getIntent().getExtras().getString("url"))
               .diskCacheStrategy( DiskCacheStrategy.SOURCE )
               .crossFade()
               .override(450,450)
               .centerCrop()
               .into(imageView);

       }
        title.setText( getIntent().getExtras().getString("title"));
        description.setText( getIntent().getExtras().getString("description"));


    }
}
