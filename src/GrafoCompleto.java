import java.util.ArrayList;
import java.util.LinkedList;

public class GrafoCompleto {

    private int tamanho;
    private int[][] matriz;
    private ArrayList<LinkedList<Adjacencia>> adjacencias;

    public GrafoCompleto(int n) {
        this.tamanho = n;
        this.matriz = criarMatrizAdjacencias(n);
        this.adjacencias = new ArrayList<>();
        makeEdges();
    }

    private int[][] criarMatrizAdjacencias(int n) {
        int[][] matriz = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    matriz[i][j] = 1;
                }
            }
        }
        return matriz;
    }

    private void makeEdges(){
        for (int i = 0; i < tamanho; i++) {
            adjacencias.add(new LinkedList<>());
            for (int j = 0; j < tamanho; j++) {
                if (matriz[i][j] > 0) {
                    adjacencias.get(i).addLast(new Adjacencia(j));
                }
            }
        }
    }

    public void imprimirMatriz() {
        System.out.println("Matriz do grafo completo:");
        for (int[] linha : this.matriz) {
            for (int valor : linha) {
                System.out.print(valor + " ");
            }
            System.out.println();
        }
    }

    public void printListaDeAdjacencias(){
        System.out.println("Lista de adjacências do grafo completo:");
        for (int i = 0; i < adjacencias.size(); i++) {
            System.out.print(i + ": ");

            LinkedList<Adjacencia> lista = adjacencias.get(i);
            for (Adjacencia adj : lista) {
                System.out.print(adj.num + " -> ");
            }
            System.out.println("null");
        }
    }

}
