/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tree;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;
import gui.ControlPanel;
import gui.MouseListener;
import gui.NodoPanel;
import gui.NodoSettings;
import gui.TraversalPanel;
import tree.Nodo;
import tree.BinarySearchTree;
import tree.BinaryTree;
/**
 *
 * @author HP
 */
public class BinaryTree {
       private final Nodo root;
      private final NodoPanel nodoPanel;

    public BinaryTree(Nodo root, NodoPanel nodoPanel) {
        this.root = nodoPanel.getRoot();
        this.nodoPanel = nodoPanel;
    }
    
    public Nodo getRoot() {
        return root;
    }
    public void setRoot(Nodo root) {
       this.root = root;
    }

    // public Nodo insert(Nodo nodo, int value, int level){ 
    //     if(nodo == null){
    //         return new nodo(400,300,24,value,level, Color.blue);
    //     }

    //     if(value < nodo.getValue()){
    //         nodo.setLeftChild(insert(nodo.getLeftChild(), value, level+1));
    //     }

    //     else if(value > nodo.getValue()){
    //         nodo.setRightChild(insert(nodo.getRightChild(), value, level+1));
    //     }
    //     return nodo;
    // }
    

    public void traversePreOrder(){
        PreOrder(root);
        resetNodoColors();
        nodoPanel.setTraverseString("");
    }
    private void PreOrder(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        nodoPanel.setTraverseString(nodoPanel.getTraverseString() + nodo.getValue() + " ");
        nodoPanel.selectNodoForTraversal(nodo);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PreOrder(nodo.getLeftChild());
        PreOrder(nodo.getRightChild());
    }

    public void traverseInOrder() {
        InOrder(root);
        resetNodoColors();
        nodoPanel.setTraverseString("");
    }
    private void InOrder(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        InOrder(nodo.getLeftChild());
        nodoPanel.setTraverseString(nodoPanel.getTraverseString() + nodo.getValue() + " ");
        nodoPanel.selectNodoForTraversal(nodo);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        InOrder(nodo.getRightChild());
    }

    public void traversePostOrder() {
        Postorder(root);
        resetNodoColors();
        nodoPanel.setTraverseString("");
    }
    private void Postorder(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        Postorder(nodo.getLeftChild());
        Postorder(nodo.getRightChild());
        nodoPanel.setTraverseString(nodoPanel.getTraverseString() + nodo.getValue() + " ");
        nodoPanel.selectNodoForTraversal(nodo);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void traverseLevelOrder() {
        int h = nodoPanel.treeHeight(root);
        for (int i = 1; i <= h; i++)
            LevelOrder(root, i);
        resetNodoColors();
        nodoPanel.setTraverseString("");
    }
    public void LevelOrder(Nodo nodo, int level) {
        if (nodo == null)
            return;
        if (level == 1) {
            nodoPanel.setTraverseString(nodoPanel.getTraverseString() + nodo.getValue() + " ");
            nodoPanel.selectNodoForTraversal(nodo);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (level > 1) {
            LevelOrder(nodo.getLeftChild(), level - 1);
            LevelOrder(nodo.getRightChild(), level - 1);
        }
    }

    private int populateArrayInOrder(Nodo nodo, int index, int[] treeArray) { //Llena la matriz con todos los elementos del árbol binario.
        if (nodo == null) {
            return index;
        }
        index = populateArrayInOrder(nodo.getLeftChild(),index,treeArray);
        treeArray[index] = nodo.getValue();
        index = populateArrayInOrder(nodo.getRightChild(),index+1,treeArray);
        return index;
    }

    public void sort() {
        int numElements = numElements(root);
        int[] unsortedTree = new int[numElements];
        populateArrayInOrder(root,0,unsortedTree);

        nodoPanel.nodos.clear();

        Random rand = new Random();
        for (int i = 0; i < unsortedTree.length; i++) { //Mezcla la matriz para obtener una clasificación única cada vez
            int randomIndexToSwap = rand.nextInt(unsortedTree.length);
            int temp = unsortedTree[randomIndexToSwap];
            unsortedTree[randomIndexToSwap] = unsortedTree[i];
            unsortedTree[i] = temp;
        }
        int rootVal = unsortedTree[rand.nextInt(unsortedTree.length)]; //Elige el nodo raíz aleatorio
        BinaryTree sortedTree = new BinaryTree(new Nodo(0,0,24,rootVal,0, Color.BLUE));
        for (int num : unsortedTree) {
            sortedTree.insert(sortedTree.getRoot(),num,0);
        }
        addNodosToPanelInOrder(sortedTree.getRoot());

        for (Nodo n : nodoPanel.nodos) {
            System.out.println(n.getValue() + " " + n.getLevel());
        }
        nodoPanel.reconfigureTree();
        nodoPanel.repaint();
    }

    private void addNodosToPanelInOrder(Nodo nodo) { //Agrega todos los nodos de un árbol a la lista de nodos dentro de nodoPanel
        if (nodo == null) {
            return;
        }
        addNodosToPanelInOrder(nodo.getLeftChild());
        nodoPanel.nodos.add(nodo);
        addNodosToPanelInOrder(nodo.getRightChild());
    }

    private int numElements(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + numElements(nodo.getLeftChild()) + numElements(nodo.getRightChild());
    }

    public void invert() { 
        invertTree(root);
        resetLevels(root,0);
        nodoPanel.reconfigureTree();
        nodoPanel.repaint();
    }
    private void invertTree(Nodo nodo) {
        if(nodo == null) {
            return;
        }
        Nodo temp = nodo.getLeftChild();
        nodo.setLeftChild(nodo.getRightChild());
        nodo.setRightChild(temp);

        invertTree(nodo.getLeftChild());
        invertTree(nodo.getRightChild());
    }

    private void resetLevels(Nodo nodo, int level) {
        if (nodo == null) {
            return;
        }
        nodo.setLevel(level);
        resetLevels(nodo.getLeftChild(),level+1);
        resetLevels(nodo.getRightChild(),level+1);
    }

    public boolean empty() {
        if (root == null) {
            return true;
        } else {
            return false;
        }
    }

    private void resetNodoColors() { 
        for (Nodo n : nodoPanel.nodos) {
            n.setColor(Color.BLUE);
        }
        if (MouseListener.getPrevClicked() != null) {
            MouseListener.getPrevClicked().setColor(Color.GREEN);
        }
        nodoPanel.repaint();
    }
}

class BinarySearchTree {

    private final Nodo root;

    public BinarySearchTree(Nodo root) {
        this.root = root;
    }

    public Nodo getRoot() {
        return root;
    }

    public Nodo insert(Nodo nodo, int value, int level){ 
        if(nodo == null){
            return new Nodo(400,300,24,value,level, Color.blue);
        }
        
        if(value < nodo.getValue()){
            nodo.setLeftChild(insert(nodo.getLeftChild(), value, level+1));
        }
        
        else if(value > nodo.getValue()){
            nodo.setRightChild(insert(nodo.getRightChild(), value, level+1));
        }
        return nodo;
    }
}
