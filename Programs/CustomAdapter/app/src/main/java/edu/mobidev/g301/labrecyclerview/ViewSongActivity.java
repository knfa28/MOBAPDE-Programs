package edu.mobidev.g301.labrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ViewSongActivity extends AppCompatActivity {

    TextView tvTitle, tvLyrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_song);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvLyrics = (TextView) findViewById(R.id.tv_lyrics);

        Song s = (Song) getIntent().getExtras().get(MainActivity.KEY_SONG);

        tvTitle.setText(s.getTitle());
        tvLyrics.setText(s.getLyrics());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_song, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
