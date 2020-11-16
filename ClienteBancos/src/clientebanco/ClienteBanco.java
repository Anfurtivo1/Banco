/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientebanco;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author Andres
 */
public class ClienteBanco {
    public void EjecutarCliente(){
        try{
            String mensaje;
            Scanner leer = new Scanner(System.in);
            
            DatagramSocket s=new DatagramSocket();
            DatagramPacket p;
            DatagramPacket p2;
            byte buffer1[] = new byte[256];
            byte buffer2[] = new byte[256];
            
            int longitudMensaje;
            int puerto;
            InetAddress ip;
            
            do {
                System.out.println("Escribe un mensaje");
                mensaje=leer.nextLine();
                buffer1=mensaje.getBytes();
                p = new DatagramPacket(buffer1,mensaje.length(),InetAddress.getLocalHost(),1050);
                s.send(p);
                
                p2= new DatagramPacket(buffer2,256);
                s.receive(p2);
                longitudMensaje=p2.getLength();
                puerto=p2.getPort();
                ip=p2.getAddress();
                buffer2=p2.getData();
                
                mensaje = new String(buffer2,0,longitudMensaje);
                
                System.out.println(">"+mensaje);
                
            } while (mensaje!=null);
            
            
        }catch(Exception e){
            
        }
    }
}
