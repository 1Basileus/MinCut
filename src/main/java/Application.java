import java.util.*;

public class Application {
    public static void main(String[] args) {
        Application application = new Application();

        Graph graph = application.getNewGraph();

        System.out.println("Initial GraphStructure:");
        System.out.println(graph.getStructureAsString());
        System.out.println("Running MinCut Algorithm");
        System.out.println("");

        int minEdges = graph.getEdges().size();
        List<Graph> minGraphs = new ArrayList<>();
        for(int i=0; i<Configuration.instance.numberOfRuns; i++){
            Graph cutGraph = application.minCut(application.getNewGraph());
            if(Configuration.instance.verbose){
                System.out.println("GraphStructure after MinCut:");
                System.out.println(cutGraph.getStructureAsString());

                System.out.println("If the following edges are removed, the graph is split into two graphs:");
                for(Edge edge : cutGraph.getEdges()){
                    System.out.println(" -> " + edge.getInitialV1().getLabel() + "<->" + edge.getInitialV2().getLabel());
                }
            }

            int cutGraphEdgeCount = cutGraph.getEdges().size();
            if(cutGraphEdgeCount <= minEdges){
                minEdges = cutGraphEdgeCount;
                minGraphs.add(cutGraph);
            }
            if(Configuration.instance.numberOfRuns > 1){
                System.out.println("Number of edges: " + cutGraphEdgeCount + " min: " + minEdges);
            }
        }

        System.out.println("");
        System.out.println("Done!");
        System.out.println("If more than " + minEdges + " edges are removed the graph is split into two graphs.");
        if(Configuration.instance.moreResults){
            if(Configuration.instance.numberOfRuns > 1){
                System.out.println("Candidates:");
            }
            for(Graph g : minGraphs){
                if(g.getEdges().size() == minEdges){
                    for(Edge edge : g.getEdges()){
                        System.out.println("    " + edge.getV1().getLabel() + "<->" + edge.getV2().getLabel());
                    }
                    System.out.println("");
                }
            }
        }
    }

    private Graph minCut(Graph graph){
        int step = 1;
        //Repeat until only two Vertices are left
        while(graph.getVertices().size() != 2){
            if (Configuration.instance.verbose) {
                System.out.println("Step nr." + step + ": ");
            }
            //Take random Edge
            Edge randomEdge = graph.getRandomEdge();
            if (Configuration.instance.verbose) {
                System.out.println("Selected Edge :" + randomEdge.getV1().getLabel() + "<->" + randomEdge.getV2().getLabel());
                //Merge the two Vertices connected to the Edge
                System.out.println("Merging " + randomEdge.getV1().getLabel() + " and " + randomEdge.getV2().getLabel());
            }
            //Merge the two Vertices connected to the Edge
            graph.mergeVertices(randomEdge.getV1(), randomEdge.getV2());
            step ++;

            if (Configuration.instance.verbose) {
                System.out.println(graph.getStructureAsString());
                for(Edge edge : graph.getEdges()){
                    System.out.println(" -> " + edge.getInitialV1().getLabel() + "<->" + edge.getInitialV2().getLabel() + " [" + edge.getV1().getLabel() + "<->" + edge.getV2().getLabel() + "]");
                }
            }
        }
        return graph;
    }

    private Graph getNewGraph(){
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

        return graph;
    }
}
