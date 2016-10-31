package com.programotuojes.hellointernet.activities.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.programotuojes.hellointernet.R;
import com.programotuojes.hellointernet.web.Entry;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Entry> entries;

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.archive_list_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(entries.get(position).getTitle());
        holder.date.setText(entries.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public RecyclerViewAdapter(List<Entry> entries) {
        this.entries = entries;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView date;
        RelativeLayout relativeLayout;

        ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            date = (TextView) view.findViewById(R.id.date);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.entry);
        }
    }
}
