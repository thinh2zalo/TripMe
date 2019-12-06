package com.hoaithi.tripme.yourplan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hoaithi.tripme.R;
import com.hoaithi.tripme.model.yourplan.BackSection;
import com.hoaithi.tripme.model.yourplan.ChartSection;
import com.hoaithi.tripme.model.yourplan.CostSection;
import com.hoaithi.tripme.model.yourplan.DateSection;
import com.hoaithi.tripme.model.yourplan.DrivingTimeline;
import com.hoaithi.tripme.model.yourplan.ItemCostDetail;
import com.hoaithi.tripme.model.yourplan.PlaceTimeline;
import com.hoaithi.tripme.model.yourplan.PlanObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YourPlanAdapter extends RecyclerView.Adapter<YourPlanAdapter.ItemHolder> {
    private ArrayList<PlanObject> mData = new ArrayList<>();

    public void setData(List<PlanObject> list) {
        mData.clear();
        if(list!=null) mData.addAll(list);
        notifyDataSetChanged();
    }

    public interface OnPlanObjectClickListener {
        void onPlanObjectClicked(PlanObject object, int postion);
    }

    public void setListener(OnPlanObjectClickListener listener) {
        mListener = listener;
    }

    private OnPlanObjectClickListener mListener;

    public void attachToRecyclerView(RecyclerView recyclerView) {
        recyclerView.setAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext(),2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(mData.get(position) instanceof CostSection) return 1;
                return 2;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false);
        switch (viewType) {
            case R.layout.item_plan_timeline_place:
                return new PlaceTimelineHolder(v);

            case R.layout.item_plan_timeline_driving:
                return new DrivingTimelineHolder(v);

            case R.layout.item_watch_timeline:
                return new ChartSectionHolder(v);
            case R.layout.item_plan_cost_section:
                return new CostSectionHolder(v);
            case R.layout.item_plan_cost_detail:
                return new ItemCostDetailHolder(v);
            case R.layout.item_go_back:
                return new BackSectionHolder(v);
            default:
                return new DateSectionHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        PlanObject object = mData.get(position);
        if(object instanceof DateSection)
             return R.layout.item_plan_date_section;
        else if(object instanceof PlaceTimeline)
            return R.layout.item_plan_timeline_place;
        else if(object instanceof DrivingTimeline)
            return R.layout.item_plan_timeline_driving;
        else if(object instanceof ChartSection)
            return R.layout.item_watch_timeline;
        else if(object instanceof CostSection)
            return R.layout.item_plan_cost_section;
        else if(object instanceof ItemCostDetail)
            return R.layout.item_plan_cost_detail;
        else if(object instanceof BackSection)
            return R.layout.item_go_back;
        else
        return R.layout.item_plan_date_section;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class BackSectionHolder extends ItemHolder<BackSection> {
        @OnClick(R.id.image)
        void onClick() {
            if(mListener!=null) mListener.onPlanObjectClicked(mData.get(getAdapterPosition()),getAdapterPosition());
        }
        public BackSectionHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public class ChartSectionHolder extends ItemHolder<ChartSection> {
        @OnClick({R.id.detail_button,R.id.detail_icon})
        void onClick() {
            if(mListener!=null) mListener.onPlanObjectClicked(mData.get(getAdapterPosition()),getAdapterPosition());
        }
        public ChartSectionHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void bind(ChartSection chartSection) {
        }
    }

    public class ItemCostDetailHolder extends ItemHolder<ItemCostDetail> {
        @BindView(R.id.icon)
        ImageView mIcon;

        @BindView(R.id.title)
        TextView mTitle;

        @BindView(R.id.description)
        TextView mDescription;

        @BindView(R.id.cost_text_view)
        TextView mCostTextView;

        @OnClick(R.id.root)
        void onClick() {
            if(mListener!=null)
                mListener.onPlanObjectClicked(mData.get(getAdapterPosition()),getAdapterPosition());
        }

        public ItemCostDetailHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void bind(ItemCostDetail itemCostDetail) {
            if(itemCostDetail.mType==ItemCostDetail.TYPE_ADD)
                mIcon.setImageResource(R.drawable.ic_add_blue_24dp);
            else mIcon.setImageResource(R.drawable.ic_remove_red_24dp);

            mTitle.setText(itemCostDetail.mTItle);
            mDescription.setText(itemCostDetail.mDescription);
            mCostTextView.setText(itemCostDetail.mCost);
        }
    }

    public class CostSectionHolder extends ItemHolder<CostSection> {
        @BindView(R.id.title)
        TextView mTitle;

        @BindView(R.id.description)
        TextView mDescription;

        @OnClick(R.id.root)
        void onClick() {
            if(mListener!=null)
                mListener.onPlanObjectClicked(mData.get(getAdapterPosition()),getAdapterPosition());
        }

        public CostSectionHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void bind(CostSection costSection) {
            mTitle.setText(costSection.mTitle);
            mDescription.setText(costSection.mDescription);
            itemView.setBackgroundResource(costSection.mBackColor);
        }
    }


    public class DrivingTimelineHolder extends ItemHolder<DrivingTimeline> {
        @BindView(R.id.icon)
        ImageView mIcon;

        @BindView(R.id.description)
        TextView mDescription;

        public DrivingTimelineHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bind(DrivingTimeline drivingTimeline) {
            if(drivingTimeline.mType == DrivingTimeline.TYPE_CAR)
                mIcon.setImageResource(R.drawable.car);
            else mIcon.setImageResource(R.drawable.motor);
            mDescription.setText(drivingTimeline.mDescription);
        }
    }

    public class PlaceTimelineHolder extends ItemHolder<PlaceTimeline> {
        @BindView(R.id.place_text_view)
        TextView mPlaceTextView;

        @BindView(R.id.cost_text_view)
        TextView mCostTextView;

        @BindView(R.id.time_from_text_view)
        TextView mTimeFrom;

        @BindView(R.id.time_to_text_view)
        TextView mTimeTo;

        @OnClick(R.id.menu_button)
        void clickMenu() {

        }

        public PlaceTimelineHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void bind(PlaceTimeline placeTimeline) {
            mTimeFrom.setText(placeTimeline.mTimeFrom);
            mTimeTo.setText(placeTimeline.mTimeTo);
            mPlaceTextView.setText(placeTimeline.mTitle);
            mCostTextView.setText(placeTimeline.mCost);
        }
    }

    public class DateSectionHolder extends ItemHolder<DateSection> {
        @BindView(R.id.title)
        TextView mTitle;

        @BindView(R.id.description)
        TextView mDescription;
        public DateSectionHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void bind(DateSection dateSection) {
            mTitle.setText(dateSection.mTitle);
            mDescription.setText(dateSection.mDescription);
        }
    }

    public abstract class ItemHolder<T extends PlanObject> extends RecyclerView.ViewHolder {

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(T t) {

        }
    }
}
