package com.soues.monprojet.model.beans;

public abstract class PersonneBean {

    protected String nom, prenom;
    protected int age;

    public PersonneBean(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public abstract String afficher();

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
