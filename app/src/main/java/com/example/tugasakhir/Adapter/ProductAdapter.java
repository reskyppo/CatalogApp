package com.example.tugasakhir.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tugasakhir.Model.Product;
import com.example.tugasakhir.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private Context context;
    private List<Product> mProductList;

    public  ProductAdapter(Context context,List <Product> ProductList){
        this.context = context;
        this.mProductList = ProductList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from((parent.getContext())).inflate(R.layout.product_list, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextViewTitle.setText(mProductList.get(position).getTitle());
        holder.mTextViewPrice.setText("$ " + mProductList.get(position).getPrice());
        holder.mTextViewCategory.setText(mProductList.get(position).getCategory());
        Glide.with(holder.mimageViewImg.getContext())
                .load(mProductList.get(position).getImage())
                .into(holder.mimageViewImg);
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewId, mTextViewTitle, mTextViewPrice, mTextViewCategory;
        public ImageView mimageViewImg;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            mTextViewPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            mTextViewCategory = (TextView) itemView.findViewById(R.id.tvCategory);
            mimageViewImg = (ImageView) itemView.findViewById(R.id.ivImage);
        }
    }
}
