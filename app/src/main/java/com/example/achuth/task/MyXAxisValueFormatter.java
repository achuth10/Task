package com.example.achuth.task;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

public class MyXAxisValueFormatter implements IAxisValueFormatter {
    private ArrayList<String> values;
    MyXAxisValueFormatter(ArrayList<String> values)
    {
        this.values=values;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return values.get((int)value);
    }
}
