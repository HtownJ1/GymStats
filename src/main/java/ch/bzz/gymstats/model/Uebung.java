package ch.bzz.gymstats.model;

import ch.bzz.gymstats.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Uebung with the name of the uebung and with the stats of each rep.
 */
public class Uebung {

    private String uebungUUID;
    private List<String> wiederholungListe;
    @JsonIgnore
    private Maschine maschine;
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
