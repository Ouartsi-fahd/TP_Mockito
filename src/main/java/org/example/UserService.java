package org.example;

import java.util.Random;

public class UserService {
        private final UtilisateurApi utilisateurApi;
        public UserService(UtilisateurApi utilisateurApi) {
            this.utilisateurApi = utilisateurApi;
        }
        public int creerUtilisateur(Utilisateur utilisateur) throws ServiceException {
            if(utilisateur.Prenom == null) {
                throw new ServiceException("Echec de la création de l'utilisateur");
            }
            if(utilisateur.Nom == null) {
                throw new ServiceException("Echec de la création de l'utilisateur");
            }
            if(utilisateur.Adresse == null) {
                throw new ServiceException("Echec de la création de l'utilisateur");
            }

            utilisateurApi.creerUtilisateur(utilisateur);
            // supposant que ce nombre est unique
            int nb = new Random().nextInt(201);
            return  nb;
        }
}
