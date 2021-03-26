import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private List<Vertex> vertices;
    private List<Edge> edges;

    Graph() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public Edge getRandomEdge(){
        return edges.get((int) (Math.random()*edges.size()));
    }

    public String getStructureAsString(){
        StringBuilder sb = new StringBuilder();

        for(Vertex vertex : vertices){
            sb.append(vertex.getLabel());
            sb.append(": ");
            List<Edge> connectedEdges = getEdgesForVertex(vertex);
            for(Edge edge : connectedEdges){
                if(edge.getV1().equals(vertex)){
                    sb.append(edge.getV2().getLabel());
                }else{
                    sb.append(edge.getV1().getLabel());
                }
                sb.append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public void addVertex(Vertex vertex) {
        if(!vertices.contains(vertex)){
            vertices.add(vertex);
        }
    }

    public void removeVertex(Vertex vertex) {
        vertices.remove(vertex);
    }

    public void addEdge(Edge edge) {
        for(Edge e : edges){
            if(e.equals(edge)){
                return;
            }
        }
        if(vertices.contains(edge.getV1()) && vertices.contains(edge.getV2())){
            edges.add(edge);
        }
    }

    public void removeEdge(Edge edge) {
        for(Edge e : edges){
            if(e.equals(edge)){
                edges.remove(e);
                return;
            }
        }
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public Vertex getVertexByLabel(String label){
        for(Vertex vertex : vertices){
            if(vertex.getLabel().equals(label)){
                return vertex;
            }
        }
        return null;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Edge> getEdgesForVertex(Vertex vertex){
        List<Edge> connectedEdges = new ArrayList<>();
        for(Edge edge : this.edges){
            if(edge.getV1().equals(vertex)){
                connectedEdges.add(edge);
            }else if(edge.getV2().equals(vertex)){
                connectedEdges.add(new Edge(vertex,edge.getV1()));
            }
        }
        return connectedEdges;
    }
}