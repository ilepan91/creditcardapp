package com.ilepan.creditcardapp.entity;
import com.ilepan.creditcardapp.constants.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


/**
 * Entity class representing User.
 * This class is annotated with {@link Entity} to indicate that it is a JPA entity,
 * and it is mapped to the "user" table in the database using {@link Table} annotation.
 */
@Entity
@Table(name="users")
public class User {

    /**
     * The unique identifier for the User.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name="id")
    private int id;

    /**
     * The first name of the User.
     */
    @NotNull(message = "First name is required!")
    @Size(min = 1, message = "First name can not be empty!")
    @Pattern(regexp = "\\S+", message = "Incorrect input!")
    @Schema(defaultValue = "Ivan", description = "The User's first name")
    @Column(name="first_name")
    private String firstName;

    /**
     * The last name of the User.
     */
    @NotNull(message = "Last name is required!")
    @Size(min = 1, message = "Last name can not be empty!")
    @Pattern(regexp = "\\S+", message = "Incorrect input!")
    @Schema(defaultValue = "Horvat", description = "The User's last name")
    @Column(name="last_name")
    private String lastName;

    /**
     * User's OIB (osobni identifikacijski broj)
     */
    @NotNull(message = "OIB is required!")
    @Column(name="oib", length = 11)
    @Schema(defaultValue = "05552352140", description = "The User's OIB (osobni identifikacijski broj)")
    private String oib;

    /**
     * Status of the User
     */
    @NotNull(message = "Status is required!")
    @Pattern(regexp = "[01]", message = "Status can only have values 0  = INACTIVE or 1 = ACTIVE")
    @Column(name="status")
    private String status;

    /**
     * File name of the User
     */
    @Schema(hidden = true)
    @Column(name="file_name")
    private String fileName;

    /**
     * Default constructor for the User class.
     */
    public User () {

    }

    /**
     * Constructs a new User object with the specified details.
     *
     * @param firstName      The first name of the User.
     * @param lastName       The last name of the User.
     * @param oib            User's OIB.*
     * @param status        Status of the user
     */
    public User(String firstName, String lastName, String oib, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.oib = oib;
        this.status = status;
    }

     /**
     * Gets the unique identifier of the User.
     *
     * @return The User's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the User.
     *
     * @param id User's ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the first name of the User.
     *
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the User.
     *
     *  @param firstName The first name of the User.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the User.
     *
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the User.
     *
     *  @param lastName The last name of the User.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets OIB of the User.
     *
     * @return The User's OIB.
     */
    public String  getOib() {
        return oib;
    }

    /**
     * Sets OIB of the User.
     *
     * @param oib The User's OIB.
     */
    public void setOib(String oib) {
        this.oib = oib;
    }

    /**
     * Gets Status of the User.
     *
     * @return The User's status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status of the User.
     *
     * @param status The User's status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets file name of the User.
     *
     * @return file name.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets file name for the User.
     *
     * @param fileName File name.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Returns a string representation of the User object.
     *
     * @return A string containing information of the User object.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", oib='" + oib + '\'' +
                ", status='" + status + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
