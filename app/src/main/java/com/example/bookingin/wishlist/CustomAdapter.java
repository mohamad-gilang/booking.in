package com.example.bookingin.wishlist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingin.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList wishlist_id, wishlist_title, wishlist_budget;

    CustomAdapter(Activity activity, Context context, ArrayList wishlist_id, ArrayList wishlist_title, ArrayList wishlist_budget){
        this.activity = activity;
        this.context = context;
        this.wishlist_id = wishlist_id;
        this.wishlist_title = wishlist_title;
        this.wishlist_budget = wishlist_budget;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.wishlist_id_txt.setText(String.valueOf(wishlist_id.get(position)));
        holder.wishlist_title_txt.setText(String.valueOf(wishlist_title.get(position)));
        holder.wishlist_budget_txt.setText(String.valueOf(wishlist_budget.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityUpdate.class);
                intent.putExtra("id", String.valueOf(wishlist_id.get(position)));
                intent.putExtra("title", String.valueOf(wishlist_title.get(position)));
                intent.putExtra("budget", String.valueOf(wishlist_budget.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return wishlist_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView wishlist_id_txt, wishlist_title_txt, wishlist_budget_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            wishlist_id_txt = itemView.findViewById(R.id.wishlist_id_txt);
            wishlist_title_txt = itemView.findViewById(R.id.wishlist_title_txt);
            wishlist_budget_txt = itemView.findViewById(R.id.wishlist_budget_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
