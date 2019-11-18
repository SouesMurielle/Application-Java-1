package com.soues.monprojet.model.beans;

public class EnseignantBean extends PersonneBean {

    private String matiere;

    public EnseignantBean(String nom, String prenom, String matiere) {
        super(nom, prenom);
        this.matiere = matiere;
    }

    public EnseignantBean(String nom, String prenom) {
        super(nom, prenom);
    }

    @Override
    public String afficher() {
        return "Enseignant : " + nom + " " + prenom + " (" + matiere + ")";

    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }
}
