/**
 *
 * @author xze15agu
 */
public class PingBlock {
    private Host host;
    private String timestamp;
    private String packetLoss;
    private double min;
    private double avg;
    private double max;
    private double mdev;
    private String type;
   

    public PingBlock(Host host, String timestamp, String packetLoss, double min, double avg, double max, double mdev) {
        this.host = host;
        this.timestamp = timestamp;
        this.packetLoss = packetLoss;
        this.min = min;
        this.avg = avg;
        this.max = max;
        this.mdev = mdev;
    }
    
    public PingBlock(){
        this.host = null;
        this.timestamp = "";
        this.packetLoss = "";
        this.min = 0;
        this.avg = 0;
        this.max = 0;
        this.mdev = 0;    
    }

    public Host getHost() {
        return host;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getPacketLoss() {
        return packetLoss;
    }
    
    public double getMin() {
        return min;
    }

    public double getAvg() {
        return avg;
    }

    public double getMax() {
        return max;
    }

    public double getMdev() {
        return mdev;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setPacketLoss(String packetLoss) {
        this.packetLoss = packetLoss;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public void setMdev(double mdev) {
        this.mdev = mdev;
    }
    
    
}
