package aditya.eclectika17;

/**
 * Created by lenovo on 24-12-2016.
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Event_category_list_adapter_going extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList itemname;
    private final Integer imgid;
   SharedPreferences preferences= getContext().getSharedPreferences("mypref",Context.MODE_PRIVATE);

    public Event_category_list_adapter_going(Activity context, ArrayList itemname, Integer imgid) {
        super(context, R.layout.listitem2, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }

    public View getView(final int position, View rowView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        ViewHolder holder;
        //View rowView=inflater.inflate(R.layout.listitem1, null,true);


        if (rowView != null) {
            holder = (ViewHolder) rowView.getTag();
        } else {
            rowView = inflater.inflate(R.layout.listitem2, parent, false);
            holder = new ViewHolder(rowView);
            rowView.setTag(holder);
        }




        UserDatabase database= new UserDatabase(context);
        SQLiteDatabase db= database.getReadableDatabase();





        holder.txtTitle.setText(database.name(itemname.get(position).toString(),db));







       holder. t.setText(database.event_venue(itemname.get(position).toString(),db));
        holder. q.setText(database.event_date_time(itemname.get(position).toString(),db));






        return rowView;

    };

    private class ViewHolder {
        private TextView txtTitle,t,q;
         private LinearLayout layout;
        Button p;

        public ViewHolder(View v) {
             txtTitle = (TextView) v.findViewById(R.id.Itemname);

             t= (TextView) v.findViewById(R.id.lastmsg) ;

            q=(TextView) v.findViewById(R.id.time) ;


        }
    }
}