package com.example.mystore;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CategoriesPage extends AppCompatActivity {
    SearchView search;
    ImageButton mic,camera;
    private ListView mListView;
    StoreDataBase db;
    FloatingActionButton cart;

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf = new MenuInflater(this);
        inf.inflate(R.menu.menu1,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case (R.id.pro):
                return true;
            case (R.id.cart):
                return true;
            case (R.id.logout):
                return true;

        }
        return true;
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_page);

        db = new StoreDataBase(this);
        mic = (ImageButton) findViewById(R.id.mic);
        camera = (ImageButton) findViewById(R.id.camera);
        search= findViewById(R.id.searchView);
        mListView = (ListView) findViewById(R.id.listview);
        cart=(FloatingActionButton)findViewById(R.id.cart);

cart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(CategoriesPage.this,Cart.class);
        startActivity(i);

    }
});

        ///// SEARCH BY TEXT /////
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                Intent i = new Intent(CategoriesPage.this, Products.class);
                i.putExtra("searchText",s);
                startActivity(i);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {



                return false;
            }

        });

        ///// SEARCH BY VOICE /////
        ActivityResultLauncher<Intent> MicLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                            Intent i = new Intent(CategoriesPage.this, Products.class);
                            i.putExtra("searchVoice",text.get(0));
                            startActivity(i);
                        }
                    }
                });

        mic.setOnClickListener(view -> {
            Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            MicLauncher.launch(i);
        });

        /////// SEARCH BY BARCODE //////
        ActivityResultLauncher<Intent> CamLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Bitmap image = (Bitmap) data.getExtras().get("data");
                            Intent i = new Intent(CategoriesPage.this, Products.class);
                            i.putExtra("searchBarcode",image);
                            startActivity(i);
                        }
                    }
                });

        camera.setOnClickListener(view -> {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            CamLauncher.launch(i);
        });



        MyAdapter adapter = new MyAdapter();
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CategoriesPage.this, Products.class);
                intent.putExtra("Cat_ID", i+1);
                startActivity(intent);
            }
        });



    }


    class MyAdapter extends BaseAdapter {

        Cursor cursor = db.retrieveCat();
        int[] colors = {R.color.white};

        @Override
        public int getCount() {
            return cursor.getCount();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            if(!cursor.isAfterLast()) {
                if (position % 2 == 0) {
                    convertView = getLayoutInflater().inflate(R.layout.card_left, parent, false);
                    TextView textView = convertView.findViewById(R.id.textview);
                    ImageView imageView = convertView.findViewById(R.id.imageview);
                    CardView card = convertView.findViewById(R.id.card_left);

                    card.setCardBackgroundColor(getResources().getColor(colors[position % colors.length]));
                    textView.setText(cursor.getString(1));
                    Bitmap bmp = BitmapFactory.decodeByteArray(cursor.getBlob(2), 0, cursor.getBlob(2).length);
                    imageView.setImageBitmap(bmp);
                }
                else {
                    convertView = getLayoutInflater().inflate(R.layout.card_left, parent, false);
                    TextView textView = convertView.findViewById(R.id.textview);
                    ImageView imageView = convertView.findViewById(R.id.imageview);
                    CardView card = convertView.findViewById(R.id.card_left);

                    card.setCardBackgroundColor(getResources().getColor(colors[position % colors.length]));
                    textView.setText(cursor.getString(1));
                    Bitmap bmp = BitmapFactory.decodeByteArray(cursor.getBlob(2), 0, cursor.getBlob(2).length);
                    imageView.setImageBitmap(bmp);
                }


                cursor.moveToNext();
            }
            return  convertView;
        }
    }
}