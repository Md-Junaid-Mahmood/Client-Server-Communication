import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server{
    public static void main(String[] args) {
        int port;
        try{
            port = Integer.parseInt(args[0]);
            System.out.println("Server Successfully Started at Port: " + port);
        }catch(Exception e){
            port = 8000;
            System.out.println("Server Successfully Started at default Port: " + port);
        }

        try{
            DatagramSocket serverSocket = new DatagramSocket(port);
            byte dataReceived[] = new byte[256];
            byte dataSent[] = new byte[256];

            DatagramPacket initialReceivedPacket = new DatagramPacket(dataReceived, dataReceived.length);
            serverSocket.receive(initialReceivedPacket);
            InetAddress address = initialReceivedPacket.getAddress();
            int portAddr = initialReceivedPacket.getPort();

            String initialRequest = new String(initialReceivedPacket.getData());
            System.out.print("Client:> " + initialRequest);

            String initialWelcome = "Your Request For Connection is granted!\nPress 1 for using Server as Calculator\nPress 2 for using Server for General Communication\n";
            System.out.print("Server:> " + initialWelcome);

            dataSent = initialWelcome.getBytes();
            DatagramPacket inititalRequestGrant = new DatagramPacket(dataSent, dataSent.length, address, portAddr);
            serverSocket.send(inititalRequestGrant);

            dataReceived = new byte[256];
            System.gc();
            
            initialReceivedPacket = new DatagramPacket(dataReceived, dataReceived.length);
            serverSocket.receive(initialReceivedPacket);
            
            String numStr = new String(initialReceivedPacket.getData());
            numStr = numStr.trim();
            int number = Integer.parseInt(numStr);

            if(number != 1 && number != 2){
                initialWelcome = "Kindly enter valid number\n";
                System.out.print("Server:> " + initialWelcome);

                dataSent = new byte[256];
                System.gc();
                dataSent = initialWelcome.getBytes();
                inititalRequestGrant = new DatagramPacket(dataSent, dataSent.length, address, portAddr);
                serverSocket.send(inititalRequestGrant);
            }else if(number == 1){
                initialWelcome = "Format For Using Calculator is:\nADD x y for addition\nMUL x y for multiplication\nSUB x y for substraction\nDIV x y for division\n";
                System.out.print("Server:> " + initialWelcome);

                dataSent = new byte[256];
                System.gc();
                dataSent = initialWelcome.getBytes();
                inititalRequestGrant = new DatagramPacket(dataSent, dataSent.length, address, portAddr);
                serverSocket.send(inititalRequestGrant);

                while(true){
                    dataReceived = new byte[256];
                    System.gc();
                    DatagramPacket receivePacket = new DatagramPacket(dataReceived, dataReceived.length);
                    serverSocket.receive(receivePacket);

                    String messageFromClient = new String(receivePacket.getData());
                    messageFromClient = messageFromClient.trim();
                    String arr[] = messageFromClient.split(" ");

                    if(arr[0].equalsIgnoreCase("Exit")){
                        System.out.println("Session Terminated By Client");
                        break;
                    }
                    System.out.print("Server:> ");
                    dataSent = new byte[256];
                    System.gc();


                    if(arr.length != 3){
                        String err = "Error! Wrong Expression Entered";
                        System.out.println(err);
                        dataSent = err.getBytes();
                        DatagramPacket sentPacket = new DatagramPacket(dataSent, dataSent.length, address, portAddr);
                        serverSocket.send(sentPacket);
                        continue;
                    }else{
                        String operator = arr[0];
                        int x, y;
                        try {
                            x = Integer.parseInt(arr[1]);
                            y = Integer.parseInt(arr[2]);

                        } catch (Exception e) {
                            String err = "Error! Wrong Operand Entered";
                            System.out.println(err);
                            dataSent = err.getBytes();
                            DatagramPacket sentPacket = new DatagramPacket(dataSent, dataSent.length, address, portAddr);
                            serverSocket.send(sentPacket);
                            continue;
                        }
                        
                        int z;
                        if(operator.equalsIgnoreCase("ADD")){
                            z = x + y;
                            String result = "Result Of Addition is: " + Integer.toString(z);
                            dataSent = result.getBytes();
                            DatagramPacket sentPacket = new DatagramPacket(dataSent, dataSent.length, address, portAddr);
                            serverSocket.send(sentPacket);
                            System.out.println(result);
                        }else if(operator.equalsIgnoreCase("MUL")){
                            z = x * y;
                            String result = "Result Of Multiplication is: " + Integer.toString(z);
                            dataSent = result.getBytes();
                            DatagramPacket sentPacket = new DatagramPacket(dataSent, dataSent.length, address, portAddr);
                            serverSocket.send(sentPacket);
                            System.out.println(result);
                        }else if(operator.equalsIgnoreCase("SUB")){
                            z = x - y;
                            String result = "Result Of Substraction is: " + Integer.toString(z);
                            dataSent = result.getBytes();
                            DatagramPacket sentPacket = new DatagramPacket(dataSent, dataSent.length, address, portAddr);
                            serverSocket.send(sentPacket);
                            System.out.println(result);
                        }else if(operator.equalsIgnoreCase("DIV")){
                            z = (int)(x/y);
                            String result = "Quotient is: " + Integer.toString(z);
                            z = x % y;
                            result = result + " and Remainder is: " + Integer.toString(z);
                            dataSent = result.getBytes();
                            DatagramPacket sentPacket = new DatagramPacket(dataSent, dataSent.length, address, portAddr);
                            serverSocket.send(sentPacket);
                            System.out.println(result);
                        }else{
                            String err = "Error! Wrong Operator Entered";
                            System.out.println(err);
                            dataSent = err.getBytes();
                            DatagramPacket sentPacket = new DatagramPacket(dataSent, dataSent.length, address, portAddr);
                            serverSocket.send(sentPacket);
                            continue;
                        }
                    }
                }

            }else{
                initialWelcome = "You can send your first message now!\n";
                System.out.print("Server:> " + initialWelcome);

                dataSent = new byte[256];
                System.gc();
                dataSent = initialWelcome.getBytes();

                inititalRequestGrant = new DatagramPacket(dataSent, dataSent.length, address, portAddr);
                serverSocket.send(inititalRequestGrant);
                while(true){
                    dataReceived = new byte[256];
                    System.gc();
                    DatagramPacket receivePacket = new DatagramPacket(dataReceived, dataReceived.length);
                    serverSocket.receive(receivePacket);

                    String messageFromClient = new String(receivePacket.getData());
                    messageFromClient = messageFromClient.trim();
                    if(messageFromClient.equalsIgnoreCase("Exit")){
                        System.out.println("Connection Terminated From Client");
                        break;
                    }else{
                        System.out.println("Client:> " + messageFromClient);
                    }

                    System.out.print("Server:> ");
                    BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
                    String messageFromServer = bReader.readLine();

                    dataSent = new byte[256];
                    System.gc();
                    dataSent = messageFromServer.getBytes();
                    DatagramPacket sentPacket = new DatagramPacket(dataSent, dataSent.length, address, portAddr);
                    serverSocket.send(sentPacket);
                
                    if(messageFromServer.equalsIgnoreCase("Exit")){
                        break;
                    }
                }
            }
            serverSocket.close();

        } catch (Exception e) {
            System.out.println("Sorry! Something Unexpected Happened");
        } 
    }
}

