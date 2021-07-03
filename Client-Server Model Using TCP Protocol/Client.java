import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args){
        String server;
        int port;

        if(args.length == 0){
            server = "localHost";
            port = 8000;
        }else if(args.length == 2){
            server = args[0];
            port = Integer.parseInt(args[1]);
        }else{
            System.out.println(("Incomplete Details!"));
            return;
        }

        try {
            Socket clientSocket = new Socket(server, port);
            System.out.println("Connection with the Server is Established");

            DataInputStream initialInput = new DataInputStream(clientSocket.getInputStream());
            String initialMessageFromServer = (String)initialInput.readUTF();
            System.out.print("Server:> " + initialMessageFromServer);

            DataOutputStream initialOutput = new DataOutputStream(clientSocket.getOutputStream());
            System.out.print("Client:> ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String initialMessageFromClient = br.readLine();
            initialOutput.writeUTF(initialMessageFromClient);

            if(Integer.parseInt(initialMessageFromClient) == 1){
                initialMessageFromServer = (String)initialInput.readUTF();
                System.out.print("Server:> " + initialMessageFromServer);
                if(!initialMessageFromServer.equals("Error! Wrong Number Entered")){
                    while(true){
                        System.out.print("Client:> ");
                        initialMessageFromClient = br.readLine();
                        initialOutput.writeUTF(initialMessageFromClient);
                        if(initialMessageFromClient.equals("Exit")){
                            clientSocket.close();
                            return;
                        }

                        initialMessageFromServer = (String)initialInput.readUTF();
                        System.out.println("Server:> " + initialMessageFromServer);
                    }
                }
            }else{
                initialMessageFromServer = (String)initialInput.readUTF();
                System.out.print("Server:> " + initialMessageFromServer);
                while(true){
                    DataOutputStream dataOutput = new DataOutputStream(clientSocket.getOutputStream());
                    System.out.print("Client:> ");
                    BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
                    String messageFromClient = bReader.readLine();
                    
                    dataOutput.writeUTF(messageFromClient);
                    if(messageFromClient.equalsIgnoreCase("Exit")){
                        break;
                    }

                    DataInputStream dataInput = new DataInputStream(clientSocket.getInputStream());
                    String messageFromServer = (String)dataInput.readUTF();
                    if(messageFromServer.equalsIgnoreCase("Exit")){
                        System.out.println("Session Terminated By Server!");
                        break;
                    }else{
                        System.out.println("Server:> " + messageFromServer);
                    }
                }
            }
            clientSocket.close();
        } catch (Exception e) {
            System.out.println("Unfortunately! Something Unexpected Happened!");
        }
    }
}
