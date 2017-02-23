package com.example.kurt.s20_kurtaquino;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    View buttonAdd;
    ExpenseSkeletonAdapter expenseAdapter;

    static TextView displayName;
    static TextView displayPrice;
    static TextView displayDate;
    Button buttonEdit;
    Button buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Step 1: create recycler view
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        displayName = (TextView) findViewById(R.id.expenseView);
        displayPrice = (TextView) findViewById(R.id.priceView);
        displayDate = (TextView) findViewById(R.id.dateView);
        buttonDelete = (Button) findViewById(R.id.deleteBtn);
        buttonEdit = (Button) findViewById(R.id.editButton);

        ArrayList<Expense> list = new ArrayList<>();
        list.add(new Expense("Bacsilog", "75"));
        list.add(new Expense("Iced Coffee", "25"));

        // Step 3: Create our adapter
        expenseAdapter = new ExpenseSkeletonAdapter(list);

        // Step 4: Attach adapter to UI
        recyclerView.setAdapter(expenseAdapter);

        // Step 5: Attach layout manager to UI
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        //setSong = (EditText) findViewById(R.id.et_song);
        buttonAdd = findViewById(R.id.addButton);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ExpenseDialog().show(getFragmentManager(), "");
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenseAdapter.deleteExpense(displayName.toString());
            }
        });
    }

    public static void setDetails(String name, String price, String date){
        displayName.setText(name);
        displayPrice.setText(price);
        displayDate.setText(date);
    }

    public void OnSubmitSelected(String nameText, String priceText){
        Expense temp = new Expense(nameText, priceText);
        expenseAdapter.addExpense(temp);
        Toast.makeText(getBaseContext(), "Added " + nameText + " - " + priceText + "php!", Toast.LENGTH_LONG).show();
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
