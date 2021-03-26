import java.util.*;

public class Application {
    public static void main(String[] args) {
        Application application = new Application();

        Graph graph = new Graph();
        //Add Vertices to Graph
        graph.addVertex(new Vertex("a"));
        graph.addVertex(new Vertex("b"));
        graph.addVertex(new Vertex("c"));
        graph.addVertex(new Vertex("d"));
        graph.addVertex(new Vertex("e"));
        graph.addVertex(new Vertex("f"));
        graph.addVertex(new Vertex("g"));
        //Add Edges to Graph
        graph.addEdge(new Edge(graph.getVertexByLabel("a"),graph.getVertexByLabel("b")));
        graph.addEdge(new Edge(graph.getVertexByLabel("a"),graph.getVertexByLabel("f")));
        graph.addEdge(new Edge(graph.getVertexByLabel("a"),graph.getVertexByLabel("g")));
        graph.addEdge(new Edge(graph.getVertexByLabel("b"),graph.getVertexByLabel("c")));
        graph.addEdge(new Edge(graph.getVertexByLabel("b"),graph.getVertexByLabel("d")));
        graph.addEdge(new Edge(graph.getVertexByLabel("c"),graph.getVertexByLabel("d")));
        graph.addEdge(new Edge(graph.getVertexByLabel("d"),graph.getVertexByLabel("e")));
        graph.addEdge(new Edge(graph.getVertexByLabel("e"),graph.getVertexByLabel("f")));
        graph.addEdge(new Edge(graph.getVertexByLabel("f"),graph.getVertexByLabel("g")));

        System.out.println(graph.getStructureAsString());
        System.out.println("");
        System.out.println("Running MinCut Algorithm");
        System.out.println("");

        Graph cutGraph = application.minCut(graph);
        for(Edge edge : cutGraph.getEdges()){
            System.out.println(edge.getV1().getLabel() + " + " + edge.getV2().getLabel());
        }
        System.out.println("Done!");
    }

    private Graph minCut(Graph graph){
        List<Edge> removedEdges = new ArrayList<>();
        //Repeat until only two Vertices are left
        while(graph.getVertices().size() != 2){
            //Take random Edge
            Edge randomEdge = graph.getRandomEdge();
            System.out.println("Selected Edge :" + randomEdge.getV1().getLabel() + "<->" + randomEdge.getV2().getLabel());
            //Merge the two Vertices connected to the Edge
            System.out.println("Merging " + randomEdge.getV1().getLabel() + " and " + randomEdge.getV2().getLabel());
            Vertex newVertex = new Vertex(randomEdge.getV1().getLabel() + randomEdge.getV2().getLabel());
            graph.addVertex(newVertex);
            for(Edge edge : graph.getEdgesForVertex(randomEdge.getV1())){
                if(!edge.getV2().equals(randomEdge.getV2())){
                    System.out.println("Added edge :" + newVertex.getLabel() + " + " + edge.getV2().getLabel());
                    graph.addEdge(new Edge(newVertex,edge.getV2()));
                }else{
                    removedEdges.add(edge);
                }
                System.out.println("Removed edge :" + edge.getV1().getLabel() + " + " + edge.getV2().getLabel());
                graph.removeEdge(edge);
            }
            for(Edge edge : graph.getEdgesForVertex(randomEdge.getV2())){
                if(!edge.getV2().equals(randomEdge.getV1())){
                    System.out.println("Added edge :" + newVertex.getLabel() + " + " + edge.getV2().getLabel());
                    graph.addEdge(new Edge(newVertex,edge.getV2()));
                }else{
                    removedEdges.add(edge);
                }
                System.out.println("Removed edge :" + edge.getV1().getLabel() + " + " + edge.getV2().getLabel());
                graph.removeEdge(edge);
            }
            graph.removeVertex(randomEdge.getV1());
            graph.removeVertex(randomEdge.getV2());
            System.out.println(graph.getStructureAsString());
            for(Edge edge : graph.getEdges()){
                System.out.println(edge.getV1().getLabel() + " + " + edge.getV2().getLabel());
            }
            System.out.println("---------------------------");
        }
        for(Edge edge : removedEdges){
            System.out.println(edge.getV1().getLabel() + " + " + edge.getV2().getLabel());
        }
        return graph;
    }
}
