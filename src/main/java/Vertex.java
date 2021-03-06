public class Vertex {
    private String label;
    public Vertex(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Vertex)) {
            return false;
        }
        Vertex vertex = (Vertex) o;
        return label == vertex.label;
    }

    public Vertex clone(){
        return new Vertex(label);
    }
}
