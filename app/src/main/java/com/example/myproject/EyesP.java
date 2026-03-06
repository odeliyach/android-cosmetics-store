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

//import com.google.firebase.database.annotations.NotNull;

import com.shashank.sony.fancytoastlib.FancyToast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import me.bendik.simplerangeview.SimpleRangeView;

public class EyesP extends AppCompatActivity
{
    //Cosmetics eyes product's list Activity
    ListView listView;
    int [] images={R.drawable.eyes1,R.drawable.eyes2,R.drawable.eyes3,R.drawable.eyes4,R.drawable.eyes5,R.drawable.eyes6,R.drawable.eyes7,R.drawable.eyes8,R.drawable.eyes9,R.drawable.eyes10,R.drawable.eyes11,R.drawable.eyes12,R.drawable.eyes13,R.drawable.eyes14,R.drawable.eyes15};
    String[]names={"ULTA","Maybelline","Urban Decay Cosmetic","Morphe","Anastasia Beverly Hills","Urban Decay Cosmetics","Benefit Cosmetics","Tarte","Benefit Cosmetics","MAC","ColourPop","Too Faced","Maybelline","Anastasia Beverly Hills","Benefit Cosmetics"};
    String[]desc={ "Automatic Eyeliner","Lash Sensational Sky High Mascara","Naked Reloaded Eyeshadow Palette","The James Charles Palette","Dipbrow Pomade","24/7 Glide-On Eyeliner Pencil","They're Real! Lengthening Mascara","Tartelette 2 In Bloom Clay Eyeshadow Palette","Roller Lash Curling & Lifting Mascara","Pro Longwear Paint Pot Eyeshadow","Boudoir Noir Eyeshadow Palette","Better Than Sex Easy Glide Waterproof Liquid Eyeliner","Lash Sensational Mascara","Brow Powder Duo","Brow Microfilling Eyebrow Pen"};
    int[]price={8,12,44,39,21,22,25,39,26,23,16,22,8,23,25,28,30};
    String[]priceString={"8$","12$","44$","39$","21$","22$","25$","39$","26$","23$","16$","22$","8$","23$","25$","28$","30$"};
    static int counter=0;
    int[]rating={4,5,3,2,3,2,5,5,1,4,3,3,2,5,5,5,5,5};
    String[]ids={"eyes1","eyes2","eyes3","eyes4","eyes5","eyes6","eyes7","eyes8","eyes9","eyes10","eyes11","eyes12","eyes13","eyes14","eyes15"};
    int[]quantity={0,20,12,45,3,6,8,9,0,20,12,45,3,6,8,5,4,7,9,2,3,5};
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
        setContentView(R.layout.activity_eyes_p);

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
                FancyToast.makeText(EyesP.this,"You chose to search by nail polish description",FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                counter=1;
            }
        });//if name button was clicked counter equals 1;
        byCompany.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FancyToast.makeText(EyesP.this,"You chose to search by company name",FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                counter=2;
            }
        });//if name button was clicked counter equals 2
        byPrice.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FancyToast.makeText(EyesP.this,"You chose to search by price", FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
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
                textView.setText(String.valueOf(i*10)+"-"+String.valueOf(i1*10));
                min=i*10;
                max=i1*10;
                if(counter==3)
                    customAdapter.getFilter().filter("kk");
            }
        });
        rangeBar.setOnTrackRangeListener(new SimpleRangeView.OnTrackRangeListener()
        {
            @Override
            public void onStartRangeChanged(@NotNull SimpleRangeView simpleRangeView, int i)
            {
                textView.setText(String.valueOf(i*10));
            }
            @Override
            public void onEndRangeChanged(@NotNull SimpleRangeView simpleRangeView, int i)
            {
                textView.setText(String.valueOf(i-10));
            }
        });
        rangeBar.setOnRangeLabelsListener(new SimpleRangeView.OnRangeLabelsListener() {
            @Nullable
            @Override
            public String getLabelTextForPosition(@NotNull SimpleRangeView simpleRangeView, int i, @NotNull SimpleRangeView.State state) {
                return String.valueOf(i*10);
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
            //itemPrice.setText(itemsModelFiltered.get(position).getPrice());//gets and shows the items price

            //if a item in the list view is clicked move to another activity
            view.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    startActivity(new Intent(EyesP.this,ItemViewActivityNails.class).putExtra("item",itemsModelFiltered.get(position)));
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