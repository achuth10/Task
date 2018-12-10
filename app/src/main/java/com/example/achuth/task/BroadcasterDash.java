package com.example.achuth.task;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import java.util.ArrayList;
import java.util.List;


public class BroadcasterDash extends Fragment {
    private ArrayList <String> values=new ArrayList<>();
    List<BarEntry> entries ;
    List<PieEntry> pieEntries;
    NestedScrollView nestedScrollView;
    private int[] setColors =new int[] {R.color.colorPrimary, R.color.yellow, R.color.colorAccent};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_broadcaster_dash, container, false);
        BarChart chart=v.findViewById(R.id.viewchart);
        PieChart pieChart=v.findViewById(R.id.piechart);
        nestedScrollView=v.findViewById(R.id.scroll);
        entries= new ArrayList<>();
        pieEntries=new ArrayList<>();
        XAxis xAxis = chart.getXAxis();
        values.add("March");
        values.add("April");
        values.add("June");
        values.add("July");
        values.add("August");
        values.add("September");
        xAxis.setValueFormatter(new MyXAxisValueFormatter(values));
        xAxis.setGranularity(1f);

        entries.add(new BarEntry(0f, 30f));
        entries.add(new BarEntry(1f, 80f));
        entries.add(new BarEntry(2f, 62f));
        entries.add(new BarEntry(3f, 55.5f));
        entries.add(new BarEntry(4f, 75f));
        entries.add(new BarEntry(5f, 60f));
        pieEntries.add(new PieEntry(20.2f, "Children"));
        pieEntries.add(new PieEntry(29.0f, "Adults"));
        pieEntries.add(new PieEntry(50.8f, "Teenagers"));

        chart.setVisibleXRangeMaximum(20);
        BarDataSet set = new BarDataSet(entries, "Viewers per thousand ");
        BarData data = new BarData(set);
        data.setBarWidth(0.9f);
        chart.getDescription().setText("6 month period");
        chart.setData(data);
        chart.setFitBars(true);
        chart.animateXY(3000, 3000,Easing.EaseInOutExpo); // animate horizontal and vertical 3000 milliseconds

        PieDataSet pieDataSet =new PieDataSet(pieEntries,"");
        pieDataSet.setColors(setColors,getContext());
        PieData pieData= new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setText("Lifetime Viewership");
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setCenterText("Viewer Demographic");
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);
        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.setDrawCenterText(true);
        pieChart.animateXY(3000,3000,Easing.EaseInOutExpo);

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY > oldScrollY) {
                    Log.i("scroll", "Scroll DOWN");
                }
                if (scrollY < oldScrollY) {
                    Log.i("scroll", "Scroll UP");
                }

                if (scrollY == 0) {
                    Log.i("scroll", "TOP SCROLL");
                    ((BroadcasterMain)getActivity()).setNavigationVisibility(true);
                }

                if (scrollY == ( v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight() )) {
                    Log.i("scroll", "BOTTOM SCROLL");
                    ((BroadcasterMain)getActivity()).setNavigationVisibility(false);
                }
            }
        });

        return v;
    }

}
