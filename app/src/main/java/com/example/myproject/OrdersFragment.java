package com.example.myproject;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersFragment extends Fragment
{
    ListView listView;
    CustomAdapter customAdapter;
    public OrdersFragment()
    {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_orders, container, false);

        listView=view.findViewById(R.id.orders);
        customAdapter=new OrdersFragment.CustomAdapter(MyCart.listOrders,this);
        listView.setAdapter(customAdapter);
        return  view;
    }
    public class CustomAdapter extends BaseAdapter implements Filterable
    {
        private List<Order> itemsModelList;
        private List<Order> itemsModelFiltered;
        private OrdersFragment contex;

        public CustomAdapter(List<Order> itemsModelList, OrdersFragment contex)
        {
            this.itemsModelList = itemsModelList;
            this.itemsModelFiltered = itemsModelList;
            this.contex = contex;
        }
        //a function that returns the list item size
        @Override
        public int getCount()
        {
            return itemsModelFiltered.size();
        }
        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent)
        {
            View view = getLayoutInflater().inflate(R.layout.row_items3, null);

            ImageView imageView = view.findViewById(R.id.imageView);
            TextView name = view.findViewById(R.id.costomerName);
            TextView price = view.findViewById(R.id.orderPrice);
            TextView numberOfItems = view.findViewById(R.id.numberOfItems);

           String s= Integer.toString(itemsModelFiltered.get(position).getTotalPrice());
            name.setText(itemsModelFiltered.get(position).getCostomerName());//gets and shows the items name
            price.setText(s);
            String s2= Integer.toString(itemsModelFiltered.get(position).getNumberOfItems());
            numberOfItems.setText(s2);
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
                protected void publishResults(CharSequence constraint, FilterResults results)
                {
                }
            };
            return filter;
        }
    }
}
