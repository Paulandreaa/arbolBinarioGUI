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
public class TraversalPanel extends JPanel{
     public TraversalPanel(NodoPanel nodoPanel) {
        this.setLayout(new GridLayout(4,1,0,10));

        JButton preOrderButton = new JButton("Preorder");
        preOrderButton.setBackground(Color.GRAY);
        preOrderButton.setForeground(Color.WHITE);
        preOrderButton.addActionListener(new PreOrderListener(nodoPanel));
        this.add(preOrderButton);

        JButton inOrderButton = new JButton("InOrder");
        inOrderButton.setBackground(Color.GRAY);
        inOrderButton.setForeground(Color.WHITE);
        inOrderButton.addActionListener(new InOrderListener(nodoPanel));
        this.add(inOrderButton);

        JButton postOrderButton = new JButton("PostOrder");
        postOrderButton.setBackground(Color.GRAY);
        postOrderButton.setForeground(Color.WHITE);
        postOrderButton.addActionListener(new PostOrderListener(nodoPanel));
        this.add(postOrderButton);

        JButton levelOrderButton = new JButton("LevelOrder");
        levelOrderButton.setBackground(Color.GRAY);
        levelOrderButton.setForeground(Color.WHITE);
        levelOrderButton.addActionListener(new LevelOrderListener(nodoPanel));
        this.add(levelOrderButton);
    }
}

class PreOrderListener implements ActionListener {

    private final NodoPanel nodoPanel;

    public PreOrderListener(NodoPanel nodoPanel) {
        this.nodoPanel = nodoPanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        BinaryTree tree = new BinaryTree(nodoPanel);
        tree.traversePreOrder();
    }
}

class InOrderListener implements ActionListener {

    private final NodoPanel nodoPanel;

    public InOrderListener(NodoPanel nodoPanel) {
        this.nodoPanel = nodoPanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        BinaryTree tree = new BinaryTree(nodoPanel);
        tree.traverseInOrder();
    }
}

class PostOrderListener implements ActionListener {

    private final NodoPanel nodoPanel;

    public PostOrderListener(NodoPanel nodoPanel) {
        this.nodoPanel = nodoPanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        BinaryTree tree = new BinaryTree(nodoPanel);
        tree.traversePostOrder();
    }
}

class LevelOrderListener implements ActionListener {

    private final NodoPanel nodoPanel;

    public LevelOrderListener(NodoPanel nodoPanel) {
        this.nodoPanel = nodoPanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        BinaryTree tree = new BinaryTree(nodoPanel);
        tree.traverseLevelOrder();
    }
}
