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
    private enum TYPE {
        LOSS_FREE, MINOR_LOSS, SIGNIFICANT_LOSS, MAJOR_LOSS, FAIL, ERROR
    }
    private TYPE type;
   

    public PingBlock(Host host, String timestamp, String packetLoss, 
            double min, double avg, double max, double mdev, TYPE type) {
        this.host = host;
        this.timestamp = timestamp;
        this.packetLoss = packetLoss;
        this.min = min;
        this.avg = avg;
        this.max = max;
        this.mdev = mdev;
        this.type = type;
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

    public TYPE getType() {
        return type;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setPacketLoss(String packetLoss) {
        packetLoss = packetLoss.replaceAll("\\s+", "");
        this.packetLoss = packetLoss;
        int loss = 0;
        if("+1errors".equals(packetLoss)){
            this.setType(TYPE.ERROR);
        } else {
            loss = Integer.parseInt(packetLoss);
        }
        if(loss == 0){
            this.setType(TYPE.LOSS_FREE);
        } else if(loss > 0 && loss <= 5){
            this.setType(TYPE.LOSS_FREE);
        } else if(loss > 5 && loss <= 10){
            this.setType(TYPE.SIGNIFICANT_LOSS);
        } else if(loss > 10){
            this.setType(TYPE.MAJOR_LOSS);
        } else if(loss == 100){
            this.setType(TYPE.FAIL);
        } else {
            this.setType(TYPE.ERROR);
        }
            
        
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

    public void setType(TYPE type) {
        this.type = type;
    } 
    
}
