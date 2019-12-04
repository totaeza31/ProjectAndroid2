package com.example.helloandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class UserListAapter extends RecyclerView.Adapter<UserListAapter.ViewHolder> {

    public List<Users> usersList;

    public UserListAapter(List<Users> userList){

        this.usersList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

         holder.nameText.setText(usersList.get(position).getName());
         holder.statusText.setText(usersList.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       View mView;
       public TextView nameText;
       public TextView statusText;

       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           mView = itemView;

           nameText = (TextView) mView.findViewById(R.id.name_text);
           statusText =(TextView) mView.findViewById(R.id.status_text);
       }
   }
}
