package com.oxyl.epf;

public class map {
    private int id_map;
    private int ligne;
    private int colonne;
    private String chemin_image;

    public map( int ligne, int colonne, String chemin_image) {

        this.ligne = ligne;
        this.colonne = colonne;
        this.chemin_image = chemin_image;
    }
    public map( int id_map,int ligne, int colonne, String chemin_image) {
        this.id_map = id_map;
        this.ligne = ligne;
        this.colonne = colonne;
        this.chemin_image = chemin_image;
    }
    public map(){
        this.ligne =5;
        this.colonne = 9;
        this.chemin_image = "";
    }
    public map(map uneMap){
        this.ligne = uneMap.getLigne();
        this.colonne = uneMap.getColonne();
        this.chemin_image = uneMap.getChemin_image();
    }

    public int getLigne() {
        return ligne;
    }
    public void setLigne(int ligne) {
        this.ligne = ligne;
    }
    public int getColonne() {
        return colonne;
    }
    public void setColonne(int colonne) {
        this.colonne = colonne;
    }
    public String getChemin_image() {
        return chemin_image;
    }
    public void setChemin_image(String chemin_image) {
        this.chemin_image = chemin_image;
    }
    public int getId_map() {
        return id_map;
    }

    public void setId_map(int id_map) {
        this.id_map = id_map;
    }
}
