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
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.azurewebsites.pedromiguelmartins.pedromiguelmartins.ToolFragment.OnListFragmentInteractionListener;
import net.azurewebsites.pedromiguelmartins.pedromiguelmartins.tool.ToolContent;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ToolContent.ToolItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyToolRecyclerViewAdapter extends RecyclerView.Adapter<MyToolRecyclerViewAdapter.ViewHolder> {

    private final List<ToolContent.ToolItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context mContext;
    private String listItemDetailsForPopupMenu;

    public MyToolRecyclerViewAdapter(Context mContext, List<ToolContent.ToolItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        this.mContext = mContext;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tool, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).title);
        holder.mContentView.setText(mValues.get(position).details);
        holder.mSummaryView.setText(mValues.get(position).summary);

        Glide.with(mContext).load(mValues.get(position).thumbnail).into(holder.thumbnail);
        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.


                    // showPopupMenu(holder.overflow, mValues.get(position).content);


                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view, String listItemPosition) {
        listItemDetailsForPopupMenu = listItemPosition;
        // inflate menu
        PopupMenu popup = new PopupMenu(view.getContext(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_main, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }


        /**
         * This method will be invoked when a menu item is clicked if the item
         * itself did not already handle the event.
         *
         * @param item the menu item that was clicked
         * @return {@code true} if the event was handled, {@code false}
         * otherwise
         */

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_details:
                    Toast.makeText(mContext, listItemDetailsForPopupMenu, Toast.LENGTH_LONG).show();

                    return true;

                default:
            }
            return false;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mSummaryView;
        public final ImageView thumbnail, overflow;
        public ToolContent.ToolItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            mSummaryView = (TextView) view.findViewById(R.id.summary);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
        }


        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
