package com.example.kurt.customdatabase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    RecyclerView rvRight, rvLeft;
    TextView equals, tvRight;
    EditText etLeft;
    UnitCursorAdapter ucaRight, ucaLeft;

    DatabaseOpenHelper db;
    Unit unit1 = null;
    Unit unit2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLeft = (EditText) findViewById(R.id.et_unit_convert_left);
        equals = (TextView) findViewById(R.id.tv_equals);
        tvRight = (TextView) findViewById(R.id.tv_unit_convert_right);
        rvRight = (RecyclerView) findViewById(R.id.rv_right);
        rvLeft = (RecyclerView) findViewById(R.id.rv_left);

        db = new DatabaseOpenHelper(getBaseContext());
        db.addUnit(new Unit("Cornetto", 1));

        Cursor cursor = db.getCursorUnits();
        ucaRight = new UnitCursorAdapter(getBaseContext(), cursor);
        ucaLeft = new UnitCursorAdapter(getBaseContext(), cursor);

        rvRight.setAdapter(ucaLeft);
        rvRight.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        rvLeft.setAdapter(ucaRight);
        rvLeft.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        ucaRight.setmOnItemClickListener(new UnitCursorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                unit1 = db.getUnit(id);
                etLeft.setText("1");
                if (unit1 != null && unit2 != null)
                    getConversion();
            }

            @Override
            public void onItemLongClick(int id) {
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), EditUnitActivity.class);
                intent.putExtra(Unit.COLUMN_ID, id);
                startActivity(intent);
            }
        });

        ucaLeft.setmOnItemClickListener(new UnitCursorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                unit2 = db.getUnit(id);
                if (unit1!= null && unit2!=null)
                    getConversion();
            }

            @Override
            public void onItemLongClick(int id) {
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), EditUnitActivity.class);
                intent.putExtra(Unit.COLUMN_ID, id);
                startActivity(intent);
            }
        });

        etLeft.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0)
                    etLeft.setText("1");
                if (unit1 != null && unit2 != null)
                    getConversion();
            }
        });

        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getBaseContext(), AddUnitActivity.class);
                startActivity(intent);
            }
        });
    }

    public void getConversion(){
        int conv = (Integer.parseInt(etLeft.getText().toString()) * (unit1.getRate()/unit2.getRate()));
        tvRight.setText(String.valueOf(conv));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Cursor cursor = db.getCursorUnits();
        ucaRight.swapCursor(cursor);
        ucaLeft.swapCursor(cursor);
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
