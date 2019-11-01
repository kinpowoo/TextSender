package com.szsszwl.textsender.ui.send;

import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.szsszwl.textsender.R;
import com.szsszwl.textsender.adapter.TextItemAdapter;
import com.szsszwl.textsender.bean.Chat;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class SendFragment extends Fragment implements View.OnClickListener {

    private List<Chat> sendListStr;
    private RecyclerView recyclerView;
    private TextItemAdapter adapter;

    private EditText editText;
    private ImageView sendBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sender, container, false);
        recyclerView = root.findViewById(R.id.send_history);
        editText = root.findViewById(R.id.text_input_et);
        sendBtn = root.findViewById(R.id.send_btn);
        sendBtn.setOnClickListener(this);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sendListStr = new ArrayList<>();
        adapter = new TextItemAdapter(sendListStr,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        queryData();
    }

    private void queryData(){
        BmobQuery<Chat> query = new BmobQuery<>();
        query.setLimit(30).order("createdAt")
                .findObjects(new FindListener<Chat>() {
                    @Override
                    public void done(List<Chat> object, BmobException e) {
                        if (e == null) {
                            sendListStr.addAll(object);
                            adapter.updateInsert(sendListStr);
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        final String textSendStr = editText.getText().toString().trim();
        if(!TextUtils.isEmpty(textSendStr)){
            final Chat p2 = new Chat();
            p2.setMsg(textSendStr);
            p2.save(new SaveListener<String>() {
                @Override
                public void done(String objectId, BmobException e) {
                    if(e==null){
                        toast("添加数据成功，返回objectId为："+objectId);
                        sendListStr.add(p2);
                        adapter.updateInsert(sendListStr);
                        editText.setText("");
                    }else{
                        toast("创建数据失败：" + e.getMessage());
                    }
                }
            });
        }else{
            toast("发送内容不能为空");
        }
    }


    private void toast(String str){
        Toast.makeText(getActivity(),str,Toast.LENGTH_SHORT).show();
    }
}