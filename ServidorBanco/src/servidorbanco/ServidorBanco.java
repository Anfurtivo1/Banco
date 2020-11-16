/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorbanco;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andres
 */
public class ServidorBanco {
    public void ejecutarServer(){
        DatagramPacket p;
        DatagramSocket s;
        byte buffer1[] = new byte[256];
        
        System.out.println("Se ha empezado la ejecucion del servidor...");

        while (true) {
                try {
                    s= new DatagramSocket(1050);
                    p = new DatagramPacket(buffer1,256);
                    s.receive(p);

                    Hilo hilo = new Hilo(p,s,buffer1);
                    hilo.start();
            } catch (SocketException ex) {
                System.out.println("Error en "+ex);
            } catch (IOException ex) {
                System.out.println("Error en "+ex);
            } catch (Exception ex) {
                System.out.println("Error en "+ex);
            }    
            
        }
        
    }
}
