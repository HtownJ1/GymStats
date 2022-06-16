package ch.bzz.gymstats.model;

import ch.bzz.gymstats.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.util.List;

/**
 * Uebung with the name of the uebung and with the stats of each rep.
 */
public class Uebung {

    @FormParam("uebungUUID")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String uebungUUID;

    @FormParam("wiederholungListe")
    @NotNull
    private List<String> wiederholungListe;

    @JsonIgnore
    private Maschine maschine;

    @FormParam("uebungName")
    @NotEmpty
    @Size(min=4, max=40)
    private String uebungName;


    @JsonProperty("maschine")
    public String getMaschineUUID() {
        if (maschine != null) {
            return maschine.getMaschineUUID();
        } else {
            return null;
        }
    }


    public void setMaschineUUID(String maschineUUID) {
        setMaschine(DataHandler.getInstance().readMaschineByUUID(maschineUUID));
    }

    /**
     * zurückgibt uebungUUID
     *
     * @return Wert von uebungUUID
     */
    public String getUebungUUID() {
        return uebungUUID;
    }

    /**
     * setzt uebungUUID
     *
     * @param uebungUUID der Wert zu setzen
     */
    public void setUebungUUID(String uebungUUID) {
        this.uebungUUID = uebungUUID;
    }

    /**
     * zurückgibt wiederholungListe
     *
     * @return Wert von wiederholungListe
     */
    public List<String> getWiederholungListe() {
        return wiederholungListe;
    }

    /**
     * setzt wiederholungListe
     *
     * @param wiederholungListe der Wert zu setzen
     */
    public void setWiederholungListe(List<String> wiederholungListe) {
        this.wiederholungListe = wiederholungListe;
    }

    /**
     * zurückgibt maschine
     *
     * @return Wert von maschine
     */
    public Maschine getMaschine() {
        return maschine;
    }

    /**
     * setzt maschine
     *
     * @param maschine der Wert zu setzen
     */
    public void setMaschine(Maschine maschine) {
        this.maschine = maschine;
    }

    /**
     * zurückgibt uebungName
     *
     * @return Wert von uebungName
     */
    public String getUebungName() {
        return uebungName;
    }

    /**
     * setzt uebungName
     *
     * @param uebungName der Wert zu setzen
     */
    public void setUebungName(String uebungName) {
        this.uebungName = uebungName;
    }
}
