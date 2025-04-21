package exo2tp2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Controller implements ActionListener {
    private Annuaire model;
    private View view;

    private JButton ajouterButton;
    private JButton rechercherButton;
    private JButton modifierButton;
    private JButton supprimerButton;

    public Controller(Annuaire model, View view) {
        this.model = model;
        this.view = view;

        ajouterButton = view.ajouterButton;
        rechercherButton = view.rechercherButton;
        modifierButton = view.modifierButton;
        supprimerButton = view.supprimerButton;

        ajouterButton.addActionListener(this);
        rechercherButton.addActionListener(this);
        modifierButton.addActionListener(this);
        supprimerButton.addActionListener(this);

        
        view.actualiserTable(model.getContacts().values());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == ajouterButton) {
            String nom = view.getNom();
            String numero = view.getNumero();
            if (!nom.isEmpty() && !numero.isEmpty()) {
                model.ajouterContact(new Contact(nom, numero));
                view.actualiserTable(model.getContacts().values());
            } else {
                view.afficherMessage("Veuillez remplir tous les champs.");
            }
        }

        else if (source == rechercherButton) {
            String nom = view.getNom();
            Contact c = model.rechercherContact(nom);
            if (c != null) {
                view.nomField.setText(c.getNom());
                view.numeroField.setText(c.getNumero());
                view.afficherMessage("Contact trouvé !");
            } else {
                view.afficherMessage("Contact introuvable !");
            }
        }

        else if (source == modifierButton) {
            String ancienNom = view.getNomSelectionne();
            String nouveauNom = view.getNom();
            String nouveauNumero = view.getNumero();
            if (ancienNom != null && !nouveauNom.isEmpty() && !nouveauNumero.isEmpty()) {
                model.modifierContact(ancienNom, nouveauNom, nouveauNumero);
                view.afficherMessage("Contact modifié !");
                view.actualiserTable(model.getContacts().values());
            } else {
                view.afficherMessage("Veuillez sélectionner un contact dans le tableau.");
            }
        }

        else if (source == supprimerButton) {
            String nom = view.getNomSelectionne();
            if (nom != null) {
                model.supprimerContact(nom);
                view.afficherMessage("Contact supprimé.");
                view.actualiserTable(model.getContacts().values());
                view.nomField.setText("");
                view.numeroField.setText("");
            } else {
                view.afficherMessage("Veuillez sélectionner un contact à supprimer.");
            }
        }
    }

    public void demarrer() {
        view.setVisible(true);
    }
}
