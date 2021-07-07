package sg.edu.rp.c346.id20022678.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert;
    Button btnGetTasks;
    TextView tvResults;
    ListView lvItems;
    ArrayList<Task> al;
    ArrayAdapter<Task> aa;
    EditText etName;
    EditText etDate;
    boolean asc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asc = true;

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        lvItems = findViewById(R.id.lv);
        etName = findViewById(R.id.editTextName);
        etDate = findViewById(R.id.editTextDate);

        al = new ArrayList<>();

        aa = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1,al);
        lvItems.setAdapter(aa);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                String name = etName.getText().toString();
                String date = etDate.getText().toString();
                dbh.insertTask(name,date);
            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                ArrayList<String> data = dbh.getTaskContent();

                String txt = "";
                for (int i = 0 ; i<data.size(); i++){
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);

                al.clear();
                if (asc == true){
                    al.addAll(dbh.getTasks(false));
                    asc = false;
                }
                else {
                    al.addAll(dbh.getTasks(true));
                    asc = true;
                }
                aa.notifyDataSetChanged();
            }
        });
    }
}