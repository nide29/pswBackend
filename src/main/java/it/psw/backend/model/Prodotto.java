package it.psw.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
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

    @OneToMany(mappedBy = "prodotto")
    @JsonIgnore
    @ToString.Exclude
    private Set<ProdottoNelCarrello> prodottiNelCarrello;


    public Prodotto(long id, String nome, String descrizione, String marca, String categoria, double prezzo) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.marca = marca;
        this.categoria = categoria;
        this.prezzo = prezzo;
    }//Constructor

}//Prodotto
