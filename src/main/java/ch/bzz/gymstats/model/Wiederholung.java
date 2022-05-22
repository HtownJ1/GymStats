package ch.bzz.gymstats.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Wiederholung {

    private String wiederholungUUID;
    private Integer anzahlWiederholungen;
    private Integer gewicht;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date datum;

    /**
     * zurückgibt wiederholungUUID
     *
     * @return Wert von wiederholungUUID
     */
    public String getWiederholungUUID() {
        return wiederholungUUID;
    }

    /**
     * setzt wiederholungUUID
     *
     * @param wiederholungUUID der Wert zu setzen
     */
    public void setWiederholungUUID(String wiederholungUUID) {
        this.wiederholungUUID = wiederholungUUID;
    }

    /**
     * zurückgibt anzahlWiederholungen
     *
     * @return Wert von anzahlWiederholungen
     */
    public Integer getAnzahlWiederholungen() {
        return anzahlWiederholungen;
    }

    /**
     * setzt anzahlWiederholungen
     *
     * @param anzahlWiederholungen der Wert zu setzen
     */
    public void setAnzahlWiederholungen(Integer anzahlWiederholungen) {
        this.anzahlWiederholungen = anzahlWiederholungen;
    }

    /**
     * zurückgibt gewicht
     *
     * @return Wert von gewicht
     */
    public Integer getGewicht() {
        return gewicht;
    }

    /**
     * setzt gewicht
     *
     * @param gewicht der Wert zu setzen
     */
    public void setGewicht(Integer gewicht) {
        this.gewicht = gewicht;
    }

    /**
     * zurückgibt datum
     *
     * @return Wert von datum
     */
    public Date getDatum() {
        return datum;
    }

    /**
     * setzt datum
     *
     * @param datum der Wert zu setzen
     */
    public void setDatum(Date datum) {
        this.datum = datum;
    }
}
