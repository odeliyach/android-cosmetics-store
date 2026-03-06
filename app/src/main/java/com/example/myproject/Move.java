package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Move extends AppCompatActivity
{
    static ListView listView;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);

        listView=findViewById(R.id.orderList);

        customAdapter=new Move.CustomAdapter(DeleteLater.listItemsCart,this);
        listView.setAdapter(customAdapter);
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
    public void onStop()
    {
        super.onStop();
        startService(new Intent(this, MyCart.NotificationService.class));
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
        public Object getItem(int position)
        {
            return null;
        }

        @Override
        public long getItemId(int position)
        {
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


            //imageView.setImageResource(itemsModelFiltered.get(position).getImage());//gets and shows the items image
            imageView.setImageResource(R.drawable.armani);//gets and shows the items image
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