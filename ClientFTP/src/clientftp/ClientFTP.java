    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientftp;

import GUI.ConectaServidor;
import GUI.Registro;
import GUI.VentanaClienteFTP;
import Logica.EchoClient;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import static java.lang.Thread.sleep;


//




/**
 *
 * @author Jordan
 */
public class ClientFTP {
 private static EchoClient client;
 private static VentanaClienteFTP ventana;
 private static Registro ventanaRegistro;
private static ConectaServidor conection;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        client = new EchoClient();
        ventana= new VentanaClienteFTP();
        ventanaRegistro= new Registro();
        conection = new ConectaServidor();
        conection.init();
    }

    public static EchoClient getClient() {
        return client;
    }

    public static VentanaClienteFTP getVentanaClienteFTP() {
        return ventana;
    }

    public static Registro getVentanaRegistro() {
        return ventanaRegistro;
    }
    
    
    
}
