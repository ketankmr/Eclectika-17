package aditya.eclectika17;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.Resource;

/**
 * Created by Ketan on 2/10/2017.
 */

public class CustomGridAdapter extends BaseAdapter {

    private Context context;



    UserDatabase database;
    SQLiteDatabase db;






    //Constructor to initialize values
    public CustomGridAdapter(Context context) {

        this.context        = context;


    }

    @Override
    public int getCount() {

        database= new UserDatabase(context);

        db = database.getReadableDatabase();
        Cursor cursor = database.get_event_by_category("1",db);

        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }


    // Number of times getView method call depends upon gridValues.length

    public View getView(int position, View convertView, ViewGroup parent) {



        db= database.getReadableDatabase();


        // LayoutInflator to call external grid_item.xml file


        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from grid_item.xml ( Defined Below )

            gridView = inflater.inflate( R.layout.grid_item , null);

            // set value into textview

            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);



            // set image based on selected text

            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);



            Cursor cursor = database.get_event_by_category("1",db);

            if(cursor.moveToPosition(position)){



                    textView.setText(cursor.getString(1));

                    Glide
                            .with(context)
                            .load("https://s-media-cache-ak0.pinimg.com/originals/e4/a0/a1/e4a0a179a199e74936e98b9aff80a423.jpg")
                            .diskCacheStrategy( DiskCacheStrategy.SOURCE )
                            .fitCenter()
                            .placeholder(R.drawable.star)
                            .crossFade()
                            .centerCrop()
                            .into(imageView);

            }






        } else {

            gridView =  convertView;
        }

        return gridView;
    }
}