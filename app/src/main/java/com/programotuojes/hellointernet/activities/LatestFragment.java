package com.programotuojes.hellointernet.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.programotuojes.hellointernet.R;
import com.programotuojes.hellointernet.web.Entry;
import com.programotuojes.hellointernet.web.Player;

import java.util.List;

public class LatestFragment extends Fragment {

    private Player player;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest, container, false);

        List<Entry> entries = ((MainActivity) getActivity()).getEntries();

        ImageButton playPauseButton = (ImageButton) view.findViewById(R.id.play_pause_button);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView date = (TextView) view.findViewById(R.id.date);
        WebView webView = (WebView) view.findViewById(R.id.web_view);

        title.setText(entries.get(0).getTitle());
        date.setText(entries.get(0).getDate());
        player = new Player(entries.get(0).getAudio(), playPauseButton, getContext());
        webView.loadData(entries.get(0).getShowNotes(), "text/html", "UTF-8");
        webView.setBackgroundColor(Color.TRANSPARENT);

        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.togglePlayer();
            }
        });

        return view;
    }
}
