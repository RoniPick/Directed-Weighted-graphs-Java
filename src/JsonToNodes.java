import api.GeoLocation;
import api.NodeData;

public class JsonToNodes {

    private String pos;
    private int id;

    public JsonToNodes(String pos, int id){
        this.pos = pos;
        this.id = id;
    }

    public JsonToNodes(Node node){
        this.pos = node.getLocation().x() + "," + node.getLocation().y() + "," + node.getLocation().z();
        this.id = node.getKey();
    }

    public Node getNode(){
        Node node = new Node(id, 0.0, 0, getLocation());
        return node;
    }

    public JsonToNodes fromNode(NodeData n){
        GeoLocation l = n.getLocation();
        this.pos = l.x()+","+l.y()+","+l.z();
        this.id = n.getKey();
        return this;
    }

    public double getX(){
        double x = 0;
        String s = this.pos.substring(0, this.pos.indexOf(","));
        x = Double.parseDouble(s);
        return x;
    }

    public double getY(){
        double y = 0;
        int cur = this.pos.indexOf(",") +1;
        int temp = cur+1;
        while(this.pos.charAt(temp)!=','){
            temp++;
        }
        String s = this.pos.substring(cur, temp);
        y = Double.parseDouble(s);
        return y;
    }

    public double getZ(){
        double z = 0;
        int cur = this.pos.indexOf(",") +1;
        int temp = cur+1;
        while(this.pos.charAt(temp)!=','){
            temp++;
        }
        String s = this.pos.substring(temp+1);
        z = Double.parseDouble(s);
        return z;
    }

    public Geo getLocation(){
        Geo g = new Geo(getX(), getY(), getZ());
        return g;
    }

    public int getId() {
        return this.id;
    }

    public String toString(){
        return "id:" + this.id + "[" + this.pos + "]";
    }
}
