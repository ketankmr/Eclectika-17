package aditya.eclectika17;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ADI on 2/16/2017.
 */

public class ScheduleAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList itemname;
    Long timeInMilliseconds;

    ArrayList tim,even,venu,remin;
    TextView time , event, venue;
    String day;

    public ScheduleAdapter(Activity context, ArrayList itemname, ArrayList regis,ArrayList time,ArrayList event,ArrayList venue,String day) {
        super(context, R.layout.schedule_listitem, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.tim=time;
        this.even=event;
        this.venu=venue;
        this.remin=regis;
        this.day=day;
    }


    public View getView(final int position, View rowView, ViewGroup parent) {
        final ViewHolder holder;
        LayoutInflater inflater=context.getLayoutInflater();
        // rowView=inflater.inflate(R.layout.schedule_listitem, null,true);

        if (rowView != null) {
            holder = (ViewHolder) rowView.getTag();
        } else {
            rowView = inflater.inflate(R.layout.schedule_listitem, parent, false);
            holder = new ViewHolder(rowView);
            rowView.setTag(holder);
        }

       holder. time.setText(tim.get(position).toString().substring(11,19));

       holder. event.setText(even.get(position).toString());

       holder. venue.setText(venu.get(position).toString());

      if(remin.get(position).toString().equalsIgnoreCase("false")) {holder.bi.setEnabled(true);
          holder.bi.setColorFilter(Color.parseColor("#e0b973"));}
        if(remin.get(position).toString().equalsIgnoreCase("true")){holder.bi.setEnabled(false);

            holder.bi.setColorFilter(Color.parseColor("#919191"));}
        holder.bi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "I am clicked", Toast.LENGTH_SHORT).show();

                UserDatabase database = new UserDatabase(context);
                SQLiteDatabase db= database.getReadableDatabase();
                database.insert_reminder(even.get(position).toString(),day,db);





                String givenDateString = tim.get(position).toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                try {
                    Date mDate = sdf.parse(givenDateString);
                    timeInMilliseconds = mDate.getTime();

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType("vnd.android.cursor.item/event");


                long startTime =timeInMilliseconds;

                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime);

                intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false);
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,false);
                intent.putExtra(CalendarContract.Reminders.MINUTES,20);
                intent.putExtra(CalendarContract.Events.HAS_ALARM,true);

                intent.putExtra(CalendarContract.Events.TITLE, even.get(position).toString());

                intent.putExtra(CalendarContract.Events.EVENT_LOCATION, venu.get(position).toString());
                holder.bi.setEnabled(false);
                holder.bi.setColorFilter(Color.parseColor("#919191"));


                context.startActivity(intent);

            }
        });





        return rowView;

    }
    private class ViewHolder {
         TextView time,event,venue;
        ImageButton bi;

        LinearLayout linearLayout;


        public ViewHolder(View v) {
            time = (TextView) v.findViewById(R.id.time);
            event = (TextView) v.findViewById(R.id.event);
            venue = (TextView) v.findViewById(R.id.venue);
             bi= (ImageButton)v.findViewById(R.id.imageButton) ;




        }
    }

    public void s (String s){

        MainActivity m= new MainActivity();
        m.start_event_detail(s);
    }
}
