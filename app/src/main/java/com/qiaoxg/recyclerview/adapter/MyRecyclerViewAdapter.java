package com.qiaoxg.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiaoxg.recyclerview.R;
import com.qiaoxg.recyclerview.bean.MyRecyclerBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/5/26.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<MyRecyclerBean> results;

    //type
    public static final int TYPE_TYPE_HEAD = 0xff02;
    public static final int TYPE_TYPE1 = 0xff01;
    public static final int TYPE_TYPE2 = 0xff03;
    public static final int TYPE_TYPE3 = 0xff05;
    public static final int TYPE_TYPE4 = 0xff06;

    public MyRecyclerViewAdapter(Context context) {
        this.context = context;
        initData();
    }

    private void initData() {
        results = new ArrayList<>();
        int size = 20;
        for (int i = 0; i < size; i++) {
            MyRecyclerBean bean = new MyRecyclerBean();
            int type;
            String picUrl = null;
            String title = "";
            int labelId = 0;
            if (i == 0) {
                title = "足球新闻";
                type = TYPE_TYPE_HEAD;
                labelId = R.drawable.my_football;
            } else if (2 <= i && i <= 5) {
                title = "足球新闻" + i;
                type = TYPE_TYPE2;
                picUrl = "http://pic61.nipic.com/file/20150227/14862788_233538615303_2.jpg";
            } else if (i == 6) {
                title = "篮球新闻";
                type = TYPE_TYPE_HEAD;
                labelId = R.drawable.my_basketball;
            } else if (8 <= i && i <= 13) {
                title = "篮球新闻" + i;
                picUrl = "http://pic.58pic.com/58pic/15/15/06/00m58PICjQ9_1024.jpg";
                type = TYPE_TYPE3;
            } else if (14 == i) {
                title = "排球新闻";
                labelId = R.drawable.my_volleyball;
                type = TYPE_TYPE_HEAD;
            } else if (15 <= i && i < size) {
                title = "排球新闻";
                picUrl = "http://pica.nipic.com/2007-10-09/200710994020530_2.jpg";
                type = TYPE_TYPE4;
            } else {
                picUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495885409501&di=f36355e005df444c1c37910f401b4753&imgtype=0&src=http%3A%2F%2Fpic8.nipic.com%2F20100715%2F5369298_134205164697_2.jpg";
                type = TYPE_TYPE1;
            }
            bean.setType(type);
            bean.setPicUrl(picUrl);
            bean.setTitle(title);
            bean.setLabelId(labelId);
            results.add(bean);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TYPE1:
                return new AdHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_rv_ad, parent, false));
            case TYPE_TYPE_HEAD:
                return new HolderType2Head(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_rv_one, parent, false));
            case TYPE_TYPE2:
                return new HolderType3(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_rv_three, parent, false));
            case TYPE_TYPE3:
            case TYPE_TYPE4:
                return new HolderType2(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_rv_tow, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyRecyclerBean bean = results.get(position);
        if (holder instanceof HolderType2Head) {
            bindType2Head((HolderType2Head) holder, bean);
        } else if (holder instanceof HolderType2) {
            bindType2((HolderType2) holder, bean);
        } else if (holder instanceof HolderType3) {
            bindType3((HolderType3) holder, bean);
        } else if (holder instanceof AdHolder) {
            bindType1((AdHolder) holder, bean);
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    @Override
    public int getItemViewType(int position) {
        MyRecyclerBean bean = results.get(position);
        return bean.getType();
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        case TYPE_TYPE1:
                        case TYPE_TYPE_HEAD:
                        case TYPE_TYPE4:
                            return gridManager.getSpanCount();
                        case TYPE_TYPE2:
                            return 3;
                        case TYPE_TYPE3:
                            return 2;
                        default:
                            return 3;
                    }
                }
            });
        }
    }

    private void bindType1(AdHolder holder, MyRecyclerBean bean) {
        Picasso.with(context).load(bean.getPicUrl()).into(holder.AdPic);
    }

    private void bindType2Head(HolderType2Head holder, MyRecyclerBean bean) {
        holder.titleTv.setText(bean.getTitle());
        holder.pictureIv.setImageResource(bean.getLabelId());
    }

    private void bindType2(HolderType2 holder, MyRecyclerBean position) {
        Picasso.with(context).load(position.getPicUrl()).into(holder.item_img_type2);
        holder.titleTv.setText(position.getTitle());
    }

    private void bindType3(HolderType3 holder, MyRecyclerBean position) {
        Picasso.with(context).load(position.getPicUrl()).into(holder.item_img_type2);
        holder.titleTv.setText(position.getTitle());
    }


    public class HolderType2Head extends RecyclerView.ViewHolder {

        private TextView titleTv;
        private ImageView pictureIv;

        public HolderType2Head(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.ballName_tv);
            pictureIv = (ImageView) itemView.findViewById(R.id.ballHeader_iv);
        }
    }

    public class HolderType2 extends RecyclerView.ViewHolder {
        public ImageView item_img_type2;
        private TextView titleTv;

        public HolderType2(View itemView) {
            super(itemView);
            item_img_type2 = (ImageView) itemView.findViewById(R.id.picture_iv);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
        }
    }

    public class HolderType3 extends RecyclerView.ViewHolder {
        public ImageView item_img_type2;
        private TextView titleTv;

        public HolderType3(View itemView) {
            super(itemView);
            item_img_type2 = (ImageView) itemView.findViewById(R.id.picture_iv);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
        }
    }

    public class AdHolder extends RecyclerView.ViewHolder {
        public ImageView AdPic;

        public AdHolder(View itemView) {
            super(itemView);
            AdPic = (ImageView) itemView.findViewById(R.id.ad_iv);
        }
    }
}
