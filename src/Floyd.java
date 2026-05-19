import java.util.ArrayList;

public class Floyd {
    private int[][] distancias;
    private String[][] caminos;
    private ArrayList<String> ciudades;

    public Floyd(Grafo grafo) {

        ciudades = grafo.getCiudades();

        int[][] original = grafo.getMatriz();

        int n = original.length;

        distancias = new int[n][n];
        caminos = new String[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                distancias[i][j] = original[i][j];

                caminos[i][j] = "";
            }
        }
    }

    public void calcular() {

        int n = distancias.length;

        for (int k = 0; k < n; k++) {

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {

                    if (distancias[i][k] + distancias[k][j]
                            < distancias[i][j]) {

                        distancias[i][j] =
                                distancias[i][k]
                                        + distancias[k][j];

                        caminos[i][j] = ciudades.get(k);
                    }
                }
            }
        }
    }

    public void rutaMasCorta(String origen,
                             String destino) {

        int i = ciudades.indexOf(origen);
        int j = ciudades.indexOf(destino);

        if (distancias[i][j] >= Grafo.INF) {

            System.out.println("No existe ruta");
            return;
        }

        System.out.println("\nDistancia mínima: "
                + distancias[i][j] + " KM");

        System.out.print("Ruta: " + origen);

        imprimirIntermedios(i, j);

        System.out.println(" -> " + destino);
    }

    private void imprimirIntermedios(int i, int j) {

        if (!caminos[i][j].equals("")) {

            String intermedio = caminos[i][j];

            int k = ciudades.indexOf(intermedio);

            imprimirIntermedios(i, k);

            System.out.print(" -> " + intermedio);

            imprimirIntermedios(k, j);
        }
    }

    public String centroGrafo() {

        int n = distancias.length;

        int min = Grafo.INF;

        String centro = "";

        for (int i = 0; i < n; i++) {

            int excentricidad = 0;

            for (int j = 0; j < n; j++) {

                if (distancias[i][j] > excentricidad) {

                    excentricidad = distancias[i][j];
                }
            }

            if (excentricidad < min) {

                min = excentricidad;

                centro = ciudades.get(i);
            }
        }

        return centro;
    }

    public int obtenerDistancia(String origen,
                                String destino) {

        int i = ciudades.indexOf(origen);
        int j = ciudades.indexOf(destino);

        return distancias[i][j];
    }
}
