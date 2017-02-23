package com.example.kurt.customdatabase;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddUnitActivity extends AppCompatActivity {

    EditText etUnitName, etEquivalence;
    Button buttonCancel, buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_unit);

        etUnitName = (EditText) findViewById(R.id.et_unitname);
        etEquivalence = (EditText) findViewById(R.id.et_equivalence);
        buttonCancel = (Button) findViewById(R.id.button_cancel);
        buttonAdd = (Button) findViewById(R.id.button_add);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etUnitName.getText().toString();
                String rate = etEquivalence.getText().toString();

                if(!title.isEmpty() && !rate.isEmpty()) {
                    DatabaseOpenHelper db = new DatabaseOpenHelper(getBaseContext());
                    Unit u = new Unit(title, Integer.parseInt(rate));
                    db.addUnit(u);
                    finish();
                }
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

