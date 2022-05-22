package ch.bzz.gymstats.model;

public class Maschine {

    private String maschineUUID;
    private String name;
    private String muskel;

    /**
     * zurückgibt maschineUUID
     *
     * @return Wert von maschineUUID
     */
    public String getMaschineUUID() {
        return maschineUUID;
    }

    /**
     * setzt maschineUUID
     *
     * @param maschineUUID der Wert zu setzen
     */
    public void setMaschineUUID(String maschineUUID) {
        this.maschineUUID = maschineUUID;
    }

    /**
     * zurückgibt name
     *
     * @return Wert von name
     */
    public String getName() {
        return name;
    }

    /**
     * setzt name
     *
     * @param name der Wert zu setzen
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * zurückgibt muskel
     *
     * @return Wert von muskel
     */
    public String getMuskel() {
        return muskel;
    }

    /**
     * setzt muskel
     *
     * @param muskel der Wert zu setzen
     */
    public void setMuskel(String muskel) {
        this.muskel = muskel;
    }
}
