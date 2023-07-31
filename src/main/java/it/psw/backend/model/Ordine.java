package it.psw.backend.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "ordine", schema = "psw")
public class Ordine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Basic
    @Column(name = "importo", nullable = false)
    private double importo;

    @Basic
    @Column(name = "data_acquisto", nullable = false)
    private Date dataAcquisto;

    @ManyToOne()
    @JoinColumn(name = "acquirente") //JOIN COLUMN STA SOLO NELLA MANY TO ONE
    private Utente acquirente;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<ProdottoNelCarrello> prodotti;

    public Ordine (double importo, Date dataAcquisto, Utente acquirente, Set<ProdottoNelCarrello> prodotti){
        this.importo=importo;
        this.dataAcquisto=dataAcquisto;
        this.acquirente=acquirente;
        this.prodotti=prodotti;
    }

}//Ordine
