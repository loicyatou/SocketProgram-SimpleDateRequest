import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class DatagramWeatherSend {

    public static void main(String[] args) throws IOException {

        DatagramSocket ds = new DatagramSocket();

        Scanner in = new Scanner(System.in);

        String str = in.nextLine();

        InetAddress ip = InetAddress.getLocalHost();

        DatagramPacket dp = new DatagramPacket(str.getBytes(),str.length(),ip,9889);
        ds.send(dp);

        //receive the dates response
        DatagramSocket response = new DatagramSocket(9888);
        byte[] buffer = new byte[1024];

        DatagramPacket storeResponse = new DatagramPacket(buffer,1024);
        response.receive(storeResponse);

        String receive = new String(storeResponse.getData(),0,storeResponse.getLength());
        System.out.println(receive);

        ds.close();
    }
}
