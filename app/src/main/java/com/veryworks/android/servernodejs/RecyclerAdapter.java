package com.veryworks.android.servernodejs;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by pc on 7/25/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class Holder extends RecyclerView.ViewHolder{
        private TextView textTitle;
        private TextView textDate;

        public Holder(View itemView) {
            super(itemView);
            textTitle = (TextView) itemView.findViewById(R.id.textTitle);
            textDate = (TextView) itemView.findViewById(R.id.textDate);
        }

        public void setTitle(String title){
            textTitle.setText(title);
        }

        public void setDate(String date){
            textDate.setText(date);
        }
    }
}
