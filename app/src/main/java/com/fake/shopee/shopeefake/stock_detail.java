package com.fake.shopee.shopeefake;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fake.shopee.shopeefake.Main_pages.main_cart;
import com.squareup.picasso.Picasso;

import java.sql.ResultSet;

public class stock_detail extends AppCompatActivity {

    TextView nama,harga,keterangan,kategori,berat,stock,penjual;
    Button beli;
    ImageView image;
    session_class session;
    SQLclass sqlclass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_detail);

        sqlclass = new SQLclass();
        session = new session_class(this);

        nama = (TextView) findViewById(R.id.detailproduk);
        penjual = (TextView) findViewById(R.id.detailpenjual);

        harga = (TextView) findViewById(R.id.detailharga);
        keterangan = (TextView) findViewById(R.id.detailketerangan);
        kategori = (TextView) findViewById(R.id.detailkategori);
        berat = (TextView) findViewById(R.id.detailberat);
        stock = (TextView) findViewById(R.id.detailstok);
        image = (ImageView) findViewById(R.id.imagestock);



        beli = (Button) findViewById(R.id.buttonbuy);

        beli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int buy = sqlclass.queryexecute("")
                Intent a = new Intent(stock_detail.this,main_cart.class);

            }
        });

        String s = getIntent().getStringExtra("request");
        Log.e("Request data",s);
        ResultSet result = sqlclass.querydata("select * from stock where namaproduk='"+s+"'");

        try{
            while (result.next()){
                nama.setText(result.getString("namaproduk"));
                keterangan.setText(result.getString("keterangan"));
                kategori.setText(result.getString("kategori"));
                berat.setText(result.getString("berat"));
                Picasso.with(this).load(result.getString("imagedir")).resize(800,800).centerCrop().into(image);
                harga.setText(result.getString("harga"));
                stock.setText(result.getString("stock"));
            }
            penjual.setText(session.getusename());
        }catch (Exception e){
            Log.e("stock detail error",e.getMessage());
        }

    }
}
