package com.example.masterminds;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



// The adapter class for our recycler.
public class EffortAdapter extends RecyclerView.Adapter<EffortAdapter.ViewHolder> {


    private ArrayList<Effort> effortArrayList;
    private Context context;


    // Constructor of the class.
    public EffortAdapter(ArrayList<Effort> effortArrayList, Context context)
    {
        this.effortArrayList = effortArrayList;
        this.context = context;
    }

    // Inflating the layout with a recycler view.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.effort_item, parent, false);
        return new ViewHolder(view);
    }

    // Showing the stats for this particular recycler.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Effort effort = effortArrayList.get(position);
        holder.playerName.setText(effort.getPlayersName());
        holder.date.setText(effort.getDate());
        holder.points.setText(effort.getPoints());
    }

    @Override
    public int getItemCount() {
        return effortArrayList.size();
    }


    // Showing the results for this effort in a recycler.
    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView playerName, points, date, dialogtextView;
        Button ok;
        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            playerName = itemView.findViewById(R.id.playerNames);
            points = itemView.findViewById(R.id.points);
            date = itemView.findViewById(R.id.date);


            // By choosing the "DETAILS" button for this particular try,
            // will show a dialog, where the user can see all the stats
            // that are being stored.

            itemView.findViewById(R.id.details).setOnClickListener(View ->
            {
                Dialog dialog = new Dialog(itemView.getContext());
                dialog.setContentView(R.layout.dialog_stats);

                position = getAdapterPosition();
                Effort effort = effortArrayList.get(position);

                dialogtextView = dialog.findViewById(R.id.dialog_status);
                dialogtextView.setText(effort.getResult());

                dialogtextView = dialog.findViewById(R.id.dialog_playersname);
                dialogtextView.setText("Name: " + effort.getPlayersName());

                dialogtextView = dialog.findViewById(R.id.dialog_pointsganed);
                dialogtextView.setText("Points gained: " + effort.getPoints());

                dialogtextView = dialog.findViewById(R.id.dialog_totaltime);
                dialogtextView.setText("Total time: " + effort.getTime());

                dialogtextView = dialog.findViewById(R.id.dialog_date);
                dialogtextView.setText("Date: " + effort.getDate());

                ok = dialog.findViewById(R.id.dialog_ok_button);

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View v) {



                        dialog.dismiss();
                    }
                });

                dialog.show();


            });




        }
    }
}
