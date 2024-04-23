import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        //Exercício 1

        LeitorMatriz lm = new LeitorMatriz();

        lm.lerMatrizDeArquivo();

        Grafo grafo = new Grafo(lm.getN(), lm.getMatriz());

        grafo.printArestas();

        System.out.println("É direcionado: " + (grafo.isConnected() ? "Sim" : "Não"));
        System.out.println("É conexo: "+ (grafo.isConnected() ? "Sim" : "Não"));
        System.out.println("É cíclico: "+ (grafo.isCyclic() ? "Sim" : "Não"));

        int[] graus = grafo.grau();

        for (int i = 0; i < graus.length; i++) {
            System.out.println("O vértice "+i+" tem grau "+graus[i]);
        }

        grafo.printListaDeAdjacencias();

        //Exercício 2

        System.out.println("Escreva o número de vértices para um grafo completo: ");
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        GrafoCompleto grafoCompleto = new GrafoCompleto(n);
        grafoCompleto.imprimirMatriz();
        grafoCompleto.printListaDeAdjacencias();

        //Exercício 3
        lm.lerMatrizDeArquivo();

        Grafo grafo2 = new Grafo(lm.getN(), lm.getMatriz());

        if (grafo2.isConnected()){
            grafo2.printArestas();
        }else{
            System.out.println("O grafo não é conexo!");
        }

        //Exercício 4
        lm.lerMatrizIncompletaDeArquivo();

        Grafo grafo3 = new Grafo(lm.getN(), lm.getMatriz());
        grafo3.imprimirMatriz();

    }
}