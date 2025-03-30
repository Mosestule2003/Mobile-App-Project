package com.example.carveal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    Context context;
    ArrayList<MessageModel> messageModels;

    public MessageAdapter(Context context, ArrayList<MessageModel> messageModels) {
        this.context = context;
        this.messageModels = messageModels;
    }

    @NonNull
    @Override
    //giving a look to the rows
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.message_item, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    //assigning values to the views
    //based on the position of the RecycleView
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        holder.name.setText(messageModels.get(position).getName());
        holder.avatar.setImageResource(messageModels.get(position).getAvatar());
        holder.last.setText(messageModels.get(position).getLastMessage());
        holder.time.setText(messageModels.get(position).getTime());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PrivateChat.class);
                intent.putExtra("name", messageModels.get(position).getName());
                intent.putExtra("avatar", messageModels.get(position).getAvatar());
                intent.putExtra("last", messageModels.get(position).getLastMessage());
                intent.putExtra("time", messageModels.get(position).getTime());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    //total number of items
    public int getItemCount() {
        return messageModels.size();
    }

    //inner class
    public static class MessageViewHolder extends RecyclerView.ViewHolder{
        //grabbing the views from Row layout file
        //kinda like onCreate method
        ImageView avatar;
        TextView name, last, time;
        CardView card;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.cardView);
            avatar = itemView.findViewById(R.id.avatar);
            name = itemView.findViewById(R.id.name);
            last = itemView.findViewById(R.id.lastMess);
            time = itemView.findViewById(R.id.time);
        }
    }
}
