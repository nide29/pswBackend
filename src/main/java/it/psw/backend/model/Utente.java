package it.psw.backend.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "utente", schema = "psw")
public class Utente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Basic
    @Column(name = "nome", nullable = false)
    private String nome;

    @Basic
    @Column(name = "cognome", nullable = false)
    private String cognome;

    @Basic
    @Column(name = "email", nullable = false)
    private String email;

    @Basic
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "acquirente", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER) // aggiungere cascade = CascadeType.*
    @JsonIgnore
    @ToString.Exclude
    private Set<Ordine> ordini;

}//Utente
