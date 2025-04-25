package com.oxyl.epf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class planteTest {
    //@Test
    public void testPlanteConstructeurVide() {
        plante plante = new plante();

        //vérification constructeur vide
        assertEquals("", plante.getNom());
        assertEquals(100, plante.getPoint_de_vie());
        assertEquals(1.0, plante.getAttaque_par_seconde());
        assertEquals(1, plante.getDegat_attaque());
        assertEquals(100, plante.getCout());
        assertEquals(0.0, plante.getSoleil_par_seconde());
        assertEquals("aucun", plante.getEffet());
        assertEquals("", plante.getChemin_image());

    }
    //@Test
    public void testPlanteConstructeurNonVide() {
        plante plante = new plante("tournesol",100,0,0,75,25.0,"aucun","/images/tournesol.png");
        assertEquals("tournesol", plante.getNom());
        assertEquals(100, plante.getPoint_de_vie());
        assertEquals(0, plante.getAttaque_par_seconde());
        assertEquals(0, plante.getDegat_attaque());
        assertEquals(75, plante.getCout());
        assertEquals(25.0, plante.getSoleil_par_seconde());
        assertEquals("aucun", plante.getEffet());
        assertEquals("/images/tournesol.png", plante.getChemin_image());

    }
    //@Test
    void testPlanteConstructeurCopie() {
        plante planteTournesol = new plante("tournesol",100,0,0,75,25.0,"aucun","/images/tournesol.png");

        // Création d'une copie de cette plante
        plante planteTournesolCopie = new plante(planteTournesol);

        assertEquals(planteTournesol.getNom(), planteTournesolCopie.getNom());
        assertEquals(planteTournesol.getPoint_de_vie(), planteTournesolCopie.getPoint_de_vie());
        assertEquals(planteTournesol.getAttaque_par_seconde(), planteTournesolCopie.getAttaque_par_seconde());
        assertEquals(planteTournesol.getDegat_attaque(), planteTournesolCopie.getDegat_attaque());
        assertEquals(planteTournesol.getCout(), planteTournesolCopie.getCout());
        assertEquals(planteTournesol.getSoleil_par_seconde(), planteTournesolCopie.getSoleil_par_seconde());
        assertEquals(planteTournesol.getEffet(), planteTournesolCopie.getEffet());
        assertEquals(planteTournesol.getChemin_image(), planteTournesolCopie.getChemin_image());
    }

    //@Test
    void testSetEtGet() {
        plante plante = new plante();

        plante.setNom("tournesol");
        plante.setPoint_de_vie(100);
        plante.setAttaque_par_seconde(0.0);
        plante.setDegat_attaque(0);
        plante.setCout(75);
        plante.setSoleil_par_seconde(25.0);
        plante.setEffet("aucun");
        plante.setChemin_image("/images/tournesol.png");

        // Vérifier si les valeurs sont bien modifiées
        assertEquals("tournesol", plante.getNom());
        assertEquals(100, plante.getPoint_de_vie());
        assertEquals(0.0, plante.getAttaque_par_seconde());
        assertEquals(0, plante.getDegat_attaque());
        assertEquals(75, plante.getCout());
        assertEquals(25.0, plante.getSoleil_par_seconde());
        assertEquals("aucun", plante.getEffet());
        assertEquals("/images/tournesol.png", plante.getChemin_image());
    }



}
