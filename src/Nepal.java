/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.JDialog;

import controller.NepalController;
import view.NepalView;

/**
 *
 * @author diaz
 */
public class Nepal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //VinotecaModel model = new VinotecaModel();
        NepalView view = new view.NepalView();
        NepalController controller = new NepalController(view);
        JDialog.setDefaultLookAndFeelDecorated(true); 

        view.setLocationRelativeTo(null);
        view.setVisible(true);// TODO code application logic here
    }
}
