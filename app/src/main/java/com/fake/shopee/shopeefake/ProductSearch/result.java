package com.fake.shopee.shopeefake.ProductSearch;

import java.security.PrivateKey;

/**
 * Created by riandy on 1/13/2018.
 */

public class result {
    private int likecount;
    private String nama;
    private int harga;
    private String imagedir;

    public result() {
    }

    public result(int likecount,int harga,String imagedir,String nama) {
        this.likecount = likecount;
        this.imagedir = imagedir;
        this.harga = harga;
        this.nama = nama;
    }

    public String getImagedir() {
        return imagedir;
    }

    public void setImagedir(String name) {
        this.imagedir = name;
    }

    public int getLikecount() {
        return likecount;
    }

    public void setLikecount(int likecount) {
        this.likecount = likecount;
    }
    public String getNama() {
        return nama;
    }

    public void setnama(String name) {
        this.nama = name;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int likecount) {
        this.harga = likecount;
    }
}
