package it.psw.backend.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
//@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "utente", schema = "psw")
public class Utente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true) //nullable e unique li mette di default l'annotazione @Id
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
    //@JsonManagedReference
    @ToString.Exclude
    private Set<Ordine> ordini;


}//Utente
