/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverftp;

import GUI.VentanaPrincipal;
import Logica.EchoMultiServer;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author brend
 */
public class ServerFTP {
private static EchoMultiServer server;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
        ventanaPrincipal.setVisible(true);
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setResizable(false);
        ventanaPrincipal.setLocationRelativeTo(null);
        server = new EchoMultiServer();
        server.start(5555);

        
    }

    public static EchoMultiServer getServer() {
        return server;
    }

    public static void setServer(EchoMultiServer server) {
        ServerFTP.server = server;
    }
    
}
