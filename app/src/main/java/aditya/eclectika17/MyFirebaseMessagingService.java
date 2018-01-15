package aditya.eclectika17;



import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;



/**
 * Created by Ketan on 9/24/2016.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

   String title,news,url;
    UserDatabase database = new UserDatabase(this);
    SQLiteDatabase db;
    Cursor cursor;




    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

      if(remoteMessage.getData().get("category").toString().equalsIgnoreCase("news")){
        title = remoteMessage.getData().get("title").toString();
        news =  remoteMessage.getData().get("news").toString();
        url =  remoteMessage.getData().get("url").toString();

        db = database.getWritableDatabase();

         database.insert_news(title,news,url,db);

        database.close();}

        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        // prepare intent which is triggered if the
// notification is selected

        Intent intent = new Intent(this, MainActivity.class);
// use System.currentTimeMillis() to have a unique ID for the pending intent
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

// build notification
// the addAction re-use the same intent to keep the example short
        Notification n  = new Notification.Builder(this)
                .setContentTitle(title)
                .setContentText(news)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .build();


         

        notificationManager.notify(0, n);









    }





}
