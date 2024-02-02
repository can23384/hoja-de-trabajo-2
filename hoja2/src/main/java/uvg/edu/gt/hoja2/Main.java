import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Leer el archivo datos.txt
            BufferedReader br = new BufferedReader(new FileReader("datos.txt"));
            String linea;
            
            while ((linea = br.readLine()) != null) {
                // Imprimir la expresión postfix
                System.out.println("Expresión Postfix: " + linea);

                // Evaluar la expresión
                int resultado = evaluarExpresion(linea);
                
                // Imprimir el resultado
                System.out.println("Resultado: " + resultado);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int evaluarExpresion(String expresion) {
        Stack<Integer> pila = new Stack<>();

        // Dividir la expresión en tokens (números y operadores)
        String[] tokens = expresion.split(" ");

        for (String token : tokens) {
            if (Operando(token)) {
                // Si es un operando, hacer push en la pila
                pila.push(Integer.parseInt(token));
            } else {
                // Si es un operador, realizar la operación y hacer push del resultado
                int operandoB = pila.pop();
                int operandoA = pila.pop();

                switch (token) {
                    case "+":
                        pila.push(operandoA + operandoB);
                        break;
                    case "-":
                        pila.push(operandoA - operandoB);
                        break;
                    case "*":
                        pila.push(operandoA * operandoB);
                        break;
                    case "/":
                        pila.push(operandoA / operandoB);
                        break;
                }
            }
        }

        // El resultado final estará en la cima de la pila
        return pila.pop();
    }

    private static boolean Operando(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}