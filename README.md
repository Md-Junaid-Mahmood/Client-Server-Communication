# <div align = "center"> Client Server Communication  </div>
The repository holds the project work that I did on Client-Server Commumnication Model using Socket Programming under the guidance of IIT Roorkee ACM Student Chapter.

---

## <div align = "left"> Introduction </div>
Any kind of communication involves following two entities:

- **Client** - Client is a system that is requesting for a connection and services of the another system.
- **Server** - Server is a system that receives the requests from clients and renders the service requested.

There must be a set of standard protocols that supported by Client-side and Server-side in order to ensure smooth flow of the messages. Under this project, communication using *TCP/IP Protocol* and *UDP Protocol* was studied and implemented.

- **TCP/IP** - Transmission Control Protocol/ Internet Protocol provides end to end connectivity. It ensures that data packets are well-addressed and are sent error free and in sequence. If the receiving system detects any loss or damage in the data packets, then it can requests the sender to transmit it again. In this protocol, sender has knowledge whether receiver had received the data packets or not. This protocol is used for providing reliable communication between computers.
- **UDP** - Unreliable Datagram Protocol provides unreliable and connectionless connectivity. It does the not guarantee that the packets will be delivered correctly or delivered at all. It is generally used in scenario where fast delivery is more important than reliability and loss of few data packets does not affect much in the communication.

---

## <div align = "left"> How To Run </div>
In order to run the code JAVA Compiler must be installed into the system.

1. Download the source code or clone the GitHub repository.

```
git clone https://github.com/Md-Junaid-Mahmood/Client-Server-Communication
```

2. Change your directory to the appropriate folder based on whether you want to run the code based on TCP/IP Protocol or UDP Protocol.

- If you want to run the code based on TCP/IP Protocol

```
cd Client-Server Model Using TCP Protocol
```
- If you want to run the code based on TCP/IP Protocol

```
cd Client-Server Model Using UDP Protocol
```

3. Run the following command, irrespective of the choice of the current directory

  - In the current terminal run code given below:
  ```
  javac Server.java
  java Server 8000
  ```
  
  - Open another terminal and run code given below:
  ```
  javac Client.java
  java Client localHost 8000
  ```
  
With this the required connection between Client and Server is established and they can communicate among each other. Furthur, details about the format for writing messages and other necessary stuffs are provided to the client at runtime.

## <div align = "left"> Features Implemented </div>
Following features were implemented in the project:

1. Communication between Client-side and Server-side using both TCP/IP and UDP protocols. Corresponding code under each protocol is present in seperate directories. Necessary details of the port on which server would be created is passed as a command line arguments. Although passing details of the port is optional. For instance, `javac Server` would bound the server socket to default port 8000. On other hand, `javac Server 8080` would bound the server socket to port 8080. Similarly, `javac Client` would look for the for server socket at port 8000 in localHost. On the other hand, `javac Client <IP Address> <Port>` would look for the server socket at specified IP address and port number.
2. Server written under both the protocols is capable of doing two distinct things based on user's choice.
    - Server can be used for doing normal communication, that is, transfer of simple textual messages from both sides.
    - Server can be used for performing rudimentary arithmetic operations.
3. At present only four types of arithmetic operations are supported by the server, which are addition, substraction, multiplication and division. For each arithmetic operations, exactly two operands must be given. For example, ADD 4 6 is a valid expression. Details about the format for arithmetic expressions is provided to the client at runtime.
4. Both Client-side and Server-side is capable of terminating the connection by sending "Exit" message. 

## <div align = "left"> Future Goals </div>
Following is the list of the things that can be added to make the project more robust.

1. At present only four arithmetic operations are supported by the Server-side. It can be furthur enhanced to include features like evaluation of a mathematical expression involving multiple such arithmetic operations.
2. At present only one client can connect to the server at a time. Furthur development can be made to connect multiple clients to the server simultaneously so that the requirement of each client can be satisfied efficiently.
3. In addition, multiple clients can also be connected to the server in such a way that they can send messages to each other through the central server.
