package com.example.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.kitri.dto.Product;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    private Context ctx;
    private List<Product> items = new ArrayList<Product>();

    public MyAdapter(Context ctx) {
        super();
        this.ctx = ctx;
    }

    public List<Product> getItems() {
        return items;
    }

    public MyAdapter(Context ctx, List<Product> items) {
        super();
        this.ctx = ctx;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProductView productView = new ProductView(ctx, items.get(position));
        return productView;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
