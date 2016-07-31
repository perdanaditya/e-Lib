/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaSocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author RAP
 */
public class TestClientModel {

    Socket socket;

    public TestClientModel() {
        try {
            socket = new Socket("localhost", 9999);//create new socket
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Socket Recive serror \n" + e.getMessage(), "SOCKET ERROR", 0);
        }
    }

    public void postMessage() {
        try {
            //post
            PrintStream ps = new PrintStream(socket.getOutputStream());//catch message from socket
            System.out.print("Enter some message: ");
            InputStreamReader is = new InputStreamReader(System.in);//catch input from user
            BufferedReader bf = new BufferedReader(is);//read these input
            String message=bf.readLine();//read input in line (String)
            ps.println(message);//post message to server
            
            //get
            BufferedReader bf2=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String cofMessage = bf2.readLine();
            System.out.println("Message from server: "+cofMessage);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Post Message Recive Error : \n" + e.getMessage(), "POST MESSAGE ERROR", 0);
        }
    }
}
