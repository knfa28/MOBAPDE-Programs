package com.example.kurt.labdatabase;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button buttonAddNote;
    RecyclerView recyclerNotes;
    DatabaseOpenHelper db;
    NoteCursorAdapter noteCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAddNote = (Button) findViewById(R.id.addNoteButton);
        recyclerNotes = (RecyclerView) findViewById(R.id.recyclerView);

        db = new DatabaseOpenHelper(getBaseContext());
        db.addNote(new Note("Title", "Content"));

        Cursor cursor = db.getCursorNotes();
        noteCursorAdapter = new NoteCursorAdapter(getBaseContext(), cursor);

        recyclerNotes.setAdapter(noteCursorAdapter);
        recyclerNotes.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), AddNoteActivity.class);
                startActivity(intent);
            }
        });

        noteCursorAdapter.setmOnItemClickListener(new NoteCursorAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int id) {
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), ViewNoteActivity.class);
                intent.putExtra(Note.COLUMN_ID, id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Cursor cursor = db.getCursorNotes();
        noteCursorAdapter.swapCursor(cursor);
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
