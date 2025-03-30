package com.example.carveal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    Context context;
    ArrayList<ChatModel> chatModels;

    public ChatAdapter(Context context, ArrayList<ChatModel> chatModels) {
        this.context = context;
        this.chatModels = chatModels;
    }

    @NonNull
    @Override
    //giving a look to the rows
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.chat_item, parent, false);
        return new ChatViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        holder.avatar.setImageResource(chatModels.get(position).getMyAvatar());
        holder.chat.setText(chatModels.get(position).getMyChat());
    }

    @Override
    //total number of items
    public int getItemCount() {
        return chatModels.size();
    }
    public static class ChatViewHolder extends RecyclerView.ViewHolder{
        //grabbing the views from Row layout file
        //kinda like onCreate method
        ImageView avatar;
        TextView chat;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);

            avatar = itemView.findViewById(R.id.imageView);
            chat = itemView.findViewById(R.id.textView);

        }
    }
}
