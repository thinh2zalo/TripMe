package com.hoaithi.tripme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hoaithi.tripme.model.Attraction;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AttractionItemAdapter extends RecyclerView.Adapter<AttractionItemAdapter.AttractionViewHolder> {

    List<Attraction> attractionList;
    Context mContext;

    public AttractionItemAdapter(Context context, List<Attraction> attractions)
    {
        this.attractionList = attractions;
        this.mContext = context;
    }

    @NonNull
    @Override
    public AttractionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.attraction_item_layout, parent, false);
        return new AttractionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AttractionViewHolder holder, int position) {
        Attraction attraction = attractionList.get(position);
        holder.nameTextView.setText(attraction.mName);
        holder.addressTextView.setText(attraction.mAddress);
        holder.ratingTextView.setText(attraction.mRating);
    }

    @Override
    public int getItemCount() {
        return attractionList.size();
    }

    public class AttractionViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.attraction_image_view)
        ImageView attractionImageView;

        @BindView(R.id.attractionName_text_view)
        TextView nameTextView;

        @BindView(R.id.attractionAddress_text_view)
        TextView addressTextView;

        @BindView(R.id.attractionRating_text_view)
        TextView ratingTextView;

        public AttractionViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
