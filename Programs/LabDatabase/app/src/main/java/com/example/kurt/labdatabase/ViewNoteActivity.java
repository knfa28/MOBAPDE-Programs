package com.example.kurt.labdatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ViewNoteActivity extends AppCompatActivity {

    Button buttonEdit;
    Button buttonDelete;
    Button buttonCancel;
    EditText editTitle;
    EditText editContent;
    DatabaseOpenHelper dbHelper;
    Note currentNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);

        buttonEdit = (Button) findViewById(R.id.editButton);
        buttonDelete = (Button) findViewById(R.id.deleteButton);
        buttonCancel = (Button) findViewById(R.id.cancelButton);
        editTitle = (EditText) findViewById(R.id.titleEdit);
        editContent = (EditText) findViewById(R.id.contentEdit);

        int id = getIntent().getExtras().getInt(Note.COLUMN_ID);

        dbHelper = new DatabaseOpenHelper(getBaseContext());
        currentNote = dbHelper.getNote(id);

        editTitle.setText(currentNote.getTitle());
        editContent.setText(currentNote.getContent());

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString();
                String content = editContent.getText().toString();

                currentNote.setTitle(title);
                currentNote.setContent(content);

                if (!title.isEmpty() && !content.isEmpty()) {
                    DatabaseOpenHelper db = new DatabaseOpenHelper(getBaseContext());
                    db.editNote(currentNote);
                    finish();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseOpenHelper db = new DatabaseOpenHelper(getBaseContext());
                db.deleteNote(currentNote);
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
