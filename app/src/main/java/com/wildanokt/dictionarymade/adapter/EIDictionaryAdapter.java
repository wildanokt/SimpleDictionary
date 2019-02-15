package com.wildanokt.dictionarymade.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wildanokt.dictionarymade.R;
import com.wildanokt.dictionarymade.activity.DictionaryDetailItem;
import com.wildanokt.dictionarymade.model.EIDictionaryModel;

import java.util.ArrayList;

public class EIDictionaryAdapter extends RecyclerView.Adapter<EIDictionaryAdapter.DictionaryViewHolder> {
    private ArrayList<EIDictionaryModel> mData = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public EIDictionaryAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public DictionaryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dictionary_items, viewGroup, false);
        return new DictionaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DictionaryViewHolder holder, final int i) {
        holder.tvHeader.setText(mData.get(i).getHeader());
        holder.tvContent.setText(mData.get(i).getContent());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),DictionaryDetailItem.class);
                intent.putExtra("HEADER", mData.get(i).getHeader());
                intent.putExtra("CONTENT", mData.get(i).getContent());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(ArrayList<EIDictionaryModel> mData){
        this.mData = mData;
        notifyDataSetChanged();
    }

    public class DictionaryViewHolder extends RecyclerView.ViewHolder {
        TextView tvHeader;
        TextView tvContent;

        public DictionaryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHeader = itemView.findViewById(R.id.tv_header);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }
}
