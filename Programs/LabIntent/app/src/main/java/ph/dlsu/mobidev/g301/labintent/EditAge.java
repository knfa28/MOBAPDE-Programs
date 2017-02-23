package ph.dlsu.mobidev.g301.labintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditAge extends AppCompatActivity {

    EditText editAge;
    Button btnCancel, btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_age);

        editAge = (EditText) findViewById(R.id.ageEdit);
        btnCancel = (Button) findViewById(R.id.cancelButton);
        btnSubmit = (Button) findViewById(R.id.submitButton);

        String value = getIntent().getExtras().getString(MainActivity.KEY_AGE);
        editAge.setText(value);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String age = editAge.getText().toString();
                Intent result = new Intent();
                result.putExtra(MainActivity.KEY_AGE, age);
                setResult(RESULT_OK, result);

                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_age, menu);
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
