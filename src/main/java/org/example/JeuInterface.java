package org.example;

public interface JeuInterface {

    public void jouer(Joueur joueur, De de1, De de2) throws JeuFermeException;
    public void fermer();
    public boolean estOuvert();
}
