
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author xze15agu
 */
public class ReadLogs {

    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        try {
            FileInputStream fstream = new FileInputStream("ping.log");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;

            while ((strLine = br.readLine()) != null) {
                list.add(strLine);
            }
            fstream.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        ArrayList<Host> hosts = new ArrayList<>();
        hosts.add(new Host("PLACEHOLDER", "45.221.77.2"));
        hosts.add(new Host("PLACEHOLDER", "116.90.239.10"));
        hosts.add(new Host("PLACEHOLDER", "41.72.99.143"));
        hosts.add(new Host("PLACEHOLDER", "163.43.24.70"));
        hosts.add(new Host("PLACEHOLDER", "133.130.35.170"));
        hosts.add(new Host("PLACEHOLDER", "103.242.31.11"));
        hosts.add(new Host("PLACEHOLDER", "103.75.188.168"));
        hosts.add(new Host("PLACEHOLDER", "107.190.134.163"));
        hosts.add(new Host("PLACEHOLDER", "103.241.150.190"));
        hosts.add(new Host("PLACEHOLDER", "112.137.166.236"));
        hosts.add(new Host("PLACEHOLDER", "178.33.235.187"));
        hosts.add(new Host("PLACEHOLDER", "101.100.209.220"));
        hosts.add(new Host("PLACEHOLDER", "116.12.51.45"));
        hosts.add(new Host("PLACEHOLDER", "188.42.162.69"));
        hosts.add(new Host("PLACEHOLDER", "116.12.51.45"));
        hosts.add(new Host("PLACEHOLDER", "180.92.193.10"));
        hosts.add(new Host("PLACEHOLDER", "104.20.72.23"));
        hosts.add(new Host("PLACEHOLDER", "202.78.202.70"));
        hosts.add(new Host("PLACEHOLDER", "110.4.45.6"));
        hosts.add(new Host("PLACEHOLDER", "200.170.94.6"));

        int count = 0;
        FileWriter writer = new FileWriter("ping.csv");
        StringBuilder sb = new StringBuilder();
        Host h = null;
        String columns = "Date, Host, Packet loss, min, avg, max, mdev";
        sb.append(columns);
        sb.append("\n");
        writer.append(sb.toString());
        PingBlock ping = null;
        ArrayList<PingBlock> blockList = new ArrayList<>();
        
        for (int i = 0; i < list.size(); i++) {
            String current = list.get(i);

            if (current.startsWith("PING")) {
                h = hosts.get(count);
                count++;
            }
            if (current.contains("BST")) {
                sb = new StringBuilder();
                sb.append(current);
                sb.append(", ");
                ping.setTimestamp(current);
            } else if (current.startsWith("-")) {
                String packetLossLine = list.get(i + 1);
                packetLossLine = h.getIP() + ", " + packetLossLine;
                String[] blocks = packetLossLine.split(",");
                blocks[3] = blocks[3].replaceAll(" packet loss", "");
                ping.setPacketLoss(blocks[3]);
                h = hosts.get(count);
                ping.setHost(h);
                // ip
                sb.append(blocks[0]);
                sb.append(", ");
                // packet loss
                sb.append(blocks[3]);
                sb.append(", ");

            } else if (current.startsWith("rtt")) {
                
                String rttData = list.get(i);
                rttData = rttData.replaceFirst("rtt min/avg/max/mdev = ", "");
                rttData = rttData.replaceAll(" ms", "");
                rttData = rttData.replaceAll("/", ", ");
                sb.append(rttData);
                sb.append("\n");
                writer.append(sb.toString());

            }
            if (count == 20) {
                count = 0;
            }
        }
        writer.close();
    }
}
