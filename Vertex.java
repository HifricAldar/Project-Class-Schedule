import java.util.ArrayList;

public class Vertex {
    int level;
    String matkul;
    int semester;
    String jam;

    Vertex next;
    boolean visited = false;
   public ArrayList<Edge> edges;
    Vertex(int level, String matkul, int semester, String jam) {
        this.level = level;
        this.matkul = matkul;
        this.semester = semester;
        this.jam = jam;
        edges = new ArrayList<Edge>();
    }
}
