package aditya.eclectika17;

/**
 * Created by lenovo on 24-12-2016.
 */
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class event_detail_fragment extends Fragment {


   String final_category;
    private String mParam2;
    ArrayList itemname;



    public event_detail_fragment() {


        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     *
     * @return A new instance of fragment event_tab.
     */
    // TODO: Rename and change types and number of parameters
    public static event_detail_fragment newInstance(String category) {
        event_detail_fragment fragment = new event_detail_fragment();
        Bundle args = new Bundle();
        args.putString("category", category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final_category= getArguments().getString("category");
        UserDatabase database = new UserDatabase(getActivity().getApplicationContext());
        SQLiteDatabase db= database.getReadableDatabase();





        View inflatedView = inflater.inflate(R.layout.fragment_event_tab, container, false);
       TextView textView = (TextView)inflatedView.findViewById(R.id.inner_event_name);
         textView.setText(database.name(final_category,db));



        TabLayout tabLayout = (TabLayout) inflatedView.findViewById(R.id.tabLayout1);
        tabLayout.addTab(tabLayout.newTab().setText("Desciption"));
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"),Color.parseColor("#e0b973"));
        tabLayout.addTab(tabLayout.newTab().setText("Rules"));
        tabLayout.addTab(tabLayout.newTab().setText("Contacts "));
        tabLayout.addTab(tabLayout.newTab().setText("Registration"));

        final ViewPager viewPager = (ViewPager) inflatedView.findViewById(R.id.viewpager1);


        //   tabLayout.setupWithViewPager(viewPager);

        viewPager.setAdapter(new PagerAdapter
                (getFragmentManager(), tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return inflatedView;
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {
            event_detail_tabs_fragment tab = new event_detail_tabs_fragment();

            switch (position) {
                case 0:


                    return tab.newInstance(final_category,"one");
                case 1:

                    return tab.newInstance(final_category,"two");
                case 2:

                    return tab.newInstance(final_category,"three");
                case 3:

                    return tab.newInstance(final_category,"four");

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }
}