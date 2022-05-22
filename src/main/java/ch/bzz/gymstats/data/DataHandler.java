package ch.bzz.gymstats.data;

import ch.bzz.gymstats.model.Maschine;
import ch.bzz.gymstats.model.Uebung;
import ch.bzz.gymstats.model.Wiederholung;
import ch.bzz.gymstats.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static DataHandler instance = null;
    private List<Maschine> maschineList;
    private List<Wiederholung> wiederholungList;
    private List<Uebung> uebungList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setMaschineList(new ArrayList<>());
        readMaschineJSON();
        setWiederholungList(new ArrayList<>());
        readWiederholungJSON();
        setUebungList(new ArrayList<>());
        readUebungJSON();
    }

    /**
     * gets the only instance of this class
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all Maschinen
     * @return list of Maschinen
     */
    public List<Maschine> readAllMaschinen() {
        return getMaschineList();
    }

    /**
     * reads all Uebungen
     * @return list of Uebungen
     */
    public List<Uebung> readAllUebungen() {
        return getUebungList();
    }

    /**
     * reads a Maschine by its uuid
     * @param maschineUUID
     * @return the Maschine (null=not found)
     */
    public Maschine readMaschineByUUID(String maschineUUID) {
        Maschine maschine = null;
        for (Maschine entry : getMaschineList()) {
            if (entry.getMaschineUUID().equals(maschineUUID)) {
                maschine = entry;
            }
        }
        return maschine;
    }

    /**
     * reads all Wiederholungen
     * @return list of Wiederholung
     */
    public List<Wiederholung> readAllWiederholungen() {

        return getWiederholungList();
    }

    /**
     * reads a Wiederholung by its uuid
     * @param wiederholungUUID
     * @return the Wiederholung (null=not found)
     */
    public Wiederholung readWiederholungByUUID(String wiederholungUUID) {
        Wiederholung wiederholung = null;
        for (Wiederholung entry : getWiederholungList()) {
            if (entry.getWiederholungUUID().equals(wiederholungUUID)) {
                wiederholung = entry;
            }
        }
        return wiederholung;
    }

    /**
     * reads the Maschine from the JSON-file
     */
    private void readMaschineJSON() {
        try {
            String path = Config.getProperty("maschineJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Maschine[] maschines = objectMapper.readValue(jsonData, Maschine[].class);
            for (Maschine maschine : maschines) {
                getMaschineList().add(maschine);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the Wiederholung from the JSON-file
     */
    private void readWiederholungJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("wiederholungJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Wiederholung[] wiederholungen = objectMapper.readValue(jsonData, Wiederholung[].class);
            for (Wiederholung wiederholung : wiederholungen) {
                getWiederholungList().add(wiederholung);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the Uebung from the JSON-file
     */
    private void readUebungJSON() {
        try {
            String path = Config.getProperty("uebungJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Uebung[] uebungen = objectMapper.readValue(jsonData, Uebung[].class);
            for (Uebung uebung : uebungen) {
                getUebungList().add(uebung);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * setzt instance
     *
     * @param instance der Wert zu setzen
     */
    public static void setInstance(DataHandler instance) {
        DataHandler.instance = instance;
    }

    /**
     * zurückgibt maschineList
     *
     * @return Wert von maschineList
     */
    public List<Maschine> getMaschineList() {
        return maschineList;
    }

    /**
     * setzt maschineList
     *
     * @param maschineList der Wert zu setzen
     */
    public void setMaschineList(List<Maschine> maschineList) {
        this.maschineList = maschineList;
    }

    /**
     * zurückgibt wiederholungList
     *
     * @return Wert von wiederholungList
     */
    public List<Wiederholung> getWiederholungList() {
        return wiederholungList;
    }

    /**
     * setzt wiederholungList
     *
     * @param wiederholungList der Wert zu setzen
     */
    public void setWiederholungList(List<Wiederholung> wiederholungList) {
        this.wiederholungList = wiederholungList;
    }

    /**
     * zurückgibt uebungList
     *
     * @return Wert von uebungList
     */
    public List<Uebung> getUebungList() {
        return uebungList;
    }

    /**
     * setzt uebungList
     *
     * @param uebungList der Wert zu setzen
     */
    public void setUebungList(List<Uebung> uebungList) {
        this.uebungList = uebungList;
    }
}
