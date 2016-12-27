package net.azurewebsites.pedromiguelmartins.pedromiguelmartins;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.azurewebsites.pedromiguelmartins.pedromiguelmartins.project.ProjectContent;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a single ProjectDetail detail screen.
 * This fragment is either contained in a {@link ProjectDetailListActivity}
 * in two-pane mode (on tablets) or a {@link ProjectDetailDetailActivity}
 * on handsets.
 */
public class ProjectDetailDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The Project content this fragment is presenting.
     */
    private ProjectContent.ProjectItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProjectDetailDetailFragment() {
    }

    private static List<ProjectContent.ProjectItem> loadXmlFromXML(String urlString, Context context) throws XmlPullParserException, IOException {
        AssetManager assetManager = context.getResources().getAssets();
        InputStream stream = null;
        // Instantiate the parser
        ProjectXmlParser stackOverflowXmlParser = new ProjectXmlParser();
        List<ProjectXmlParser.Entry> entries = null;
        String title = null;
        String url = null;
        String summary = null;


        try {
            stream = assetManager.open(urlString);
            entries = stackOverflowXmlParser.parse(stream, TypeParser.PROJECT);
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (stream != null) {
                stream.close();
            }
        }

        // StackOverflowXmlParser returns a List (called "entries") of Entry objects.
        // Each Entry object represents a single post in the XML feed.
        // This section processes the entries list to combine each entry with HTML markup.
        // Each entry is displayed in the UI as a link that optionally includes
        // a text summary.
        List<ProjectContent.ProjectItem> ITEMS = new ArrayList<ProjectContent.ProjectItem>();
        for (ProjectXmlParser.Entry entry : entries) {

            // If the user set the preference to include summary text,
            // adds it to the display.
            Integer value = Integer.parseInt(entry.link);
            if (value == null)
                value = 14;
            Integer res = Utils.GetResumeListImages()[value];
            if (res == null)
                res = 14;
            ITEMS.add(new ProjectContent.ProjectItem(entry.title, entry.content, entry.details, entry.summary, res, entry.id));
        }

        return ITEMS;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the Project content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = ProjectContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            try {
                mItem = loadXmlFromXML(getResources().getString(R.string.URL_PROJECTS), getContext()).get(Integer.parseInt(getArguments().getString(ARG_ITEM_ID)));
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.title);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.projectdetail_detail, container, false);

        // Show the Project content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.projectdetail_detail)).setText(mItem.content);
        }

        return rootView;
    }
}
