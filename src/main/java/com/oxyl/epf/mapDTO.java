package com.oxyl.epf;

import com.fasterxml.jackson.annotation.JsonProperty;

public class mapDTO {
    private int id_map;
    private int ligne;
    private int colonne;
    @JsonProperty("chemin_image")
    private String chemin_image;

    public mapDTO() {}
    public mapDTO(int id_map,int ligne, int colonne, String chemin_image) {
        this.id_map = id_map;
        this.ligne = ligne;
        this.colonne = colonne;
        this.chemin_image = chemin_image;
    }
    public mapDTO(int ligne, int colonne, String chemin_image) {

        this.ligne = ligne;
        this.colonne = colonne;
        this.chemin_image = chemin_image;
    }
    public int getId_map() {return id_map;}
    public int getLigne() {return ligne;}
    public int getColonne() {return colonne;}
    public String getChemin_image() {return chemin_image;}

    public void setLigne(int ligne) {this.ligne = ligne;}
    public void setColonne(int colonne) {this.colonne = colonne;}
    public void setChemin_image(String chemin_image) {this.chemin_image = chemin_image;}


    public void setId_map(int id) {
        this.id_map = id;
    }
}
