package com.example.kurt.customdatabase;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EditUnitActivity extends AppCompatActivity {

    EditText etUnitName, etEquivalence;
    Button buttonCancel, buttonSave, buttonDelete;
    DatabaseOpenHelper dbHelper;
    Unit currentUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_unit);

        etUnitName = (EditText) findViewById(R.id.et_unitname);
        etEquivalence = (EditText) findViewById(R.id.et_equivalence);
        buttonCancel = (Button) findViewById(R.id.button_cancel);
        buttonSave = (Button) findViewById(R.id.button_save);
        buttonDelete = (Button) findViewById(R.id.button_delete);

        int id = getIntent().getExtras().getInt(Unit.COLUMN_ID);

        dbHelper = new DatabaseOpenHelper(getBaseContext());
        currentUnit = dbHelper.getUnit(id);

        etUnitName.setText(currentUnit.getTitle());
        etEquivalence.setText(String.valueOf(currentUnit.getRate()));

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etUnitName.getText().toString();
                String content = etEquivalence.getText().toString();

                currentUnit.setTitle(title);
                currentUnit.setRate(Integer.parseInt(content));

                if (!title.isEmpty() && !content.isEmpty()) {
                    DatabaseOpenHelper db = new DatabaseOpenHelper(getBaseContext());
                    db.editUnit(currentUnit);
                    finish();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseOpenHelper db = new DatabaseOpenHelper(getBaseContext());
                db.deleteUnit(currentUnit);
                finish();
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

