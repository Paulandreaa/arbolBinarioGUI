/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tree;
import java.awt.*;
import gui.ControlPanel;
import gui.MouseListener;
import gui.NodoPanel;
import gui.NodoSettings;
import gui.TraversalPanel;
/**
 *
 * @author HP
 */
public class Nodo {
     private int value;
    private Nodo parent;
    private Nodo leftChild;
    private Nodo rightChild;
    private int x, y; 
    private int diameter;
    private int level; //altura
    private Color color;

    public Nodo(int value, int x, int y, int diameter, int level, Color color) {
        this.value = value;
        parent = null;
        this.leftChild = null;
        this.rightChild = null;
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.level = level;
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

     public Nodo getParent() {
        return parent;
    }

    public void setParent(Nodo parent) {
        this.parent = parent;
    }

    public Nodo getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Nodo n) {
        if (n != null) {
            n.setLevel(level+1);
        } else if (leftChild != null) {
            leftChild.setLevel(0);
        }
        leftChild = n;
    }

    public Nodo getRightChild() {
        return rightChild;
    }

    public void setRightChild(Nodo n) {
        if (n != null) {
            n.setLevel(level+1);
        } else if (rightChild != null) {
            rightChild.setLevel(0);
        }
        rightChild = n;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setLoc(Point p){
        x = p.x;
        y = p.y;
    }

    public void updateDiameterAndOffset(int diameterChange) {
        x -= diameterChange/2;
        y -= diameterChange/2;
        diameter += diameterChange;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x,y,diameter,diameter);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Serif", Font.PLAIN, 30));
        g.drawString(String.valueOf(value),x,y);
    }
}
