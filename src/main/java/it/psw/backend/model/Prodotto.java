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
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prodotto", schema = "psw")
public class Prodotto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Basic
    @Column(name = "nome", nullable = false )
    private String nome;

    @Basic
    @Column(name = "descrizione",nullable = false)
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

    @Basic
    @Column(name = "quantita", nullable = false)
    private int quantita;

    @OneToMany(mappedBy = "prodotto")
    @JsonIgnore
    @ToString.Exclude
    private Set<ProdottoNelCarrello> prodottiNelCarrello;

}//Prodotto
