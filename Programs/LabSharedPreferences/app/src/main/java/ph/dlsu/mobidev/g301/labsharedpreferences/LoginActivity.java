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
import android.widget.EditText;

public class LoginActivity extends Activity {

    EditText editName;
    Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        editName = (EditText) findViewById(R.id.nameEdit);
        buttonSubmit = (Button) findViewById(R.id.submitButton);

        editName.setText(PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString(MainActivity.SP_KEY_USERNAME, null));

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

                SharedPreferences.Editor spEditor = sp.edit();
                spEditor.putString(MainActivity.SP_KEY_USERNAME, editName.getText().toString());
                spEditor.commit();

                setResult(RESULT_OK);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
