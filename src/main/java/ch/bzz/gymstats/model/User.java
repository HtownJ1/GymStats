package ch.bzz.gymstats.model;

/**
 * a user class
 */
public class User {

    private String userUUID;
    private String userName;
    private String password;
    private String userRole;

    /**
     * zurückgibt userUUID
     *
     * @return Wert von userUUID
     */
    public String getUserUUID() {
        return userUUID;
    }

    /**
     * setzt userUUID
     *
     * @param userUUID der Wert zu setzen
     */
    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    /**
     * zurückgibt userName
     *
     * @return Wert von userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * setzt userName
     *
     * @param userName der Wert zu setzen
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * zurückgibt password
     *
     * @return Wert von password
     */
    public String getPassword() {
        return password;
    }

    /**
     * setzt password
     *
     * @param password der Wert zu setzen
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * zurückgibt userRole
     *
     * @return Wert von userRole
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * setzt userRole
     *
     * @param userRole der Wert zu setzen
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
