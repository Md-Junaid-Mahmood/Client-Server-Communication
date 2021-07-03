import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public static void main(String[] args) {
        int port = 8000;
        String serverAddress = "localHost";
        if(args.length != 0 && args.length != 2){
            System.out.println("Invalid Details");
            return;
        }else if(args.length == 2){
            serverAddress = args[0];
            try{
                port = Integer.parseInt(args[1]);
            }catch(Exception e){
                System.out.println("Invalid Details");
                return;
            }
        }

        try {
            DatagramSocket clientSocket = new DatagramSocket();
            byte dataSent[] = new byte[256];
            byte dataReceived[] = new byte[256];

            String initialMessage = "Request Sent For Connection to the Server\n";
            dataSent = initialMessage.getBytes();
            System.out.print("Client:> " + initialMessage); 

            InetAddress address = InetAddress.getByName(serverAddress);
            DatagramPacket initialSentPacket = new DatagramPacket(dataSent, dataSent.length, address, port);
            clientSocket.send(initialSentPacket);

            DatagramPacket connectPermission = new DatagramPacket(dataReceived, dataReceived.length);
            clientSocket.receive(connectPermission);
            String permission = new String(connectPermission.getData());
            System.out.print("Server:> " + permission);

            dataSent = new byte[256];
            System.gc();
            
            System.out.print("Client:> ");
            BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
            String numStr = bReader.readLine();
            dataSent = numStr.getBytes();

            initialSentPacket = new DatagramPacket(dataSent, dataSent.length, address, port);
            clientSocket.send(initialSentPacket);

            dataReceived = new byte[256];
            System.gc();

            connectPermission = new DatagramPacket(dataReceived, dataReceived.length);
            clientSocket.receive(connectPermission);
            String reply = new String(connectPermission.getData());
            System.out.print("Server:> " + reply);

            reply = reply.trim();
            if(reply.equalsIgnoreCase("Kindly enter valid number")){
                clientSocket.close();
                return;
            }else if(Integer.parseInt(numStr) == 1){
                while(true){
                    System.out.print("Client:> ");
                    String messageFromClient = bReader.readLine();

                    dataSent = new byte[256];
                    System.gc();
                    dataSent = messageFromClient.getBytes();
                    DatagramPacket sentPacket = new DatagramPacket(dataSent, dataSent.length, address, port);
                    clientSocket.send(sentPacket);

                    if(messageFromClient.equalsIgnoreCase("Exit")){
                        break;
                    }

                    dataReceived = new byte[256];
                    System.gc();
                    DatagramPacket receivedPacket = new DatagramPacket(dataReceived, dataReceived.length);
                    clientSocket.receive(receivedPacket);
                    String messageFromServer = new String(receivedPacket.getData());
                    messageFromServer = messageFromServer.trim();
                    System.out.println("Server:> " + messageFromServer);
                }
            }else{
                while(true){
                    System.out.print("Client:> ");
                    String messageFromClient = bReader.readLine();

                    dataSent = new byte[256];
                    System.gc();
                    dataSent = messageFromClient.getBytes();
                    DatagramPacket sentPacket = new DatagramPacket(dataSent, dataSent.length, address, port);
                    clientSocket.send(sentPacket);

                    if(messageFromClient.equalsIgnoreCase("Exit")){
                        break;
                    }

                    dataReceived = new byte[256];
                    System.gc();
                    DatagramPacket receivedPacket = new DatagramPacket(dataReceived, dataReceived.length);
                    clientSocket.receive(receivedPacket);

                    String messageFromServer = new String(receivedPacket.getData());
                    messageFromServer = messageFromServer.trim();
                    if(messageFromServer.equalsIgnoreCase("Exit")){
                        System.out.println("Connection Terminated From Server!");
                        break;
                    }else{
                        System.out.println("Server:> " + messageFromServer);
                    }
                }
            }
            clientSocket.close();

        } catch (Exception e) {
            System.out.println("Sorry! Something Unexpected Happened");
        }
    }
}
