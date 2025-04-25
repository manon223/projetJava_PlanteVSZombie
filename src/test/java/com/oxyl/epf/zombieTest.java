package com.oxyl.epf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class zombieTest {
    //@Test
    public void zombieTestConstructeurVide() {
        zombie z = new zombie();
        //vérification constructeur vide
        assertEquals("", z.getNom());
        assertEquals(100, z.getPoint_de_vie());
        assertEquals(0.5, z.getAttaque_par_seconde());
        assertEquals(10, z.getDegat_attaque());
        assertEquals(1.0, z.getVitesse_de_deplacement());
        assertEquals("", z.getChemin_image());
        assertEquals(1, z.getId_map());
    }
   //@Test
    public void testZombieConstructeurNonVide() {
        zombie z = new zombie("classique",100,1.0,20,1,"/images/classique.png",1);
        assertEquals("classique", z.getNom());
        assertEquals(100, z.getPoint_de_vie());
        assertEquals(1.0, z.getAttaque_par_seconde());
        assertEquals(20, z.getDegat_attaque());
        assertEquals(1, z.getVitesse_de_deplacement());
        assertEquals("/images/classique.png", z.getChemin_image());
        assertEquals(1, z.getId_map());
    }
    //@Test
    void testZombieConstructeurCopie() {
        zombie zClassique = new zombie("classique",100,1.0,20,1,"/images/classique.png",1);

        // Création d'une copie de cette plante
        zombie zClassiqueCopie = new zombie(zClassique);

        assertEquals(zClassique.getNom(), zClassiqueCopie.getNom());
        assertEquals(zClassique.getPoint_de_vie(), zClassiqueCopie.getPoint_de_vie());
        assertEquals(zClassique.getAttaque_par_seconde(), zClassiqueCopie.getAttaque_par_seconde());
        assertEquals(zClassique.getDegat_attaque(), zClassiqueCopie.getDegat_attaque());
        assertEquals(zClassique.getVitesse_de_deplacement(), zClassiqueCopie.getVitesse_de_deplacement());
        assertEquals(zClassique.getChemin_image(), zClassiqueCopie.getChemin_image());
        assertEquals(zClassique.getId_map(), zClassiqueCopie.getId_map());
    }
    //@Test
    void testSetEtGet() {
        zombie z = new zombie();

        z.setNom("classique");
        z.setPoint_de_vie(100);
        z.setAttaque_par_seconde(1.0);
        z.setDegat_attaque(20);
        z.setVitesse_de_deplacement(1);
        z.setChemin_image("/images/classique.png");
        z.setId_map(1);

        // Vérifier si les valeurs sont bien modifiées
        assertEquals("classique", z.getNom());
        assertEquals(100, z.getPoint_de_vie());
        assertEquals(1.0, z.getAttaque_par_seconde());
        assertEquals(20, z.getDegat_attaque());
        assertEquals(1, z.getVitesse_de_deplacement());
        assertEquals("/images/classique.png", z.getChemin_image());
        assertEquals(1, z.getId_map());
    }



}
