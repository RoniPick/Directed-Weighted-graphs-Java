public class keys {
    private int src;
    private int dest;

    public keys(){ //empty constructor
        this.src = 0;
        this.dest = 0;
    }

    public keys(int s, int d){ //constructor
        this.src = s;
        this.dest = d;
    }

    public int getSrc() {
        return src;
    }

    public int getDest() {
        return dest;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }
}
