package com.example.kurt.labconnection;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;
    Document document;
    Button btnSearch;
    EditText editSearch;
    String search;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = (TextView) findViewById(R.id.tv_result);
        editSearch = (EditText) findViewById(R.id.tv_search);
        btnSearch = (Button) findViewById(R.id.button);

        url = "https://en.m.wikipedia.org/wiki/";

        document = null;

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search = editSearch.getText().toString();
                url += search;
                tvResult.setText("Searching...");
                new UrlHelper().execute();
            }
        });
    }

    public class UrlHelper extends AsyncTask<Void, Void, String>{
        @Override
        protected String doInBackground(Void... params) {

            String cssSelector = "#mw-content-text div p";
            String result = "Nothing...";

            try{
                document = Jsoup.connect(url).get();
                Elements elements = document.select(cssSelector);

                result = "";
                for(Element e: elements) {
                    result += e.text();
                }
            } catch(IOException  e){
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tvResult.setText(s);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
