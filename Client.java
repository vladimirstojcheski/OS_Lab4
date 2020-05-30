package Lab4;

import java.io.*;
import java.net.Socket;

// Klientot prima edna poraka od serverot koga se povrzuva so isiot
// Koga klientot ispraka poraki do serverot, istite se prikazuvaat na server konzolata!

public class Client extends Thread{
    String name;
    private Socket socket;

    public Client(String name, String host, int port) throws IOException {
        this.name = name;
        this.socket = new Socket(host, port);
    }

    @Override
    public void run()
    {
        try {
            System.out.println("Connection established!");
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String message = dis.readUTF();
            System.out.println(message);
            while (true){
                String sentence;
                BufferedReader inFromUser =
                        new BufferedReader(new InputStreamReader(System.in));
                System.out.print(name + ": ");
                sentence = inFromUser.readLine();

                // send to server
                DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
                outToServer.writeUTF(name + ": " + sentence);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Client c1 = new Client("user1", "localhost", 8080);
        c1.start();
    }
}

