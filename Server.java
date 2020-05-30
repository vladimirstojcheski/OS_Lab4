package Lab4;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    private ServerSocket serverSocket;

    public Server(int port) throws IOException
    {
        this.serverSocket = new ServerSocket(port);
    }

    @Override
    public void run()
    {
        try {
            System.out.println("Server is up!");
            while (true)
            {
                Socket socket = this.serverSocket.accept();
                System.out.println("A new user just jumped in \n Welcome " + socket.getInetAddress());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeUTF("Welcome to the server");
                ServerWorker sw = new ServerWorker(socket);
                sw.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Server server= new Server(8080);
        server.start();
    }
}
