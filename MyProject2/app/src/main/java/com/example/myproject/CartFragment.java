package com.example.myproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static androidx.core.content.ContextCompat.getSystemService;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment
{
    CustomAdapter customAdapter;
    TextView price,checkout,itemCountTextView;
    public CartFragment()
    {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_cart, container, false);
        price=(TextView) view.findViewById(R.id.checkout_amount);
        checkout=(TextView) view.findViewById(R.id.checkout);
        itemCountTextView = (TextView) view.findViewById(R.id.item_count);
        MyCart.listView=view.findViewById(R.id.orderList2);
        price.setText(String.valueOf(MyCart.totalPrice));
        itemCountTextView.setText(String.valueOf(MyCart.itemCount));
        if(MyCart.itemCount!=0)
        {
            customAdapter = new CartFragment.CustomAdapter(MyCart.listItemsCart, getActivity());
            MyCart.listView.setAdapter(customAdapter);

            MyCart.listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    final int which_item = position;
                    new AlertDialog.Builder(getActivity()).setIcon(android.R.drawable.ic_delete).setTitle("Are you sure?").setMessage("Do you want to delete this item?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    MyCart.listItemsCart.remove(which_item);
                                    customAdapter.notifyDataSetChanged();
                                }
                            }).setNegativeButton("No", null).show();
                    return true;
                }
            });
            checkout.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    new FancyGifDialog.Builder(getContext())
                            .setTitle("Are you sure?")
                            .setMessage("Do you want to continue to payment?")
                            .setNegativeBtnText("No")
                            .setPositiveBtnBackground(R.color.pink)
                            .setPositiveBtnText("Yes")
                            .setNegativeBtnBackground(R.color.blue)
                            .setGifResource(R.drawable.gif)
                            .isCancellable(true)
                            .OnPositiveClicked(new FancyGifDialogListener()
                            {
                                @Override
                                public void OnClick()
                                {
                                    Intent intent = new Intent(getContext(), PaymentActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .OnNegativeClicked(new FancyGifDialogListener()
                            {
                                @Override
                                public void OnClick()
                                {
                                    Toast.makeText(getContext(),"Cancel",Toast.LENGTH_SHORT).show();
                                }
                            }).build();
                }
            });
        }
        return  view;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int id=item.getItemId();
        if(id==R.id.search_view) { return  true; }
        return super.onOptionsItemSelected(item);
    }
    public  class  CustomAdapter extends BaseAdapter implements Filterable
    {
        private List<ItemsModel> itemsModelList;
        private List<ItemsModel> itemsModelFiltered;
        private Context contex;

        public CustomAdapter(List<ItemsModel> itemsModelList, Context contex)
        {
            this.itemsModelList = itemsModelList;
            this.itemsModelFiltered = itemsModelList;
            this.contex = contex;
        }
        //a function that returns the list item size
        @Override
        public int getCount() { return itemsModelFiltered.size(); }
        @Override
        public Object getItem(int position) { return null; }
        @Override
        public long getItemId(int position) { return 0; }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent)
        {
            View view = getLayoutInflater().inflate(R.layout.row_items2, null);

            ImageView imageView = view.findViewById(R.id.imageView);
            TextView itemsName = view.findViewById(R.id.itemName);
            TextView itemDesc = view.findViewById(R.id.itemDesc);
            TextView itemPrice = view.findViewById(R.id.itemPrice);

            imageView.setImageResource(itemsModelFiltered.get(position).getImage());//gets and shows the items image
            itemsName.setText(itemsModelFiltered.get(position).getName());//gets and shows the items name
            itemDesc.setText(itemsModelFiltered.get(position).getDesc());//gets and shows the items company name
            itemPrice.setText(itemsModelFiltered.get(position).getPriceInString());

            return view;
        }
        @Override
        public Filter getFilter()
        {
            Filter filter=new Filter()
            {
                @Override
                protected FilterResults performFiltering(CharSequence constraint)
                {
                    return null;
                }
                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) { }
            };
            return filter;
        }
    }
}