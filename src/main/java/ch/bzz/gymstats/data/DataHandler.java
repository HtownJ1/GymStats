package ch.bzz.gymstats.data;

import ch.bzz.gymstats.model.Maschine;
import ch.bzz.gymstats.model.Wiederholung;
import ch.bzz.gymstats.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {
    private static DataHandler instance = null;
    private List<Maschine> maschineList;
    private List<Wiederholung> wiederholungList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setMaschineList(new ArrayList<>());
        readMaschineJSON();
        setWiederholungList(new ArrayList<>());
        readWiederholungJSON();
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
     * reads all books
     * @return list of books
     */
    public List<Maschine> readAllMaschinen() {
        return getMaschineList();
    }

    /**
     * reads a book by its uuid
     * @param maschineUUID
     * @return the Book (null=not found)
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
     * reads all Publishers
     * @return list of publishers
     */
    public List<Wiederholung> readAllWiederholungen() {

        return getWiederholungList();
    }

    /**
     * reads a publisher by its uuid
     * @param wiederholungUUID
     * @return the Publisher (null=not found)
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
     * reads the books from the JSON-file
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
     * reads the publishers from the JSON-file
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

    public List<Maschine> getMaschineList() {
        return maschineList;
    }

    public void setMaschineList(List<Maschine> maschineList) {
        this.maschineList = maschineList;
    }

    public List<Wiederholung> getWiederholungList() {
        return wiederholungList;
    }

    public void setWiederholungList(List<Wiederholung> wiederholungList) {
        this.wiederholungList = wiederholungList;
    }
}
