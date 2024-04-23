import java.io.*;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LeitorMatriz {

    private int n;
    private int [][] matriz;

    public void lerMatrizDeArquivo() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Escolha o arquivo de entrada");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");

        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {

            File selectedFile = fileChooser.getSelectedFile();
            Scanner scanner = new Scanner(selectedFile);

            n = scanner.nextInt();

            matriz = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (scanner.hasNextInt()) {
                        matriz[i][j] = scanner.nextInt();
                    }
                }
            }

            scanner.close();
        }else{

            throw new IllegalArgumentException("Não foi possível ler a matriz.");

        }

    }

    public void lerMatrizIncompletaDeArquivo() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Escolha o arquivo com a matriz incompleta");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");

        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {

            File selectedFile = fileChooser.getSelectedFile();
            Scanner scanner = new Scanner(selectedFile);

            n = scanner.nextInt();

            matriz = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (scanner.hasNextInt()) {
                        matriz[i][j] = scanner.nextInt();
                        matriz[j][i] = matriz[i][j];
                    }
                }
            }

            scanner.close();
        }else{

            throw new IllegalArgumentException("Não foi possível ler a matriz.");

        }

    }

    public int getN() {
        return n;
    }

    public int[][] getMatriz() {
        return matriz;
    }
}
