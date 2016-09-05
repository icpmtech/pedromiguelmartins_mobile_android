package net.azurewebsites.pedromiguelmartins.pedromiguelmartins;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewFlipper mViewFlipper;
    private Context mContext;
    private float initialX;
    ViewPager pager;
    private List<TimeLine> persons;
    String dest_file_path = "test.pdf";
    int downloadedSize = 0, totalsize;
    String download_file_url = "http://pedromiguelmartins.azurewebsites.net/Home/DownloadCV";
    float per = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_action_icon);
        setSupportActionBar(toolbar);
        CardView cardView = (CardView) findViewById(R.id.android_card_view_example);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfile();
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mContext = this;
        mViewFlipper = (ViewFlipper) this.findViewById(R.id.view_flipper);
        Button buttonCV = (Button) this.findViewById(R.id.getCV);

        buttonCV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                        copyAssets();



            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
                Intent intent = new Intent(MainActivity.this, ActivityPdf.class);
                intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME, outFile.getAbsolutePath());
                startActivity(intent);
            } catch(IOException e) {
                Log.e("tag", "Failed to copy asset file: cv.pdf ", e);
            }
            finally {
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
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {

        switch (permsRequestCode) {

            case 200:

                boolean writeAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                break;

        }

    }

    private boolean shouldAskPermission() {

        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);

    }

    String[] perms = {"android.permission. WRITE_EXTERNAL_STORAGE"};

    int permsRequestCode = 200;


    @Override
    protected void onStart() {
        if (this.persons == null)
            this.initializeData();

        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://net.azurewebsites.pedromiguelmartins.pedromiguelmartins/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private List<TimeLine> initializeData() {
        persons = new ArrayList<>();
        persons.add(new TimeLine("Emma Wilson", "23 years old", R.drawable.ic_windows_store));
        persons.add(new TimeLine("Lavery Maiss", "25 years old", R.drawable.ic_net));
        return persons;
    }

    @Override
    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initialX = touchevent.getX();
                break;
            case MotionEvent.ACTION_UP:
                float finalX = touchevent.getX();
                if (initialX < finalX) {
                    if (mViewFlipper.getDisplayedChild() == 1)
                        break;

                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.in_from_left));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.out_from_left));

                    mViewFlipper.showNext();
                } else {
                    if (mViewFlipper.getDisplayedChild() == 0)
                        break;

                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.in_from_right));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.out_from_right));

                    mViewFlipper.showPrevious();
                }
                break;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void openProfile() {
        Intent intent = new Intent(this, ProjectsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {


            case R.id.action_settings:

                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
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
}
