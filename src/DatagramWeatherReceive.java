import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.time.LocalDate;
import java.util.Scanner;

public class DatagramWeatherReceive {

    public static void main(String[] args) throws IOException {

        //accepting connectinons to this socket and storing bytes into buffer
        DatagramSocket ds = new DatagramSocket(9889);
        byte[] buffer = new byte[1024];

        while (true){
            //prep the packet
            DatagramPacket dp = new DatagramPacket(buffer,1024);
            ds.receive(dp);
            String recieve = new String(dp.getData(),0,dp.getLength());
            System.out.println(recieve);

            if(recieve.equals("Exit") || recieve.equals("exit")){
                ds.close();
                System.out.println("Closing Connection");
                break;
            }

            //send the date back to them
            if(recieve.equals("date") || recieve.equals("Date")){
                DatagramSocket sendDate = new DatagramSocket();

                String str = "The date is: " + LocalDate.now();

                InetAddress ip = InetAddress.getLocalHost();

                DatagramPacket sendDp = new DatagramPacket(str.getBytes(),str.length(),ip,9888);
                sendDate.send(sendDp);
            }
        }

        ds.close();
    }

}
