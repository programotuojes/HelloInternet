package com.programotuojes.hellointernet.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.programotuojes.hellointernet.R;
import com.programotuojes.hellointernet.web.Player;

public class EntryActivity extends AppCompatActivity {

    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        Intent intent = getIntent();

        ImageButton playPauseButton = (ImageButton) findViewById(R.id.play_pause_button);
        TextView title = (TextView) findViewById(R.id.title);
        TextView date = (TextView) findViewById(R.id.date);
        WebView webView = (WebView) findViewById(R.id.web_view);

        title.setText(intent.getStringExtra("Title"));
        date.setText(intent.getStringExtra("Date"));
        player = new Player(intent.getStringExtra("Audio"), playPauseButton, getApplicationContext());
        webView.loadData(intent.getStringExtra("ShowNotes"), "text/html", "UTF-8");
        webView.setBackgroundColor(Color.TRANSPARENT);

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        Typeface openSans = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/OpenSans.ttf");
        toolbarTitle.setTypeface(openSans);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.togglePlayer();
            }
        });

        /* TODO: 10/31/2016 incomplete transition animation
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            title.setTransitionName("TitleTransition");
            date.setTransitionName("DateTransition");
        }
        */
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        player.stop();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
