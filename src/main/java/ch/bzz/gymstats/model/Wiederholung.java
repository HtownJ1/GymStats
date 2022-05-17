package ch.bzz.gymstats.model;

import java.time.LocalDate;

public class Wiederholung {

    private String wiederholungUUID;
    private Integer anzahlWiederholungen;
    private Integer gewicht;
    private LocalDate datum;

    public Wiederholung(String wiederholungUUID, Integer anzahlWiederholungen, Integer gewicht, LocalDate datum) {
        this.wiederholungUUID = wiederholungUUID;
        this.anzahlWiederholungen = anzahlWiederholungen;
        this.gewicht = gewicht;
        this.datum = datum;
    }

    public String getWiederholungUUID() {
        return wiederholungUUID;
    }

    public void setWiederholungUUID(String wiederholungUUID) {
        this.wiederholungUUID = wiederholungUUID;
    }

    public Integer getAnzahlWiederholungen() {
        return anzahlWiederholungen;
    }

    public void setAnzahlWiederholungen(Integer anzahlWiederholungen) {
        this.anzahlWiederholungen = anzahlWiederholungen;
    }

    public Integer getGewicht() {
        return gewicht;
    }

    public void setGewicht(Integer gewicht) {
        this.gewicht = gewicht;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
}
