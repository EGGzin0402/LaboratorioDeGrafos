import java.util.ArrayList;
import java.util.LinkedList;

public class Grafo {

    private final int tamanho;

    private final int[][] matriz;

    private final ArrayList<Aresta> arestas;
    private final ArrayList<LinkedList<Adjacencia>> adjacencias;

    public Grafo(int tamanho, int[][] matriz) {
        this.tamanho = tamanho;
        this.matriz = matriz;
        this.arestas = new ArrayList<>();
        this.adjacencias = new ArrayList<>();
        makeEdges();
    }

    public boolean isDirected() {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (matriz[i][j] != matriz[j][i]) {
                    return true;
                }
            }
        }
        return false;
    }

    public int[] grau(){
        int[] graus = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (matriz[i][j] > 0) {
                    graus[i]++;
                    if (isDirected()) {
                        graus[j]++;
                    }
                }
            }
        }
        return graus;
    }

    public boolean isConnected(){

        boolean[] visited = new boolean[tamanho];

        int i;
        for (i = 0; i < tamanho; i++) {
            if (!adjacencias.get(i).isEmpty()) {
                break;
            }
        }

        if (i == tamanho) {
            return true;
        }

        DFS(i, visited);

        for (i = 0; i < tamanho; i++) {
            if (!visited[i] && !adjacencias.get(i).isEmpty()) {
                return false;
            }
        }

        return true;

    }

    public void DFS(int v, boolean[] visited) {

        visited[v] = true;

        for (Adjacencia n : adjacencias.get(v)) {
            if (!visited[n.getNum()]) {
                DFS(n.getNum(), visited);
            }
        }
    }

    public boolean isCyclic(){

        boolean[] visited = new boolean[tamanho];
        for (int i = 0; i < tamanho; i++) {
            if (!visited[i]) {
                if (DFSCiclo(i, visited, -1)) {
                    return true;
                }
            }
        }
        return false;

    }

    boolean DFSCiclo(int v, boolean[] visited, int parent) {
        visited[v] = true;

        for (Adjacencia n : adjacencias.get(v)) {
            if (!visited[n.getNum()]) {
                if (DFSCiclo(n.getNum(), visited, v))
                    return true;
            }

            else if (n.getNum() != parent)
                return true;
        }
        return false;
    }

    private void makeEdges(){
        for (int i = 0; i < tamanho; i++) {
            adjacencias.add(new LinkedList<>());
            for (int j = 0; j < tamanho; j++) {
                if (matriz[i][j] > 0) {
                    arestas.add(new Aresta(i, j, matriz[i][j]));
                    adjacencias.get(i).addLast(new Adjacencia(j));
                }
            }
        }
    }

    public void printListaDeAdjacencias(){
        System.out.println("Lista de Adjacências:");
        for (int i = 0; i < adjacencias.size(); i++) {
            System.out.print(i + ": ");
            LinkedList<Adjacencia> lista = adjacencias.get(i);
            for (Adjacencia adj : lista) {
                System.out.print(adj.getNum() + " -> ");
            }
            System.out.println("null");
        }
    }

    public void printArestas(){
        System.out.println("Arestas do grafo: ");
        for (Aresta a: arestas){
            System.out.println("("+a.getA()+","+a.getB()+") e seu peso é "+a.getPeso());
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

}
