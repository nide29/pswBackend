package it.psw.backend.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.core.PriorityOrdered;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table(name = "prodottoNelCarrello", schema = "psw")
public class ProdottoNelCarrello {

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
    private Ordine ordine;


}//ProdottoNelCarrello
