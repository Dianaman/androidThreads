package com.example.claseseis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Handler.Callback {

    TextView tv;
    ImageView iv;

    Handler handlerPersonas;
    Handler handlerImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tv = (TextView) this.findViewById(R.id.tv);
        tv.setText("Hola desde el onCreate");

        this.iv = (ImageView) this.findViewById(R.id.iv);


        this.handlerPersonas = new Handler(this);
        MiHilo miHilo  = new MiHilo(this.handlerPersonas);
        miHilo.start(); // -> Se lanza en paralelo

        this.handlerImagen = new Handler(this);
        OtroHilo otroHilo  = new OtroHilo(this.handlerImagen);
        otroHilo.start(); // -> Se lanza en paralelo
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        if(message.getTarget() == this.handlerPersonas){
            String msj = (String) message.obj;
            if(msj != null){
                this.tv.setText(msj);
            }
        } else {
            byte[] byteArray = (byte[]) message.obj;
            if(byteArray != null) {
                Bitmap imagen = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                this.iv.setImageBitmap(imagen);
            }
        }


        return false;
    }
}
