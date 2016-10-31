package com.programotuojes.hellointernet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.programotuojes.hellointernet.R;
import com.programotuojes.hellointernet.activities.adapters.RecyclerTouchListener;
import com.programotuojes.hellointernet.activities.adapters.RecyclerViewAdapter;
import com.programotuojes.hellointernet.activities.adapters.Separator;
import com.programotuojes.hellointernet.web.Entry;

import java.util.ArrayList;
import java.util.List;

public class ArchiveFragment extends Fragment {

    private List<Entry> entries = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_archive, container, false);
        entries = ((MainActivity) getActivity()).getEntries();

        // Recycler view
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        Separator separator = new Separator(view.getContext());
        recyclerView.addItemDecoration(separator);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(entries);
        recyclerView.setAdapter(adapter);

        // Handling list clicks
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(view.getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(view.getContext(), EntryActivity.class);

                intent.putExtra("Title", entries.get(position).getTitle());
                intent.putExtra("Date", entries.get(position).getDate());
                intent.putExtra("Audio", entries.get(position).getAudio());
                intent.putExtra("ShowNotes", entries.get(position).getShowNotes());

                /* TODO: 10/31/2016 incomplete transition animation
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    View title = view.findViewById(R.id.title);
                    View date = view.findViewById(R.id.date);

                    title.setTransitionName("TitleTransition");
                    date.setTransitionName("DateTransition");

                    Pair<View, String> pair1 = Pair.create(title, title.getTransitionName());
                    Pair<View, String> pair2 = Pair.create(date, date.getTransitionName());

                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), pair1, pair2);
                    startActivity(intent, options.toBundle());
                } else
                */

                startActivity(intent);
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }

            @Override
            public void onLongClick(View view, int position) {

                // TODO: 10/29/2016 for shits and giggles
                Snackbar.make(view, "Fucking works", Snackbar.LENGTH_LONG)
                        .setAction("ye, sure", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(view.getContext(), "fuk u mang", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        }));

        return view;
    }
}