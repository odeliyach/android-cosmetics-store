package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.ui.Navigation;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MyCart extends AppCompatActivity
{
    static ListView listView;
    //static List<ItemsModel> listItemsCart=new ArrayList<>();
    CustomAdapter customAdapter;
    static ItemsModel itemsModel;
   // static int totalPrice=0,itemCount=0;
    TextView price,checkout,itemCountTextView;-+
    static int orderId=0;
    static List<Order> listOrders=new ArrayList<>();
    OrderDatabase myDb2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        myDb2= new ShoppingCartDb(getBaseContext());

        createNotificationChannel();
        listView=findViewById(R.id.orderList);
        price=(TextView) findViewById(R.id.checkout_amount);
        checkout=(TextView) findViewById(R.id.checkout);
        itemCountTextView = (TextView) findViewById(R.id.item_count);
        Intent intent=getIntent();
        if(intent.getExtras()!=null)
        {
            itemsModel= (ItemsModel) intent.getSerializableExtra("item");//take the image item
            int image=itemsModel.getImage();//take the image
            String name= itemsModel.getName();//take the  name
            String desc=itemsModel.getDesc();//take the company name
            String priceString=itemsModel.getPriceInString();//get price
            int price=itemsModel.getPrice();

            itemsModel=new ItemsModel(name,desc,image,price,priceString);

           /* ItemViewActivity.listItemsCart.add(itemsModel);
            ItemViewActivity.totalPrice +=itemsModel.getPrice();
            ItemViewActivity.itemCount++;*/
        }
        price.setText(String.valueOf(ItemViewActivity.totalPrice));
        itemCountTextView.setText(String.valueOf(ItemViewActivity.itemCount));

        customAdapter=new CustomAdapter(ItemViewActivity.listItemsCart,this);
        listView.setAdapter(customAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int which_item=position;
                new AlertDialog.Builder(MyCart.this).setIcon(android.R.drawable.ic_delete).setTitle("Are you sure?").setMessage("Do you want to delete this item?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ItemViewActivity.listItemsCart.remove(which_item);
                        customAdapter.notifyDataSetChanged();
                        //totalPrice-=listItemsCart
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
                new FancyGifDialog.Builder(MyCart.this)
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
                                onStop();
                                add(v);
                                all2(v);
                               // Order order=new Order(LoginActivity2.username,"ss",ItemViewActivity.totalPrice,MyCart.orderId,ItemViewActivity.itemCount,false);
                                MyCart.listOrders.add(order);
                                Intent intent = new Intent(MyCart.this, PaymentActivity.class);
                                startActivity(intent);
                            }
                        })
                        .OnNegativeClicked(new FancyGifDialogListener()
                        {
                            @Override
                            public void OnClick()
                            {
                                Toast.makeText(MyCart.this,"Cancel",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem menuItem=menu.findItem(R.id.search_view);
        SearchView searchView= (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                customAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        int id=item.getItemId();
        if(id==R.id.search_view)
        {
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
    //a function that creates the notification channel
    private void createNotificationChannel()
    {
        //Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            CharSequence name = getString(R.string.project_id);
            String description = getString(R.string.app_name);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("order", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    //a function that shows the user all the products that were saved
    public void all2(View view)
    {
        Cursor res = myDb2.getAllData3();
        if(res.getCount() == 0)
        {
            return;
        }
        while (res.moveToNext())
        {
            itemsModel=new ItemsModel(res.getString(1),res.getString(2),res.getInt(5),res.getInt(4),res.getString(3));

            long i= LoginActivity2.id_to_use;
            if(res.getLong(0)==(LoginActivity2.id_to_use))
            {
                listItemsCart.add(itemsModel);
            }
        }
    }
    // a function that adds the data to the table
    public void add(View view)
    {
        boolean isInserted = myDb2.insertDataShopping(itemsModel.getName(),itemsModel.getDesc(),itemsModel.getPriceInString(),itemsModel.getPrice(),itemsModel.getImage());
        if(isInserted == true)
        //if the data was inserted
        {
            FancyToast.makeText(this, "Data Inserted", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
        }
        else
            //if the data was not inserted
            FancyToast.makeText(this,"Data not Inserted",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
    }
    public void onStop()
    {
        super.onStop();
        ItemViewActivity.listItemsCart=new ArrayList<>();
        ItemViewActivity.itemCount=0;
        ItemViewActivity.totalPrice=0;
    startService(new Intent(this, NotificationService.class));
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
                protected void publishResults(CharSequence constraint, FilterResults results)
                {
                }
            };
            return filter;
        }
    }

    public static class NotificationService extends Service
    {
        Timer timer;
        TimerTask timerTask;
        String TAG = "Timers";
        int Your_X_SECS = 50;

        public NotificationService()
        {
            // No args constructor
        }

        @Override
        public IBinder onBind(Intent arg0)
        {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId)
        {
            Log.e(TAG, "onStartCommand");
            super.onStartCommand(intent, flags, startId);

            startTimer();
            return START_STICKY;
        }
        @Override
        public void onCreate()
        {
            Log.e(TAG, "onCreate");
        }
        @Override
        public void onDestroy()
        {
            Log.e(TAG, "onDestroy");
            stoptimertask();
            super.onDestroy();
        }
        //we are going to use a handler to be able to run in our TimerTask
        final Handler handler = new Handler();
        public void startTimer()
        {
            //set a new Timer
            timer = new Timer();
            //initialize the TimerTask's job
            initializeTimerTask();
            //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
            timer.schedule(timerTask, 200, Your_X_SECS * 10); //
            //timer.schedule(timerTask, 5000,1000); //
        }
        public void stoptimertask()
        {
            //stop the timer, if it's not already null
            if (timer != null)
            {
                timer.cancel();
                timer = null;
            }
        }
        public void initializeTimerTask()
        {
            timerTask = new TimerTask()
            {
                public void run()
                {
                    //use a handler to run a toast that shows the current timestamp
                    handler.post(new Runnable()
                    {
                        public void run()
                        {

                            NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext(), "order")
                                    .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                                    .setContentTitle("Order notification")
                                    .setContentText("Check out what's going on with your order!")
                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                                    // Set the intent that will fire when the user taps the notification
                            NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(getBaseContext());
                            notificationManagerCompat.notify(100,builder.build());
                        }
                    });
                }
            };
        }
    }
}


