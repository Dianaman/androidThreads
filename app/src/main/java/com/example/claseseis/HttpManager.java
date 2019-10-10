package com.example.claseseis;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpManager {
    byte[] resultado;
    byte[] imagen;

    public String obtenerPersonas(){
        try {
            URL url = new URL("http://www.lslutnfra.com/alumnos/practicas/listaPersonas.xml");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // información extra aquí
            connection.connect();

            if(connection.getResponseCode() == 200) {
                this.resultado = this.leerStream(connection);

                return new String(this.resultado);
            }

        } catch(MalformedURLException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }

        return null;
    }

    public byte[] obtenerImagen(){
        try {
            URL url = new URL("http://www.lslutnfra.com/alumnos/practicas/ubuntu-logo.png");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // información extra aquí
            connection.connect();

            if(connection.getResponseCode() == 200) {
                this.imagen = this.leerStream(connection);

                return this.imagen;
            }

        } catch(MalformedURLException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }

        return null;
    }

    private byte[] leerStream(HttpURLConnection conexion){
        byte[] byteArray = null;

        try {
            InputStream is = conexion.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1000];
            int cantidadLeida;

            while((cantidadLeida = is.read(buffer, 0, 1000)) > -1){
                baos.write(buffer, 0, cantidadLeida);
            }

            byteArray = baos.toByteArray();

            is.close();

        } catch(IOException e){
            e.printStackTrace();
        }
        return byteArray;
    }
}
