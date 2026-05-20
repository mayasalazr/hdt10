import networkx as nx


class Grafo:

    def __init__(self):

        self.grafo = nx.DiGraph()

    def cargar_archivo(self, archivo):

        with open(archivo, "r") as f:

            for linea in f:

                origen, destino, distancia = linea.strip().split()

                self.grafo.add_edge(
                    origen,
                    destino,
                    weight=int(distancia)
                )

    def mostrar_matriz(self):

        print("\nMatriz de adyacencia:\n")

        ciudades = list(self.grafo.nodes())

        for origen in ciudades:

            for destino in ciudades:

                if self.grafo.has_edge(origen, destino):

                    peso = self.grafo[origen][destino]["weight"]

                    print(f"{peso}\t", end="")

                else:

                    print("INF\t", end="")

            print()

    def ruta_mas_corta(self, origen, destino):

        try:

            ruta = nx.shortest_path(
                self.grafo,
                source=origen,
                target=destino,
                weight="weight"
            )

            distancia = nx.shortest_path_length(
                self.grafo,
                source=origen,
                target=destino,
                weight="weight"
            )

            print("\nRuta más corta:")

            print(" -> ".join(ruta))

            print(f"\nDistancia total: {distancia} KM")

        except nx.NetworkXNoPath:

            print("\nNo existe ruta.")

    def centro_grafo(self):

        floyd = dict(nx.floyd_warshall(self.grafo))

        excentricidades = {}

        for ciudad in floyd:

            max_distancia = max(floyd[ciudad].values())

            excentricidades[ciudad] = max_distancia

        centro = min(excentricidades,
                     key=excentricidades.get)

        return centro

    def eliminar_conexion(self, origen, destino):

        if self.grafo.has_edge(origen, destino):

            self.grafo.remove_edge(origen, destino)

            print("\nConexión eliminada.")

        else:

            print("\nLa conexión no existe.")

    def agregar_conexion(self,
                         origen,
                         destino,
                         distancia):

        self.grafo.add_edge(
            origen,
            destino,
            weight=distancia
        )

        print("\nConexión agregada.")


def main():

    g = Grafo()

    g.cargar_archivo("guategrafo.txt")

    while True:

        print("\n===== MENÚ =====")

        print("1. Ruta más corta")
        print("2. Centro del grafo")
        print("3. Modificar grafo")
        print("4. Mostrar matriz")
        print("5. Salir")

        opcion = input("\nSeleccione opción: ")

        if opcion == "1":

            origen = input("Origen: ")
            destino = input("Destino: ")

            g.ruta_mas_corta(origen, destino)

        elif opcion == "2":

            centro = g.centro_grafo()

            print(f"\nCentro del grafo: {centro}")

        elif opcion == "3":

            print("\n1. Eliminar conexión")
            print("2. Agregar conexión")

            sub = input("Seleccione: ")

            if sub == "1":

                origen = input("Origen: ")
                destino = input("Destino: ")

                g.eliminar_conexion(origen, destino)

            else:

                origen = input("Origen: ")
                destino = input("Destino: ")

                distancia = int(
                    input("Distancia KM: ")
                )

                g.agregar_conexion(
                    origen,
                    destino,
                    distancia
                )

        elif opcion == "4":

            g.mostrar_matriz()

        elif opcion == "5":

            print("\nPrograma finalizado.")

            break

        else:

            print("\nOpción inválida.")

if __name__ == "__main__":

    main()