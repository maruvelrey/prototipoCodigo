import java.util.Scanner;

public class AF {


    // 1. ESTRUCTURA DE DATOS DESDE CERO: PILA DE CARACTERES
    private static class PilaPropia {
        private char[] elementos;
        private int tope;

        public PilaPropia(int capacidad) {
            this.elementos = new char[capacidad];
            this.tope = -1;
        }

        public void push(char c) {
            if (tope < elementos.length - 1) {
                tope++;
                elementos[tope] = c;
            }
        }

        public char pop() {
            if (!estaVacia()) {
                char c = elementos[tope];
                tope--;
                return c;
            }
            return '\0'; // Retorna carácter nulo si está vacía
        }

        public boolean estaVacia() {
            return tope == -1;
        }
    }

    // 2. MÉTODO OPTIMO PARA VALIDAR LAS LLAVES
    public static boolean esSecuenciaValida(String texto) {
        if (texto == null) {
            return false;
        }

        // Creamos la pila con la capacidad del tamaño del texto
        PilaPropia pila = new PilaPropia(texto.length());

        // Recorrido obligatorio usando charAt()
        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);

            // Si es llave que abre '{', se guarda en la pila
            if (caracter == '{') {
                pila.push(caracter);
            } 
            // Si es llave que cierra '}'
            else if (caracter == '}') {
                // Si encontramos '}' y la pila está vacía, no hay '{' correspondiente
                if (pila.estaVacia()) {
                    return false;
                }
                // Sacamos la llave '{' que correspondía
                pila.pop();
            }
            
        }

        // Si al terminar la pila quedó completamente vacía, la secuencia es correcta
        return pila.estaVacia();
    }

    // 3. MÉTODO MAIN (PRUEBAS EN CONSOLA)
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("   VALIDADOR DE SECUENCIAS DE LLAVES    ");
        System.out.println("=========================================");
        System.out.print("Ingresa tu texto: ");
        
        String entrada = scanner.nextLine();

        // Validar si el texto está vacío
        if (entrada.trim().isEmpty()) {
            System.out.println("\n¡Oyeee!");
            System.out.println("Ingresa primero el texto para ver el resultado.");
        } else {
            boolean esValido = esSecuenciaValida(entrada);
            
            if (esValido) {
                System.out.println("\n¡Enhorabuena!");
                System.out.println("Tu texto no tiene errores.");
            } else {
                System.out.println("\n¡Upsss!");
                System.out.println("Tu texto tiene errores, inténtalo de nuevo.");
            }
        }

        scanner.close();
    }
}