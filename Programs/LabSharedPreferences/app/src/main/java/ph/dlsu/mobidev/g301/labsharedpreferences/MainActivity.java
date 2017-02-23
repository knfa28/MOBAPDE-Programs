package ph.dlsu.mobidev.g301.labsharedpreferences;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    final static String SP_KEY_USERNAME = "username";
    final static int LOGIN_REQUEST_CODE_ID = 0;

    TextView textGreeting;
    TextView buttonChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textGreeting = (TextView) findViewById(R.id.greetingText);
        buttonChange = (TextView) findViewById(R.id.changeButton);

        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), LoginActivity.class);

                startActivityForResult(intent, LOGIN_REQUEST_CODE_ID);
            }
        });

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String username = sp.getString(SP_KEY_USERNAME, null);

        if(username == null){
            Intent intent = new Intent();
            intent.setClass(getBaseContext(), LoginActivity.class);
            startActivityForResult(intent, LOGIN_REQUEST_CODE_ID);
        } else{
            textGreeting.setText("Wuzz good, " + username + "!");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == LOGIN_REQUEST_CODE_ID){
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            String username = sp.getString(SP_KEY_USERNAME, null);
            textGreeting.setText("Wuzz good, " + username + "!");
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
