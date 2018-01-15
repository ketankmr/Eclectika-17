package aditya.eclectika17;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by lenovo on 28-12-2016.
 */
public class ContactsAdapter extends
        RecyclerView.Adapter<ContactsAdapter.ViewHolder> {



    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView title,description;
      //  public Button messageButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.Itemname);
            description = (TextView) itemView.findViewById(R.id.lastmsg);
           // messageButton = (Button) itemView.findViewById(R.id.message_button);
        }
    }
    // Store a member variable for the contacts
    private ArrayList  mContacts,mdes,murl;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public ContactsAdapter(Context context, ArrayList contacts,ArrayList des,ArrayList url) {
        mContacts = contacts;
        mContext = context;
        mdes=des;
        murl=url;
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
        View contactView = inflater.inflate(R.layout.nes_item, parent, false);

        // Return a new holder instance
       ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        // Get the data model based on position
        final String contact = mContacts.get(position).toString();
        final String descrip = mdes.get(position).toString();

        // Set item views based on your views and data model
        TextView textView = viewHolder.title;
        viewHolder.description.setText(descrip);
        textView.setText(contact);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             int p=   viewHolder.getAdapterPosition();
                Intent intent= new Intent(mContext,news_details.class);
                intent.putExtra("title",contact);
                intent.putExtra("description",descrip);
                intent.putExtra("url",murl.get(position).toString());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
                Toast.makeText(mContext, contact, Toast.LENGTH_SHORT).show();
            }
        });
       // Button button = viewHolder.messageButton;
       // button.setText("Message");
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}