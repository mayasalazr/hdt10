import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {

            Scanner sc = new Scanner(System.in);

            Grafo grafo = new Grafo();

            grafo.cargarArchivo("guategrafo.txt");

            Floyd floyd = new Floyd(grafo);

            floyd.calcular();

            int opcion;

            do {

                System.out.println("\n===== MENÚ =====");

                System.out.println("1. Ruta más corta");
                System.out.println("2. Centro del grafo");
                System.out.println("3. Modificar grafo");
                System.out.println("4. Mostrar matriz");
                System.out.println("5. Salir");

                opcion = sc.nextInt();

                sc.nextLine();

                switch (opcion) {

                    case 1:

                        System.out.print("Origen: ");
                        String origen = sc.nextLine();

                        System.out.print("Destino: ");
                        String destino = sc.nextLine();

                        floyd.rutaMasCorta(origen, destino);

                        break;

                    case 2:

                        System.out.println("\nCentro del grafo: "
                                + floyd.centroGrafo());

                        break;

                    case 3:

                        System.out.println("\n1. Eliminar conexión");
                        System.out.println("2. Agregar conexión");

                        int sub = sc.nextInt();

                        sc.nextLine();

                        if (sub == 1) {

                            System.out.print("Origen: ");
                            String o = sc.nextLine();

                            System.out.print("Destino: ");
                            String d = sc.nextLine();

                            grafo.eliminarConexion(o, d);

                        } else {

                            System.out.print("Origen: ");
                            String o = sc.nextLine();

                            System.out.print("Destino: ");
                            String d = sc.nextLine();

                            System.out.print("Distancia: ");

                            int km = sc.nextInt();

                            grafo.agregarConexion(o, d, km);
                        }

                        floyd = new Floyd(grafo);

                        floyd.calcular();

                        System.out.println("\nGrafo actualizado");

                        break;

                    case 4:

                        grafo.mostrarMatriz();

                        break;
                }

            } while (opcion != 5);

        } catch (Exception e) {

            System.out.println("Error: "
                    + e.getMessage());
        }
    }
    
}
