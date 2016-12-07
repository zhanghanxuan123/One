package com.zhx.one.mvp.read.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.zhx.one.R;
import com.zhx.one.bean.CommentEntity;
import com.zhx.one.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author   :zhx
 * Create at 2016/12/5
 * Description:
 */
public class ReadDetailAdapter extends RecyclerView.Adapter<ReadDetailAdapter.ReadContentHolder> {


    Context mContext;

    private List<CommentEntity.DataBean> mList;

    public ReadDetailAdapter(Context context, List<CommentEntity.DataBean> mData) {
        this.mContext = context;
        //this.mEssayDetailEntity = essayDetailEntity;
        this.mList = new ArrayList<>();
        this.mList = mData;
        Log.i("ReadDetailAdapter", String.valueOf(mList.size()));
    }


    @Override
    public ReadContentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_read_comment, parent, false);
        return new ReadContentHolder(view);
    }

    @Override
    public void onBindViewHolder(final ReadContentHolder holder, int position) {
        holder.mTvUserName.setText(mList.get(position).getUser().getUser_name());
        String[] array = String.valueOf(DateUtils.stringToDate(mList.get(position).getInput_date())).split(" ");
        holder.mTvInputDate.setText(array[2] + " · " + array[1] + " · " + array[5]);
        holder.mTvContent.setText(mList.get(position).getContent());
        //Log.i("ReadDetailAdapter", String.valueOf(mList.get(position).getPraisenum()));
        holder.mTvPraisenum.setText(String.valueOf(mList.get(position).getPraisenum()));
        Glide.with(mContext)
                .load(mList.get(position).getUser().getWeb_url())
                .asBitmap()
                .centerCrop()
                .into(new BitmapImageViewTarget(holder.mImgUserUrl) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        holder.mImgUserUrl.setImageDrawable(circularBitmapDrawable);
                    }
                });

    }


    @Override
    public int getItemCount() {

        return mList.size();
    }


    class ReadContentHolder extends RecyclerView.ViewHolder {


        ImageView mImgUserUrl;
        TextView mTvInputDate;
        TextView mTvUserName;
        TextView mTvContent;
        TextView mTvPraisenum;

        public ReadContentHolder(View itemView) {
            super(itemView);
            mImgUserUrl = (ImageView) itemView.findViewById(R.id.img_user_url);
            mTvContent = (TextView) itemView.findViewById(R.id.tv_content);
            mTvInputDate = (TextView) itemView.findViewById(R.id.tv_input_date);
            mTvUserName = (TextView) itemView.findViewById(R.id.tv_user_name);
            mTvPraisenum = (TextView) itemView.findViewById(R.id.tv_praise);
        }
    }
}
