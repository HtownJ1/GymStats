package ch.bzz.gymstats.model;

import java.util.List;

public class Uebung {

    private String uebungUUID;
    private List<Wiederholung> wiederholungListe;
    private Maschine maschine;
    private String uebungName;

    public Uebung(String uebungUUID, List<Wiederholung> wiederholungListe, Maschine maschine, String uebungName) {
        this.uebungUUID = uebungUUID;
        this.wiederholungListe = wiederholungListe;
        this.maschine = maschine;
        this.uebungName = uebungName;
    }

    public String getUebungUUID() {
        return uebungUUID;
    }

    public void setUebungUUID(String uebungUUID) {
        this.uebungUUID = uebungUUID;
    }

    public List<Wiederholung> getWiederholungListe() {
        return wiederholungListe;
    }

    public void setWiederholungListe(List<Wiederholung> wiederholungListe) {
        this.wiederholungListe = wiederholungListe;
    }

    public Maschine getMaschine() {
        return maschine;
    }

    public void setMaschine(Maschine maschine) {
        this.maschine = maschine;
    }

    public String getUebungName() {
        return uebungName;
    }

    public void setUebungName(String uebungName) {
        this.uebungName = uebungName;
    }
}
