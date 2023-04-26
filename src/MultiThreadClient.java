import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MultiThreadClient {

    public static void main(String[] args){

    try(Socket socket = new Socket(InetAddress.getLocalHost(), 9898)) {

        //writer to server
        PrintWriter output = new PrintWriter(socket.getOutputStream(),true); //auto flushes the buffer and sends the contents to the outputdevice. means the printer is constatly porcessing data and is not delayed by buffering

        //reading from server
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        Scanner user = new Scanner(System.in);
        String line = null;

        while(!"exit".equalsIgnoreCase(line)){
            //read what the user says
            line = user.nextLine();

            //send the user input to the server
            output.println(line);
            output.flush();

            //display to the console what the server replied with
            System.out.println("Server replied " + input.readLine());
        }

        user.close();

    } catch (IOException e) {
        e.printStackTrace();
        }
    }
}
