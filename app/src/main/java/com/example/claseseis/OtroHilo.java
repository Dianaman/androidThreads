package com.example.claseseis;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class OtroHilo extends Thread {
    private Handler handler;

    public OtroHilo(Handler handler){
        this.handler = handler;
    }

    @Override
    public void run() {
        HttpManager manager = new HttpManager();
        byte[] imagen = manager.obtenerImagen();
        Message message = new Message();
        message.obj = imagen;
        this.handler.sendMessage(message);
    }
}
