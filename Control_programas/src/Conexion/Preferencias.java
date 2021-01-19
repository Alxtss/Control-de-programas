package Conexion;

import programa1.regPrograma1;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Preferencias {

    public static String dirLFVPO = "";
    public static String dirEALAF = "";
    public static String dirComent = "";
    public static String dirConciencia = "";
    public static String dirTTB = "";
    public static String dirEC = "";
    public static String dirLPDDPH = "";
    public static String dirCruzada = "";
    public static String dirResponde = "";
    public static String dirNotas = "";
    public static String dirHorarios = "";
    public static String dirNormas = "";

    public void preferencias() {
        FileOutputStream fichero = null;

        try {
            fichero = new FileOutputStream("preferences.txt");
            ObjectOutputStream os = new ObjectOutputStream(fichero);
            os.writeObject(regPrograma1.listValidator);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Preferencias.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Preferencias.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fichero.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }

    public void getPreferences() {
        try {
            FileInputStream f = new FileInputStream("preferences.txt");
            ObjectInputStream input = new ObjectInputStream(f);
            regPrograma1.listValidator = (Integer) input.readObject();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Preferencias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selectDirectoryPath() {
        FileOutputStream file = null;

        try {
            file = new FileOutputStream("directoryPath.txt");
            ObjectOutputStream ost = new ObjectOutputStream(file);
            ost.writeObject(dirLFVPO);
            ost.writeObject(dirResponde);
            ost.writeObject(dirCruzada);
            ost.writeObject(dirEC);
            ost.writeObject(dirConciencia);
            ost.writeObject(dirComent);
            ost.writeObject(dirEALAF);
            ost.writeObject(dirLPDDPH);
            ost.writeObject(dirTTB);
            ost.writeObject(dirNotas);
            ost.writeObject(dirHorarios);
            ost.writeObject(dirNormas);

        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                file.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public void getDirectoryPath() {
        try {
            FileInputStream fi = new FileInputStream("directoryPath.txt");
            ObjectInputStream in = new ObjectInputStream(fi);
            dirLFVPO = (String) in.readObject();
            dirResponde = (String) in.readObject();
            dirCruzada = (String) in.readObject();
            dirEC = (String) in.readObject();
            dirConciencia = (String) in.readObject();
            dirComent = (String) in.readObject();
            dirEALAF = (String) in.readObject();
            dirLPDDPH = (String) in.readObject();
            dirTTB = (String) in.readObject();
            dirNotas = (String) in.readObject();
            dirHorarios = (String) in.readObject();
            dirNormas = (String) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}
