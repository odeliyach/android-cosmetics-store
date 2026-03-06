package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import me.bendik.simplerangeview.SimpleRangeView;

public class Hair extends AppCompatActivity
{
    //Hair product's list Activity
    ListView listView;
    int [] images={R.drawable.hair1,R.drawable.hair2,R.drawable.hair3,R.drawable.hair4,R.drawable.hair5,R.drawable.hair6,R.drawable.hair7,R.drawable.hair8,R.drawable.hair9,R.drawable.hair10,R.drawable.hair11,R.drawable.hair12,R.drawable.hair13};
    String[]names={"BaBylissPRO","Dyson","Dyson","L'ange","L'ange","BaBylissPRO","T3","Tyme","T3","Ghd","BaBylissPRO","Hot Tools","Hot Tools"};
    String[]desc={"Nano Titanium Ultra-Thin Straightening Iron","Supersonic Hair Dryer","One-Step Volumizer Hair Dryer","Le Volume 2-in-1 Volumizing Brush Dryer","Le Duo 360 Airflow Styler","Nano Titanium Dryer","SinglePass Curl Professional Ceramic Curling Iron","Iron Pro All-In-One Styling Tool","Cura Hair Dryer","Glide Smoothing Hot Brush","Nano Titanium Limited Edition Styling Set","24K Gold Extra Long 1-1/4'' Curling Iron","Professional Silicone Bristle Hot Brush Styler for Added Volume, 1 Inch"};
    int[]price={150,400,60,100,90,90,150,180,235,169,150,60,50};
    String[]priceString={"150$","400$","60$","100$","90$","90$","150$","180$","235$","169$","150$","60$","50$"};
    static int counter=0;
    int[]rating={4,3,5,3,4,2,5,4,3,2,5,4,3,3};
    String[]ids={"1","2","3","4","5","6","7","8","9","10","11","12","13"};
    int[]quantity={2,20,12,45,3,6,8,9,0,20,12,45,3,6,8};
    CustomAdapter customAdapter;
    List<ItemsModel> listItems=new ArrayList<>();
    Button byName,byCompany,byPrice;
    ImageView add;
    SimpleRangeView rangeBar;
    TextView textView;
    static int min,max;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hair);

        listView=findViewById(R.id.listview);
        for(int i=0;i<names.length;i++)//loop that adds items to the list view
        {
            ItemsModel itemsModel=new ItemsModel(names[i],desc[i],images[i],price[i],priceString[i],ids[i],quantity[i],rating[i]);
            listItems.add(itemsModel);
        }
        customAdapter=new CustomAdapter(listItems,this);
        listView.setAdapter(customAdapter);

        byName =(Button)findViewById(R.id.searchName);
        byCompany =(Button)findViewById(R.id.searchCompanyName);
        byPrice =(Button)findViewById(R.id.searchPrice);
        byName.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FancyToast.makeText(Hair.this,"You chose to search by nail polish description",FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                counter=1;
            }
        });//if name button was clicked counter equals 1;
        byCompany.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FancyToast.makeText(Hair.this,"You chose to search by company name",FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                counter=2;
            }
        });//if name button was clicked counter equals 2
        byPrice.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FancyToast.makeText(Hair.this,"You chose to search by price", FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                counter=3;
            }
        });//if name button was clicked counter equals 3
        rangeBar=findViewById(R.id.range_bar);
        textView=findViewById(R.id.text_view_range);

        rangeBar.setOnChangeRangeListener(new SimpleRangeView.OnChangeRangeListener()
        {
            @Override
            public void onRangeChanged(@NotNull SimpleRangeView simpleRangeView, int i, int i1)
            {
                textView.setText(String.valueOf(i*50)+"-"+String.valueOf(i1*50));
                min=i*50;
                max=i1*50;
                if(counter==3)
                    customAdapter.getFilter().filter("kk");
            }
        });
        rangeBar.setOnTrackRangeListener(new SimpleRangeView.OnTrackRangeListener()
        {
            @Override
            public void onStartRangeChanged(@NotNull SimpleRangeView simpleRangeView, int i)
            {
                textView.setText(String.valueOf(i*50));
            }
            @Override
            public void onEndRangeChanged(@NotNull SimpleRangeView simpleRangeView, int i)
            {
                textView.setText(String.valueOf(i*50));
            }
        });
        rangeBar.setOnRangeLabelsListener(new SimpleRangeView.OnRangeLabelsListener() {
            @Nullable
            @Override
            public String getLabelTextForPosition(@NotNull SimpleRangeView simpleRangeView, int i, @NotNull SimpleRangeView.State state) {
                return String.valueOf(i*50);
            }
        });
    }
    //a function that checks if the items price is in the range bar's range
    public  boolean price(int price)
    {
        if(min<price && max>price)
        {
            return true;
        }
        else
            return false;
    }
    //a function that creates the menu (search icon) and summons the filter
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

    public  class  CustomAdapter extends BaseAdapter implements Filterable
    {
        private  List<ItemsModel> itemsModelList;
        private  List<ItemsModel> itemsModelFiltered;
        private Context contex;

        public CustomAdapter(List<ItemsModel> itemsModelList,  Context contex)
        {
            this.itemsModelList = itemsModelList;
            this.itemsModelFiltered = itemsModelList;
            this.contex = contex;
        }
        @Override
        public int getCount() //a function that returns the list item size
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
            View view=getLayoutInflater().inflate(R.layout.row_items2,null);

            ImageView imageView=view.findViewById(R.id.imageView);
            TextView itemsName=view.findViewById(R.id.itemName);
            TextView itemDesc=view.findViewById(R.id.itemDesc);
            TextView itemPrice=view.findViewById(R.id.itemPrice);

            imageView.setImageResource(itemsModelFiltered.get(position).getImage());//gets and shows the items image
            itemsName.setText(itemsModelFiltered.get(position).getName());//gets and shows the items name
            itemDesc.setText(itemsModelFiltered.get(position).getDesc());//gets and shows the items company name
            itemPrice.setText(itemsModelFiltered.get(position).getPriceInString());
            //if a item in the list view is clicked move to another activity
            view.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    startActivity(new Intent(Hair.this,ItemViewActivity.class).putExtra("item",itemsModelFiltered.get(position)));
                }
            });
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
                    FilterResults filterResults=new FilterResults();

                    if(constraint==null|| constraint.length()==0)
                    {
                        filterResults.count=itemsModelList.size();
                        filterResults.values=itemsModelList;
                    }
                    else
                    {
                        String searchStr=constraint.toString().toLowerCase();
                        List<ItemsModel> resultDate= new ArrayList<>();
                        //if counter equals 1 search by name
                        if(counter==2)
                        {
                            //for each item in the list view
                            for (ItemsModel itemsModel : itemsModelList)
                            {
                                //if the string that was inserted equals the name
                                if (itemsModel.getName().toLowerCase().contains(searchStr))
                                {
                                    resultDate.add(itemsModel);//add the item to the filtered list view
                                }
                                filterResults.count = resultDate.size();
                                filterResults.values = resultDate;//get all the values of the item and add them
                            }
                        }
                        //if counter equals 2 search by company name
                        if(counter==1)
                        {
                            //for each item in the list view
                            for (ItemsModel itemsModel : itemsModelList)
                            {
                                //if the string that was inserted equals the company name
                                if (itemsModel.getDesc().toLowerCase().contains(searchStr))
                                {
                                    resultDate.add(itemsModel);//add the item to the filtered list view
                                }
                                filterResults.count = resultDate.size();
                                filterResults.values = resultDate;//get all the values of the item and add them
                            }
                        }
                        else
                        {
                            //for each item in the list view
                            for (ItemsModel itemsModel : itemsModelList)
                            {
                                //if the string that was inserted equals the company name
                                if (price(itemsModel.getPrice()))
                                {
                                    resultDate.add(itemsModel);//add the item to the filtered list view
                                }
                                filterResults.count = resultDate.size();
                                filterResults.values = resultDate;//get all the values of the item and add them
                            }
                        }
                    }
                    return filterResults;
                }
                @Override
                protected void publishResults(CharSequence constraint, FilterResults results)
                {
                    //returns the items that were filtered and put it into a list view
                    itemsModelFiltered= (List<ItemsModel>) results.values;
                    notifyDataSetChanged();
                }
            };
            return filter;
        }
    }
}
