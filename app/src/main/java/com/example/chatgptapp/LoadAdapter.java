package com.example.chatgptapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class LoadAdapter extends RecyclerView.Adapter<LoadAdapter.ViewHolder> {
    List<String> names;
    LoadActivity l;

    public LoadAdapter(List<String> names, LoadActivity l)
    {
        this.names=names;
        this.l = l;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private final TextView nameLabel;
        private final ImageButton del;
        LoadActivity l;

        public ViewHolder(@NonNull View itemView,LoadActivity l) {
            super(itemView);
            nameLabel = itemView.findViewById(R.id.nameLabel);
            itemView.setOnClickListener(this);
            del = itemView.findViewById(R.id.deleteButton);
            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    l.l.deleteByName(nameLabel.getText().toString());
                    l.finish();
                }
            });
            this.l = l;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "there is nothing. Yet.", Toast.LENGTH_SHORT).show();
            Gson gson = new Gson();
            DialogLoader dl = new DialogLoader(v.getContext());
            List<Message> mess = gson.fromJson(dl.getByName(nameLabel.getText().toString()),new TypeToken<List<Message>>(){}.getType());
            MainActivity.adapter.messages = mess;
            MainActivity.messages = mess;
            MainActivity.adapter.notifyDataSetChanged();
            l.finish();
        }

        public TextView getNameLabel() {
            return nameLabel;
        }
    }

    @NonNull
    @Override
    public LoadAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.load_item,parent,false);
        return new ViewHolder(v,l);
    }

    @Override
    public void onBindViewHolder(@NonNull LoadAdapter.ViewHolder holder, int position) {
        holder.getNameLabel().setText(names.get(position));
    }

    @Override
    public int getItemCount() {
        return names.size();
    }
}
