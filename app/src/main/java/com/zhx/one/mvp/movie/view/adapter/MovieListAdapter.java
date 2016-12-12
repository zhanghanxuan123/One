package com.zhx.one.mvp.movie.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhx.one.R;
import com.zhx.one.bean.MovieListEntity;
import com.zhx.one.mvp.read.view.adapter.ReadListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author   :zhx
 * Create at 2016/12/12
 * Description:
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder>{

    private Context mContext;
    private List<MovieListEntity.DataBean>mList;
    private LayoutInflater inflater;
    private MovieListAdapter.OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }


    public void setOnItemClickLitener(MovieListAdapter.OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
    public  MovieListAdapter(Context context,List<MovieListEntity.DataBean>mdatas){
        this.mList = new ArrayList<>();
        this.mContext = context;
        this.mList = mdatas;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = inflater.inflate(R.layout.item_movie,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Glide.with(mContext)
                .load(mList.get(position).getCover())
                .into(holder.mImageView);
        Log.i("MovieListAdapter",mList.get(position).getCover());
        if (mOnItemClickLitener!=null){
            holder.mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.mImageView, pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.img_cover);
        }
    }
}
