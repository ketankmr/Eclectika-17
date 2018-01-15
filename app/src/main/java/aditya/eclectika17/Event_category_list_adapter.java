package aditya.eclectika17;

/**
 * Created by lenovo on 24-12-2016.
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Event_category_list_adapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList itemname;
    private final Integer imgid;
   SharedPreferences preferences= getContext().getSharedPreferences("mypref",Context.MODE_PRIVATE);

    public Event_category_list_adapter(Activity context, ArrayList itemname, Integer imgid) {
        super(context, R.layout.listitem1, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }

    public View getView(int position,View rowView,ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater=context.getLayoutInflater();
       // View rowView=inflater.inflate(R.layout.listitem1, null,true);

        if (rowView != null) {
            holder = (ViewHolder) rowView.getTag();
        } else {
            rowView = inflater.inflate(R.layout.listitem1, parent, false);
            holder = new ViewHolder(rowView);
            rowView.setTag(holder);
        }



        UserDatabase database= new UserDatabase(context);
        SQLiteDatabase db= database.getReadableDatabase();


        Spanned tit= Html.fromHtml(database.name(itemname.get(position).toString(),db));
        Spanned desc=Html.fromHtml(database.event_venue(itemname.get(position).toString(),db));
        Spanned time=Html.fromHtml(database.event_date_time(itemname.get(position).toString(),db));


       holder.textTitle.setText(tit);







        holder.t.setText(desc);
        holder.q.setText(time);


        if(database.is_registered(itemname.get(position).toString(),db).equalsIgnoreCase("yes"))
        {holder.p.setText("Registered");}
        else if(preferences.getString("logged_in","false").equalsIgnoreCase("false")){

           holder.p.setText("not logged in");
        }


        else {
           holder.p.setText("Not Registered");
        }


        return rowView;

    };
    private class ViewHolder {
        private TextView textTitle,t,p,q;


        public ViewHolder(View v) {
             textTitle = (TextView) v.findViewById(R.id.Itemname);
             t= (TextView) v.findViewById(R.id.lastmsg) ;
             p= (TextView) v.findViewById(R.id.reg_indi) ;
            q=(TextView)v.findViewById(R.id.time);

        }
    }
}