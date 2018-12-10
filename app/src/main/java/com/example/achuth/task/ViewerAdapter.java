package com.example.achuth.task;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class ViewerAdapter extends RecyclerView.Adapter <ViewerAdapter.ViewerHolder> {
    private int count = 0;
    private Context context;
    private ArrayList<Viewer> viewers;

    ViewerAdapter(Context context, ArrayList <Viewer> viewers) {
        this.context = context;
        this.viewers = viewers;
    }
    public ViewerAdapter.ViewerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewrowlayout,viewGroup, false);
        return new ViewerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewerAdapter.ViewerHolder viewerHolder, int i) {
        Viewer viewer=viewers.get(i);
        viewerHolder.setinfo(viewer);
//        viewerHolder.view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,"clicked",Toast.LENGTH_LONG).show();
//                Intent i =new Intent(context,StreamerPage.class);
//                Log.i("View pressed","Herere");
//                context.startActivity(i);
//            }
//        });
//        viewerHolder.dp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,"clicked",Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return viewers.size();
    }

    private void delete(int position) { //removes the row
        Toast.makeText(context, "Press again to block user", Toast.LENGTH_SHORT).show();
        if (++count >= 2) {
            Viewer viewer = viewers.get(position);
            viewers.remove(position);
            viewer.setBlocked(true);
            notifyItemRemoved(position);
            Toast.makeText(context, "Viewer blocked  for current session", Toast.LENGTH_LONG).show();
        }
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                count = 0;
            }
        }, 3000);
    }

     public class ViewerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView Name, Age,location;
         public View view;
        public ImageView dp;
        public ImageView menu;

        public ViewerHolder(final View itemView) {
            super(itemView);
            view=itemView;
            Name = itemView.findViewById(R.id.Name);
            Age = itemView.findViewById(R.id.Age);
            dp = itemView.findViewById(R.id.icon1);
            location=itemView.findViewById(R.id.location);
            menu = itemView.findViewById(R.id.streammenu);
            menu.setOnClickListener(this);
        }

        public void setinfo(Viewer Viewer) {
            if (!Viewer.isBlocked()) {
                Name.setText(Viewer.getName());
                Age.setText("Age:" + String.valueOf(Viewer.getAge()));
                location.setText(Viewer.getLocation());
                dp.setImageResource(Viewer.getDpid());

            }
        }

        @Override
        public void onClick(View v) {
            delete(getAdapterPosition());
        }
    }
}
