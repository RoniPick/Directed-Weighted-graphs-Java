import api.GeoLocation;

public class Geo implements GeoLocation{
    public double x;
    public double y;
    public double z;

    public Geo(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Geo(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Geo(GeoLocation g){
        this.x = g.x();
        this.y = g.y();
        this.z = g.z();
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public double distance(GeoLocation g) {
        double dist = Math.sqrt(Math.pow(this.x-g.x(),2)+Math.pow(this.y-g.y(),2)+Math.pow(this.z-g.z(),2));
        return dist;
    }
}
