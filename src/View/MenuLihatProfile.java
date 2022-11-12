package View;

import javax.swing.*;
import Control.Sql;
import Model.*;

public class MenuLihatProfile {

    public MenuLihatProfile(Customer customer) {
        JFrame frame = new JFrame();
        frame.setTitle("Data Profil");
        frame.setSize(700, 600);

        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Sql con = new Sql();
    }
}
