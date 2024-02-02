import java.util.*;

public class CalculadoraPostfix {
    private Stack<Integer> pila;
    
    public CalculadoraPostfix() {
        pila = new Stack<>();
    }

    public int evaluarExpresion(String expresion) {
        // Dividir la expresión en tokens (números y operadores)
        String[] tokens = expresion.split(" ");

        for (String token : tokens) {
            if (esOperando(token)) {
                // Si es un operando, hacer push en la pila
                pila.push(Integer.parseInt(token));
            } else if (esOperador(token)) {
                // Si es un operador, realizar la operación y hacer push del resultado
                if (pila.size() < 2) {
                    System.out.println("Insuficiente cantidad de operandos para realizar la operación");
                }

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
                        if (operandoB == 0) {
                            System.out.println("División entre cero");
                        }
                        pila.push(operandoA / operandoB);
                        break;
                }
            } else {
                System.out.println("Carácter no reconocido como operando o operador");
            }
        }

        // Verificar si quedaron operandos en la pila
        if (pila.size() != 1) {
            System.out.println("Error en la expresión");
        }

        // El resultado final estará en la cima de la pila
        return pila.pop();
    }

    private boolean esOperando(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean esOperador(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }
}