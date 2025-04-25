package com.oxyl.epf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class mapTest {
    //@Test
    public void testMapConstructeurVide() {
        map m = new map();
        assertEquals(5,m.getLigne());
        assertEquals(9,m.getColonne());
        assertEquals("",m.getChemin_image());
    }
    //@Test
    public void testMapConstructeurNonVide() {
        map m = new map(5,9,"/images/map1.png");
        assertEquals(5,m.getLigne());
        assertEquals(9,m.getColonne());
        assertEquals("/images/map1.png",m.getChemin_image());
    }
   //@Test
    public void testPlanteConstructeurCopie(){
        map m = new map(5,9,"/images/map1.png");
        map mCopie=new map(m);
        assertEquals(m.getLigne(),mCopie.getLigne());
        assertEquals(m.getColonne(),mCopie.getColonne());
        assertEquals(m.getChemin_image(),mCopie.getChemin_image());
    }
    //@Test
    public void TestGetEtSet(){
       map m=new map();
       m.setLigne(5);
       m.setColonne(9);
       m.setChemin_image("/images/map1.png");

       assertEquals(5,m.getLigne());
       assertEquals(9,m.getColonne());
       assertEquals("/images/map1.png",m.getChemin_image());
    }
}
