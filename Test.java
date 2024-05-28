package ejr09;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        BSTree<Integer> tree = new BSTree<>();
        
        // Insertar elementos aleatorios
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int randomNumber = rand.nextInt(100);
            try {
                tree.insert(randomNumber);
            } catch (ItemDuplicated e) {
                System.out.println(e.getMessage());
            }
        }
        
        // Mostrar árbol en inOrden
        System.out.println("Árbol en inOrden:");
        tree.inOrden();
        System.out.println();
        
        // Mostrar el número de nodos
        System.out.println("Número de nodos: " + tree.countNodes());
        
        // Mostrar la altura de un elemento aleatorio
        int randomElement = rand.nextInt(100);
        System.out.println("Altura del elemento " + randomElement + ": " + tree.height(randomElement));
        
        // Mostrar el área del árbol
        System.out.println("Área del árbol: " + tree.areaBST());
        
        // Mostrar el árbol jerárquicamente
        System.out.println("Árbol jerárquico:");
        tree.parenthesize();
        
        // Mostrar el recorrido PreOrden
        System.out.println("Recorrido PreOrden:");
        tree.iterativePreOrder();
        System.out.println();
    }
}








