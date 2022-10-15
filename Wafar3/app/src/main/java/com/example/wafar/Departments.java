package com.example.wafar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class Departments extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    String s1[],s2[];

    int images[]={R.drawable.nikon1,R.drawable.iphones,R.drawable.shoes2,R.drawable.clothes,R.drawable.laptop,R.drawable.appliances,R.drawable.detergents};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);
        s1=getResources().getStringArray(R.array.departments);
        s2=getResources().getStringArray(R.array.Sale);

        recyclerView=findViewById(R.id.recyclerview);
        recyclerAdapter =new RecyclerAdapter(this,s1,s2,images);

        recyclerView.setAdapter(recyclerAdapter);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        DividerItemDecoration divider=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(divider);
        ImageSlider imageSlider=findViewById(R.id.slide);
        List<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTZvhdl1XsAR4mSgLJHX48UXHkFH0lWt7aeuQ&usqp=CAU",""));
        slideModels.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRG-WXare9ZgXw_DNVoEFTFStuZIklQw-Xg6w&usqp=CAU",""));
        slideModels.add(new SlideModel("https://cdn.shopify.com/s/files/1/0805/6249/files/referral.jpg?v=1505319706",""));
        slideModels.add(new SlideModel("https://i.pinimg.com/originals/de/5d/75/de5d759534542d37b43a67553f881718.jpg",""));
        imageSlider.setImageList(slideModels,true);


    }

}