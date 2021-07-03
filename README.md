### <div align = "center"> Client Server Communication  </div>
The repository holds the project work that I did on Client-Server Commumnication Model using Socket Programming under the guidance of IIT Roorkee ACM Student Chapter.

---

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
  
With this the required connection between Client and Server is established and they can communicate among each other.
