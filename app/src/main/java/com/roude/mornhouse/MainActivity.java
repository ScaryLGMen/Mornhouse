package com.roude.mornhouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.roude.mornhouse.adapter.FactAdapter;
import com.roude.mornhouse.database.FactsDatabase;
import com.roude.mornhouse.model.Fact;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FactsDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room
                .databaseBuilder(getApplicationContext(), FactsDatabase.class, "database")
                .allowMainThreadQueries().build();


        EditText numberET = findViewById(R.id.numberET);
        Button select = findViewById(R.id.select_button);
        Button random = findViewById(R.id.random_button);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!numberET.getText().toString().equals("")){
                    new GetFact(
                            MainActivity.this,
                            Integer.parseInt(numberET.getText().toString()),
                            recyclerView).execute();
                }else{
                    Toast.makeText(MainActivity.this, "Enter number", Toast.LENGTH_LONG).show();
                }
            }
        });
        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetRandomFact(MainActivity.this, recyclerView).execute();
            }
        });

        setItemRecycler(recyclerView);
    }

    private void setItemRecycler(RecyclerView recyclerView) {

        List<Fact> items = db.getFactDao().getAll();
        Collections.reverse(items);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext(), RecyclerView.VERTICAL, false );
        recyclerView.setLayoutManager(layoutManager);
        FactAdapter adapter = new FactAdapter (recyclerView.getContext(), items, recyclerView);
        recyclerView.setAdapter(adapter);
    }
}