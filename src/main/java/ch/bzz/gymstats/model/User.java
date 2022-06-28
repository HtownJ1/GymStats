package ch.bzz.gymstats.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

/**
 * a user class
 */
public class User {

    @FormParam("userUUID")
    @Pattern(regexp = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-4[0-9a-fA-F]{3}-[89ABab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}")
    private String userUUID;

    @FormParam("userName")
    @NotEmpty
    @Size(min = 3,max = 30)
    private String userName;

    @FormParam("password")
    @NotEmpty
    @Size(min = 8,max = 30)
    private String password;

    @FormParam("userRole")
    @NotEmpty
    @Size(min = 3,max = 30)
    private String userRole;

    /**
     * empty constructor
     */
    public User() {
    }

    /**
     * constructor
     *
     * @param userUUID userUUID of User
     * @param userName userName of User
     * @param password password of User
     * @param userRole userRole of User
     */
    public User(String userUUID,
                String userName,
                String password,
                String userRole) {
        this.userUUID = userUUID;
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
    }

    /**
     * Used to logon the user
     */
    public void logon(){

    }

    /**
     * Used to logoff the user
     */
    public void logoff(){

    }

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
