package com.roude.mornhouse;

import android.content.Context;
import android.os.AsyncTask;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.roude.mornhouse.adapter.FactAdapter;
import com.roude.mornhouse.database.FactsDatabase;
import com.roude.mornhouse.model.Fact;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;

public class GetRandomFact extends AsyncTask<String, Void, String> {



    private Context context;
    private FactsDatabase db;
    private RecyclerView recyclerView;

    public GetRandomFact(Context context, RecyclerView recyclerView){
        this.context = context;
        this.recyclerView = recyclerView;
        this.db = Room
                .databaseBuilder(context, FactsDatabase.class, "database")
                .allowMainThreadQueries().build();
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            String link = "http://numbersapi.com/random/math";
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoInput(true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();
            while(line != null){
                sb.append(line);
                break;
            }

            return sb.toString();

        }catch (Exception e){
            return new String("Exeption" + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        System.out.println(result);

        new Dialog(context,result).show();

        List<Fact> items = db.getFactDao().getAll();
        boolean isExists = false;
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getText().equals(result)){
                isExists = true;
            }
        }
        if(!isExists){
            items.add(new Fact(items.size() + 1, result));
            db.getFactDao().insertAll(new Fact(db.getFactDao().getAll().size() + 1, result));
        }

        setItemRecycler(items);
        db.close();

    }

    private void setItemRecycler(List<Fact> items) {
        Collections.reverse(items);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext(), RecyclerView.VERTICAL, false );
        recyclerView.setLayoutManager(layoutManager);
        FactAdapter adapter = new FactAdapter (recyclerView.getContext(), items, recyclerView);
        recyclerView.setAdapter(adapter);
    }

}

