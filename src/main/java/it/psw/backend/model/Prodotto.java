package it.psw.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table(name = "prodotto", schema = "psw")
public class Prodotto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Basic
    @Column(name = "nome", nullable = false)
    private String nome;

    @Basic
    @Column(name = "descrizione", nullable = false)
    private String descrizione;

    @Basic
    @Column(name = "marca", nullable = false)
    private String marca;

    @Basic
    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Basic
    @Column(name = "prezzo", nullable = false)
    private double prezzo;

    @OneToMany(mappedBy = "prodotto")
    @JsonIgnore
    @ToString.Exclude
    private Set<ProdottoNelCarrello> prodottiNelCarrello;

}//Prodotto
