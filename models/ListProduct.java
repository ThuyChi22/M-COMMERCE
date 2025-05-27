package com.tranduythanh.models;

import com.tranduythanh.k22411csampleproject.R;

import java.util.ArrayList;

public class ListProduct {
    ArrayList<Product> products;
    public ListProduct()
    {
        products= new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
    public void generate_sample_product_dataset(){
        Product p1=new Product(1,"Coca",100,10.0,1,"Coca",R.mipmap.coca);
        Product p2=new Product(2,"Pepsi",120,15.0,1,"Pepsi",R.mipmap.pepsi);
        Product p3=new Product(3,"7up",150,8.5,1,"7up",R.mipmap.sevenup);
        Product p4=new Product(4,"Fanta",130,9.5,1,"Fanta",R.mipmap.fanta);
        Product p5=new Product(5,"Sprite",110,10.5,1,"Sprite",R.mipmap.sprite);
        products.add(p1);
    }

    }
