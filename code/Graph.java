package code;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    public ArrayList<Vertex> vertices = new ArrayList<>();
    Vertex head;
    Vertex tail;
    int size;
    int jum;
    int warna[];

    public void addVertex(int level, String matkul, int semester, String jam) {
        Vertex newVertex = new Vertex(level, matkul, semester, jam);
        if (isEmpty()) {
            head = newVertex;
            tail = newVertex;
        } else {
            tail.next = newVertex;
            tail = newVertex;
        }
        size++;
        vertices.add(newVertex);
    }

    private boolean isEmpty() {
        return (head == null && tail == null);
    }

    public void insertEdge(String from, String to) {
        Vertex data1 = GetVertex(from);
        Vertex data2 = GetVertex(to);
        data1.edges.add(new Edge(data2));
        data2.edges.add(new Edge(data1));
    }

    public Vertex GetVertex(String searc) {
        if (searc.trim().isEmpty()) {
            System.out.println("Nama matakuliah tidak boleh kosong");
            return null;
        }
        Vertex current = head;
        while (current != null) {
            if (current.matkul.equals(searc)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void printAdj() {
        Vertex current = head;
        for (int i = 0; i < vertices.size(); i++) {
            current = vertices.get(i);
            System.out.println("Vertex: " + current.matkul);
            System.out.print("  => Edges: ");
            for (int j = 0; j < current.edges.size(); j++) {
                System.out.print("[" + current.edges.get(j).dest.matkul + "] ");
            }
            System.out.println();
            System.out.println();
        }
    }

    public int getVertex(Vertex data) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i) == data) {
                return i;
            }
        }
        return -1;
    }

    public void greedyColoring(){
        warna = new int[vertices.size()];
        Arrays.fill(warna, -1);
        warna[0] = 0;
        boolean tersedia[] = new boolean[vertices.size()];
        Arrays.fill(tersedia, true);
        for (int i = 1; i < vertices.size(); i++) {
            Vertex cur = vertices.get(i);
            for (int j = 0; j < cur.edges.size(); j++) {
                Vertex edge = cur.edges.get(j).dest;
                int index = getVertex(edge);
                if (warna[index] != -1) {
                    tersedia[warna[index]] = false;
                }
            }
            int cr;
            for (cr = 0; cr < vertices.size(); cr++) {
                if (tersedia[cr]) {
                    break;
                }
            }
            warna[i] = cr;
            Arrays.fill(tersedia, true);
        }
        for (int i = 0; i < vertices.size(); i++) {
            System.out.println(vertices.get(i).matkul + ": " + warna[i]);
            jum++;
        }

    }

    public void printJadwal() throws InterruptedException, IOException {
        greedyColoring();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        Vertex current = head;
        int v = 0;
        int cek = 0;
        int x = 0;
        int y = 0;
        String days[] = { "Senin", "Selasa", "Rabu", "Kamis", "Jum'at" };
        while (x < jum) {
            System.out.println("");
            System.out.println("Hari " + days[y] + " :");
            System.out.println("----------------------------------------------------------------------");
            System.out.println("| Jam |      Waktu      | Semester |            Mata Kuliah          |");
            System.out.println("======================================================================");
            while (current != null) {
                if (x == warna[v]) {
                    System.out.println("|  " + current.level + "  |   " + current.jam + "   |     " + current.semester
                            + "    | " + current.matkul + " |");
                    cek++;
                }
                v++;
                current = current.next;
            }
            if (cek == 0) {
                System.out.println("Tidak ada jadwal");
                break;
            }
            System.out.println("----------------------------------------------------------------------");
            x++;
            y++;
            if (y == 5) {
                y = 0;
            }
            v = 0;
            cek = 0;
            current = head;

        }
    }

    public void BFS(Vertex start, Vertex target) {
        if (start == null) {
            System.out.println("Matakuliah tidak ditemukan");
            return;
        }
        Queue<Vertex> queue = new LinkedList<>();
        start.visited = true;
        queue.add(start);
        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            if (current == target) {
                System.out.println(" ");
                System.out.println("Data Mata Kuliah Ditemukan : " + current.matkul);
                System.out.println("Mata Kuliah Semester       : " + current.semester);
                System.out.println("Waktu Pelaksanaan          : " + current.jam);
                return;
            }
            for (int i = 0; i < current.edges.size(); i++) {
                Vertex neighbor = current.edges.get(i).dest;
                if (!neighbor.visited) {
                    neighbor.visited = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    public static void addEdgesByLevel(Graph graph) {
        for (int i = 0; i < graph.size; i++) {
            for (int j = (i + 1); j < graph.size; j++) {
                if (graph.vertices.get(i).level == graph.vertices.get(j).level) {
                    graph.insertEdge(graph.vertices.get(i).matkul, graph.vertices.get(j).matkul);
                }
            }
        }
    }
}
