package com.zhx.one.mvp.read.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.zhx.one.R;
import com.zhx.one.bean.EssayDetailEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author   :zhx
 * Create at 2016/12/5
 * Description:
 */
public class ReadDetailAdapter extends RecyclerView.Adapter {

    private static final int TYPE_READ_HEAD = 0;
    private static final int TYPE_READ_CONTENT = 1;
    private static final int HEADER_COUNT = 1;
    EssayDetailEntity mEssayDetailEntity;
    Context mContext;

    public ReadDetailAdapter(Context context,EssayDetailEntity essayDetailEntity){
        this.mContext = context;
        this.mEssayDetailEntity = essayDetailEntity;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        /*if (viewType == TYPE_READ_HEAD) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_read_head, parent, false);
            return new ReadHeadHolder(view);
        } else {*/
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            WebView mView = new WebView(parent.getContext());
            mView.setLayoutParams(lp);
            //view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_read_content, parent, false);
            return new ReadContentHolder(mView);
        //}
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        /*if(holder instanceof ReadHeadHolder){
            ((ReadHeadHolder)holder).mTvHpAuthor.setText(mEssayDetailEntity.getData().getHp_author());
            ((ReadHeadHolder)holder).mTvHpTitle.setText(mEssayDetailEntity.getData().getHp_title());
            ((ReadHeadHolder)holder).mTvMaketime.setText(mEssayDetailEntity.getData().getHp_makettime());
            Glide.with(mContext)
                    .load(mEssayDetailEntity.getData().getWeb_url())
                    .asBitmap()
                    .centerCrop()
                    .into(new BitmapImageViewTarget(((ReadHeadHolder) holder).mImgWeb) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            ((ReadHeadHolder)holder).mImgWeb.setImageDrawable(circularBitmapDrawable);
                        }
                    });
        }else {*/
            ((ReadContentHolder)holder).mWebview.getSettings().setJavaScriptEnabled(true);
            ((ReadContentHolder)holder).mWebview.loadData(mEssayDetailEntity.getData().getHp_content(),"text/html; charset=UTF-8", null);
            //((ReadContentHolder)holder).mTextView.setText(mEssayDetailEntity.getData().getHp_content());
        //}
    }

    @Override
    public int getItemCount() {
        //int count = HEADER_COUNT;

        return 2;
    }

    /*@Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_READ_HEAD;
        } else {
            return TYPE_READ_CONTENT;
        }
    }*/

    class ReadHeadHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_web)
        ImageView mImgWeb;
        @BindView(R.id.tv_maketime)
        TextView mTvMaketime;
        @BindView(R.id.tv_hp_author)
        TextView mTvHpAuthor;
        @BindView(R.id.tv_hp_title)
        TextView mTvHpTitle;


        public ReadHeadHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ReadContentHolder extends RecyclerView.ViewHolder {
        //@BindView(R.id.webview)
        WebView mWebview;
        //TextView mTextView;

        public ReadContentHolder(View itemView) {
            super(itemView);
            //mTextView = (TextView) itemView.findViewById(R.id.tv_hp_content);
            LayoutInflater inflater;
            inflater = LayoutInflater.from(mContext);
            RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.item_read_content, null);
            mWebview = (WebView) layout.findViewById(R.id.webview);
            //mWebview = (WebView) itemView.findViewById(R.id.webview);
        }
    }
}
