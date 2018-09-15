package com.revisao.dh.android.aplicacaorevisao.controler.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.revisao.dh.android.aplicacaorevisao.R;
import com.revisao.dh.android.aplicacaorevisao.helpers.ClickListener;
import com.revisao.dh.android.aplicacaorevisao.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class  AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder> {

    List<Result> resultList;
    Context context;
    ClickListener clickListener;

    public AdapterCategory(List<Result> resultList, Context context){
        this.resultList = resultList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterCategory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflate.inflate(R.layout.item_recycler_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCategory.ViewHolder holder, int position) {
            Result result = resultList.get(position);
            holder.bind(result.getTitle(),result.getBackdropPath());
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        ImageView image;


        public ViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.titleTextView);
            this.image = itemView.findViewById(R.id.image);

            itemView.setTag(itemView);
            itemView.setOnClickListener(this);

        }
        public void bind(String title,String endPoint){
            this.title.setText(title);
            String path = "https://image.tmdb.org/t/p/w780";
            Picasso.get().load(path+endPoint).into(image);

        }

        public int getImageDrawableResId(String imageId ){
            Resources resources = context.getResources();
            return resources.getIdentifier(imageId, "drawable", context.getPackageName());
        }

        @Override
        public void onClick(View v) {
                if (clickListener != null) clickListener.onClick(v, getAdapterPosition());
        }
    }
}
