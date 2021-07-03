import java.net.*;
import java.io.*;

public class Server{
    public static void main(String[] args) {
        try{
            int port;
            if(args.length == 0){
                port = 8000;
            }else{
                port = Integer.parseInt(args[0]);
            }
            ServerSocket serverSocket = new ServerSocket(port);
            if(args.length == 0){
                System.out.println("Server Established at default port 8000");
            }else{
                System.out.println("Server Established at port " + port);
            }
            System.out.println("Waiting for the Client to Connect...");

            Socket dedicatedServerSocket = serverSocket.accept();
            InetAddress ip = dedicatedServerSocket.getInetAddress();
            String clientHostAddress = ip.getHostAddress();
            System.out.println("Connected to Client with IP: " + clientHostAddress);

            DataOutputStream inititalOutput = new DataOutputStream(dedicatedServerSocket.getOutputStream());
            String initialMessage = "Welcome to the Server\nPress 1 For Using Calculator Feature\nPress 2 For Normal Conversation\n";
            inititalOutput.writeUTF(initialMessage);

            DataInputStream initialInput = new DataInputStream(dedicatedServerSocket.getInputStream());
            int number = Integer.parseInt((String)initialInput.readUTF());
            System.out.println("Client:> " + number);

            if(number != 1 && number != 2){
                inititalOutput.writeUTF("Error! Wrong Number Entered");
                System.out.println("Server:> Wrong Number Error!");
            }else if(number == 1){
                System.out.print("Server:> ");
                initialMessage = "Format For Using Calculator is:\nADD x y for addition\nMUL x y for multiplication\nSUB x y for substraction\nDIV x y for division\n";
                System.out.print(initialMessage);
                inititalOutput.writeUTF(initialMessage);

                while(true){
                    String expression = (String)initialInput.readUTF();
                    expression = expression.trim();
                    System.out.println("Client:> " + expression);

                    String arr[] = expression.split(" ");
                    if(arr[0].equalsIgnoreCase("Exit")){
                        System.out.println("Session Terminated By Client");
                        break;
                    }
                    System.out.print("Server:> ");
                    if(arr.length != 3){
                        System.out.println("Error! Wrong Expression Entered");
                        inititalOutput.writeUTF("Error! Wrong Expression Entered");
                        continue;
                    }else{
                        String operator = arr[0];
                        int x = Integer.parseInt(arr[1]);
                        int y = Integer.parseInt(arr[2]);

                        int z;
                        if(operator.equalsIgnoreCase("ADD")){
                            z = x + y;
                            String result = "Result Of Addition is: " + Integer.toString(z);
                            inititalOutput.writeUTF(result);
                            System.out.println(result);
                        }else if(operator.equalsIgnoreCase("MUL")){
                            z = x * y;
                            String result = "Result Of Multiplication is: " + Integer.toString(z);
                            inititalOutput.writeUTF(result);
                            System.out.println(result);
                        }else if(operator.equalsIgnoreCase("SUB")){
                            z = x - y;
                            String result = "Result Of Substraction is: " + Integer.toString(z);
                            inititalOutput.writeUTF(result);
                            System.out.println(result);
                        }else if(operator.equalsIgnoreCase("DIV")){
                            z = (int)(x/y);
                            String result = "Quotient is: " + Integer.toString(z);
                            z = x % y;
                            result = result + " and Remainder is: " + Integer.toString(z);
                            inititalOutput.writeUTF(result);
                            System.out.println(result);
                        }else{
                            inititalOutput.writeUTF("Error! Wrong Expression Entered");
                            System.out.println("Error! Wrong Expression Entered");
                            continue;
                        }
                    }

                }
            }else{
                System.out.print("Server:> ");
                initialMessage = "You are connected Successfully!\nYou can send your first message now\n";
                System.out.print(initialMessage);
                inititalOutput.writeUTF(initialMessage);
                while(true){
                    DataInputStream dataInput = new DataInputStream(dedicatedServerSocket.getInputStream());

                    String messageFromClient = (String)(dataInput.readUTF());
                    if(messageFromClient.equalsIgnoreCase("Exit")){
                        System.out.println("Session Terminated By Client!");
                        break;
                    }else{
                        System.out.println("Client:> " + messageFromClient);
                    }

                    DataOutputStream dataOutput = new DataOutputStream(dedicatedServerSocket.getOutputStream());
                    System.out.print("Server:> ");
                    BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
                    String messageFromServer = bReader.readLine();

                    dataOutput.writeUTF(messageFromServer);
                    if(messageFromServer.equalsIgnoreCase("Exit")){
                        break;
                    }
                }
            }
            serverSocket.close();
        }catch (Exception e){
            System.out.println("Unfortunately! Something Unexpected Happened!");
        }
    }
}