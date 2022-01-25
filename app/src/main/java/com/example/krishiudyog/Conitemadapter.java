package com.example.krishiudyog;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class Conitemadapter extends RecyclerView.Adapter<Conitemadapter.Conitemviewholder> {
    private OnItemClickListener mlistener;
    private Context cntxt;
    private List<Itemslist> conitemslist;
    String Proname;

    public  Conitemadapter(){}
    public Conitemadapter(Context cntxt, List<Itemslist> conitemslist) {
        this.cntxt = cntxt;
        this.conitemslist = conitemslist;
    }

    @NonNull
    @Override
    public Conitemviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(cntxt);
        View view=inflater.inflate(R.layout.consumer_items,null);
        return new Conitemadapter.Conitemviewholder(view,mlistener);
    }
    public void passpname(String proname){
        this.Proname=proname;
    }


    @Override
    public void onBindViewHolder(@NonNull Conitemviewholder holder, int position) {

        HashMap<String,Integer> hashMap=new HashMap<String, Integer>();

        hashMap.put("ONION",R.mipmap.bigonion);
        hashMap.put("POTATO",R.mipmap.bigpotato);
        hashMap.put("CABBAGE",R.mipmap.bigcabbage);
        hashMap.put("RICE",R.mipmap.bigrice);
        hashMap.put("WHEAT",R.mipmap.bigwheat);
        hashMap.put("CAULIFLOWER",R.mipmap.bigcauliflower);
        hashMap.put("BRINJAL",R.mipmap.bigbrinjal);
        hashMap.put("CARROT",R.mipmap.bigcarrot);
        hashMap.put("RADISH",R.mipmap.bigradish);
        hashMap.put("GREEN BEANS",R.mipmap.biggreenbeans);
        hashMap.put("CAPSICUM",R.mipmap.bigcapsicum);

        final Itemslist temp=conitemslist.get(position);

        Itemslist i=new Itemslist();
        i=conitemslist.get(position);

        holder.itemimage.setImageDrawable(cntxt.getResources().getDrawable(hashMap.get(i.getProductname())));
        holder.itemname.setText(i.getProductname());
        holder.itemprice.setText(i.getRate());
        holder.Place.setText(i.getLocation());
        holder.ProducerName.setText(i.getPname());

        holder.itemimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(cntxt,Display_item.class);
                intent.putExtra("productname",temp.getProductname());
                intent.putExtra("producername",temp.getPname());
                intent.putExtra("availablequantity",temp.getQuantity());
                intent.putExtra("price",temp.getRate());
                intent.putExtra("contactno",temp.getContact());
                intent.putExtra("location",temp.getLocation());
                intent.putExtra("quality",temp.getQuality());
                intent.putExtra("producerid",temp.getPid());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                cntxt.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return conitemslist.size();
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnClickListener(OnItemClickListener listener){
        mlistener=listener;
    }

    public static class Conitemviewholder extends RecyclerView.ViewHolder{

        ImageView itemimage;
        TextView itemname,itemprice,ProducerName,Place;

        public Conitemviewholder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                    {
                        int position=getLayoutPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            itemimage=itemView.findViewById(R.id.itemimage);
            itemname=itemView.findViewById(R.id.itemname);
            itemprice=itemView.findViewById(R.id.itemprice);
            ProducerName=itemView.findViewById(R.id.ProducerName);
            Place=itemView.findViewById(R.id.Place);


        }

    }

}
