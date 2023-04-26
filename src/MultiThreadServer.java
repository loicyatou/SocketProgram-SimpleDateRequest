import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer {
    public static void main(String[] args){
        ServerSocket server = null;

        try{
            //server is listening on port 1234
            server = new ServerSocket(9898);

            //run infinite loop waiitng for client request
            Socket client = server.accept();
            System.out.println("Server Is Running");

            //create a new thread object
            ClientHandler clientSock = new ClientHandler(client, "Client 1");
            clientSock.start();

            ClientHandler clientSock2 = new ClientHandler(client, "Client 2");
            clientSock2.start();

            ClientHandler clientSock3 = new ClientHandler(client, "Client 3");
            clientSock3.start();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(server != null){
                try {
                    server.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        private Thread t;

        private String threadName;

        public ClientHandler(Socket socket, String threadname){
            this.clientSocket = socket;
            this.threadName = threadname;
        }

        public void start() { //3. creates a new thread using the runnable instantiation as the first argument and the name of the thread as the second
            System.out.println(threadName + " is connected with IP " + clientSocket.getInetAddress().getHostAddress() );
            if (t == null) {
                t = new Thread (this, threadName);
                t.start (); //4. calling the start method then triggers the .run() method
            }
        }

        @Override
        public void run() {
            PrintWriter output = null;
            BufferedReader input = null;

            try{
                //get the output the server wants to send back
                output = new PrintWriter(clientSocket.getOutputStream(),true);

                //recieve the input from the client
                input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String line;

                while((line = input.readLine()) != null){
                    //print message received from client

                    System.out.printf(" Sent from the client " + threadName + ": %s\n", line);
                    output.println(line);
                }



            }catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                        System.out.println(threadName + " has disconnected");
                    if (output != null) {
                        output.close();
                    }
                    if (input != null) {
                        input.close();
                        clientSocket.close();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
