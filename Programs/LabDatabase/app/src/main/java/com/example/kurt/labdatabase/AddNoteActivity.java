package com.example.kurt.labdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddNoteActivity extends AppCompatActivity {

    Button buttonAdd;
    Button buttonCancel;
    EditText editTitle;
    EditText editContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        buttonAdd = (Button) findViewById(R.id.addButton);
        buttonCancel = (Button) findViewById(R.id.cancelButton);
        editTitle = (EditText) findViewById(R.id.titleEdit);
        editContent = (EditText) findViewById(R.id.contentEdit);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString();
                String content = editContent.getText().toString();

                if(!title.isEmpty() && !content.isEmpty()) {
                    DatabaseOpenHelper db = new DatabaseOpenHelper(getBaseContext());
                    Note n = new Note(title, content);
                    db.addNote(n);
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
