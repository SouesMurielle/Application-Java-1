package com.soues.monprojet.controler;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.soues.monprojet.R;
import com.soues.monprojet.model.beans.EleveBean;
import com.soues.monprojet.model.beans.EnseignantBean;
import com.soues.monprojet.model.beans.PersonneBean;
import com.soues.monprojet.view.PersonneAdapter;

import java.util.ArrayList;

//implements concerne les interfaces
//extends concerne les classes
public class MainActivity extends AppCompatActivity implements View.OnClickListener, PersonneAdapter.OnPersonneListener {

    //Composants graphiques
    Button bt_ajouter, bt_ajouter_plusieurs, bt_supprimer_dernier;
    EditText et_nom, et_prenom;
    SeekBar sb;
    RadioButton rb_eleve, rb_enseignant;
    RecyclerView rv;

    //Données = Collections
    ArrayList<PersonneBean> personnes;

    //Outil
    PersonneAdapter personneAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //cherche le composant qui correspond au composant ciblé
//        tv_console = findViewById(R.id.tv_console);
        bt_ajouter = findViewById(R.id.bt_ajouter);
        bt_ajouter_plusieurs = findViewById(R.id.bt_ajouter_plusieurs);
        bt_supprimer_dernier = findViewById(R.id.bt_supprimer_dernier);
        et_nom = findViewById(R.id.et_nom);
        et_prenom = findViewById(R.id.et_prenom);
        sb = findViewById(R.id.sb);
        rb_eleve = findViewById(R.id.rb_eleve);
        rb_enseignant = findViewById(R.id.rb_enseignant);
        rv = findViewById(R.id.rv);

        bt_ajouter.setOnClickListener(this);
        bt_ajouter_plusieurs.setOnClickListener(this);
        bt_supprimer_dernier.setOnClickListener(this);

        personnes = new ArrayList<>();

        //On donne notre ArrayList à notre Adapter
        personneAdapter = new PersonneAdapter(personnes, this);

        //On donne notre adapter à notre RecyclerView
        rv.setAdapter(personneAdapter);

        //On choisit la "présentation", la façon d'afficher
        rv.setLayoutManager(new LinearLayoutManager(this));
        //Autres types de rendus
        //Grid : context, puis nombre de colonnes
//        rv.setLayoutManager(new GridLayoutManager(this,2));
        //StaggeredGrid : Nombre de colonnes, orientation (plusieurs choix possibles)
//        rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL));
    }

    @Override
    public void onClick(View v) {

        if (v == bt_ajouter) {
            String nom = et_nom.getText().toString();
            String prenom = et_prenom.getText().toString();

            ajouter(nom, prenom, 1);

//            rafraichirEcran();

        } else if (v == bt_supprimer_dernier) {
            supprimerDernier();

//            rafraichirEcran();

        } else if (v == bt_ajouter_plusieurs) {
            int nb = sb.getProgress();
            String nom = et_nom.getText().toString();
            String prenom = et_prenom.getText().toString();

            ajouter(nom, prenom, nb);

//            rafraichirEcran();
        }

    }

    public void ajouter(String nom, String prenom, int nb) {

        if (nom.trim().length() == 0) {
            Toast.makeText(this, "Le nom est vide", Toast.LENGTH_SHORT).show();
            return;
        }
        if (prenom.trim().length() == 0) {
            Toast.makeText(this, "Le prénom est vide", Toast.LENGTH_SHORT).show();
            return;
        }


        for (int i = 0; i < nb; i++) {
            if (rb_eleve.isChecked()) {
                EleveBean eleve = new EleveBean(nom, prenom + personnes.size(), "6ème");
                personnes.add(eleve);
            } else {
                EnseignantBean enseignant = new EnseignantBean(nom, prenom + personnes.size(), "Français");
                personnes.add(enseignant);
            }
        }
        //Met à jour la liste avec les données modifiées
        personneAdapter.notifyDataSetChanged();
    }


    public void supprimerDernier() {
        PersonneBean dernier = null;
        for (PersonneBean personne : personnes) {
            if (rb_eleve.isChecked() && personne instanceof EleveBean) dernier = personne;
            else if (rb_enseignant.isChecked() && personne instanceof EnseignantBean)
                dernier = personne;
        }

        if (dernier != null)
            personnes.remove(dernier);
        else if (rb_eleve.isChecked())
            Toast.makeText(this, "Il n'y a plus d'élève dans la liste", Toast.LENGTH_SHORT).show();
        else if (rb_enseignant.isChecked())
            Toast.makeText(this, "Il n'y a plus d'enseignant dans la liste", Toast.LENGTH_SHORT).show();

        personneAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(PersonneBean personneBean) {
        Toast.makeText(this, "Clic sur " + personneBean.getPrenom() + " " + personneBean.getNom(), Toast.LENGTH_SHORT).show();
    }


    //        Désormais on ne va plus utiliser tv_console, mais le recyclervieuw. On va donc appeler la fonction notifyDataSetChanged();

//    public void rafraichirEcranUneListe() {
//        String resultat = "Élèves : \n";
//
//        for (PersonneBean personne : personnes) {
//            resultat += personne.afficher() + "\n";
//        }
//
//        tv_console.setText(resultat);
//    }

//    public void rafraichirEcran() {
//        String resultat = "Élèves : \n";
//
//        for (PersonneBean personne : personnes) {
//            if (personne instanceof EleveBean) {
////                EleveBean eleve = (EleveBean) personne;
//                resultat += personne.afficher() + "\n";
//            }
//        }
//
//        resultat += "\nEnseignants : \n";
//
//        for (PersonneBean personne : personnes) {
//            if (personne instanceof EnseignantBean) {
////                EnseignantBean enseignant = (EnseignantBean) personne;
//                resultat += personne.afficher() + "\n";
//            }
//        }
//
//        tv_console.setText(resultat);
//    }


//    public void supprimerDernier() {
//
//        if (rb_eleve.isChecked()) {
//
//            EleveBean dernierEleve = null;
//
//            for (PersonneBean personne : personnes) {
//                if (personne instanceof EleveBean) {
//                    dernierEleve = (EleveBean) personne;
//                }
//            }
//
//            if (dernierEleve != null) {
//                personnes.remove(dernierEleve);
//            } else {
//                Toast.makeText(this, "Il n'y a plus d'élève dans la liste", Toast.LENGTH_SHORT).show();
//            }
//
//        } else {
//            EnseignantBean dernierEnseignant = null;
//
//            for (PersonneBean personne : personnes) {
//                if (personne instanceof EnseignantBean) {
//                    dernierEnseignant = (EnseignantBean) personne;
//                }
//            }
//
//            if (dernierEnseignant != null) {
//                personnes.remove(dernierEnseignant);
//            } else {
//                Toast.makeText(this, "Il n'y a plus d'enseignant dans la liste", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}










