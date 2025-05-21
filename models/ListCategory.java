package com.tranduythanh.models;

import java.util.ArrayList;

public class ListCategory {
    private ArrayList<Category> categories;

    public ListCategory() {
        categories=new ArrayList<>();
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
    public void generate_sample_product_dataset(){
        Category c1=new Category(1, "Cate1", 110);
        Product p1=new Product(1,"Product1",10,35.5,1,"Đồ Trung Quốc",101);
        c1.addProduct(p1);
        categories.add(c1);
        Category c2 = new Category(2, "Cate2", 111);
        Product p2 = new Product(2, "Product2", 15, 40.0, 2, "Hàng Nhật nội địa", 102);
        c2.addProduct(p2);
        categories.add(c2);

        Category c3 = new Category(3, "Cate3", 112);
        Product p3 = new Product(3, "Product3", 8, 55.5, 3, "Đồ Mỹ chính hãng", 103);
        c3.addProduct(p3);
        categories.add(c3);

        Category c4 = new Category(4, "Cate4", 113);
        Product p4 = new Product(4, "Product4", 20, 25.0, 4, "Hàng Thái Lan", 104);
        c4.addProduct(p4);
        categories.add(c4);

        Category c5 = new Category(5, "Cate5", 114);
        Product p5 = new Product(5, "Product5", 12, 60.0, 5, "Đồ Hàn Quốc", 105);
        c5.addProduct(p5);
        categories.add(c5);

        Category c6 = new Category(6, "Cate6", 115);
        Product p6 = new Product(6, "Product6", 18, 22.5, 6, "Đồ handmade", 106);
        c6.addProduct(p6);
        categories.add(c6);

        Category c7 = new Category(7, "Cate7", 116);
        Product p7 = new Product(7, "Product7", 9, 19.0, 7, "Hàng khuyến mãi", 107);
        c7.addProduct(p7);
        categories.add(c7);

        Category c8 = new Category(8, "Cate8", 117);
        Product p8 = new Product(8, "Product8", 14, 45.0, 8, "Sản phẩm thân thiện môi trường", 108);
        c8.addProduct(p8);
        categories.add(c8);

        Category c9 = new Category(9, "Cate9", 118);
        Product p9 = new Product(9, "Product9", 7, 75.0, 9, "Đồ tái chế", 109);
        c9.addProduct(p9);
        categories.add(c9);

        Category c10 = new Category(10, "Cate10", 119);
        Product p10 = new Product(10, "Product10", 11, 33.0, 10, "Đồ dùng học tập", 110);
        c10.addProduct(p10);
        categories.add(c10);

        Category c11 = new Category(11, "Cate11", 120);
        Product p11 = new Product(11, "Product11", 6, 55.0, 11, "Văn phòng phẩm", 111);
        c11.addProduct(p11);
        categories.add(c11);

        Category c12 = new Category(12, "Cate12", 121);
        Product p12 = new Product(12, "Product12", 10, 27.0, 12, "Sản phẩm công nghệ", 112);
        c12.addProduct(p12);
        categories.add(c12);

        Category c13 = new Category(13, "Cate13", 122);
        Product p13 = new Product(13, "Product13", 16, 42.0, 13, "Thiết bị điện tử", 113);
        c13.addProduct(p13);
        categories.add(c13);

        Category c14 = new Category(14, "Cate14", 123);
        Product p14 = new Product(14, "Product14", 8, 99.0, 14, "Đồ điện gia dụng", 114);
        c14.addProduct(p14);
        categories.add(c14);

        Category c15 = new Category(15, "Cate15", 124);
        Product p15 = new Product(15, "Product15", 13, 37.5, 15, "Thời trang nam", 115);
        c15.addProduct(p15);
        categories.add(c15);

        Category c16 = new Category(16, "Cate16", 125);
        Product p16 = new Product(16, "Product16", 10, 52.0, 16, "Thời trang nữ", 116);
        c16.addProduct(p16);
        categories.add(c16);

        Category c17 = new Category(17, "Cate17", 126);
        Product p17 = new Product(17, "Product17", 9, 67.0, 17, "Thực phẩm chức năng", 117);
        c17.addProduct(p17);
        categories.add(c17);

        Category c18 = new Category(18, "Cate18", 127);
        Product p18 = new Product(18, "Product18", 19, 88.8, 18, "Đồ chơi trẻ em", 118);
        c18.addProduct(p18);
        categories.add(c18);

        Category c19 = new Category(19, "Cate19", 128);
        Product p19 = new Product(19, "Product19", 6, 15.5, 19, "Sản phẩm chăm sóc cá nhân", 119);
        c19.addProduct(p19);
        categories.add(c19);

        Category c20 = new Category(20, "Cate20", 129);
        Product p20 = new Product(20, "Product20", 17, 23.5, 20, "Thiết bị nhà bếp", 120);
        c20.addProduct(p20);
        categories.add(c20);

    }
}
