package net.azurewebsites.pedromiguelmartins.pedromiguelmartins;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import net.azurewebsites.pedromiguelmartins.pedromiguelmartins.article.ArticleContent;
import net.azurewebsites.pedromiguelmartins.pedromiguelmartins.contact.ContactContent;
import net.azurewebsites.pedromiguelmartins.pedromiguelmartins.project.ProjectContent;
import net.azurewebsites.pedromiguelmartins.pedromiguelmartins.resume.ResumeContent;
import net.azurewebsites.pedromiguelmartins.pedromiguelmartins.technology.TechnologyContent;
import net.azurewebsites.pedromiguelmartins.pedromiguelmartins.tool.ToolContent;
import net.sf.andpdf.pdfviewer.PdfViewerActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnFragmentInteractionListener,
        TimeLineFragment.OnFragmentInteractionListener,
        ResumeFragment.OnListFragmentInteractionListener,
        AboutMeFragment.OnFragmentInteractionListener,
        TechnologyFragment.OnListFragmentInteractionListener,
        ProjectFragment.OnListFragmentInteractionListener,
        ToolFragment.OnListFragmentInteractionListener,
        ContactFragment.OnListFragmentInteractionListener,
        ArticleFragment.OnListFragmentInteractionListener {

    Toolbar toolbar;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private ShareActionProvider mShareActionProvider;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    // nav drawer title
    private CharSequence mDrawerTitle;
    // used to store app title
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        mTitle = mDrawerTitle = getTitle();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            // on first time display view for first nav item
            updateFragment();
        }


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void updateFragment() {
        /* Getting reference to the FragmentManager */
        FragmentManager fragmentManager = getFragmentManager();

        /* Creating fragment instance */
        HomeFragment rFragment = new HomeFragment();



        /* Replace fragment */
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.drawer_content, rFragment);
        ft.commit();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void copyAssets() {
        AssetManager assetManager = getAssets();

        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open("Files/cv.pdf");
            File outFile = new File(getExternalFilesDir(null), "cv_temp.pdf");
            out = new FileOutputStream(outFile);
            copyFile(in, out);
            Intent intent = new Intent(MainHomeActivity.this, ActivityPdf.class);
            intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME, outFile.getAbsolutePath());
            startActivity(intent);
        } catch (IOException e) {
            Log.e("tag", "Failed to copy asset file: cv.pdf ", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // NOOP
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // NOOP
                }
            }
        }

    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // Inflate menu resource file.
        getMenuInflater().inflate(R.menu.main_home, menu);
        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.action_share);

        // Fetch and store ShareActionProvider
        SupportMenuItem shareItem=null;
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        // Return true to display menu
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

    // Call to update the share intent
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        int id = item.getItemId();

        if (id == R.id.nav_timeLine) {
            fragment = new TimeLineFragment();

        } else if (id == R.id.nav_technologies) {
            fragment = new TechnologyFragment();

        } else if (id == R.id.nav_profile) {
            fragment = new HomeFragment();

        } else if (id == R.id.nav_projects) {
            fragment = new ProjectFragment();

        } else if (id == R.id.nav_about_me) {
            fragment = new AboutMeFragment();

        } else if (id == R.id.nav_tool) {

            fragment = new ToolFragment();
        } else if (id == R.id.nav_resume) {

            fragment = new ResumeFragment();

        }  else if (id == R.id.nav_contact) {
            fragment = new ContactFragment();
        } else if (id == R.id.nav_gallery) {

            fragment = new ArticleFragment();
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.drawer_content, fragment).commit();

            setTitle(item.getTitle());
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(ResumeContent.ResumeItem item) {
        Intent intent = new Intent(this.getBaseContext(), DetailResumeDetailActivity.class);
        intent.putExtra("item_id", item.id);
        startActivity(intent);

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MainHome Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://net.azurewebsites.pedromiguelmartins.pedromiguelmartins/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MainHome Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://net.azurewebsites.pedromiguelmartins.pedromiguelmartins/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);

    }

    @Override
    public void onListFragmentInteraction(ProjectContent.ProjectItem item) {
        Intent intent = new Intent(this.getBaseContext(), ProjectDetailDetailActivity.class);
        intent.putExtra("item_id", item.id);
        startActivity(intent);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onListFragmentInteraction(TechnologyContent.TechnologyItem item) {

        Intent intent = new Intent(this.getBaseContext(), DetailTecnologyDetailActivity.class);
        intent.putExtra("item_id", item.id);
        startActivity(intent);
    }

    @Override
    public void onListFragmentInteraction(ToolContent.ToolItem item) {

    }

    @Override
    public void onListFragmentInteraction(ContactContent.ContactItem item) {

    }

    @Override
    public void onListFragmentInteraction(ArticleContent.ArticleItem item) {
        Intent intent = new Intent(this.getBaseContext(), ArticleDetailActivity.class);
        intent.putExtra("item_id", item.id);
        startActivity(intent);
    }
}
