package com.example.claseseis;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;


public class MiHilo extends Thread {
    /*private TextView tv;

    public MiHilo(TextView tv){
        this.tv = tv;
    }*/

    private Handler handler;

    public MiHilo(Handler handler){
        this.handler = handler;
    }

    @Override
    public void run() {
        // tv.setText("Texto modificado desde MiHilo"); // --> No se puede por cuestiones de seguridad

        /*Log.d("step", "run");

        for(int i=0; i<4;i++){
            Log.d("for", "for "+i);
            try {
                Thread.sleep(3000);
                Log.d("sleep", "sleep "+i);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            Message message = new Message();
            message.obj = "Mensaje nro" + i;
            this.handler.sendMessage(message);
        }*/

        HttpManager manager = new HttpManager();
        String personas = manager.obtenerPersonas();
        Message message = new Message();
        message.obj = personas;
        this.handler.sendMessage(message);
    }
}
