package com.szsszwl.textsender.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.szsszwl.textsender.R;
import com.szsszwl.textsender.bean.Chat;

import java.util.List;

public class TextItemAdapter extends RecyclerView.Adapter<TextItemAdapter.VH> {

    private List<Chat> data;
    private Context context;
    public TextItemAdapter(List<Chat> string, Context context) {
        this.data = string;
        this.context = context;
    }

    //更新插入
    public void updateInsert(List<Chat> list){
        int originSize = data.size()-1;
        originSize = originSize<0?0:originSize;
        this.data = list;
        notifyItemInserted(originSize);
    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_list_item,parent,false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        String str = data.get(position).getMsg();
        holder.textItem.setText(str);
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    class VH extends RecyclerView.ViewHolder{
        private TextView textItem;
        public VH(@NonNull View itemView) {
            super(itemView);
            textItem = itemView.findViewById(R.id.text_item_tv);
        }
    }
}
