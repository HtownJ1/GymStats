package ch.bzz.gymstats.data;

import ch.bzz.gymstats.model.Maschine;
import ch.bzz.gymstats.model.Uebung;
import ch.bzz.gymstats.model.User;
import ch.bzz.gymstats.model.Wiederholung;
import ch.bzz.gymstats.service.Config;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
    private List<User> userList;


    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setUserList(new ArrayList<>());
        readUserJSON();
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
     * reads all User
     *
     * @return list of User
     */
    public List<User> readAllUser() {
        return getUserList();
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
     * reads a Uebung by its uuid
     * @param uebungUUID
     * @return the Uebung (null=not found)
     */
    public Uebung readUebungByUUID(String uebungUUID) {
        Uebung uebung = null;
        for (Uebung entry : getUebungList()) {
            if (entry.getUebungUUID().equals(uebungUUID)) {
                uebung = entry;
            }
        }
        return uebung;
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
     * inserts a new maschine into the maschineList
     *
     * @param maschine which has to be saved
     */
    public  void insertMaschine(Maschine maschine) {
        getMaschineList().add(maschine);
        writeMaschineJSON();
    }

    /**
     * updates the maschineList
     */
    public void updateMaschine() {
        writeMaschineJSON();
    }


    /**
     * deletes a maschine identified by the maschineUUID
     * @param maschineUUID
     * @return -
     */
    public boolean deleteMaschine(String maschineUUID) {
        Maschine maschine = readMaschineByUUID(maschineUUID);
        if (maschine != null) {
            getMaschineList().remove(maschine);
            writeMaschineJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * reads the Users from the JSON-file
     */
    private void readUserJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("userJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            User[] users = objectMapper.readValue(jsonData, User[].class);
            for (User user : users) {
                getUserList().add(user);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * writes the userList to the JSON-file
     */
    private void writeUserJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String pathToJson = Config.getProperty("userJSON");
        try {
            fileOutputStream = new FileOutputStream(pathToJson);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getUserList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads a User by its uuid
     *
     * @param userUUID
     * @return the User
     *
     */
    public User readUserByUUID(String userUUID) {
        User user = null;
        for (User entry : getUserList()) {
            if (entry.getUserUUID().equals(userUUID)) {
                user = entry;
            }
        }
        return user;
    }

    /**
     * inserts a new user into the userList
     *
     * @param user the user to be saved
     *
     */
    public void insertUser(User user) {
        getUserList().add(user);
        writeUserJSON();
    }

    /**
     * updates the userList
     */
    public void updateUser() {
        writeUserJSON();
    }

    /**
     * deletes a user identified by the UUID
     *
     * @param userUUID the key
     * @return success = true/false
     */
    public boolean deleteUser(String userUUID) {
        User user = readUserByUUID(userUUID);
        if (user != null) {
            getUserList().remove(user);
            writeUserJSON();
            return true;
        }
        return false;
    }

    /**
     * inserts a new wiederholung into the wiederholungList
     *
     * @param wiederholung which has to be saved
     */
    public  void insertWiederholung(Wiederholung wiederholung) {
        getWiederholungList().add(wiederholung);
        writeWiederholungJSON();
    }

    /**
     * updates the wiederholungList
     */
    public void updateWiederholung() {
        writeWiederholungJSON();
    }

    /**
     * deletes a wiederholung identified by the wiederholungUUID
     * @param wiederholungUUID
     * @return -
     */
    public boolean deleteWiederholung(String wiederholungUUID) {
        Wiederholung wiederholung = readWiederholungByUUID(wiederholungUUID);
        if (wiederholung != null) {
            getWiederholungList().remove(wiederholung);
            writeWiederholungJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * inserts a new uebung into the uebungList
     *
     * @param uebung which has to be saved
     */
    public  void insertUebung(Uebung uebung) {
        getUebungList().add(uebung);
        writeUebungJSON();
    }

    /**
     * updates the publisherList
     */
    public void updateUebung() {
        writeUebungJSON();
    }

    /**
     * deletes a uebung identified by the uebungUUID
     * @param uebungUUID
     * @return -
     */
    public boolean deleteUebung(String uebungUUID) {
        Uebung uebung = readUebungByUUID(uebungUUID);
        if (uebung != null) {
            getUebungList().remove(uebung);
            writeUebungJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * writes the wiederholungList to the JSON-File
     */
    private void writeWiederholungJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String wiederholungPath = Config.getProperty("wiederholungJSON");
        try {
            fileOutputStream = new FileOutputStream(wiederholungPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getInstance().getWiederholungList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * writes the maschineList to the JSON-File
     */
    private void writeMaschineJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String maschinePath = Config.getProperty("maschineJSON");
        try {
            fileOutputStream = new FileOutputStream(maschinePath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getInstance().getMaschineList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * writes the uebungList to the JSON-File
     */
    private void writeUebungJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String uebungPath = Config.getProperty("uebungJSON");
        try {
            fileOutputStream = new FileOutputStream(uebungPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getInstance().getUebungList());
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

    /**
     * zurückgibt userList
     *
     * @return Wert von userList
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * setzt userList
     *
     * @param userList der Wert zu setzen
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
