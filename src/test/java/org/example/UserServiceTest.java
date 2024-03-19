package org.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UtilisateurApi utilisateurApiMock;
    @Test
    public void testCreerUtilisateur() throws ServiceException {
        // Création d'un nouvel utilisateur
        Utilisateur user = new Utilisateur("Jean", "Dupont", "jeandupont@email.com");

        // TODO : Configuration du comportement du mock, utiliser la directive « when » avec sa méthode « thenReturn »
        when( utilisateurApiMock.creerUtilisateur(user) ).thenReturn(Boolean.TRUE);


        // TODO : Création du service avec le mock
        UserService ServiceMock = mock(UserService.class);
        // TODO : Appel de la méthode à tester
        ServiceMock.creerUtilisateur(user);
        // TODO : Vérification de l'appel à l'API
        verify(ServiceMock).creerUtilisateur(user);


        // Exercice 03 :
        // Verification que la fonction creerUtilisateur n'a jamais été appelée avec un paramatere Null
        verify(ServiceMock,never()).creerUtilisateur(null);

        // Définition d'un ID fictif
        int idUtilisateur = 123;
// TODO: Configuration du mock pour renvoyer l'ID
    when(ServiceMock.creerUtilisateur(user)).thenReturn(idUtilisateur);
// Appel de la méthode à tester
       int nb =  ServiceMock.creerUtilisateur(user);
// TODO: Vérification de l'ID de l'utilisateur
        assertEquals(123, nb);

        ///////////////////
        ArgumentCaptor<Utilisateur> argumentCaptor = ArgumentCaptor.forClass(Utilisateur.class);
        when(utilisateurApiMock.creerUtilisateur(any(Utilisateur.class))).thenReturn(true);
        utilisateurApiMock.creerUtilisateur(user);
        // capturer l'argument user
        verify(utilisateurApiMock).creerUtilisateur(argumentCaptor.capture());
        Utilisateur utilisateurCapture = argumentCaptor.getValue();

// TODO : Vérification des arguments capturés, utiliser les getters de l’obj
        assertEquals(user.Prenom, utilisateurCapture.Prenom);
        assertEquals(user.Nom, utilisateurCapture.Nom);
        assertEquals(user.Adresse, utilisateurCapture.Adresse);
    }
}