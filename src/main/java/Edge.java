public class Edge {
    private Vertex v1;
    private Vertex v2;
    private Vertex initialV1;
    private Vertex initialV2;

    public Edge(Vertex v1, Vertex v2){
        this.v1 = v1;
        this.v2 = v2;
        this.initialV1 = v1.clone();
        this.initialV2 = v2.clone();
    }

    public Vertex getV1() {
        return v1;
    }

    public void setV1(Vertex v1) {
        this.v1 = v1;
    }

    public Vertex getV2() {
        return v2;
    }

    public void setV2(Vertex v2) {
        this.v2 = v2;
    }

    public Vertex getInitialV1() {
        return initialV1;
    }

    public void setInitialV1(Vertex initialV1) {
        this.initialV1 = initialV1;
    }

    public Vertex getInitialV2() {
        return initialV2;
    }

    public void setInitialV2(Vertex initialV2) {
        this.initialV2 = initialV2;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Edge)) {
            return false;
        }
        Edge edge = (Edge) o;
        boolean case1 = v1.equals(edge.getV1()) && v2.equals(edge.getV2());
        boolean case2 = v1.equals(edge.getV2()) && v2.equals(edge.getV1());
        return case1 || case2;
    }

    public Edge clone(){
        Edge e = new Edge(this.v1,this.v2);
        e.setInitialV1(this.initialV1);
        e.setInitialV2(this.initialV2);
        return e;
    }

    public Edge clone(Vertex v1, Vertex v2){
        Edge e = new Edge(v1,v2);
        e.setInitialV1(this.initialV1);
        e.setInitialV2(this.initialV2);
        return e;
    }
}
