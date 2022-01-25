package com.example.krishiudyog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.Orderviewholder> {

    //String currentdate;
    //Calendar calendar;
    private Context cntxt;
    private List<Orders> orderslist;

    public OrderAdapter(Context cntxt, List<Orders> list) {
        this.cntxt = cntxt;
        this.orderslist = list;
    }

    @NonNull
    @Override
    public Orderviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(cntxt);
        View view=inflater.inflate(R.layout.orders_list,null);
        return new Orderviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Orderviewholder holder, int position) {
        Orders order=orderslist.get(position);
        holder.prodName.setText(order.getOpname());
        holder.consName.setText(order.getConsumername());
        holder.quantity.setText(order.getOqty());
        holder.textViewPrice.setText(order.getAmount());
        holder.orderStatus.setText("yet to despatch");
        holder.orderDate.setText(order.getDate());
        holder.contactno.setText(order.getConsumerno());
        final String number=order.getConsumerno();

        holder.contactno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+number));
                cntxt.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderslist.size();
    }

    public class Orderviewholder extends RecyclerView.ViewHolder {

        TextView prodName,consName,textViewPrice,orderStatus,orderDate,quantity,contactno;
        public Orderviewholder(View orderview) {
            super(orderview);

             prodName = (TextView) orderview.findViewById(R.id.prod_name);
            consName = (TextView) orderview.findViewById(R.id.cons_name);
            textViewPrice = (TextView) orderview.findViewById(R.id.price);
             orderStatus = (TextView) orderview.findViewById(R.id.status);
             quantity = (TextView) orderview.findViewById(R.id.qty);
             contactno= (TextView) orderview.findViewById(R.id.contactno);
            orderDate = (TextView) orderview.findViewById(R.id.date);

        }
    }
}
