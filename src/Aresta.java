public class Aresta {

    int A, B, peso;

    public Aresta(int a, int b, int peso) {
        A = a;
        B = b;
        this.peso = peso;
    }

    public int getA() {
        return A;
    }

    public void setA(int a) {
        A = a;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}
