package Logica;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @author Jordan
 */
import GUI.VentanaClienteFTP;
import clientftp.ClientFTP;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.InlineView;

public class EchoClient {

    private DataOutputStream dos;
    private BufferedOutputStream bos;
    
    private String inputLineVentana;
    private Socket serverSocket;
    private PrintWriter out;
    private BufferedReader in;
    public static ReciveAndSendServer reciveFromServer;
    private int estado;
    private String[] directorio;
    

    public boolean startConnection(String ip, int port) {
        try {
            serverSocket = new Socket(ip, port);
            out = new PrintWriter(serverSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            estado = 0;
            reciveFromServer = new ReciveAndSendServer();
            reciveFromServer.start();
            
            this.dos = new DataOutputStream(serverSocket.getOutputStream());
            this.bos = new BufferedOutputStream(serverSocket.getOutputStream());
            
            return true;
        } catch (IOException e) {
            return false;
        }

    }

  

    public void stopConnection() {
        try {
            in.close();
            out.close();
            reciveFromServer.interrupt();
            serverSocket.close();
        } catch (IOException e) {

        }

    }

    public PrintWriter getOut() {
        return out;
    }

    public String getInputLineVentana() {
        return inputLineVentana;
    }

    public void setInputLineVentana(String inputLineVentana) {
        this.inputLineVentana = inputLineVentana;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Socket getServerSocket() {
        return serverSocket;
    }

    public DataOutputStream getDos() {
        return dos;
    }

    public void setDos(DataOutputStream dos) {
        this.dos = dos;
    }

    public BufferedOutputStream getBos() {
        return bos;
    }

    public void setBos(BufferedOutputStream bos) {
        this.bos = bos;
    }

  

   

    public ReciveAndSendServer getReciveFromServer() {
        return reciveFromServer;
    }

    public String[] getDirectorio() {
        return directorio;
    }

  
    
       
    public void setDirectorio(String[] directorio) {
        this.directorio = directorio;
    }
    
    
public void inicio(){
     String inputLine="";
      try {
                if ((inputLine = ClientFTP.getClient().getIn().readLine()) != null) {
                    
                    if(inputLine.equalsIgnoreCase("Enviar nombre archivo")){
                        if(ClientFTP.getVentanaClienteFTP().jTableG.getSelectedRow() != -1){
                            DefaultTableModel model = (DefaultTableModel) ClientFTP.getVentanaClienteFTP().jTableG.getModel();
                            String nombre=(String) model.getValueAt(ClientFTP.getVentanaClienteFTP().jTableG.getSelectedRow(), 0);
                            ClientFTP.getClient().getOut().println(nombre);
                            ClientFTP.getVentanaClienteFTP().descargarArchivo();
                        }
                    }
                    
                    if (inputLine.equalsIgnoreCase("inicio")) {
                        ClientFTP.getVentanaRegistro().init();
                        ClientFTP.getClient().getOut().println("Recibido inicio");
                    }
                    if (inputLine.equalsIgnoreCase("acceso")) {
                        ClientFTP.getVentanaClienteFTP().init();
                        ClientFTP.getClient().getOut().println("Recibido acceso");
                        ClientFTP.getVentanaRegistro().dispose();
                    }
                    if (inputLine.equalsIgnoreCase("Datos incorrectos")) {
                        System.err.println("Error introduzca datos validos de usuario");
                    }
                    
                    if(inputLine.equalsIgnoreCase("Enviar directorios")){
                    ClientFTP.getClient().getOut().println("Recibido: Enviar directorios");
                    int tam= Integer.parseInt(ClientFTP.getClient().getIn().readLine());
                    ClientFTP.getClient().getOut().println("Recibido tam");
                    System.out.println("tamaño:  "+tam);
                    ClientFTP.getClient().getOut().println("tam recibido");
       
                    String[] directorios= new String[tam];
                    for (int j = 0; j <tam ; j++) {
                        directorios[j]= ClientFTP.getClient().getIn().readLine();
                    }
                    for (int j = 0; j < directorios.length; j++) {
                        System.out.println("Directorios: "+directorios[j]);
                    }
                    
                    ClientFTP.getVentanaClienteFTP().setDirectorios(directorios);
                    }
                        
                }
               

            } catch (IOException ex) {

            } catch (NumberFormatException ex) {

            }
 }
}


class ReciveAndSendServer extends Thread {


    public ReciveAndSendServer() throws IOException {
        
      
    }


    @Override
    public void run() {
        while (true) {
           ClientFTP.getClient().inicio();
        }
        }
    
    
    
    public boolean enviarMensaje(String inputLine) {
        if (inputLine.equals("1")) {
            ClientFTP.getClient().getOut().printf("Enviar directorios");
            System.out.println("Entro 1");
          
            return true;
        }
        return false;
    }

  
}

    
    

