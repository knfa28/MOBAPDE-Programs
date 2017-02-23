package com.example.kurt.labclient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;
    EditText editName;
    Button buttonConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = (TextView) findViewById(R.id.tv_result);
        editName = (EditText) findViewById(R.id.edit_name);
        buttonConnect = (Button) findViewById(R.id.btn_connect);

        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                new UrlHelper().execute(name);
            }
        });
    }

    public class UrlHelper extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String url = "http://10.100.212.3:8080/LabServer/RetrieveStudentServlet";
            OkHttpClient client = new OkHttpClient();

            /*
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("username", params[0])
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            */

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response;

            try {
                response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            ArrayList<Student> students = new ArrayList<Student>();

            try {
                JSONArray studentArray = new JSONArray();

                for( int i = 0; i < studentArray.length(); i++){
                    JSONObject student = studentArray.getJSONObject(i);
                    int id = student.getInt("id");
                    String name = student.getString("name");
                    students.add(new Student(id, name));
                }
            } catch(JSONException e) {
                e.printStackTrace();
            }

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
