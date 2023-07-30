package it.psw.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.core.PriorityOrdered;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
//@EqualsAndHashCode
@ToString
//@AllArgsConstructor
@NoArgsConstructor
@Table(name = "prodotto_nel_carrello", schema = "psw")
public class ProdottoNelCarrello implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Basic
    @Column(name = "quantita", nullable = false)
    private int quantita;

    @ManyToOne
    @JoinColumn(name = "prodotto")
    //@JsonBackReference
    private Prodotto prodotto;

    @ManyToOne
    @JoinColumn(name = "ordine")
    @JsonIgnore
    //@JsonBackReference
    private Ordine ordine;


    public ProdottoNelCarrello(Ordine ordine, int quantita, Prodotto prodotto) {
        this.ordine = ordine;
        this.quantita = quantita;
        this.prodotto = prodotto;
    }//Constructor


}//ProdottoNelCarrello
