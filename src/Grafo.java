import java.util.ArrayList;
import java.util.LinkedList;

public class Grafo {

    private int tamanho;

    private int[][] matriz;

    private ArrayList<Aresta> arestas;
    private ArrayList<LinkedList<Adjacencia>> adjacencias;

    public Grafo(int tamanho, int[][] matriz) {
        this.tamanho = tamanho;
        this.matriz = matriz;
        this.arestas = new ArrayList<>();
        this.adjacencias = new ArrayList<>();
        makeEdges();
    }

    public void addAresta(int i, int j, int con){
        matriz[i][j] = con;
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

        int startNode = 0;
        while (startNode < tamanho && adjacencias.get(startNode).isEmpty()) {
            startNode++;
        }
        if (startNode == tamanho) return true;

        DFS(startNode, visited, -1);
        for (int i = 0; i < tamanho; i++) {
            if (!visited[i] && !adjacencias.get(i).isEmpty()) {
                return false;
            }
        }
        return true;

    }

    public boolean DFS(int v, boolean[] visited, int parent) {

        visited[v] = true;
        boolean hasCycle = false;

        for (Adjacencia n : adjacencias.get(v)) {
            if (!visited[n.num]) {
                hasCycle = hasCycle || DFS(n.num, visited, v);
            } else if (n.num != parent) {
                return true;
            }
        }
        return hasCycle;
    }

    public boolean isCyclic(){

        boolean[] visited = new boolean[tamanho];
        for (int i = 0; i < tamanho; i++) {
            if (!visited[i]) {
                if (DFS(i, visited, -1)) {
                    return true;
                }
            }
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

    public ArrayList<Aresta> getArestas() {
        return arestas;
    }

    public ArrayList<LinkedList<Adjacencia>> getAdjacencias() {
        return adjacencias;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void printListaDeAdjacencias(){
        System.out.println("Lista de Adjacências:");
        for (int i = 0; i < adjacencias.size(); i++) {
            System.out.print(i + ": ");
            LinkedList<Adjacencia> lista = adjacencias.get(i);
            for (Adjacencia adj : lista) {
                System.out.print(adj.num + " -> ");
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
