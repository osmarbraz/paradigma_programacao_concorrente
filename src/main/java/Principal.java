
import java.util.Scanner;

public class Principal {

    /**
     * Verifica a validade do triangulo
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static boolean verificaTriangulo(double a, double b, double c) {
        return a > 0 && b > 0 && c > 0
                && a < b + c
                && b < a + c
                && c < a + b;
    }

    /**
     * Programa Principal.
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        //Scanner para a estrada de dados padrão.
        Scanner entrada = new Scanner(System.in);

        // Entrada
        System.out.print("Lado A: ");
        double a = entrada.nextDouble();
        System.out.print("Lado B: ");
        double b = entrada.nextDouble();
        System.out.print("Lado C: ");
        double c = entrada.nextDouble();
        if (verificaTriangulo(a, b, c)) {
            System.out.println("Triangulo valido!");

            // Thread para calcular o perimetro
            Thread tarefaPerimetro = new Thread(() -> {
                double p = a + b + c;
                System.out.println("Perimetro: " + p);
            });

            // Thread para calcular a area
            Thread tarefaArea = new Thread(() -> {
                double s = (a + b + c) / 2;
                double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
                System.out.println("Area: " + area);
            });

            // Inicia as duas threads
            tarefaPerimetro.start();
            tarefaArea.start();

            // Aguarda o termino das threads
            tarefaPerimetro.join();
            tarefaArea.join();

            System.out.println("Calculos finalizados!");

        } else {
            System.out.println("Triangulo invalido!");
        }

        //Fecha a entrada de dados padrão
        entrada.close();
    }
}
