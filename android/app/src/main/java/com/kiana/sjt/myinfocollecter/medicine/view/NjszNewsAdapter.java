package com.kiana.sjt.myinfocollecter.medicine.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kiana.sjt.fluttermyinfo.R;
import com.kiana.sjt.myinfocollecter.medicine.model.NjszrlModel;

import java.util.List;

/**
 * 南京市中adapter
 * Created by taodi on 2018/4/24.
 */

public class NjszNewsAdapter extends RecyclerView.Adapter<MyHolder> {

    private List<NjszrlModel.Newslist> dataList;

    private Context context;

    private OnItemClickListener onItemClickListener;

    public NjszNewsAdapter(Context context, List<NjszrlModel.Newslist> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_njsz, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        holder.titleTv.setText(dataList.get(position).getTitle());
        holder.dateTv.setText(dataList.get(position).getDate());
        if (null != onItemClickListener) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
