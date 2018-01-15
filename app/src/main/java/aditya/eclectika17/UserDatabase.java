package aditya.eclectika17;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Ketan on 1/25/2017.
 */

public class UserDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "USER_INFO.DB";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_QUERY =
            "CREATE TABLE "+ "Events"+"("+ "number"+" text collate nocase,"+ "name"+" text collate nocase,"+ "description"+ " text collate nocase DEFAULT 'na',"+"contact"+" text collate nocase DEFAULT 'na',"+"prize"+" text collate nocase,"+"rules"+" text collate nocase DEFAULT 'na',"+"place"+" text collate nocase DEFAULT 'na',"+"category"+" text collate nocase DEFAULT 'na',"+"time"+" text collate nocase DEFAULT 'na',"+"url_1"+" text collate nocase DEFAULT 'na',"+"url_2"+" text collate nocase DEFAULT 'na',"+"registerd"+" text collate nocase DEFAULT 'no');";

    private static final String CREATE_QUERY2 =
            "CREATE TABLE "+ "Registered"+"("+ "event_name"+" text collate nocase );";
    private static final String CREATE_QUERY3 =
            "CREATE TABLE "+ "day1"+"("+ "name"+" text collate nocase,"+"remin"+" text collate nocase,"+ "venue"+" text collate nocase,"+ "time"+" text collate nocase );";
    private static final String CREATE_QUERY4 =
            "CREATE TABLE "+ "day2"+"("+ "name"+" text collate nocase,"+"remin"+" text collate nocase,"+ "venue"+" text collate nocase,"+ "time"+" text collate nocase );";
    private static final String CREATE_QUERY5 =
            "CREATE TABLE "+ "day3"+"("+ "name"+" text collate nocase,"+"remin"+" text collate nocase,"+ "venue"+" text collate nocase,"+ "time"+" text collate nocase );";
    private static final String CREATE_QUERY6 =
            "CREATE TABLE "+ "news"+"("+ "title"+" text collate nocase,"+ "description"+" text collate nocase,"+ "url"+" text collate nocase );";

    static final String CREATE_QUERY7 =
            "CREATE TABLE "+ "Reminder"+"("+ "event_name"+" text collate nocase,"+ "day"+" text );";
    public UserDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.e("DATABASE OPERATION","Database created/opened .....");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_QUERY);
        db.execSQL(CREATE_QUERY2);
        db.execSQL(CREATE_QUERY3);
        db.execSQL(CREATE_QUERY4);
        db.execSQL(CREATE_QUERY5);
        db.execSQL(CREATE_QUERY6);
        db.execSQL(CREATE_QUERY7);


        Log.e("DATABASE OPERATION","Table created .....");

    }

    public void update_events(String number,String name,String description,String contact,String prize,String rules,String place,String time,String category,String url_1,String url_2 ,SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put("number",number);
        contentValues.put("name",name);
        contentValues.put("description",description);

        contentValues.put("prize",prize);
        contentValues.put("rules",rules);
        contentValues.put("url_1",url_1);
        contentValues.put("url_2",url_2);
        contentValues.put("place",place);
        contentValues.put("time",time);
        contentValues.put("category",category);
        contentValues.put("contact",contact);

        db.insert("Events",null,contentValues);

        Log.e("DATABASE OPERATION","One Row Inserted .....");
    }
    public  void delete_events(SQLiteDatabase db){


        db.execSQL("delete from "+ "Events");
    }
    public void insert_registered(String name,SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();

        contentValues.put("event_name",name);
        db.insert("Registered",null,contentValues);

        Log.e("DATABASE OPERATION","One Row Inserted .....");
    }
    public void insert_reminder(String name,String day,SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();

        contentValues.put("event_name",name);
        contentValues.put("day",day);
        db.insert("Reminder",null,contentValues);

        Log.e("DATABASE OPERATION","One Row Inserted .....");
    }
    public void update_schedule(String name,String desc,String time,String day,SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();

        contentValues.put("name",name);
        contentValues.put("venue",desc);
        contentValues.put("time",time);

        db.insert("day1",null,contentValues);

        Log.e("DATABASE OPERATION","One Row Inserted .....");
    }
    public  void delete_schedule(String day,SQLiteDatabase db

    ){


        db.execSQL("delete from "+ day);
    }
    public  void delete_registered(SQLiteDatabase db

    ){


        db.execSQL("delete from "+ "Registered");
    }


    public Cursor getUser(SQLiteDatabase db ){
        Cursor cursor;

        String[] projection = {"number", "name","description","contact","prize","rules","place","time","category","registerd","contact","url_1","url_2"};


        cursor= db.query("Events",projection,null,null,null,null,null);

        return cursor;
    }
    public Cursor Day1(SQLiteDatabase db ){
        Cursor cursor;

        String[] projection = {"name", "venue","time"};


        cursor= db.query("day1",projection,null,null,null,null,null);

        return cursor;
    }
    public String get_reminder(String event,String day,SQLiteDatabase db ){
        Cursor cursor;

        String[] projection = {"event_name", "day"};


        cursor= db.query("Reminder",projection,"event_name ="+"'"+event+"'" +" and  day ="+"'"+day+"'",null,null,null,null);

       if (cursor.moveToFirst()){return "true";}
        else {return "false";}
    }
    public String is_registered(String id,SQLiteDatabase db ){
        Cursor cursor;

        String[] projection = {"event_name"};


        cursor= db.query("Registered",projection,"event_name = "+"'"+id+"'" ,null,null,null,null);
        if (cursor.moveToFirst()){

            return "yes";}
        else {return "no";}


    }

    public Cursor Day3(SQLiteDatabase db ){
        Cursor cursor;

        String[] projection = {"name", "venue","time"};


        cursor= db.query("day3",projection,null,null,null,null,null);

        return cursor;
    }
    public Cursor Day2(SQLiteDatabase db ){
        Cursor cursor;

        String[] projection = {"name", "venue","time"};


        cursor= db.query("day2",projection,null,null,null,null,null);

        return cursor;
    }
    public Cursor get_complete_event(String number,SQLiteDatabase db ){
        Cursor cursor;

        String[] projection = {"number", "name","description","contact","prize","rules","place","time","category","registerd","contact","url_1","url_2"};


        cursor= db.query("Events",projection,"number = "+"'"+number+"'",null,null,null,null);

        return cursor;
    }
    public String name(String number,SQLiteDatabase db ){
        Cursor cursor;

        String[] projection = {"name"};


        cursor= db.query("Events",projection,"number = "+"'"+number+"'",null,null,null,null);
         cursor.moveToFirst();
        return cursor.getString(0);
    }
    public String description(String number,SQLiteDatabase db ){
        Cursor cursor;

        String[] projection = {"description"};


        cursor= db.query("Events",projection,"number = "+"'"+number+"'",null,null,null,null);
        cursor.moveToFirst();

        return cursor.getString(0);
    }
    public String event_venue(String number,SQLiteDatabase db ){
        Cursor cursor;

        String[] projection = {"place"};


        cursor= db.query("Events",projection,"number = "+"'"+number+"'",null,null,null,null);
        cursor.moveToFirst();

        return cursor.getString(0);
    }
    public String event_date_time(String number,SQLiteDatabase db ){
        Cursor cursor;

        String[] projection = {"time"};


        cursor= db.query("Events",projection,"number = "+"'"+number+"'",null,null,null,null);
        cursor.moveToFirst();

        return cursor.getString(0);
    }

    public Cursor get_event_by_category(String category,SQLiteDatabase db ){
        Cursor cursor;

        String[] projection = {"number","name","description","url_1"};


        cursor= db.query("Events",projection,"category = "+"'"+category+"'",null,null,null,null);

        return cursor;
    }

    public Cursor get_event_by_name(String name,SQLiteDatabase db ){
        Cursor cursor;

        String[] projection = {"number","category","description","url_1"};


        cursor= db.query("Events",projection,"name = "+"'"+name+"'",null,null,null,null);

        return cursor;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert_news(String title,String description,String url,SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);

        contentValues.put("description",description);

        contentValues.put("url",url);


        db.insert("news",null,contentValues);

        Log.e("DATABASE OPERATION","One Row Inserted .....");
    }

    public Cursor get_news(SQLiteDatabase db ){
        Cursor cursor;

        String[] projection = {"title","description","url"};


        cursor= db.query("news",projection,null,null,null,null,null);

        return cursor;
    }
    public String get_Registr(SQLiteDatabase db ){
        Cursor cursor;

        String[] projection = {"event_name"};


        cursor= db.query("Registered",projection,null,null,null,null,null);

        String reg="";
        if (cursor.moveToFirst()){

            do{reg=reg+cursor.getString(0)+",";}while (cursor.moveToNext());


        }
        return reg;
    }

}
