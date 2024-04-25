/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import java.lang.Math;
import tree.BinaryTree;
import tree.BinaryTreeMain;
import tree.Nodo;
public class NodoPanel extends JPanel{
     public List< Nodo> nodos = new LinkedList<>();

    Nodo prevSelectedNodo; //Mantiene el nodo seleccionado anterior al atravesar
    private String traverseString = ""; //Una String que se dibuja en el lienzo para mostrar el orden de recorrido.

    public NodoPanel() {
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(600,500));
    }

    public String getTraverseString() {
        return traverseString;
    }

    public void setTraverseString(String newString) {
        traverseString = newString;
    }

    public void addNodo(Nodo nodo) {
        nodos.add(nodo);
        this.repaint();
    }

    public void removeNodo(Nodo nodo) {
        nodos.remove(nodo);
    }

    public Nodo getRoot() { //Devuelve null si no hay raíz o hay más de 1 raíz; de lo contrario, devuelve la raíz
        Nodo root = null;
        int numZero = 0;
        for (Nodo n : nodos) {
            if (n.getLevel() == 0) {
                numZero++;
                root = n;
            }
            if (numZero > 1) {
                return null;
            }
        }
        return root;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font("Serif", Font.PLAIN, 30));
        g.drawString(traverseString,10,getHeight()-20);
        for (Nodo n : nodos) { //dubuja los nodos
            n.draw(g);
            if(n.getLeftChild() != null) { //dibuja las lineas
                g.setColor(Color.blue);
                g.drawLine(n.getX()+n.getDiameter()/2,n.getY()+n.getDiameter()/2,n.getLeftChild().getX()+n.getLeftChild().getDiameter()/2,n.getLeftChild().getY()+n.getLeftChild().getDiameter()/2);
            }
            if(n.getRightChild() != null) {
                g.setColor(Color.red);
                g.drawLine(n.getX()+n.getDiameter()/2,n.getY()+n.getDiameter()/2,n.getRightChild().getX()+n.getRightChild().getDiameter()/2,n.getRightChild().getY()+n.getRightChild().getDiameter()/2);
            }
        }
    }

    public Nodo checkNodoClick(MouseEvent e) { //Comprueba si se hace clic en un nodo y devuelve el nodo en el que se hizo clic.
        for (Nodo n : nodos) { 
            double distance = Math.sqrt(Math.pow(n.getX() + n.getDiameter()/2.0 -e.getX(),2)+Math.pow(n.getY() + n.getDiameter()/2.0 -e.getY(),2)); //Distancia desde el punto en el que se hizo clic hasta el centro del nodo
            if (distance < n.getDiameter()/2.0) { //Si la distancia es menor que el radio, el usuario ha hecho clic dentro del nodo
                return n;
            }
        }
        return null; //Devuelve null si no se hace clic en ningún nodo
    }

    public void reconfigureTree() { //Coloca todos los nodos en formato de árbol binario perfecto.
        Nodo root = getRoot();
        if (root != null) {
            for (Nodo n : nodos) { //Establezca la posición de todos los nodos en la parte superior de la pantalla
                n.setX(getWidth()/2);
                n.setY(30);
            }
            int bottomWidth = (int) Math.pow(2, treeHeight(root) - 1) * 15; //El ancho en la parte inferior se calcula mediante (2^alto * algún desplazamiento(offset))
            reconfigureHelper(root,root.getX(),root.getY(),bottomWidth);

        }
        this.repaint();
    }

    private void reconfigureHelper(Nodo root, int xPos, int yPos,int width) { //Coloca recursivamente todos los nodos en sus ubicaciones correctas.
        if (root == null) {
            return;
        }
        root.setX(xPos);
        root.setY(yPos);
        width /= 2;
        reconfigureHelper(root.getLeftChild(),xPos-width,yPos+50,width);
        reconfigureHelper(root.getRightChild(),xPos+width,yPos+50,width);
    }

    public int treeHeight(Nodo root) { //Devuelve la altura máxima del árbol binario.
        if (root == null) {
            return 0;
        }
        int leftHeight = treeHeight(root.getLeftChild());
        int rightHeight = treeHeight(root.getRightChild());
        return Math.max(leftHeight,rightHeight) + 1;
    }

    public void selectNodoForTraversal(Nodo nodo) { //Se utiliza para todos los métodos transversales para seleccionar un nodo y cambiar su color.
        for (Nodo n : nodos) {
            if (nodo == n) {
                if (prevSelectedNodo != null && n == prevSelectedNodo) {
                    prevSelectedNodo.setColor(Color.green);
                } else if (prevSelectedNodo != null) {
                    prevSelectedNodo.setColor(Color.blue);
                }
                nodo.setColor(Color.yellow);
                this.paintImmediately(0,0,800,800); //Esto se llama porque .repaint() no funciona dentro de llamadas recursivas
                prevSelectedNodo = nodo;
                break;
            }
        }
    }
}
