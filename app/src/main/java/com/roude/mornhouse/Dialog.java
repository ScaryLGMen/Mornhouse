package com.roude.mornhouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;

public class Dialog {

    private Context context;
    private String body;

    public Dialog(Context context, String body) {
        this.context = context;
        this.body = body;
    }

    public void show(){
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View promptsView = layoutInflater.inflate(R.layout.dialog_fact, null, false);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(promptsView);
        TextView text = promptsView.findViewById(R.id.dialog_fact_text);
        text.setText(body);
        alertDialogBuilder.show();
    }
}
