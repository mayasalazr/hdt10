import java.io.*;
import java.util.*;

public class Grafo {

    public static final int INF = 999999;
    private ArrayList<String> ciudades;
    private int[][] matriz;

    public Grafo() {
        ciudades = new ArrayList<>();
    }

    public void cargarArchivo(String archivo) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(archivo));

        ArrayList<String[]> datos = new ArrayList<>();

        String linea;

        while ((linea = br.readLine()) != null) {

            String[] partes = linea.split(" ");

            datos.add(partes);

            if (!ciudades.contains(partes[0])) {
                ciudades.add(partes[0]);
            }

            if (!ciudades.contains(partes[1])) {
                ciudades.add(partes[1]);
            }
        }

        int n = ciudades.size();

        matriz = new int[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                if (i == j) {
                    matriz[i][j] = 0;
                } else {
                    matriz[i][j] = INF;
                }
            }
        }

        for (String[] d : datos) {

            int origen = ciudades.indexOf(d[0]);
            int destino = ciudades.indexOf(d[1]);
            int distancia = Integer.parseInt(d[2]);

            matriz[origen][destino] = distancia;
        }

        br.close();
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public ArrayList<String> getCiudades() {
        return ciudades;
    }


    public void agregarConexion(String origen,
                                String destino,
                                int distancia) {

        int i = ciudades.indexOf(origen);
        int j = ciudades.indexOf(destino);

        matriz[i][j] = distancia;
    }


    public void eliminarConexion(String origen,
                                 String destino) {

        int i = ciudades.indexOf(origen);
        int j = ciudades.indexOf(destino);

        matriz[i][j] = INF;
    }


    public int obtenerDistancia(String origen,
                                String destino) {

        int i = ciudades.indexOf(origen);
        int j = ciudades.indexOf(destino);

        return matriz[i][j];
    }

    
    public void mostrarMatriz() {

        System.out.println("\nMATRIZ:");

        for (int[] fila : matriz) {

            for (int valor : fila) {

                if (valor >= INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(valor + " ");
                }
            }

            System.out.println();
        }
    }
}