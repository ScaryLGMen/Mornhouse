package com.roude.mornhouse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.roude.mornhouse.Dialog;
import com.roude.mornhouse.R;
import com.roude.mornhouse.model.Fact;
import java.util.List;

public class FactAdapter extends RecyclerView.Adapter<FactAdapter.FactViewHolder> {

    private Context context;
    private List<Fact> items;
    private RecyclerView recyclerView;

    public FactAdapter(Context context, List<Fact> items, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.items = items;
    }


    @NonNull
    @Override
    public FactAdapter.FactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View basketItems = LayoutInflater.from(context).inflate(R.layout.item_fact, parent, false);
        return  new FactAdapter.FactViewHolder(basketItems);
    }

    @Override
    public void onBindViewHolder(@NonNull FactAdapter.FactViewHolder holder, int position) {

        holder.body.setText(items.get(position).getText());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Dialog(context,items.get(position).getText()).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static final class FactViewHolder extends RecyclerView.ViewHolder{

        LinearLayout item;
        TextView body;

        public FactViewHolder(@NonNull View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.item_fact);
            body = itemView.findViewById(R.id.item_fact_body);

        }
    }
}

