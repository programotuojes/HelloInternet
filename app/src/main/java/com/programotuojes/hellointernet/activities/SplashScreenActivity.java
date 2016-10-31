package com.programotuojes.hellointernet.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.programotuojes.hellointernet.R;
import com.programotuojes.hellointernet.web.DownloadHTML;
import com.programotuojes.hellointernet.web.Entry;

import java.io.Serializable;
import java.util.List;

public class SplashScreenActivity extends AppCompatActivity {

    private static final String URL = "http://www.hellointernet.fm/podcast?format=rss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        TextView title = (TextView) findViewById(R.id.title);
        Typeface openSans = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/OpenSans.ttf");
        title.setTypeface(openSans);

        // Downloading archive
        if (isNetworkAvailable(getApplicationContext())) {
            new DownloadHTML() {
                @Override
                protected void onPostExecute(List<Entry> result) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Entries", (Serializable) result);
                    intent.putExtras(bundle);

                    startActivity(intent);
                }
            }.execute(URL);
        } else {
            Toast.makeText(getApplicationContext(), R.string.no_connention, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
