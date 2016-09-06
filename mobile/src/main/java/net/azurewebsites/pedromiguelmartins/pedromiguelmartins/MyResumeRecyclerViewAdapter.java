package net.azurewebsites.pedromiguelmartins.pedromiguelmartins;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.azurewebsites.pedromiguelmartins.pedromiguelmartins.ResumeFragment.OnListFragmentInteractionListener;
import net.azurewebsites.pedromiguelmartins.pedromiguelmartins.resume.ResumeContent.ResumeItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ResumeItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class MyResumeRecyclerViewAdapter extends RecyclerView.Adapter<MyResumeRecyclerViewAdapter.ViewHolder> {

    private final List<ResumeItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context mContext;
    public MyResumeRecyclerViewAdapter(Context mContext,List<ResumeItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        this.mContext = mContext;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_resume, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);

        Glide.with(mContext).load(mValues.get(position).thumbnail).into(holder.thumbnail);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    showPopupMenu(holder.overflow);
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }
    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(this.mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_main, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {

            }
            return false;
        }
    }




    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mContentDeatilsView;
        public ResumeItem mItem;
        public ImageView thumbnail, overflow;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            mContentDeatilsView = (TextView) view.findViewById(R.id.short_description);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
