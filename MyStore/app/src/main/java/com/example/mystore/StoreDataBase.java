package com.example.mystore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class StoreDataBase  extends SQLiteOpenHelper {
    private static String databaseName="userDB";
    SQLiteDatabase userDB;
    Context context;
    // SQLiteDatabase s;

    public StoreDataBase(Context context){
        super(context, databaseName,null,1);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Customers(CustID integer primary key autoincrement, CutName text not null ,Username text not null ," +
                "Password text not null , Gender text not null ,Birthdate text not null , Job text not null)");
        sqLiteDatabase.execSQL("create table Categories(CatID integer primary key autoincrement , CatName text not null, CatImage blob)");
        sqLiteDatabase.execSQL("create table Products(ProdID integer primary key autoincrement," +
                " ProdName text not null ,Price Integer , Quantity Integer ,Cat_ID Integer, ProdImage blob ,Foreign key (Cat_ID) references Categories (CatID))");
        sqLiteDatabase.execSQL("create table Orders(OrderID integer primary key autoincrement, OrderDate date,Cust_ID Integer, Address Text ,Foreign key (Cust_ID) references Customers (CustID) )");
        sqLiteDatabase.execSQL("create table OrderDetails(prodid1 integer   , orderid1 integer  , quantity integer not null,primary key(prodid1,orderid1),FOREIGN KEY(prodid1) REFERENCES Products(ProdID)  , FOREIGN KEY(orderid1) REFERENCES Orders(OrderID) )");

        InsertIntiCat("Dresses", R.drawable.dress1,sqLiteDatabase,context);
        InsertIntiCat("Shirts", R.drawable.tshirt1,sqLiteDatabase, context);
        InsertIntiCat("Pants", R.drawable.pants,sqLiteDatabase, context);
        InsertIntiCat("Shoes", R.drawable.shoes1,sqLiteDatabase,context);


        InsertIntiProduct("Black Dress", 200, 25, R.drawable.dress2,1,sqLiteDatabase,context);
        InsertIntiProduct("Yellow Dress", 200, 30, R.drawable.dress,1,sqLiteDatabase,context);
        InsertIntiProduct("High Quality Party Dresses Slim Elegant Women Evening Dress", 15, 30, R.drawable.dress1,1,sqLiteDatabase,context);
        InsertIntiProduct("Black Flower Women Evening Dress", 100, 30, R.drawable.dress4,1,sqLiteDatabase,context);


        InsertIntiProduct("Woman Black T-Shirt", 18, 10, R.drawable.tshirt5,2,sqLiteDatabase,context);
        InsertIntiProduct("Woman Black T-Shirt", 18, 20, R.drawable.tshirt2,2,sqLiteDatabase,context);
        InsertIntiProduct("Woman T-Shirt", 28, 8, R.drawable.tshirt3,2,sqLiteDatabase,context);
        InsertIntiProduct("Woman Blue T-Shirt", 10, 10, R.drawable.tshirt4,2,sqLiteDatabase,context);
        InsertIntiProduct("Black Shirt", 20, 20, R.drawable.tshirt1,2,sqLiteDatabase,context);



        InsertIntiProduct("Men's Straight-Fit 5-Pocket Stretch Twill Pant", 10, 20, R.drawable.pant2,3,sqLiteDatabase,context);
        InsertIntiProduct("Levi's Men's 514 Straight Fit Jeans", 20, 20, R.drawable.pant1,3,sqLiteDatabase,context);
        InsertIntiProduct("Men's Classic-Fit Wrinkle-Resistant Flat-Front Chino Pant", 18, 20, R.drawable.pant3,3,sqLiteDatabase,context);
        InsertIntiProduct("ILT - Pants BOYS Flat Front - Khaki", 19, 20, R.drawable.pant6,3,sqLiteDatabase,context);
        InsertIntiProduct("Cotton Pant", 19, 20, R.drawable.pant5,3,sqLiteDatabase,context);




        InsertIntiProduct("Adidas Shoes Adidas Shoe Kids Superstar Daddy Grade ", 18, 20, R.drawable.shoes6,4,sqLiteDatabase,context);
        InsertIntiProduct("2022 Nike Shoes", 38, 20, R.drawable.shoes2,4,sqLiteDatabase,context);
       // InsertIntiProduct("Round Toe Flock Ankle Strap Women Chunky Heel Pumps 4982", 20, 20, R.drawable.shoes4,4,sqLiteDatabase,context);
        InsertIntiProduct("Running Nike Shoes", 38, 20, R.drawable.shoes3,4,sqLiteDatabase,context);
        InsertIntiProduct("Gray Shoes", 38, 20, R.drawable.shoes8,4,sqLiteDatabase,context);
        InsertIntiProduct("Yellow Shoes", 38, 20, R.drawable.shoes5,4,sqLiteDatabase,context);
        InsertIntiProduct("Simon Nike Running Shoes", 38, 20, R.drawable.shoes11,4,sqLiteDatabase,context);
        InsertIntiProduct("Women black Shoes", 10, 20, R.drawable.shoes7,4,sqLiteDatabase,context);
        InsertIntiProduct("Elegent Women black Heels ", 20, 20, R.drawable.shoes9,4,sqLiteDatabase,context);
        InsertIntiProduct("Women black heels", 10, 20, R.drawable.shoes10,4,sqLiteDatabase,context);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists Customers");
        sqLiteDatabase.execSQL("drop table if exists Categories");
        sqLiteDatabase.execSQL("drop table if exists Products");
        sqLiteDatabase.execSQL("drop table if exists Orders");
        sqLiteDatabase.execSQL("drop table if exists OrderDetails");
        onCreate(sqLiteDatabase);
    }

    public void InsertIntiCat(String name, int drawable, SQLiteDatabase db, Context context){
        ContentValues value = new ContentValues();

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), drawable);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] img = stream.toByteArray();

        value.put("CatName", name);
        value.put("CatImage", img);
        db.insert("Categories", null, value);
    }
    ////// USED FUNCTION //////////
    public void InsertIntiProduct(String name, int price, int quantity,  int drawable, int Cat_ID, SQLiteDatabase db, Context context){
        ContentValues value = new ContentValues();

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), drawable);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] img = stream.toByteArray();

        value.put("ProdName", name);
        value.put("Price", price);
        value.put("Quantity", quantity);
        value.put("ProdImage", img);
        value.put("Cat_ID", Cat_ID);
        db.insert("Products", null, value);
    }

    public void CreateNewCustomer(String name, String email, String password, String gender , String BD,String job){
        userDB=getReadableDatabase();
        ContentValues row= new ContentValues();
        row.put("CutName",name);
        row.put("Username",email);
        row.put("Password",password);
        row.put("Gender",gender);
        row.put("Birthdate",BD);
        row.put("Job",job);
        userDB=getWritableDatabase();
        userDB.insert("Customers",null,row);
        //userDB.close();
    }

    public Cursor getcategoies()
    {
        userDB = this.getReadableDatabase();
        String  query = "select CatName from Categories";
        Cursor c = userDB.rawQuery(query, null);
        c.moveToFirst();
        //database.close();
        return c;
    }
    public void CreateNewCategory( String name )
    {
        userDB=getReadableDatabase();
        ContentValues row= new ContentValues();
        row.put("CatName",name);
        userDB=getWritableDatabase();
        userDB.insert("Categories",null,row);
        //userDB.close();
    }
    //function 3shan a3rf user mowgod wala fel login
    public boolean availableUser(String email, String password){
        userDB=getReadableDatabase();
        Cursor cursor = userDB.rawQuery("Select * from Customers where Username = ? and Password=?", new String[]{email,password});
        if(cursor.getCount()>0){
            return true;
        }
        return  false;
    }

    public String getPassword(String email){
        String res="";
        userDB=getReadableDatabase();
        Cursor cursor=userDB.rawQuery("Select Password from Customers where Username = ?",new String[]{email});
        if(cursor.getCount()<=0){
            return "this email not found";
        }else{

            if(cursor.moveToFirst()){
                res=cursor.getString(0);
            }

        }
        return  res;

    }
    public boolean availableEmail(String email){
        userDB=getReadableDatabase();
        Cursor cursor=userDB.rawQuery("Select * from Customers where Username = ?",new String[]{email});
        if(cursor.getCount()>0){
            return  true;
        }
        return false;
    }




    //    function 3shan a3rf el email da mowgod abl kda wala
    public boolean CheckUniqueEmail(String email)
    {
        userDB=getReadableDatabase();
        Cursor cursor = userDB.rawQuery("Select * from Customers where Username = ?", new String[]{email});
        if (cursor.getCount() > 0)
            return false;
        else
            return true;
    }

//function 3shan el regular expression ll email

    public boolean isValidEmail(String email){
        return true;
    }

    public Cursor Show_profile(int  cust_id)
    {
        userDB = this.getReadableDatabase();
        String query = "";
        Cursor c = userDB.rawQuery(query, null);
        c.moveToFirst();
        //database.close();
        return c;
    }

    //if text entered is category return all products in this category
    //if text is product return all products similar to product entered
    public Cursor search_by_text(String text)
    {

        userDB = this.getReadableDatabase();
        String query = "Select ProdName FROM Products where ProdName LIKE '%" + text + "%' ";
        Cursor c = userDB.rawQuery(query, null);
        c.moveToFirst();
        //database.close();
        return c;
    }
    //search by voice and barcode => figure out how will they be implemented

    //when a category is selected get category id and return all products in this category
    public List<Product_Details> getproducts()
    {
        userDB = this.getReadableDatabase();
        String query = "Select * FROM Products";
        Cursor c = userDB.rawQuery(query, null);
        c.moveToFirst();
        //database.close();
        List<Product_Details> products = new ArrayList<>();
        if(c.moveToFirst()) {
            do {
                Product_Details p = new Product_Details();
                p.setProd_Name(c.getString(c.getColumnIndexOrThrow("ProdName")));
                p.setPrice(c.getDouble(c.getColumnIndexOrThrow("Price")));
                p.setQuantity(c.getInt(c.getColumnIndexOrThrow("Quantity")));
                p.setCat_id(c.getInt(c.getColumnIndexOrThrow("Cat_ID")));
                p.setImage(c.getBlob(c.getColumnIndexOrThrow("ProdImage")));
                products.add(p);
            }while (c.moveToNext());
        }
        return products;
    }
    public List<Product_Details> getCatProducts(int Cat_Id) {
        userDB = this.getReadableDatabase();
        String query = "Select * FROM Products where Cat_ID like ?";
        String[] arg = {Integer.toString(Cat_Id)};
        Cursor c = userDB.rawQuery(query, arg);
        c.moveToFirst();
        //database.close();
        List<Product_Details> products = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                Product_Details p = new Product_Details();
                p.setProd_Name(c.getString(c.getColumnIndexOrThrow("ProdName")));
                p.setPrice(c.getDouble(c.getColumnIndexOrThrow("Price")));
                p.setQuantity(c.getInt(c.getColumnIndexOrThrow("Quantity")));
                p.setCat_id(c.getInt(c.getColumnIndexOrThrow("Cat_ID")));
                p.setImage(c.getBlob(c.getColumnIndexOrThrow("ProdImage")));
                products.add(p);
            } while (c.moveToNext());
        }
        return products;
    }

    ///////// USED FUNCTION ////////
    //// returns Categories data
    public Cursor retrieveCat() {

        userDB = this.getReadableDatabase();
        String query = "select * from Categories";
        Cursor c = userDB.rawQuery(query, null);
        c.moveToFirst();
        //database.close();

        return c;
    }

    //if pressed for the first time insert in orders to initiate order
    //then insert in orderdetails to link between products id and order id to use in showcart
    //quantity initially = 1
    // a new order is created if previous orders are purchased (new insertion in orders)
    public void add_to_cart(int product_id)
    {

    }
    // returns list of product ides from order details
    public Cursor show_cart(int order_id)
    {
        userDB = this.getReadableDatabase();
        String query = "";
        Cursor c = userDB.rawQuery(query, null);
        c.moveToFirst();
        //database.close();
        return c;
    }

    public Cursor getproductdetails (int product_id)
    {
        userDB = this.getReadableDatabase();
        String query = "";
        Cursor c = userDB.rawQuery(query, null);
        c.moveToFirst();
        //database.close();
        return c;
    }
    //delete from orderdetils
    //delete from orders if order contains only this product
    public void delete_product_from_cart(int product_id)
    {

    }
    // update quantity in products(remaining available quantity)
    // update quantity in orderdetails (quantity of specific product a customer ordered)
    // when purchase
    public void updatequantity(int product_id, int quantity)
    {

    }
    //return order date end address?? from orders plus final order details
    public Cursor showfinalorderdetails (int ord_id)
    {
        userDB = this.getReadableDatabase();
        String query = "";
        Cursor c = userDB.rawQuery(query, null);
        c.moveToFirst();
        //database.close();
        return c;
    }

}

