
package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tree.BinaryTree;
import tree.BinaryTreeMain;
import tree.Nodo;

public class ButtonActionListener implements ActionListener {
    private boolean activateState = false;

    private final JButton button;
    private final NodoSettings nodoSettings;
    private final Color setColor;
    private final boolean isLeftButton; 
    private final NodoPanel nodoPanel;

    public ButtonActionListener(JButton button, NodoSettings nodoSettings, NodoPanel nodoPanel, Color setColor, boolean isLeftButton) {
        this.button = button;
        this.nodoSettings = nodoSettings;
        this.setColor = setColor;
        this.nodoPanel = nodoPanel;
        this.isLeftButton = isLeftButton;
    }

    public boolean getActiveState() {
        return activateState;
    }

    public void setActivateState(boolean activateState) {
        this.activateState = activateState;
    }

    public void flipColor() {
        if (button.getBackground() == Color.GRAY) {
            button.setBackground(setColor);
        } else {
            button.setBackground(Color.GRAY);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(activateState);
        Nodo currentNodo = MouseListener.getPrevClicked();
        if (currentNodo != null) { 
            if (isLeftButton) {
                currentNodo.setLeftChild(null);
            } else {
                currentNodo.setRightChild(null);
            }
            nodoPanel.repaint();
            nodoSettings.updateNodoSettings(currentNodo);
        }
        JButton clickedChildButton = nodoSettings.getClickedChildButton();
        System.out.println(clickedChildButton);
        if (clickedChildButton == null) { 
            nodoSettings.setClickedChildButton(button);
            activateState = true;
            flipColor();
        } else if (clickedChildButton == button) { 
            activateState = false;
            flipColor();
            nodoSettings.setClickedChildButton(null);
        } else { 
            nodoSettings.flipClickedChildButton();
        }
        nodoSettings.revalidate();
    }
}
