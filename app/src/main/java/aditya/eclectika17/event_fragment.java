package aditya.eclectika17;

/**
 * Created by lenovo on 24-12-2016.
 */
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
import android.widget.Toast;

public class event_fragment extends Fragment {





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        View inflatedView = inflater.inflate(R.layout.event_fragment, container, false);

        TabLayout tabLayout = (TabLayout) inflatedView.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Mega Events"));
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#dda502"));
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"),Color.parseColor("#e0b973"));
        tabLayout.addTab(tabLayout.newTab().setText("Quiz"));
        tabLayout.addTab(tabLayout.newTab().setText("Dance"));
        tabLayout.addTab(tabLayout.newTab().setText("Speaking Art"));
        tabLayout.setBackgroundColor(Color.parseColor("#000000"));
        tabLayout.addTab(tabLayout.newTab().setText("Lifestyle"));
        tabLayout.addTab(tabLayout.newTab().setText("Litereary"));
        tabLayout.addTab(tabLayout.newTab().setText("Fine Arts"));
        tabLayout.addTab(tabLayout.newTab().setText("Dramatics"));
        tabLayout.addTab(tabLayout.newTab().setText("Music"));
        tabLayout.addTab(tabLayout.newTab().setText("Photography"));
        tabLayout.addTab(tabLayout.newTab().setText("Informals"));
        tabLayout.addTab(tabLayout.newTab().setText("Digital Art"));
        tabLayout.addTab(tabLayout.newTab().setText("Online"));
        final ViewPager viewPager = (ViewPager) inflatedView.findViewById(R.id.viewpager);
     //  tabLayout.setupWithViewPager(viewPager);

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
            event_tab tab = new event_tab();

            switch (position) {
                case 0:


                    return tab.newInstance("1",getArguments().getString("ismine"));
                case 1:

                      return tab.newInstance("2",getArguments().getString("ismine"));
                case 2:

                    return tab.newInstance("3",getArguments().getString("ismine"));
                case 3:

                    return tab.newInstance("4",getArguments().getString("ismine"));
                case 4:

                    return tab.newInstance("5",getArguments().getString("ismine"));
                case 5:

                    return tab.newInstance("6",getArguments().getString("ismine"));
                case 6:

                    return tab.newInstance("7",getArguments().getString("ismine"));
                case 7:

                    return tab.newInstance("8",getArguments().getString("ismine"));
                case 8:

                    return tab.newInstance("9",getArguments().getString("ismine"));
                case 9:

                    return tab.newInstance("10",getArguments().getString("ismine"));
                case 10:

                    return tab.newInstance("11",getArguments().getString("ismine"));
                case 11:

                    return tab.newInstance("12",getArguments().getString("ismine"));
                case 12:

                    return tab.newInstance("13",getArguments().getString("ismine"));
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