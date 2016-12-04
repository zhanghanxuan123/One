package com.zhx.one.mvp.read.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhx.one.R;
import com.zhx.one.bean.ReadingListEntity;
import com.zhx.one.mvp.hp.view.MainActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Author   :zhx
 * Create at 2016/12/4
 * Description:
 */
public class ReadListAdapter extends RecyclerView.Adapter<ReadListAdapter.ReadListViewHolder>{

    private ReadingListEntity mReadingListEntity;
    private Context mContext;
    private LayoutInflater inflater;
    private OnItemClickLitener mOnItemClickLitener;
    private List<ReadingListEntity.DataBean.EssayBean>mList;

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }



    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
    public ReadListAdapter(Context context, List<ReadingListEntity.DataBean.EssayBean>mDatas){
        mList = new ArrayList<>();
        this.mList = mDatas;
        this.mContext =context;
        inflater = LayoutInflater.from(context);
        }

    @Override
    public ReadListAdapter.ReadListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_read,parent,false);
        return new ReadListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ReadListAdapter.ReadListViewHolder holder, int position) {
        holder.tvUser_name.setText(mList.get(position).getAuthor().get(0).getUser_name());
        holder.tvHP_title.setText(mList.get(position).getHp_title());
        holder.tvGuide_word.setText(mList.get(position).getGuide_word());
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
