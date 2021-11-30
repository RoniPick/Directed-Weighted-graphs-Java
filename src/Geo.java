public class Geo implements GeoLocation{
    public double x;
    public double y;
    public double z;
    public double distance;

    public Geo(GeoLocation g){ //constructor
        this.x = g.x();
        this.y = g.y();
        this.z = g.z();
    }

    public Geo(){ //empty constructor
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }
    public Geo(double x, double y, double z){ //numbers constructor - if we dont get Node but we get the data
        this.x = x;
        this.y = y;
        this.z = z;
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

    @Override
    public double distance(GeoLocation g) {
        double dist = Math.sqrt(Math.pow(this.x-g.x(),2)+Math.pow(this.y-g.y(),2)+Math.pow(this.z-g.z(),2));
        return dist;
    }
}
