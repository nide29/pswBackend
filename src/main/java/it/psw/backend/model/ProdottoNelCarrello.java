package it.psw.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.core.PriorityOrdered;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
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
    private Prodotto prodotto;

    @ManyToOne
    @JoinColumn(name = "ordine")
    @JsonIgnore
    private Ordine ordine;

    /*
    public ProdottoNelCarrello(long id, int quantita, Prodotto prodotto, Ordine ordine) {
        this.id = id;
        this.quantita = quantita;
        this.prodotto = prodotto;
        this.ordine = ordine;
    }//Constructor
    */

}//ProdottoNelCarrello
