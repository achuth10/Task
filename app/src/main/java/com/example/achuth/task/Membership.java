package com.example.achuth.task;


import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

public class Membership extends Fragment {
LinearLayout mem3,mem6,mem12;
Button upgrade;
String mem;
boolean mem3m,mem6m,mem12m;

@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_membership, container, false);
    mem3=v.findViewById(R.id.mem3);
    mem6=v.findViewById(R.id.mem6);
    mem12=v.findViewById(R.id.mem12);
    mem3m=mem6m=mem12m=false;
    upgrade=v.findViewById(R.id.upgrade);
    mem3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mem3.setBackgroundResource(R.drawable.selectedoption);
            mem3m=true;mem6m=false;mem12m=false;
            mem="3 month membership";
            mem6.setBackgroundResource(R.drawable.circle);
            mem12.setBackgroundResource(R.drawable.circle);
        }
    });
        mem6.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mem6.setBackgroundResource(R.drawable.selectedoption);
                mem3m=false;mem6m=true;mem12m=false;
                mem="6 month membership";
                mem3.setBackgroundResource(R.drawable.circle);
                mem12.setBackgroundResource(R.drawable.circle);
            }
        });
        mem12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mem12.setBackgroundResource(R.drawable.selectedoption);
                mem3m=false;mem6m=false;mem12m=true;
                mem="12 month membership";
                mem6.setBackgroundResource(R.drawable.circle);
                mem3.setBackgroundResource(R.drawable.circle);
            }
        });
            upgrade.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mem3m||mem6m||mem12m) {
//                        Toast.makeText(getContext(), "Selected: " + mem, Toast.LENGTH_SHORT).show();
                        openDialog();
                    }

                    else
                        Toast.makeText(getContext(),"No membership selected " ,Toast.LENGTH_SHORT).show();
                }
            });
    return v;
    }
    public void openDialog() {

        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        TextView title = new TextView(getContext());
        title.setText(R.string.premmem);
        title.setPadding(10, 10, 10, 10);   // Set Position
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.BLACK);
        title.setTextSize(30);
        alertDialog.setCustomTitle(title);
        TextView msg = new TextView(getContext());
        msg.setText("Are you sure you want to purchase  \n a " + mem + "?");
        msg.setTextSize(20);
        msg.setGravity(Gravity.CENTER_HORIZONTAL);
        msg.setTextColor(Color.BLACK);
        alertDialog.setView(msg);


        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,"OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext()," Congratulations! " + mem + " purchased",Toast.LENGTH_SHORT).show();
            }
        });

        new Dialog(getContext());
        alertDialog.show();
        final Button okBT = alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);
        LinearLayout.LayoutParams neutralBtnLP = (LinearLayout.LayoutParams) okBT.getLayoutParams();
        neutralBtnLP.gravity = Gravity.FILL_HORIZONTAL;
        okBT.setPadding(50, 10, 10, 10);   // Set Position
        okBT.setTextColor(Color.BLUE);
        okBT.setLayoutParams(neutralBtnLP);
        final Button cancelBT = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        LinearLayout.LayoutParams negBtnLP = (LinearLayout.LayoutParams) okBT.getLayoutParams();
        negBtnLP.gravity = Gravity.FILL_HORIZONTAL;
        cancelBT.setTextColor(Color.RED);
        cancelBT.setLayoutParams(negBtnLP);
    }
}

