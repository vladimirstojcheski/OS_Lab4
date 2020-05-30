package Lab4;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerWorker extends Thread {
    private Socket socket = null;

    public ServerWorker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            receiveData(this.socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receiveData(Socket socket) throws IOException {
        while (true) {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String message = dis.readUTF();
            System.out.println(message);
        }
    }
}
