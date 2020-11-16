/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorbanco;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andres
 */
public class Hilo extends Thread{
    Scanner leer = new Scanner(System.in);
    DatagramPacket p;
    DatagramSocket s;
    byte buffer1[];

    public Hilo(DatagramPacket p, DatagramSocket s, byte[] buffer1) {
        this.p = p;
        this.s = s;
        this.buffer1 = buffer1;
    }
    
    
    
    @Override
    public void run(){
       String mensajeInfo;
       int puerto;
       String mensaje;
       int longitud;
       int restaDinero;
       int dinero=2000;
       boolean comprobarString=true;
       InetAddress ip;
        try {
            byte buffer2[] = new byte[256];
            DatagramPacket p2;
            ip=p.getAddress();
            buffer1=p.getData();
            puerto=p.getPort();
            longitud=p.getLength();
            mensaje= new String(buffer1,0,longitud);
            comprobarString=comprobarMensaje(mensaje);
            
                if (mensaje.equalsIgnoreCase("retirada")) {
                    mensajeInfo="Indica el dinero que quieres retirar";
                    buffer2=mensaje.getBytes();
                    p2=new DatagramPacket(buffer2,mensaje.length(),ip,puerto);
                    s.send(p);
                    restaDinero=Integer.parseInt(mensaje);
                    mensajeInfo="Se van a retirar "+restaDinero+"€";
                    dinero=(dinero-restaDinero);
                }
                

            System.out.println("Se ha recibido el eco de: "+puerto+"> "+mensaje);

            mensaje="Se ha recibido la solicitud de retirada, ahora hay "+dinero;

            if (dinero<=0) {
                mensaje="Ya no quedan caramelos";
            }

            buffer2=mensaje.getBytes();

            p2=new DatagramPacket(buffer2,mensaje.length(),ip,puerto);
            System.out.println("Se va a mandar al equipo "+ip.getHostAddress()+" el mensaje");

            s.send(p2);
        
        } catch (SocketException ex) {
            
        } catch (IOException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean comprobarMensaje(String mensaje){
        boolean isNumeric =  mensaje.matches("[+-]?\\d*(\\.\\d+)?");
        return isNumeric;
    }
    
}
