package com.zhx.one.mvp.read.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhx.one.R;
import com.zhx.one.bean.ReadingListEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Author   :zhx
 * Create at 2016/12/4
 * Description:
 */
public class ReadListAdapter02 extends RecyclerView.Adapter<ReadListAdapter02.ReadListViewHolder>{

    private Context mContext;
    private LayoutInflater inflater;
    private OnItemClickLitener mOnItemClickLitener;
    private List<ReadingListEntity.DataBean.SerialBean>mList;

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }



    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
    public ReadListAdapter02(Context context, List<ReadingListEntity.DataBean.SerialBean>mDatas){
        mList = new ArrayList<>();
        this.mList = mDatas;
        this.mContext =context;
        inflater = LayoutInflater.from(context);
        }

    @Override
    public ReadListAdapter02.ReadListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_read,parent,false);
        return new ReadListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ReadListAdapter02.ReadListViewHolder holder, int position) {
        holder.tvUser_name.setText(mList.get(position).getAuthor().getUser_name());
        holder.tvHP_title.setText(mList.get(position).getTitle());
        holder.tvGuide_word.setText(mList.get(position).getExcerpt());
        if (mOnItemClickLitener!=null){
            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.mCardView, pos);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ReadListViewHolder extends RecyclerView.ViewHolder {

        private CardView mCardView;
        private TextView tvHP_title;
        private TextView tvUser_name;
        private TextView tvGuide_word;
        public ReadListViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.card);
            tvGuide_word = (TextView) itemView.findViewById(R.id.tv_guide_word);
            tvHP_title = (TextView) itemView.findViewById(R.id.tv_hp_title);
            tvUser_name = (TextView) itemView.findViewById(R.id.tv_user_name);
        }
    }
}
