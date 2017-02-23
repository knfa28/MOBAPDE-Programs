package ph.dlsu.mobidev.g301.labintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textAge, textGender;
    Button btnAge, btnGender;

    final static int REQUEST_AGE = 0;
    final static String KEY_AGE = "age";
    final static int REQUEST_GENDER = 1;
    final static String KEY_GENDER = "gender";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textAge = (TextView) findViewById(R.id.ageText);
        textGender = (TextView) findViewById(R.id.genderText);
        btnAge = (Button) findViewById(R.id.ageButton);
        btnGender = (Button) findViewById(R.id.genderButton);

        btnGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = textGender.getText().toString();
                Intent explicit = new Intent();
                explicit.setClass(getBaseContext(), EditGender.class);
                explicit.putExtra(KEY_GENDER, value);
                startActivityForResult(explicit, REQUEST_GENDER);
            }
        });

        btnAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = textAge.getText().toString();
                Intent explicit = new Intent();
                explicit.setClass(getBaseContext(), EditAge.class);
                explicit.putExtra(KEY_AGE, value);
                startActivityForResult(explicit, REQUEST_AGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_AGE && resultCode == RESULT_OK){
            String age = data.getExtras().getString(KEY_AGE);
            textAge.setText(String.valueOf(age));
        }
        else if(requestCode == REQUEST_GENDER && resultCode == RESULT_OK){
            String gender = data.getExtras().getString(KEY_GENDER);
            textGender.setText(gender);
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
