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

public class WomanActivity extends AppCompatActivity
{
    //Woman perfume list Activity
    ListView listView;
    int [] images={R.drawable.rose,R.drawable.paradiso,R.drawable.rihana,R.drawable.versace,R.drawable.images,R.drawable.eclat,R.drawable.chloe,R.drawable.monparis,R.drawable.britny,R.drawable.calvinm,R.drawable.alienrefillable,R.drawable.hypnoticpoison,R.drawable.lavieestbelle,R.drawable.olympea,R.drawable.theone,R.drawable.si,R.drawable.euphoria,R.drawable.burberry,R.drawable.pradaw,R.drawable.hermes};
    String[]names={"rose","paradiso","reb'l Fleur","bright crystal", "mademoiselle Intense","eclat Darpege","chloé – Signature Eau De Parfum Spray","mon paris","fantasy","calvin klein women eau de toilette","alien refillable","hypnotic poison","la vie est belle","olympea","the one","si","euphoria","london","infusion d'iris","twilly d'hermes"};
    String[]desc={"carolina herrera","roberto cavalli", "rihanna", "versace", "chanel","lanvin","chloé ","yves saint laurent","britney spears","calvin klein","thierry mugler","christian dior","lancome","paco rabanne","dolce gabbana","giorgio armani","calvin klein","burberry","prada","hermes"};
    int[]price={80,70,40,125,250,90,100,120,55,35,200,250,280,115,320,250,250,130,150,190,290};
    String[]priceString={"80$","70$","40$","125&","250$","90$","100$","120$","55$","35$","200$","250$","280$","115$","320$","250$","250$","130$","150$","190$","290$"};

    int[]rating={4,5,3,3,4,2,5,1,4,4,3,2,2,5,4,0,5,3,4,2,1};
    String[]ids={"woman1","woman2","woman3","woman4","woman5","woman6","woman7","woman8","woman9","nails10","woman11","woman12","woman13","woman14","woman16","woman17","woman18","woman19","woman20","woman21","woman22","woman23","woman24","woman25","woman26","woman27"};
    int[]quantity={0,20,12,45,3,6,8,9,0,20,12,45,3,6,80,20,12,45,3,6,8,9,0,20,12,45,3,6,8};
    static int counter=0;
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
        setContentView(R.layout.activity_woman);

        listView=findViewById(R.id.listview);
        for(int i=0;i<names.length;i++)//loop that adds items to the list view
        {
            ItemsModel itemsModel=new ItemsModel(names[i],desc[i],images[i],price[i],priceString[i],ids[i],quantity[i],rating[i]);
            listItems.add(itemsModel);
        }
        customAdapter=new CustomAdapter(listItems,this);
        listView.setAdapter(customAdapter);

        byName =(Button)findViewById(R.id.searchName);//search by name button
        byCompany =(Button)findViewById(R.id.searchCompanyName);//search by description button
        byPrice =(Button)findViewById(R.id.searchPrice);//search by price button

        byName.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FancyToast.makeText(WomanActivity.this,"You chose to search by nail polish description",FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                counter=1;
            }
        });//if name button was clicked counter equals 1;
        byCompany.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FancyToast.makeText(WomanActivity.this,"You chose to search by company name",FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                counter=2;
            }
        });//if name button was clicked counter equals 2
        byPrice.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FancyToast.makeText(WomanActivity.this,"You chose to search by price", FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
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
        //constructor
        public CustomAdapter(List<ItemsModel> itemsModelList,Context contex)
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
                    startActivity(new Intent(WomanActivity.this,ItemViewActivity.class).putExtra("item",itemsModelFiltered.get(position)));
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
                        if(counter==1)
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
                        if(counter==2)
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
