import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{

    /*
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7000);
        Socket socket = serverSocket.accept();
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        FileWriter filewriter = new FileWriter("op.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(filewriter);
        *//*
        new Thread() {
            public void run() {
                javafx.application.Application.launch(Front.class);
            }
        }.start();
        Front front = Front.waitforfront();
        front.notif();
        *//*
        *//*Here server records all the data received from the client
        *//*
        while (socket.isConnected()) {
            String str = objectInputStream.readUTF();
            System.out.println("Received : " + str);
            bufferedWriter.write(str + "\n");
            bufferedWriter.flush();
        }

        System.out.println("Connection closed");
    }
    */

    public void run(){


        try {
            ServerSocket serverSocket = new ServerSocket(7000);
            Socket socket = serverSocket.accept();
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            FileWriter filewriter = new FileWriter("op.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(filewriter);

            while (socket.isConnected()) {
                String str = objectInputStream.readUTF();
                System.out.println("Received : " + str);
                bufferedWriter.write(str + "\n");
                bufferedWriter.flush();
            }
        }
        catch(Exception e){
            System.out.println(e);
        }

    }


}