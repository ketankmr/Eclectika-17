package aditya.eclectika17;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.internal.NavigationMenuItemView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.Arrays;

import static java.security.AccessController.getContext;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences preferences;
    String logged_in;


   Integer i;

    UserDatabase database;
    SQLiteDatabase db;
    Cursor cursor;



    MaterialSearchView searchView;



    event_detail_fragment e= new event_detail_fragment();


    ArrayList news,description,news_url;

    BottomSheetBehavior  mBottomSheetBehavior;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home_fragment home_fragment = new home_fragment();
        i=1;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.home_fragment, home_fragment).commit();

        database= new UserDatabase(getApplicationContext());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       preferences= getSharedPreferences("mypref", Context.MODE_PRIVATE);
       logged_in= preferences.getString("logged_in","false");





         searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                String category = null, number= null;
                Intent intent= null;
                db= database .getReadableDatabase();


                if(Arrays.asList(searchdata()).contains(query)) {
                    cursor = database.get_event_by_name(query, db);

                    if (cursor.moveToFirst()) {
                        number = cursor.getString(0);
                        category = cursor.getString(1);
                    }

                    if (category.equalsIgnoreCase("14")) {
                        intent = new Intent(MainActivity.this, news_details.class);
                        intent.putExtra("title", query);
                        intent.putExtra("description", cursor.getString(2));
                        intent.putExtra("url", cursor.getString(3));
                        startActivity(intent);

                    } else
                        start_event_detail(number);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });


        FirebaseMessaging.getInstance().subscribeToTopic("news");
        String n=FirebaseInstanceId.getInstance().getToken();



        searchView.setSuggestions(searchdata());



        update_app update_app= new update_app(getApplicationContext());
        update_app.update_events(getApplicationContext());
        update_app.update_user(getApplicationContext());
        update_app.update_schedule(getApplicationContext());


     /*   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                event_fragment event_fragment= new event_fragment();

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.home_fragment, event_fragment).commit();

            }
        });*/

        setupBottomsheet();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.ec_id);
        TextView name = (TextView)hView.findViewById(R.id.name);
        ImageView imageView=(ImageView)hView.findViewById(R.id.profile_image) ;
        Menu menu = navigationView.getMenu();
        MenuItem item = menu.findItem(R.id.logout);
        if (logged_in.equalsIgnoreCase("true")){
            Glide
                    .with(this)
                    .load("http://graph.facebook.com/"+preferences.getString("fb_id","")+"/picture?type=large")
                    .diskCacheStrategy( DiskCacheStrategy.SOURCE )
                    .crossFade()

                    .centerCrop()
                    .into(imageView);
            item.setTitle("Log out");}else {item.setTitle("Log in");}


        name.setText(preferences.getString("name","Guest"));
        String j=preferences.getString("ec_id","Not Registered");
        if(j.equalsIgnoreCase("not registered")){
        Toast.makeText(this, j, Toast.LENGTH_SHORT).show();
            nav_user.setText(j);}
        else{Toast.makeText(this, "Ec_Id: "+j, Toast.LENGTH_LONG).show();
            nav_user.setText("Ec_Id: "+j);}







        if (savedInstanceState != null) {
            return;
        }







//

     //   news_outer_fragment news_outer_fragment= new news_outer_fragment();



      //  getSupportFragmentManager().beginTransaction()
      //          .add(R.id.news_frame, news_outer_fragment).commit();






        /* bottomsheet code















         */

        CoordinatorLayout c= (CoordinatorLayout)findViewById(R.id.cord);


       View bottomSheet = c.findViewById( R.id.bottomsheet );

      // DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
    //    int dp = Math.round(bottomSheet.getHeight() / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
     //   dp=dp+40;
    //    int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
   //     int twe = Math.round(20 * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
      //  bottomSheet.setMinimumHeight(px);
      //  bottomSheet.setScrollY(twe);





        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);


      //  BottomBar boton=(BottomBar)findViewById(R.id.bottomBar);








  //    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);



    }


    public String[] searchdata (){

           database= new UserDatabase(getApplicationContext());
      SQLiteDatabase  db = database.getReadableDatabase();



        cursor = database.getUser(db);
       String [] searched = new String [cursor.getCount()];
        if (cursor.moveToFirst()){

            for (int i = 0; i<cursor.getCount();i++){


                searched[i] = cursor.getString(1);
                cursor.moveToNext();
            }
        }

        return searched;
    }



    /*



        botomsheet ends
         */
    public void show(View view){

        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

    }


    public void hide(View view){mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);}
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (mBottomSheetBehavior.getState() == 3) {
            mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            super.onBackPressed();
        } else if (i == 0) {
            home_fragment home_fragment = new home_fragment();
            i = 1;
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.home_fragment, home_fragment).commit();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();



        if (id == R.id.home) {

             i=1;
            home_fragment home_fragment = new home_fragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.home_fragment, home_fragment).commit();

            // Handle the camera action
        }  else if (id == R.id.nav_share) {
            i=0;
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String sAux = "\nLet me recommend you this application\n\n";
                sAux = sAux + "https://play.google.com/store/apps/details?id=Orion.Soft \n\n";
                i.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(i, "choose one"));
            } catch(Exception e) {
                //e.toString();
            }


        }else if(id==R.id.feedback){
             i=0;
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/html");
            intent.putExtra(Intent.EXTRA_EMAIL, "feedback@eclectika.com");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
            intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");

            startActivity(Intent.createChooser(intent, "Send Email"));




        } else if(id==R.id.events){


            i=0;
            event_fragment event_fragment= new event_fragment();
            Bundle bundle= new Bundle();
            bundle.putString("ismine","no");
            event_fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.home_fragment, event_fragment).commit();

        }
        else if(id==R.id.going){

                 i=0;
            if (logged_in.equalsIgnoreCase("true")){ event_fragment event_fragment= new event_fragment();
                Bundle bundle= new Bundle();
                bundle.putString("ismine","yes");
                event_fragment.setArguments(bundle);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.home_fragment, event_fragment).commit();}
            else if (id== R.id.qr_code)
            {
                Intent i = new Intent(MainActivity.this,qr.class);
                startActivity(i);
            }
            else {
                Guest_Mode guest_mode = new Guest_Mode();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.home_fragment, guest_mode).commit();
            }

        }
        else if (id == R.id.artist) {
            i=0;
            Artists artists = new Artists();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.home_fragment, artists).commit();


        }
        else if (id == R.id.schedule) {
            i=0;
            schedule_fragment s = new schedule_fragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.home_fragment, s).commit();


        }
        else if (id == R.id.qr_code) {
           Intent intent= new Intent(MainActivity.this,qr.class);
            startActivity(intent);


        }

        else if (id == R.id.team) {
            i=0;
            Our_Team_fragment team = new Our_Team_fragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.home_fragment, team).commit();


        }
        else if (id == R.id.logout) {
            if(logged_in.equalsIgnoreCase("true")){

                FacebookSdk.sdkInitialize(getApplicationContext());
                LoginManager.getInstance().logOut();

                preferences.edit().putString("logged_in","false").apply();
                SQLiteDatabase db= database.getWritableDatabase();

                preferences.edit().putString("name","Guest").apply();
                preferences.edit().putString("ec_id","Not Registered").apply();
                preferences.edit().putString("contact",null).apply();
                database.delete_registered(db);
                database.close();
                Intent intent= new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
            else {
                Intent intent= new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }





    public void start_event_detail(String category){


        if(getSupportFragmentManager().getBackStackEntryCount()==0){getSupportFragmentManager().beginTransaction()
                .add(R.id.home_fragment, e.newInstance(category)).addToBackStack("").commit();}
        else {getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment,e.newInstance(category)).commit();}
    }
    public void start_artist_detail(String number){
      Artist_detail detail = new Artist_detail();
        Bundle bundle = new Bundle();
        bundle.putString("number",number);
        detail.setArguments(bundle);

        if(getSupportFragmentManager().getBackStackEntryCount()==0){getSupportFragmentManager().beginTransaction()
                .add(R.id.home_fragment, detail).addToBackStack("").commit();}
        else {getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment,detail).commit();}
    }



    void setupBottomsheet(){

        news = new ArrayList();
        description= new ArrayList();
        news_url=new ArrayList();

        // to be added on click listener
        getnews("news items");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem item = menu.findItem(R.id.action_settings);
        searchView.setMenuItem(item);

        return true;
    }

    public void getnews(String p)
    {              database = new UserDatabase(getApplicationContext());
            SQLiteDatabase db=database.getReadableDatabase();
        final RecyclerView list=(RecyclerView) findViewById(R.id.bottomsheeet_recycler);
       Cursor cursor= database.get_news(db);

        news.clear();
        Integer c=0;

    if (cursor.moveToLast())

    {  do {





            news.add(c,cursor.getString(0));
        description.add(c,cursor.getString(1));
        news_url.add(c,cursor.getString(2));


            c++;
        } while (cursor.moveToPrevious());}



        ContactsAdapter adapter=new ContactsAdapter(getApplicationContext(), news,description,news_url);


        list.setAdapter(adapter);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ne,descriptio,ur;
                ne= news.get( list.getChildLayoutPosition(view)).toString();
                descriptio= description.get( list.getChildLayoutPosition(view)).toString();
                ur= news_url.get( list.getChildLayoutPosition(view)).toString();
                Intent intent = new Intent(MainActivity.this,news_details.class);
                intent.putExtra("title",ne);
                intent.putExtra("description",descriptio);
                intent.putExtra("url",ur);
                startActivity(intent);
            }
        });

        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));






    }



    public void signup_page(View v){

        Intent intent = new Intent(MainActivity.this,Register.class);
        startActivity(intent);
        finish();
    }




}

