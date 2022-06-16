package ch.bzz.gymstats.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jetbrains.annotations.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.FormParam;
import java.util.Date;

/**
 * wiederholung with the weight and reps.
 */
public class Wiederholung {

    @FormParam("wiederholungUUID")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String wiederholungUUID;

    @FormParam("anzahlWiederholungen")
    @NotNull
    @Range(from = 1,to= 20)
    private Integer anzahlWiederholungen;

    @FormParam("gewicht")
    @NotNull
    @Range(from = 1,to= 400)
    private Integer gewicht;

    @JsonIgnore
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
