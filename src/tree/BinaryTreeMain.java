
package tree;
import java.awt.*;
import javax.swing.*;
import gui.ButtonActionListener;
import gui.ControlPanel;
import gui.MouseListener;
import gui.NodoPanel;
import gui.NodoSettings;
import gui.TraversalPanel;
/**
 *
 * @author HP
 */
public class BinaryTreeMain {
    
    public static void main(String[] args) {
        BinaryTreeMain ma = new BinaryTreeMain();
        JFrame frame = new JFrame("Binary Tree");
        ma.setUpFrame(frame);
        ma.addComponentsToFrame(frame);
    }

    private void setUpFrame(JFrame frame) { 
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800,800);
    }

    private void addComponentsToFrame(JFrame frame) { 
        NodoPanel nodoPanel = new NodoPanel(); 
        ControlPanel controlPanel = new ControlPanel(nodoPanel); 
        TraversalPanel traversalPanel = new TraversalPanel(nodoPanel); 
        NodoSettings nodoSettings = new NodoSettings(nodoPanel); 

        MouseListener ccl = new MouseListener(nodoPanel, nodoSettings); 
        nodoPanel.addMouseListener(ccl);
        nodoPanel.addMouseMotionListener(ccl); 
        nodoPanel.addMouseWheelListener(ccl);

        frame.add(nodoSettings,BorderLayout.NORTH); 
        frame.add(controlPanel,BorderLayout.SOUTH);
        frame.add(traversalPanel,BorderLayout.WEST);
        frame.add(nodoPanel,BorderLayout.CENTER);
        frame.setVisible(true); 
    }
}
