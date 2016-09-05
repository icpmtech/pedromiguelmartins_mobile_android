package net.azurewebsites.pedromiguelmartins.pedromiguelmartins;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by migue_000 on 01/09/2016.
 */public class RVAdapter extends RecyclerView.Adapter<RVAdapter.TimeLineViewHolder>{

    List<TimeLine> persons=new ArrayList<>();

    RVAdapter(List<TimeLine> persons){
        this.persons = persons;
    }

    @Override
    public RVAdapter.TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_card_body, parent, false);
        TimeLineViewHolder pvh = new TimeLineViewHolder(v);
        return pvh;
    }



    @Override
    public void onBindViewHolder(RVAdapter.TimeLineViewHolder holder, int position) {
        holder.personName.setText(persons.get(position).name);
        holder.personAge.setText(persons.get(position).age);
        holder.personPhoto.setImageResource(persons.get(position).photoId);
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
if(persons==null)
    return 0;
        else
        return persons.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class TimeLineViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;

        TimeLineViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.getCV);
            personAge = (TextView)itemView.findViewById(R.id.person_age);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
        }
    }


}