package org.example;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class jeuTest {
    @Test
    void jouer() {
        // test simple :  Si le jeu est fermé  ==> Test d'état .
       Banca banque = mock(Banca.class);
       when(banque.est_solvable()).thenReturn(false); // une banque insolvable
       Player joueur = mock(Player.class) ;
       De de = new De() {
           @Override
           public int lancer() {
               return  new Random().nextInt(0,7);
           }
       };
       jeu JeuCarte = new jeu( banque ) ;
       JeuCarte.fermer();
        assertThrows(JeuFermeException.class, () -> {
            JeuCarte.jouer(joueur , de , de);
        });

        // tester le cas où le joueur est insolvable;
        JeuCarte.etat_jeu = true ; // le jeu reviens ouvert
        when(joueur.mise()).thenReturn(10000);
        try {
            JeuCarte.jouer(joueur , de , de);
        } catch (JeuFermeException e) {
            throw new RuntimeException(e);
        }
        assertEquals(false ,  JeuCarte.estOuvert() );// le jeu se fermera si le joueur est insolvable

        // tester que le jeu ne touche pas aux dés ? =>  on ne peut pas le faire car les tests unitaires s'appuient sur l'isolation cad que la classe jeu ne dépend pas de la classe Dé

        // D'autre sceniarios:
        // 1 - le joueur gangne
        // 2- le joueur perde
        // pour cela on va definir des dés trichés

        De detriche1 = new De() {
            @Override
            public int lancer() {
                return 3;
            }
        };
        De detriche2 = new De() {
            @Override
            public int lancer() {
                return 4;
            }
        };

        Banca banque2 = mock(Banca.class); // une banque solvable
        when(banque2.est_solvable()).thenReturn(true);

        jeu Jeu = new jeu(banque2);

        Joueur joeurMock = mock(Joueur.class) ;
        when(joeurMock.mise()).thenReturn(500);

        // le cas où le joeur est gagnant: on la verifier par l'appel au fonction crediter() ;
        try {
            Jeu.jouer(joeurMock , detriche1 , detriche2);
            verify(joeurMock).crediter(anyInt());
        } catch (JeuFermeException e) {
            throw new RuntimeException(e);
        }

        // le cas d'un joueur perdant
        try {
            Jeu.jouer(joueur , detriche1 , detriche1);
            verify(banque2).crediter(anyInt());
        } catch (JeuFermeException e) {
            throw new RuntimeException(e);
        }









    }
}
