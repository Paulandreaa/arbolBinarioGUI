/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tree.BinaryTree;
import tree.BinaryTreeMain;
import tree.Nodo;
/**
 *
 * @author HP
 */
public class NodoSettings extends JPanel {
     private JButton leftChild; //Botón para asignar nodos child izquierdos
    private JButton rightChild; //Botón para asignar nodos child correctos

    private JTextField newValue; //Un campo de texto para recibir entradas para nuevos valores de nodo
    private boolean placedANodo = false; //Si ya se ha colocado un nodo
    private boolean clickedANodo = false; //Si ya se había hecho clic en un nodo

    private JButton clickedChildButton; //El botón child actual en el que se hace clic (izquierdo, derecho o ninguno)

    private ButtonActionListener leftListener; //Oyentes de botones izquierdo/derecho para gestionar clics
    private ButtonActionListener rightListener;

    private JLabel levelText; //El nivel del nodo seleccionado actualmente (altura en árbol binario)

    private final NodoPanel nodoPanel;

    public NodoSettings(NodoPanel nodoPanel) {
        this.nodoPanel = nodoPanel;
        JLabel clickText = new JLabel("Haga clic en cualquier lugar para colocar un nodo");
        clickText.setFont(new Font("Serif", Font.PLAIN, 30));
        this.add(clickText);
    }

    public boolean getPlaced() {
        return placedANodo;
    }

    public boolean getClicked() {
        return clickedANodo;
    }

    public JButton getClickedChildButton() {
        return clickedChildButton;
    }

    public ButtonActionListener getLeftListener() {
        return leftListener;
    }

    public ButtonActionListener getRightListener() {
        return rightListener;
    }

    public void setClickedChildButton(JButton button) {
        clickedChildButton = button;
    }

    public void flipClickedChildButton() {
        if (leftListener.getActiveState()) {
            rightListener.setActivateState(true);
            leftListener.setActivateState(false);
            clickedChildButton = rightChild;
        } else {
            leftListener.setActivateState(true);
            rightListener.setActivateState(false);
            clickedChildButton = leftChild;
        }
        leftListener.flipColor();
        rightListener.flipColor();
    }

    public void placedANodo() { //Llamado a la primera colocación de un nodo.
        placedANodo = true;
        removeAll();
        JLabel clickText = new JLabel("Haga clic en un nodo para configurar sus ajustes"); //Actualiza el texto
        clickText.setFont(new Font("Serif", Font.PLAIN, 30));
        this.add(clickText);
        this.revalidate();
    }

    public void clickedANodo() { //Llamado al primer clic de un nodo. Elimina texto y agrega componentes de configuración de nodos.
        removeAll();
        clickedANodo = true;
        leftChild = new JButton("Izquierdo"); //Inicializar botones child
        leftChild.setBackground(Color.GRAY);
        rightChild.setBackground(Color.GRAY);
        leftChild.setForeground(Color.WHITE);
        rightChild.setForeground(Color.WHITE);

        JButton deleteNodo = new JButton("Eliminar Nodo"); //Inicializar boton delete nodo
        deleteNodo.setBackground(Color.GRAY);
        deleteNodo.setForeground(Color.WHITE);

        deleteNodo.addActionListener(new ActionListener() { //Cuando se elimina un nodo, también actualiza el hijo del padre.
            public void actionPerformed(ActionEvent e) {
                Nodo deletedNodo = MouseListener.getPrevClicked();
                Nodo parent = deletedNodo.getParent();
                if (parent != null && parent.getLeftChild() == deletedNodo) {
                    parent.setLeftChild(null);
                } else if (parent != null && parent.getRightChild() == deletedNodo) {
                    parent.setRightChild(null);
                }
                nodoPanel.removeNodo(deletedNodo);
                nodoPanel.repaint();
            }
        });
        this.add(deleteNodo);

        JLabel enterValueText = new JLabel("Enter value: ");
        newValue = new JTextField("Enter",3);
        newValue.addActionListener(new ActionListener() { //Actualiza el nodo seleccionado con el nuevo valor ingresado
            public void actionPerformed(ActionEvent ev) {
                Nodo currentClicked = MouseListener.getPrevClicked();
                currentClicked.setValue(Integer.parseInt(newValue.getText()));
                nodoPanel.repaint();
            }
        });

        leftListener = new ButtonActionListener(leftChild,this,nodoPanel,Color.blue,true); //Agregar listeners a los botones
        rightListener = new ButtonActionListener(rightChild,this,nodoPanel,Color.red,false);
        leftChild.addActionListener(leftListener);
        rightChild.addActionListener(rightListener);

        levelText = new JLabel("Level: 0");

        this.add(leftChild); //Añade todos los componentes al panel.
        this.add(rightChild);
        this.add(enterValueText);
        this.add(newValue);
        this.add(levelText);
        this.revalidate();
    }

    public void updateNodoSettings(Nodo nodo) { //Llamado para cambiar la configuración del nodo cuando se selecciona un nuevo nodo
        if(nodo.getLeftChild() != null) {
            leftChild.setText("Desasignar izquierdo");
        } else {
            leftChild.setText("Izquierdo");
        }
        if(nodo.getRightChild() != null) {
            rightChild.setText("Desasignar derecho");
        } else {
            rightChild.setText("Derecho");
        }
        levelText.setText("Level: " + nodo.getLevel());
        this.revalidate();
    }
}
