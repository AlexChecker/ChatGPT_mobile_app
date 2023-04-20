package com.example.chatgptapp;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GPTAdapter extends RecyclerView.Adapter<GPTAdapter.ViewHolder> {

    public List<Message> messages;

    public GPTAdapter(List<Message> m)
    {
        messages = m;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView user_text;
        private final ImageView user_pic;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            user_text = itemView.findViewById(R.id.userText);
            user_pic = itemView.findViewById(R.id.userIcon);
        }

        public TextView getUser_text() {
            return user_text;
        }

        public ImageView getUser_pic() {
            return user_pic;
        }
    }

    @NonNull
    @Override
    public GPTAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.response_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GPTAdapter.ViewHolder holder, int position) {
        if(messages.get(position).getRole()=="user")
        {
            holder.getUser_pic().setImageResource(R.drawable.baseline_account_circle_24);
            //holder.getUser_pic().setImageDrawable(ResourcesCompat.getDrawable(Resources.getSystem(),R.drawable.baseline_account_circle_24,null));
        }
        else holder.getUser_pic().setImageResource(R.mipmap.appicon); //holder.getUser_pic().setImageDrawable(ResourcesCompat.getDrawable(Resources.getSystem(),R.mipmap.appicon,null));
        holder.getUser_text().setText(messages.get(position).getContent());
    }

    @Override
    public int getItemCount()
    {
        return messages.size();
    }
}
