package net.azurewebsites.pedromiguelmartins.pedromiguelmartins;

import android.app.Fragment;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.azurewebsites.pedromiguelmartins.pedromiguelmartins.resume.ResumeContent;
import net.azurewebsites.pedromiguelmartins.pedromiguelmartins.resume.ResumeContent.ResumeItem;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ResumeFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ResumeFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ResumeFragment newInstance(int columnCount) {
        ResumeFragment fragment = new ResumeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    private static List<ResumeItem> loadXmlFromXML(String urlString, Context context) throws XmlPullParserException, IOException {
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
            entries = stackOverflowXmlParser.parse(stream);
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
        List<ResumeItem> ITEMS = new ArrayList<ResumeItem>();
        for (ProjectXmlParser.Entry entry : entries) {

            // If the user set the preference to include summary text,
            // adds it to the display.
            Integer value = Integer.parseInt(entry.link);
            if (value == null)
                value = 14;
            Integer res = Utils.GetResumeListImages()[value];
            if (res == null)
                res = 14;
            ITEMS.add(new ResumeItem(entry.title, entry.content, entry.details, entry.summary, res));
        }

        return ITEMS;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resume_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            try {
                recyclerView.setAdapter(new MyResumeRecyclerViewAdapter(context, loadXmlFromXML(getResources().getString(R.string.URL_RESUME), context), mListener));
                return view;
            } catch (XmlPullParserException e) {
               // e.printStackTrace();
            } catch (IOException e) {
              //  e.printStackTrace();
            }
            recyclerView.setAdapter(new MyResumeRecyclerViewAdapter(context,ResumeContent.ITEMS, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(ResumeItem item);
    }
}
