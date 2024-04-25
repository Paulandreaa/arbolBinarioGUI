
package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tree.BinaryTree;
import tree.BinaryTreeMain;
import tree.Nodo;

public class ControlPanel extends JPanel {
     public ControlPanel(NodoPanel nodoPanel) {
        JButton sortButton = new JButton("Clasificar");
        JButton reconfigureButton = new JButton("Reconfigurar");
        JButton invertButton = new JButton("Invertir");
        JButton emptyButton = new JButton("Vacio");
        invertButton.setBackground(Color.GRAY);
        invertButton.setForeground(Color.WHITE);
        sortButton.setBackground(Color.GRAY);
        sortButton.setForeground(Color.WHITE);
        reconfigureButton.setBackground(Color.GRAY);
        reconfigureButton.setForeground(Color.WHITE);
        emptyButton.setBackground(Color.GRAY);
        emptyButton.setForeground(Color.WHITE);

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BynaryTree unsortedTree = new BynaryTree(nodoPanel);
                unsortedTree.sort();
            }
        });

        reconfigureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nodoPanel.reconfigureTree();
            }
        });

        invertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BinaryTree tree = new BinaryTree(nodoPanel);
                tree.invert();
            }
        });

        emptyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BinaryTree tree = new BinaryTree(nodoPanel);
                tree.randomizeTree();
            }
        });
        this.add(sortButton);
        this.add(reconfigureButton);
        this.add(invertButton);
        this.add(emptyButton);
    }
}
