package aditya.eclectika17;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lenovo on 28-12-2016.
 */
public class news_adpter extends
        RecyclerView.Adapter<news_adpter.ViewHolder> {



    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView news_title,news_description;
        //  public Button messageButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            news_title = (TextView) itemView.findViewById(R.id.news_title);
            news_description = (TextView) itemView.findViewById(R.id.news_description);
            // messageButton = (Button) itemView.findViewById(R.id.message_button);
        }
    }
    // Store a member variable for the contacts
    private ArrayList  mContacts;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public news_adpter(Context context, ArrayList news) {
        mContacts = news;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // ... constructor and member variables

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.news_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        // Get the data model based on position
        String contact = mContacts.get(position).toString();

        // Set item views based on your views and data model
        TextView textView = viewHolder.news_title;
        textView.setText(contact);
        TextView textView2 = viewHolder.news_description;
        textView2.setText("to be  by database");
        // Button button = viewHolder.messageButton;
        // button.setText("Message");
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}