package com.szsszwl.textsender.ui.receive;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.szsszwl.textsender.R;
import com.szsszwl.textsender.adapter.TextItemAdapter;
import com.szsszwl.textsender.bean.Chat;

import java.util.ArrayList;
import java.util.List;

public class ReceiveFragment extends Fragment {

    private List<Chat> receiveStr;
    private RecyclerView recyclerView;
    private TextItemAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_receiver, container, false);
        recyclerView = root.findViewById(R.id.text_receiver);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        receiveStr = new ArrayList<>();
        adapter = new TextItemAdapter(receiveStr,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

}