package exo2tp2;

import java.util.HashMap;
import java.util.Map;

public class Annuaire {
    private Map<String, Contact> contacts;

    public Annuaire() {
        contacts = new HashMap<>();
    }

    public void ajouterContact(Contact contact) {
        contacts.put(contact.getNom(), contact);
    }

    public Contact rechercherContact(String nom) {
        return contacts.get(nom);
    }

    public void modifierContact(String ancienNom, String nouveauNom, String nouveauNumero) {
        Contact contact = contacts.remove(ancienNom);
        if (contact != null) {
            contact.setNom(nouveauNom);
            contact.setNumero(nouveauNumero);
            contacts.put(nouveauNom, contact);
        }
    }

    public void supprimerContact(String nom) {
        contacts.remove(nom);
    }

    public Map<String, Contact> getContacts() {
        return contacts;
    }
}
