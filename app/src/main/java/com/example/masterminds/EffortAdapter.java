package com.example.masterminds;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EffortAdapter extends RecyclerView.Adapter<EffortAdapter.ViewHolder> {


    private ArrayList<Effort> effortArrayList;
    private Context context;


    public EffortAdapter(ArrayList<Effort> effortArrayList, Context context)
    {
        this.effortArrayList = effortArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.effort_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Effort effort = effortArrayList.get(position);
        holder.playerName.setText(effort.getPlayersName());
        holder.date.setText(effort.getDate());
        holder.status.setText(effort.getResult());
    }

    @Override
    public int getItemCount() {
        return effortArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView playerName, status, date, dialogtextView;
        Button ok;
        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            playerName = itemView.findViewById(R.id.playerNames);
            status = itemView.findViewById(R.id.status);
            date = itemView.findViewById(R.id.date);




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
                dialogtextView.setText("Points gained: ");

                dialogtextView = dialog.findViewById(R.id.dialog_totaltime);
                dialogtextView.setText("Total time: ");

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
