package com.soues.monprojet.model.beans;

import java.util.Random;

public class EleveBean extends PersonneBean {

    public static final int AGE8ADULTE = 18;
    private static final Random random = new Random();

    private String classe;

    public EleveBean(String nom, String prenom) {
        this(nom, prenom, null);
    }

    public EleveBean(String nom, String prenom, int age) {
        this(nom, prenom, null, age);
    }

    public EleveBean(String nom, String prenom, String classe) {
        this(nom, prenom, classe, random.nextInt(100));
    }

    public EleveBean(String nom, String prenom, String classe, int age) {
        super(nom, prenom);
        this.classe = classe;
        this.age = age;
    }

    @Override
    public String afficher() {
        return "Elève : " + nom + " " + prenom + " (" + classe + ")";
    }

    public boolean isAdulte() {
        return age >= AGE8ADULTE;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }
}
