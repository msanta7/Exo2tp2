package exo2tp2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;

public class View extends JFrame {
    JTextField nomField = new JTextField(20);
    JTextField numeroField = new JTextField(20);
    JButton ajouterButton = new JButton("Ajouter");
    JButton rechercherButton = new JButton("Rechercher");
    JButton modifierButton = new JButton("Modifier");
    JButton supprimerButton = new JButton("Supprimer");

    JTable table;
    DefaultTableModel tableModel;

    public View() {
        setTitle("annuaire téléphonique");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelHaut = new JPanel(new GridLayout(4, 2, 5, 5));
        panelHaut.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelHaut.add(new JLabel("Nom :"));
        panelHaut.add(nomField);
        panelHaut.add(new JLabel("Num :"));
        panelHaut.add(numeroField);
        panelHaut.add(ajouterButton);
        panelHaut.add(rechercherButton);
        panelHaut.add(modifierButton);
        panelHaut.add(supprimerButton);

        tableModel = new DefaultTableModel(new String[]{"Nom", "Num"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(panelHaut, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    String nom = tableModel.getValueAt(row, 0).toString();
                    String numero = tableModel.getValueAt(row, 1).toString();
                    nomField.setText(nom);
                    numeroField.setText(numero);
                }
            }
        });
    }

    public String getNom() {
        return nomField.getText().trim();
    }

    public String getNumero() {
        return numeroField.getText().trim();
    }

    public void afficherMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }


    public void actualiserTable(Collection<Contact> contacts) {
        tableModel.setRowCount(0);
        for (Contact c : contacts) {
            tableModel.addRow(new Object[]{c.getNom(), c.getNumero()});
        }
    }

    public int getSelectedRowIndex() {
        return table.getSelectedRow();
    }

    public String getNomSelectionne() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            return tableModel.getValueAt(row, 0).toString();
        }
        return null;
    }
}
