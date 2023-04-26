import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class radiusClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        InetAddress host = InetAddress.getLocalHost();
        Socket socket;
        ObjectInputStream input;
        ObjectOutputStream output;
        String message;

        while (true){
            socket = new Socket(host.getHostName(), 9998);

            //write to socket using output strea
            output = new ObjectOutputStream(socket.getOutputStream());

            Scanner in = new Scanner(System.in);
            message = in.nextLine();

            if(message.equals("Exit") || message.equals("exit")){
                output.writeObject("exit");
                break;
            } else{
                output.writeObject(message);
            }

            input = new ObjectInputStream(socket.getInputStream());
            message = (String) input.readObject();

            System.out.println(message);
            //close resources

            input.close();
            output.close();
        }
    }
}
