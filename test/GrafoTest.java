package test;

public class GrafoTest {
    private Grafo grafo;
    private Floyd floyd;

    @BeforeEach
    public void setUp() throws Exception {

        grafo = new Grafo();

        grafo.cargarArchivo("guategrafo.txt");

        floyd = new Floyd(grafo);

        floyd.calcular();
    }

    // AGREGAR CONEXIÓN
    @Test
    public void testAgregarConexion() {

        grafo.agregarConexion("Mixco",
                "Coban",
                200);

        int distancia =
                grafo.obtenerDistancia("Mixco",
                        "Coban");

        assertEquals(200, distancia);
    }

    // ELIMINAR CONEXIÓN

    @Test
    public void testEliminarConexion() {

        grafo.eliminarConexion("Mixco",
                "Antigua");

        int distancia =
                grafo.obtenerDistancia("Mixco",
                        "Antigua");

        assertEquals(Grafo.INF, distancia);
    }

    // FLOYD
    @Test
    public void testFloyd() {

        int distancia =
                floyd.obtenerDistancia(
                        "Guatemala",
                        "Retalhuleu");

        assertTrue(distancia > 0);
    }


    // CENTRO DEL GRAFO
    @Test
    public void testCentroGrafo() {

        String centro = floyd.centroGrafo();

        assertNotNull(centro);

        assertFalse(centro.isEmpty());
    }
}
