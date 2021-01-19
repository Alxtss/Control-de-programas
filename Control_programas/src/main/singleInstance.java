package main;

import Frames.Principal;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class singleInstance {
    private String appPath = System.getProperties().getProperty("user.dir");
    private File f = new File(appPath+"\\miapp.tmp");
    //tiempo de actualizacion del fichero
    private int segundos = 20;
    
    public singleInstance(){
        
    }
    
    //comprobar que el archivo tp exista
    public boolean comprobar(){
        if(f.exists()){
            long tiempo = leer();
            long res = restarTiempo(tiempo);
            if(res<segundos){
                System.out.println("La app ya esta e ejecucion");
                return false;
            }else{
                programar_tarea();
                return true;
            }
        }else{//no existe el fichero
            crearTMP();
            programar_tarea();
            return true;
        }
    }
    
    //lee el archivo tmp y retorna su valor
    public long leer(){
        String linea = "0";
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(f));
            while(reader.ready()){
                linea = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return Long.valueOf(linea).longValue();
    }
    
    //programa un proceso que se repite cada cierto tiempo
    
    public void programar_tarea(){
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                crearTMP();
            }
        }, 1000, segundos * 1000, TimeUnit.MILLISECONDS);// comienza dentro de 1 seg y se repite cada N seg
    }
    
    public void crearTMP(){
        Date fecha = new Date();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            writer.write(String.valueOf(fecha.getTime()));
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    //resta el tiempo expresado en milisegundos
    public long restarTiempo(long tiempoActual){
        Date date = new Date();
        long tiempoTMP = date.getTime();
        long tiempo = tiempoTMP - tiempoActual;
        tiempo = tiempo/1000;
        return tiempo;
    }
    
    //elimina el fichero si es que existe
    public void cerrarApp(){
        if(f.exists()){
            f.delete();
            System.exit(0);
        }
    }
}
