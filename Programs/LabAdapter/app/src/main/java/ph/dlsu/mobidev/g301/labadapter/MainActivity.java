package ph.dlsu.mobidev.g301.labadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText editJoJo;
    Button buttonJoJo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        editJoJo = (EditText) findViewById(R.id.jojoEdit);
        buttonJoJo= (Button) findViewById(R.id.jojoButton);

        ArrayList<String> jojoCharacters = new ArrayList<>();
        jojoCharacters.add("Jonathan Joestar");
        jojoCharacters.add("Dio Brando");
        jojoCharacters.add("Will A. Zeppeli");
        jojoCharacters.add("Robert E. Speedwagon");
        jojoCharacters.add("Joseph Joestar");
        jojoCharacters.add("Caesar Zeppeli");
        jojoCharacters.add("Rudol von Stroheim");
        jojoCharacters.add("Kars");
        jojoCharacters.add("Wamuu");
        jojoCharacters.add("Esidisi");

        final JoJoAdapter jojoAdapter = new JoJoAdapter(jojoCharacters);

        recyclerView.setAdapter(jojoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        buttonJoJo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jojoAdapter.addJoJo(editJoJo.getText().toString());
                editJoJo.setText("");
            }
        });
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
