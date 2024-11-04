package ch.oz.chores.model;

import jakarta.persistence.*;


@Entity // Dies markiert die Klasse als JPA-Entity, die in der Datenbank gespeichert wird.
public class User {

    @Id // Dies markiert das Feld als primären Schlüssel.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Die ID wird automatisch generiert.
    private Long id;

    @Column(nullable = false, unique = true) // Benutzername muss eindeutig und nicht null sein.
    private String username;

    @Column(nullable = false, unique = true) // E-Mail muss eindeutig und nicht null sein.
    private String email;

    @Column(nullable = false) // Passwort darf nicht null sein.
    private String password;

    @Column(nullable = false) // Admin darf nicht null sein, wird als boolean gespeichert.
    private boolean admin;

    // Standard-Konstruktor
    public User() {
    }

    // Konstruktor mit allen Feldern (außer ID, da diese automatisch generiert wird)
    public User(String username, String email, String password, boolean admin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    // Getter- und Setter-Methoden

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
