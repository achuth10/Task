package com.example.achuth.task;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BroadcasterDash extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_broadcaster_dash, container, false);
        Pie pie = AnyChart.pie();
        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Children", 10000));
        data.add(new ValueDataEntry("Adults", 12000));
        data.add(new ValueDataEntry("Teenagers", 18000));

        pie.data(data);

        AnyChartView anyChartView = (AnyChartView) v.findViewById(R.id.any_chart_view);
        anyChartView.setChart(pie);
        return v;
    }

}
