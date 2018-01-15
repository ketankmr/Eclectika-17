package aditya.eclectika17;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Ketan on 2/10/2017.
 */

public class TeamGridAdapter extends BaseAdapter {


    private Context context;
    

    //Constructor to initialize values
    public TeamGridAdapter(Context context) {

        this.context        = context;
        
    }

    @Override
    public int getCount() {

        // Number of times getView method call depends upon Name.length
        return 12;
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }



    // Number of times getView method call depends upon Name.length

    public View getView(int position, View convertView, ViewGroup parent) {







        View gridView;

        if (convertView == null) {


            LayoutInflater inflater2 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView = inflater2.inflate(R.layout.team_grid, parent, false);

            // get layout from grid_item.xml ( Defined Below )

        }else{
            gridView=(View) convertView;}

            // set value into textview

            TextView name = (TextView) gridView
                    .findViewById(R.id.name);

            TextView num = (TextView) gridView
                    .findViewById(R.id.contact);

            TextView email = (TextView) gridView
                    .findViewById(R.id.email);




            // set image based on selected text

            String image_url = null;

            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            if(position==0){

                name.setText("Nishant Chanda");

                num.setText("+91 87179 01913");
                email.setText("chanda@eclectika.org");

                 image_url = "http://eclectika.org/assets/images/team/chanda-sir.jpg";
            }
            else if(position==1){
                name.setText("Ragashree Tiwari");
                num.setText("+91 97136 86924");
                email.setText("ragashree@eclectika.org");

                image_url = "http://eclectika.org/assets/images/team/ragashree.jpg";
            }
            else if(position==2){

                name.setText("Adarsh Humne");

                num.setText("+91 81039 41457");
                email.setText("adarsh@eclectika.org");

                image_url = "http://eclectika.org/assets/images/team/adarsh.jpg";
            }
            else if(position==3){

                name.setText("Dikesh Kumar");
                num.setText("+91 88272 70178");
                email.setText("dikesh@eclectika.org");

                image_url = "http://eclectika.org/assets/images/team/dikesh.jpg";
            }
             else if(position==4){

                name.setText("K Alekhya");
                num.setText("+91 73893 89337");
                email.setText("alekhya@eclectika.org");

                image_url = "http://eclectika.org/assets/images/team/alekhya.jpg";
            }
            else if(position==5){
                name.setText("Sashi Prakash");
                num.setText("+91 74158 76966");
                email.setText("shashi@eclectika.org");

                image_url = "http://eclectika.org/assets/images/team/shashi.jpg";
            }
            else if(position==6){
                name.setText("Aditya Baswatia");
                num.setText("+91 81091 24060");
                email.setText("aditya@eclectika.org");

                image_url = "http://eclectika.org/assets/images/team/aditya.jpg";
            }
            else if(position==7){
                name.setText("Anamika Prasad");
                num.setText("+91 88787 25682");
                email.setText("anamika@eclectika.org");

                image_url = "http://eclectika.org/assets/images/team/anamika.jpg";
            }

            else if(position==8){

                name.setText("Yugesh Sahu");
                num.setText("+91 89822 16202");
                email.setText("yugesh@eclectika.org");

                image_url = "http://eclectika.org/assets/images/team/yugesh.jpg";

            }
            else if(position==9){
                name.setText("Anand Amrit Raj");
                num.setText("+91 84638 26679");
                email.setText("anand@eclectika.org");

                image_url = "http://eclectika.org/assets/images/team/nandu.jpg";
            }
            else if(position==10){
                name.setText("Lakshya Verma");
                num.setText("+91 80859 80708");
                email.setText("lakshya@eclectika.org");

                image_url = "http://eclectika.org/assets/images/team/lakshya.jpg";
            }
            else if(position==11){
                name.setText("Ayush Pillai");
                num.setText("+91 77250 77850");
                email.setText("ayush@eclectika.org");

                image_url = "http://eclectika.org/assets/images/team/pillai.jpg";
            }

            Glide
                    .with(context)
                    .load(image_url)
                    .diskCacheStrategy( DiskCacheStrategy.SOURCE )
                    .crossFade()
                    .centerCrop()
                    .into(imageView);





        return gridView;
    }
}