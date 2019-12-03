package com.hoaithi.tripme;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hoaithi.tripme.model.Place;
import com.hoaithi.tripme.util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ItemHolder> {
    private static final String TAG = "PlaceAdapter";
    private ArrayList<Place> mData = new ArrayList<>();

    private Context mContext;
    private float mItemWidth;
    public PlaceAdapter(Context context) {
        mContext = context;
        float oneDp = Util.getOneDps(context);
        float screenWidth = Util.getScreenSize(context)[0];
        mItemWidth = (screenWidth - 32*oneDp)/2;
    }

    public void setData(List<Place> list) {
        mData.clear();
        if(list!=null) mData.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place,parent,false);
        v.getLayoutParams().width = (int) mItemWidth;
        return new ItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

     class ItemHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView mImage;

        @BindView(R.id.title)
        TextView mTitle;

        @BindView(R.id.description)
        TextView mDescription;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        private void bind(Place p) {
            Glide.with(mContext)
                    .load(p.mSource)
                    .into(mImage);

            mTitle.setText(p.getTitle());
            mDescription.setText(p.getDescription());
        }
    }
}
