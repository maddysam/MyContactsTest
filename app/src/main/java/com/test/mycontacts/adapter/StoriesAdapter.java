package com.test.mycontacts.adapter;/*
package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import model.ContactsModel;
import work.com.amit.R;


*/
/**
 * Created by hp on 6/21/2017.
 *//*

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder> {

    private List<ContactsModel> movies;
    private int rowLayout;
    private Context context;


    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
Button delete;
        public ContactViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            delete = (Button) v.findViewById(R.id.delete);


        }
    }

    public ContactsAdapter(List<ContactsModel> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public ContactsAdapter.ContactViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ContactViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ContactViewHolder holder, final int position) {

        holder.movieTitle.setText(movies.get(position).getName());



        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = movies.get(position).getName();
                String id = movies.get(position).getUid();

                movies.remove(holder.getPosition());

                notifyDataSetChanged();
            }



    });


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
*/
