public class Edge {
    private Vertex v1;
    private Vertex v2;

    public Edge(Vertex v1, Vertex v2){
        this.v1 = v1;
        this.v2 = v2;
    }

    public Vertex getV1() {
        return v1;
    }

    public Vertex getV2() {
        return v2;
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
}
