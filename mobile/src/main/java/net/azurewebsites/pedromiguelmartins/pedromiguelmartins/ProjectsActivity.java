package net.azurewebsites.pedromiguelmartins.pedromiguelmartins;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ProjectsActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_projects, menu);
        return true;
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = null;
            TextView textView;
            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    return getViewTechnologies(inflater, container);
                case 2:
                    return getViewTechnologies(inflater, container);
                case 3:
                    return getViewContacts(inflater, container);
                case 4:
                    rootView = inflater.inflate(R.layout.fragment_site, container, false);
                    WebView myWebView = (WebView) rootView.findViewById(R.id.webViewPedroURL);
                    myWebView.setWebViewClient(new URLWebViewClient());
                    myWebView.getSettings().setJavaScriptEnabled(true);
                    String URL = getString(R.string.URL_SITE);
                    myWebView.loadUrl(URL);
                    return rootView;
            }

            return rootView;
        }
        @NonNull
        private View getViewProjects(LayoutInflater inflater, ViewGroup container) {
            View rootView;
            rootView = inflater.inflate(R.layout.fragment_projects, container, false);
            final ListView listViewProjects = (ListView) rootView.findViewById(R.id.list_projects);
            // Defined Array values to show in ListView
            String[] values = Utils.GetProjectsList();
            Integer[] valuesImages = Utils.GetProjectsListImages();

            // Define a new Adapter
            // First parameter - Context
            // Second parameter - Layout for the row
            // Third parameter - ID of the TextView to which the data is written
            // Forth - the Array of data
            CustomListTecnologies adapter = new
                    CustomListTecnologies(this.getActivity(), values, valuesImages);


            // Assign adapter to ListView
            listViewProjects.setAdapter(adapter);
            listViewProjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    // ListView Clicked item index
                    int itemPosition = position;

                    // ListView Clicked item value
                    String  itemValue    = (String) listViewProjects.getItemAtPosition(position);

                    // Show Alert
                    Toast.makeText(view.getContext(),
                            "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                            .show();

                }
            });

            return rootView;
        }
        @NonNull
        private View getViewTechnologies(LayoutInflater inflater, ViewGroup container) {
            View rootView;
            rootView = inflater.inflate(R.layout.fragment_technologies, container, false);
            final ListView listViewTechnologies = (ListView) rootView.findViewById(R.id.list_tecnologies);
            // Defined Array values to show in ListView
            String[] values = Utils.GetTechnologiesList();
            Integer[] valuesImages = Utils.GetTechnologiesListImages();

            // Define a new Adapter
            // First parameter - Context
            // Second parameter - Layout for the row
            // Third parameter - ID of the TextView to which the data is written
            // Forth - the Array of data
            CustomListTecnologies adapter = new
                    CustomListTecnologies(this.getActivity(), values, valuesImages);


            // Assign adapter to ListView
            listViewTechnologies.setAdapter(adapter);
            listViewTechnologies.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    // ListView Clicked item index
                    int itemPosition     = position;

                    // ListView Clicked item value
                    String  itemValue    = (String) listViewTechnologies.getItemAtPosition(position);

                    // Show Alert
                    Toast.makeText(view.getContext(),
                            "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                            .show();

                }
            });

            return rootView;
        }
        @NonNull
        private View getViewContacts(LayoutInflater inflater, ViewGroup container) {
            View rootView;
            rootView = inflater.inflate(R.layout.fragment_contacts, container, false);
            final ListView listViewContacts = (ListView) rootView.findViewById(R.id.list_contacts);
            // Defined Array values to show in ListView
            String[] values = Utils.GetContactsList();
            Integer []  valuesImages=Utils.GetContactsListImages();
            // Define a new Adapter
            // First parameter - Context
            // Second parameter - Layout for the row
            // Third parameter - ID of the TextView to which the data is written
            // Forth - the Array of data

            CustomListContacts adapter = new
                    CustomListContacts(this.getActivity(), values, valuesImages);




                // Assign adapter to ListView
            listViewContacts.setAdapter(adapter);
            listViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    // ListView Clicked item index
                    int itemPosition     = position;

                    // ListView Clicked item value
                    String  itemValue    = (String) listViewContacts.getItemAtPosition(position);

                    // Show Alert
                    Toast.makeText(view.getContext(),
                            "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                            .show();

                }
            });

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Projects";
                case 1:
                    return "Technologies";
                case 2:
                    return "Contacts";
                case 3:
                    return "Site";
            }
            return null;
        }
    }
}
