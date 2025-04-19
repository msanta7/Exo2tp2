package exo2tp2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        setTitle("Annuaire Téléphonique");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Haut (formulaire + boutons)
        JPanel panelHaut = new JPanel(new GridLayout(4, 2, 5, 5));
        panelHaut.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelHaut.add(new JLabel("Nom :"));
        panelHaut.add(nomField);
        panelHaut.add(new JLabel("Numéro :"));
        panelHaut.add(numeroField);
        panelHaut.add(ajouterButton);
        panelHaut.add(rechercherButton);
        panelHaut.add(modifierButton);
        panelHaut.add(supprimerButton);

        // Tableau
        tableModel = new DefaultTableModel(new String[]{"Nom", "Numéro"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Ajout
        add(panelHaut, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);

        // Sélection de ligne → remplissage des champs
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    String nom = (String) tableModel.getValueAt(row, 0);
                    String numero = (String) tableModel.getValueAt(row, 1);
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

    public void actualiserTable(java.util.List<Contact> contacts) {
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
            return (String) tableModel.getValueAt(row, 0);
        }
        return null;
    }
}
