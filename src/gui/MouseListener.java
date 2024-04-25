
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tree.BinaryTree;
import tree.BinaryTreeMain;
import tree.Nodo;
public class MouseListener implements java.awt.event.MouseListener, MouseMotionListener, MouseWheelListener  {
    private static Nodo prevClickedNodo;

    private final NodoPanel nodoPanel;
    private final NodoSettings nodoSettings;

    private Nodo _selectedNodo;
    private Point _startingLoc;

    private int curZoom = 24;
    private int prevZoom = 24;

    public MouseListener(NodoPanel nodoPanel, NodoSettings nodoSettings) {
        this.nodoPanel = nodoPanel;
        this.nodoSettings = nodoSettings;
    }

    public static Nodo getPrevClicked() { 
        return prevClickedNodo;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        _selectedNodo = nodoPanel.checkNodoClick(e);
        _startingLoc = new Point(e.getX(), e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Nodo n = nodoPanel.checkNodoClick(e);
        if (n == null) { 
            if (!nodoSettings.getPlaced()) {
                nodoSettings.placedANodo();
            }
            nodoPanel.addNodo(new Nodo(e.getX() - 12, e.getY() - 12, curZoom, -1, 0, Color.blue));
        } else {
            if (prevClickedNodo != null && prevClickedNodo != n && n.getParent() == null && nodoSettings.getLeftListener().getActiveState() && n != nodoPanel.getRoot()) { //Selecciona el left nodo
                n.setParent(prevClickedNodo);
                prevClickedNodo.setLeftChild(n);
            } else if (prevClickedNodo != null && prevClickedNodo != n && n.getParent() == null && nodoSettings.getRightListener().getActiveState() && n != nodoPanel.getRoot()) { //Selecciona el right nodo
                n.setParent(prevClickedNodo);
                prevClickedNodo.setRightChild(n);
            }
            if (!nodoSettings.getClicked()) { //Si no se ha hecho clic en un nodo, cree el panel y los componentes de configuración
                nodoSettings.clickedANodo();
            }
            if (prevClickedNodo != null && prevClickedNodo != n) { //Si el nodo en el que se hizo clic anteriormente no es nulo y no es el nodo actual, devuelva el nodo en el que se hizo clic anteriormente al estado normal.
                prevClickedNodo.setColor(Color.blue);
                prevClickedNodo.updateDiameterAndOffset(-10);
                nodoSettings.updatenodoSettings(n);
            }
            if (n != prevClickedNodo) { //Si aún no se ha hecho clic en el nodo, cambie el color y el diámetro
                n.setColor(Color.green);
                n.updateDiameterAndOffset(10);
            }
            nodoPanel.repaint();
            prevClickedNodo = n;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        _selectedNodo = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (_selectedNodo != null) {
            _selectedNodo.setX(e.getX()-_selectedNodo.getDiameter()/2);
            _selectedNodo.setY(e.getY()-_selectedNodo.getDiameter()/2);
        } else {
            for (Nodo n : nodoPanel.nodos) {
                System.out.println((e.getX() - _startingLoc.x));
                n.setX(n.getX() + (e.getX() - _startingLoc.x));
                n.setY(n.getY() + (e.getY() - _startingLoc.y));
            }
            _startingLoc = new Point(e.getX(), e.getY());
        }
        nodoPanel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) { 
        curZoom = curZoom+ e.getWheelRotation();
        for (Nodo n : nodoPanel.nodos) {;
            if (curZoom > prevZoom) { //aumentar zoom
                n.updateDiameterAndOffset(curZoom-n.getDiameter());
            } else { //alejar zoom
                n.updateDiameterAndOffset(-1*(n.getDiameter()-curZoom));
            }
            n.setLoc(getScaledPoint(n.getX(),n.getY(),e.getX()-n.getDiameter()/2,e.getY()-n.getDiameter()/2,24.0/(24+ e.getWheelRotation())));
        }
        nodoPanel.repaint();
        prevZoom = curZoom;
    }

    public Point getScaledPoint(int x, int y,int startX,int startY, double scale){
        return new Point((int) ((x-startX) * scale)+startX, (int) ((y-startY) * scale)+startY);
    }
}
