package ejr09;

import java.util.EmptyStackException;

public class BSTree<E extends Comparable<E>> implements LinkBST<E> {
    private Node<E> root;

    public BSTree() {
        this.root = null; // sin nodos
    }

    @Override
    public void insert(E x) throws ItemDuplicated {
        this.root = insertRec(x, this.root); // raiz actual
    }

    private Node<E> insertRec(E x, Node<E> node) throws ItemDuplicated {
        Node<E> res = node; //recursivo
        if (node == null) { 
            res = new Node<>(x);
        } else {
            int resC = node.getData().compareTo(x); //compara si es mayor menor o igual
            if (resC == 0) {
                throw new ItemDuplicated("Item duplicated"); // ya existe
            }
            if (resC > 0) {
                res.setLeft(insertRec(x, node.getLeft()));
            } else {
                res.setRight(insertRec(x, node.getRight()));
            }
        }
        return res;
    }

    @Override
    public void remove(E x) {
        this.root = removeRec(x, this.root); // parametros
    }

    private Node<E> removeRec(E x, Node<E> node) {
        if (node == null) { // no existe en el arbol
            return null;
        }
        int resC = node.getData().compareTo(x);
        if (resC > 0) {
            node.setLeft(removeRec(x, node.getLeft()));
        } else if (resC < 0) {
            node.setRight(removeRec(x, node.getRight()));
        } else {
            if (node.getLeft() == null) { // comprueba si tiene hijo izquierdo
                return node.getRight();
            } else if (node.getRight() == null) {// comprueba si tiene hijo derecho
                return node.getLeft();
            }
            node.setData(minValue(node.getRight())); // valor minimo y reemplaza
            node.setRight(removeRec(node.getData(), node.getRight())); // elimina nodo sucesor derecho
        }
        return node;
    }

    private E minValue(Node<E> node) {
        E minValue = node.getData(); // inicia con el valor del nodo actual
        while (node.getLeft() != null) { // recorre toda el lado izquierdo hasta encontrar el menor
            node = node.getLeft();
            minValue = node.getData(); // actualiza al valor mas a la izquierda
        }
        return minValue;
    }

    @Override
    public E search(E x) throws ItemNotFound {
        Node<E> res = searchRec(x, this.root);
        if (res == null) {
            throw new ItemNotFound("Item not found"); //no lo encuentra
        }
        return res.getData(); // dato contenido
    }

    private Node<E> searchRec(E x, Node<E> node) {
        if (node == null) {
            return null; //no se encontro
        } else {
            int resC = node.getData().compareTo(x); // compara dato con nodo 
            if (resC > 0) {
                return searchRec(x, node.getLeft());
            } else if (resC < 0) {
                return searchRec(x, node.getRight());
            } else {
                return node; // se encontro
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    public void inOrden() {
        in_Orden(this.root); //inicia el record
    } 

    private void in_Orden(Node<E> node) {
        if (node != null) { //no pasa nada
            in_Orden(node.getLeft()); // procesan los nodos izquierdo
            System.out.print(node.toString() + ", "); // dato nodo
            in_Orden(node.getRight());
        }
    }

    public int countNodes() {
        return countNodesRec(this.root);
    }

    private int countNodesRec(Node<E> node) {
        if (node == null) { // no es nulo
            return 0;
        }
        return 1 + countNodesRec(node.getLeft()) + countNodesRec(node.getRight()); // cuenta el Derecho y izquierdo
    }

    public int height(E x) {
        Node<E> node = root;
        while (node != null) {
            int comp = node.getData().compareTo(x); //compara el nodo x con el actual
            if (comp == 0) {
                return heightRec(node); // se encontro del nodo
            } else if (comp > 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        return -1; // no se encontro
    }

    private int heightRec(Node<E> node) {
        if (node == null) {
            return -1; // caso base 
        }
        int leftHeight = heightRec(node.getLeft()); // almacena altura
        int rightHeight = heightRec(node.getRight());
        return Math.max(leftHeight, rightHeight) + 1; // retorna las alturas izq y der sumando 1
    }

    public int areaBST() {
        if (root == null) return 0;
        int height = heightRec(root);
        int leaves = countLeaves(root);
        return leaves * height; // multiplica altura por la cant de hojas
    }

    private int countLeaves(Node<E> node) {
        if (node == null) { 
            return 0;
        }
        if (node.getLeft() == null && node.getRight() == null) { //verificar si el nodo actual tiene hijos
            return 1;
        }
        return countLeaves(node.getLeft()) + countLeaves(node.getRight());
    }

    public void iterativePreOrder() {
        if (root == null) return; // Si el árbol está vacío, no hacer nada.
        StackLink<Node<E>> stack = new StackLink<>(); // Crear una nueva pila.
        stack.push(root); // Empujar la raíz a la pila.
        while (!stack.isEmpty()) {
            try {
                Node<E> node = stack.pop(); // Sacar el nodo del tope de la pila.
                System.out.print(node.getData() + " "); // Procesar el nodo (en este caso, imprimir su valor).
                if (node.getRight() != null) { // Si el nodo tiene hijo derecho,
                    stack.push(node.getRight()); // empujarlo a la pila.
                }
                if (node.getLeft() != null) { // Si el nodo tiene hijo izquierdo,
                    stack.push(node.getLeft()); // empujarlo a la pila.
                }
            } catch (EmptyStackException e) {
                e.printStackTrace(); // Manejar la excepción si la pila está vacia.
            }
        }
    }


    public void parenthesize() {
        parenthesize(root, 0); // Llama al método recursivo con el nodo raíz y nivel inicial 0.
    }

    private void parenthesize(Node<E> node, int level) {
        if (node == null) return; // Si el nodo es nulo, no hacer nada.
        printIndent(level); // Imprime la indentación según el nivel actual.
        System.out.println(node.getData()); // Imprime los datos del nodo actual.
        if (node.getLeft() != null || node.getRight() != null) { // Si el nodo tiene hijos (izquierdo o derecho),
            printIndent(level); // imprime la indentación según el nivel actual.
            System.out.println("("); // Imprime un paréntesis de apertura.
            parenthesize(node.getLeft(), level + 1); // Llama recursivamente al hijo izquierdo aumentando el nivel.
            parenthesize(node.getRight(), level + 1); // Llama recursivamente al hijo derecho aumentando el nivel.
            printIndent(level); // Imprime la indentación según el nivel actual.
            System.out.println(")"); // Imprime un paréntesis de cierre.
        }
    }

    private void printIndent(int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("\t"); // Imprime tabulaciones según el nivel.
        }
    }
}
