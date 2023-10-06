import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        Graph graph = new Graph();
        graph.addVertex(1, "Pemrograman Internet", 7, "08:00-10:00");
        graph.addVertex(2, "Keamanan Teknologi Informasi", 5, "10:15-12:15");
        graph.addVertex(3, "Jaringan Syaraf Tiruan", 7, "13:00-15:00");
        graph.addVertex(1, "Teori Bahasa dan Otomata", 5, "08:00-10:00");
        graph.addVertex(2, "Proyek Perangkat Lunak", 7, "10:15-12:15");
        graph.addVertex(3, "Pengantar Teknologi Informasi", 1, "13:00-15:00");
        graph.addVertex(1, "Metode Numerik", 3, "08:00-10:00");
        graph.addVertex(2, "Pemrograman Berorientasi Objek", 5, "10:15-12:15");
        graph.addVertex(3, "Riset Operasi", 5, "13:00-15:00");
        graph.addVertex(1, "Jaringan Komputer", 3, "08:00-10:00");
        graph.addVertex(2, "Etika Profesi", 5, "10:15-12:15");
        graph.addVertex(3, "Interaksi Manusia dan Komputer", 3, "13:00-15:00");
        graph.addVertex(1, "Kecerdasan Buatan", 3, "08:00-10:00");
        graph.addVertex(2, "Sistem Operasi", 5, "10:15-12:15");
        graph.addVertex(3, "Sistem Informasi", 5, "14:00-16:00");
        graph.addVertex(1, "Logika Informatika", 1, "08:00-10:00");
        graph.addVertex(2, "Kewirausahaan", 1, "10:15-12:15");
        graph.addVertex(3, "Sistem Basis Data", 3, "13:00-15:00");
        graph.addVertex(1, "Pancasila", 1, "08:00-10:00");
        graph.addVertex(2, "Kalkulus", 1, "10:15-12:15");

        Graph.addEdgesByLevel(graph);
        Scanner scan = new Scanner(System.in);
        int input = 0;
        int choice = 0;
        while (input != 4) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.println("");
            System.out.println("        ------------------------");
            System.out.println("\t|\tWELCOME        |");
            System.out.println("        ------------------------");
            System.out.println("\t| 1. Melihat Jadwal    |");
            System.out.println("\t| 2. Tambahkan Jadwal  |");
            System.out.println("\t| 3. Cari Jadwal       |");
            System.out.println("\t| 4. Keluar            |");
            System.out.println("        ------------------------");
            System.out.print("\t  Masukkan pilihan: ");
            choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 1:
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    graph.printJadwal();
                    System.out.println();
                    System.out.print("Tekan Enter Untuk Kembali Ke Menu Utama");
                    scan.nextLine();
                    break;
                case 2:
                    addCourse(graph);
                    System.out.print("Tekan Enter Untuk Kembali Ke Menu Utama");
                    scan.nextLine();
                    break;
                case 3:
                    SearchMatkul(graph);
                    System.out.println("");
                    System.out.print("Tekan Enter Untuk Kembali Ke Menu Utama");
                    scan.nextLine();
                    break;
                case 4:
                    System.out.println("Terima Kasih :)");
                    System.out.println("");
                    return;
                default:
                    System.out.println("Pilihan Yang dimasukkan salah, kembali ke Menu Utama");
                    System.out.print("Tekan Enter Untuk Kembali Ke Menu Utama");
                    scan.nextLine();
                    break;
            }
        }
        scan.close();
    }

    public static void addCourse(Graph graph) {
        Scanner scan1 = new Scanner(System.in);
        System.out.println(" ");
        System.out.print("Masukkan Jumlah Data: ");
        int dataMatkul = scan1.nextInt();
        scan1.nextLine();
        int newlevel;
        String newMatkul;
        int newSemester;
        String newjam;
        for (int i = 0; i < dataMatkul; i++) {
            System.out.print("Data ke-" + (i + 1) + " :\n");
            System.out.print("Masukkan Level \t: ");
            newlevel = scan1.nextInt();
            scan1.nextLine();
            System.out.print("Masukkan Nama Mata Kuliah \t: ");
            newMatkul = scan1.nextLine();
            System.out.print("Masukkan Semester \t: ");
            newSemester = scan1.nextInt();
            scan1.nextLine();
            System.out.print("Masukkan waktu Pelaksanaan \t: ");
            newjam = scan1.nextLine();
            graph.addVertex(newlevel, newMatkul, newSemester, newjam);
        }
        Graph.addEdgesByLevel(graph);
        System.out.println("........");
        System.out.println("Mata Kuliah Berhasil Ditambahkan");
        System.out.println("");
    }

    public static void SearchMatkul(Graph graph) {
        Scanner scan2 = new Scanner(System.in).useDelimiter(" ");
        System.out.println(" ");
        System.out.print("Masukkan nama matkul yang ingin dicari: ");
        String searchTerm = scan2.nextLine();
        Vertex start = graph.GetVertex(searchTerm);
        if (start != null) {
            Vertex target = graph.GetVertex(searchTerm);
            if (target != null) {
                graph.BFS(start, target);
            } else {
                System.out.println("Matakuliah tidak ditemukan");
            }
        } else {
            System.out.println("Matakuliah tidak ditemukan");
        }
    }
}
