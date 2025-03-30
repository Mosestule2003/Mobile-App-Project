package com.example.carveal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carvealapp.MessageAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessFragment#} factory method to
 * create an instance of this fragment.
 */
public class MessFragment extends Fragment {

    RecyclerView list;
    ArrayList<MessageModel> messageModels;

    int[] imageID = {R.drawable.avatar1, R.drawable.avatar2, R.drawable.avatar3,
                    R.drawable.avatar1, R.drawable.avatar2};
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mess, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        messageModels = new ArrayList<>();

        MessageThread messThread = new MessageThread();
        messThread.start();

        list = view.findViewById(R.id.recyclerView);
        LinearLayoutManager lin = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(lin);




        return view;
    }

    class MessageThread extends Thread {
        public void run() {
            String [] name = getResources().getStringArray(R.array.names);
            String [] last = getResources().getStringArray(R.array.messages_list);
            String [] time = getResources().getStringArray(R.array.time);

            for (int i = 0; i < name.length; i++) {
                messageModels.add(new MessageModel(name[i], last[i], time[i], imageID[i]));
            }

            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    MessageAdapter adapter = new MessageAdapter(getContext(), messageModels);
                    recyclerView.setAdapter(adapter);
                }
            });
        }
    }

}
