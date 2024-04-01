package org.example;

public class jeu implements JeuInterface{
    public Banque banque ;
    public String Nom_jeu ;

    public Boolean etat_jeu ;  // True == ouvert , False == Fermé

    public jeu( Banque banque ,  String Nom_jeu , Boolean etat_jeu){
        this.Nom_jeu = Nom_jeu ;
        this.banque = banque ;
        this.etat_jeu = etat_jeu ;
    }
    public jeu(Banque banque){
        this.banque = banque ;
        this.etat_jeu = true ;
        this.Nom_jeu = "jeu du 7" ;
    }


    @Override
    public void jouer(Joueur joueur, De de1, De de2) throws JeuFermeException {
        if(!this.estOuvert()){
            throw new JeuFermeException("le jeu est fermé");
        }

        int Mise = joueur.mise(); // Tjr positive

        try {
            joueur.debiter(Mise);
        } catch (DebitImpossibleException e) {
            throw new RuntimeException(e);
        }


        if(de1.lancer() + de2.lancer() == 7){
            this.banque.debiter(2*Mise);
            joueur.crediter(2*Mise);
        }else{
                // la banque recoit la mise
            this.banque.crediter(Mise);
        }

        // Verification de la banque
        if(!this.banque.est_solvable()){
            this.fermer();
        }

    }
    @Override
    public void fermer() {
        this.etat_jeu = false ;
    }
    @Override
    public boolean estOuvert() {
        return etat_jeu ? true : false;
    }







}
