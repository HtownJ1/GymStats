package ch.bzz.gymstats.model;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

/**
 * Maschine with the the name and the muscle that it's training
 */
public class Maschine {

    @FormParam("maschineUUID")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String maschineUUID;

    @FormParam("name")
    @NotEmpty
    @Size(min=4, max=40)
    private String name;

    @FormParam("muskel")
    @NotEmpty
    @Size(min=4, max=40)
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
