import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;

public class radiusServer {
        private static ServerSocket server;
        private static int port = 9998;
        public static void main(String[] args) throws IOException, ClassNotFoundException {

            server = new ServerSocket(port); //listen out for connections
            ObjectInputStream input;
            ObjectOutputStream output;
            //keep listening indefintly untill you receive an "exit" call or program terminates

            while(true){
                System.out.println("waiting to establish client connection");
                Socket socket = server.accept(); //information from client stored here
                input = new ObjectInputStream(socket.getInputStream()); //grab the input and stream into bytes after

                String message = (String) input.readObject();

                System.out.println("Message received");

                output = new ObjectOutputStream(socket.getOutputStream());

                if(message.equals("exit") || message.equals("Exit")){
                    System.out.println("Connection closed bye!");
                    break;
                }

                try{
                    double radius = Double.parseDouble(message);
                    double area = radius * radius * Math.PI;
                    output.writeObject("Server Message " + area);

                }catch (NumberFormatException e){
                    output.writeObject("Unrecognised Message");
                }catch(Exception e){
                    e.printStackTrace();
                }

            }

            //close all open resources
            input.close();
            output.close();

        }
}
