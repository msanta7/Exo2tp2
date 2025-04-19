package exo2tp2;

import java.util.ArrayList;
import java.util.List;

public class Annuaire {
    private List<Contact> contacts;

    public Annuaire() {
        contacts = new ArrayList<>();
    }

    public void ajouterContact(Contact contact) {
        contacts.add(contact);
    }

    public Contact rechercherContact(String nom) {
        for (Contact c : contacts) {
            if (c.getNom().equalsIgnoreCase(nom)) {
                return c;
            }
        }
        return null;
    }

    public void modifierContact(String ancienNom, String nouveauNom, String nouveauNumero) {
        for (Contact c : contacts) {
            if (c.getNom().equalsIgnoreCase(ancienNom)) {
                c.setNom(nouveauNom);
                c.setNumero(nouveauNumero);
                break;
            }
        }
    }

    public void supprimerContact(String nom) {
        contacts.removeIf(c -> c.getNom().equalsIgnoreCase(nom));
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}
