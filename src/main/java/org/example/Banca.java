package org.example;

public class Banca implements Banque{

    public int FondInf ;
    public int Fond ;

    public Banca(int FondInf , int Fond){
        this.FondInf = FondInf;
        this.Fond = Fond ;
    }
    @Override
    public void crediter(int somme) {
        this.Fond += somme ;
    }

    @Override
    public boolean est_solvable() {
        // ne repond pas au besoin car elle ne depend pas de la mise ;
        return (Fond < FondInf)? false : true ;
    }

    @Override
    public void debiter(int somme) {
        this.Fond -= somme ;

    }
}
