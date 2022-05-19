package ch.bzz.gymstats.model;

public class Maschine {

    private String maschineUUID;
    private String name;
    private String muskel;

    public Maschine(String maschineUUID, String name, String muskel) {
        this.maschineUUID = maschineUUID;
        this.name = name;
        this.muskel = muskel;
    }

    public String getMaschineUUID() {
        return maschineUUID;
    }

    public void setMaschineUUID(String maschineUUID) {
        this.maschineUUID = maschineUUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMuskel() {
        return muskel;
    }

    public void setMuskel(String muskel) {
        this.muskel = muskel;
    }
}
