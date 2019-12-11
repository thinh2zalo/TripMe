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
import com.hoaithi.tripme.model.yourplan.BackSection;
import com.hoaithi.tripme.model.yourplan.ChartSection;
import com.hoaithi.tripme.model.yourplan.CostSection;
import com.hoaithi.tripme.model.yourplan.ItemCostDetail;
import com.hoaithi.tripme.model.yourplan.PlanObject;

import java.util.ArrayList;

public class YourCostFragment extends Fragment implements YourPlanAdapter.OnPlanObjectClickListener {
    private static YourPlanAdapter mAdapter;
    public static ArrayList<PlanObject> list = new ArrayList<>();


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
        mAdapter.setListener(this);
        refreshData(false);
    }

    private static boolean mDetailShowing = false;

    public static void refreshData(boolean detal) {
        System.out.println("size "+list.size());
        if(list.size()==0) {

            list.add(new ChartSection());

            list.add(new CostSection(CostSection.COLOR_ORANGE,"Dự kiến", "1.500.000 đ"));
            list.add(new CostSection(CostSection.COLOR_GREEN,"Hiện có", "1.250.000 đ"));
            list.add(new CostSection(CostSection.COLOR_BLUE,"Thu", "1.500.000 đ"));
            list.add(new CostSection(CostSection.COLOR_RED,"Chi", "250.000 đ"));
        }
        mAdapter.setData(list);

    }

    @Override
    public void onPlanObjectClicked(PlanObject object, int postion) {
        if(object instanceof ChartSection &&!mDetailShowing)
            refreshData(false);
        else if(object instanceof BackSection  && mDetailShowing)
            refreshData(false);
    }
}
