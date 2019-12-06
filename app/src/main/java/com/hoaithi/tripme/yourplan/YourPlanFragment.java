package com.hoaithi.tripme.yourplan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.hoaithi.tripme.R;
import com.hoaithi.tripme.model.yourplan.DateSection;
import com.hoaithi.tripme.model.yourplan.DrivingTimeline;
import com.hoaithi.tripme.model.yourplan.PlaceTimeline;
import com.hoaithi.tripme.model.yourplan.PlanObject;

import java.util.ArrayList;

public class YourPlanFragment extends Fragment {
    private YourPlanAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plan,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        mAdapter = new YourPlanAdapter();
        mAdapter.attachToRecyclerView(recyclerView);
        refreshData();
    }

    private void refreshData() {
        ArrayList<PlanObject> list = new ArrayList<>();
        list.add(new DateSection("Ngày 1","20/12"));

        list.add(new PlaceTimeline("5:00","5:30","Bến xe miền Đông","600.000 đ"));
        list.add(new DrivingTimeline(DrivingTimeline.TYPE_CAR,"7 giờ di chuyển"));

        list.add(new PlaceTimeline("12:30","13:00","Bến xe Đà Lạt","0 đ"));
        list.add(new DrivingTimeline(DrivingTimeline.TYPE_MOTOR,"30 phút di chuyển"));

        list.add(new PlaceTimeline("13:00","15:00","Homestay","800.000 đ"));

        list.add(new DateSection("Ngày 2","21/12"));
        list.add(new PlaceTimeline("5:00","5:30","Bến xe miền Đông","600.000 đ"));
        list.add(new DrivingTimeline(DrivingTimeline.TYPE_CAR,"7 giờ di chuyển"));

        list.add(new PlaceTimeline("12:30","13:00","Bến xe Đà Lạt","0 đ"));
        list.add(new DrivingTimeline(DrivingTimeline.TYPE_MOTOR,"30 phút di chuyển"));

        list.add(new PlaceTimeline("13:00","15:00","Homestay","800.000 đ"));
        list.add(new DateSection("Ngày 3","22/12"));
        list.add(new PlaceTimeline("5:00","5:30","Bến xe miền Đông","600.000 đ"));
        list.add(new DrivingTimeline(DrivingTimeline.TYPE_CAR,"7 giờ di chuyển"));

        list.add(new PlaceTimeline("12:30","13:00","Bến xe Đà Lạt","0 đ"));
        list.add(new DrivingTimeline(DrivingTimeline.TYPE_MOTOR,"30 phút di chuyển"));

        list.add(new PlaceTimeline("13:00","15:00","Homestay","800.000 đ"));
        list.add(new DateSection("Ngày 4","23/12"));
        list.add(new PlaceTimeline("5:00","5:30","Bến xe miền Đông","600.000 đ"));
        list.add(new DrivingTimeline(DrivingTimeline.TYPE_CAR,"7 giờ di chuyển"));

        list.add(new PlaceTimeline("12:30","13:00","Bến xe Đà Lạt","0 đ"));
        list.add(new DrivingTimeline(DrivingTimeline.TYPE_MOTOR,"30 phút di chuyển"));

        list.add(new PlaceTimeline("13:00","15:00","Homestay","800.000 đ"));
        mAdapter.setData(list);
    }
}
