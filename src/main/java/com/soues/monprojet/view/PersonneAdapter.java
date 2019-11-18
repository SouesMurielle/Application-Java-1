package com.soues.monprojet.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.soues.monprojet.R;
import com.soues.monprojet.model.beans.EleveBean;
import com.soues.monprojet.model.beans.EnseignantBean;
import com.soues.monprojet.model.beans.PersonneBean;

import java.util.ArrayList;

//Role : Afficher les données et non les manipuler
public class PersonneAdapter extends RecyclerView.Adapter<PersonneAdapter.ViewHolder> {

    private ArrayList<PersonneBean> personnes;
    private int couleurEleve, couleurEnseignant;
    private OnPersonneListener onPersonneListener;

    public PersonneAdapter(ArrayList<PersonneBean> personnes, OnPersonneListener onPersonneListener) {
        this.personnes = personnes;
        this.onPersonneListener = onPersonneListener;
    }

    //Création de ligne
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ligne_personne, parent, false);
        couleurEleve = ContextCompat.getColor(parent.getContext(), R.color.colorPrimaryDark);
        couleurEnseignant = ContextCompat.getColor(parent.getContext(), R.color.colorAccent);

        return new PersonneAdapter.ViewHolder(view);
    }

    //Afficher une ligne
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final PersonneBean personneBean = personnes.get(position);

        holder.tv_nom.setText(personneBean.getNom());
        holder.tv_prenom.setText(personneBean.getPrenom());

        if (personneBean instanceof EleveBean) {
            holder.tv_section.setText(((EleveBean) personneBean).getClasse());
            holder.tv_nom.setTextColor(couleurEleve);
            holder.tv_prenom.setTextColor(couleurEleve);

        } else if (personneBean instanceof EnseignantBean) {
            holder.tv_section.setText(((EnseignantBean) personneBean).getMatiere());
            holder.tv_nom.setTextColor(couleurEnseignant);
            holder.tv_prenom.setTextColor(couleurEnseignant);
        }

        //Entre parenthèses : nouvelle classe (dite anonyme) qui n'est valable que dans cette fonction et elle ne possède qu'une méthode : onClick (cf en dessous)
        holder.root.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPersonneListener != null)
                    onPersonneListener.onClick(personneBean);
//                Toast.makeText(holder.tv_nom.getContext(), "Clic sur" + personneBean.getPrenom() + " " + personneBean.getNom(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Combien d'item dans le recyclerview (nb total de la liste)
    @Override
    public int getItemCount() {
        return personnes.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_nom, tv_prenom, tv_section;
        public View root;

        //Inner class : classe interne à une autre
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_nom = itemView.findViewById(R.id.tv_nom);
            tv_prenom = itemView.findViewById(R.id.tv_prenom);
            tv_section = itemView.findViewById(R.id.tv_section);
            root = itemView.findViewById(R.id.root);
        }
    }

    public interface OnPersonneListener {
        void onClick(PersonneBean personneBean);
    }

}
