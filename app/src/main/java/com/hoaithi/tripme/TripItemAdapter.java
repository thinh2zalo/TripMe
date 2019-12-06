package com.hoaithi.tripme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hoaithi.tripme.model.Itinerary;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TripItemAdapter extends RecyclerView.Adapter<TripItemAdapter.TripItemViewHolder> {

    public class TripItemViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.nametrip_text_view)
        TextView nametripTextView;

        @BindView(R.id.time_text_view)
        TextView timeTextView;

        @BindView(R.id.destination_text_view)
        TextView destinationTextView;

        @BindView(R.id.attendee_text_view)
        TextView attendeeTextView;

        @BindView(R.id.privacy_image_view)
        ImageView privacyImageView;

        @BindView(R.id.privacy_text_view)
        TextView privacyTextView;


        public TripItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    List<Itinerary> mItineraryList;
    Context mContext;

    public TripItemAdapter(Context context, List<Itinerary> itineraries)
    {
        this.mContext = context;
        this.mItineraryList = itineraries;
    }

    @NonNull
    @Override
    public TripItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item_layout, parent, false);
        return new TripItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TripItemViewHolder holder, int position) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Itinerary itinerary = mItineraryList.get(position);
        holder.nametripTextView.setText(itinerary.mName);
        holder.timeTextView.setText(format.format(itinerary.mTimeStart) + " - " + format.format(itinerary.mTimeEnd));
        holder.attendeeTextView.setText(Integer.toString(itinerary.mNumberAttendees));
        holder.destinationTextView.setText(itinerary.mDestination);
        if (itinerary.mIsPublic)
        {
            holder.privacyImageView.setImageResource(R.drawable.world);
            holder.privacyTextView.setText("Công khai");
        }
        else
        {
            holder.privacyImageView.setImageResource(R.drawable.padlocks);
            holder.privacyTextView.setText("Riêng tư");
        }
    }

    @Override
    public int getItemCount() {
        return this.mItineraryList.size();
    }
}
