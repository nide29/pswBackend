package it.psw.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Set;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Table(name = "ordine", schema = "psw")
public class Ordine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Basic
    @Column(name = "importo", nullable = false)
    private double importo;

    @Basic
    @Column(name = "dataAcquisto", nullable = false)
    private String dataAcquisto;

    @ManyToOne
    @JoinColumn(name = "acquirente") //JOIN COLUMN STA SOLO NELLA MANY TO ONE
    private Utente acquirente;

    @OneToMany(mappedBy = "ordine")
    @JsonIgnore
    @ToString.Exclude
    private Set<ProdottoNelCarrello> prodotti;

    public Ordine(long id, double importo, String dataAcquisto, Utente acquirente, Set<ProdottoNelCarrello> prodotti) {
        this.id = id;
        this.importo = importo;
        this.dataAcquisto = dataAcquisto;
        this.acquirente = acquirente;
        this.prodotti.addAll(prodotti); // avevo usato il for each ma intellij ha consigliato questo metodo
    }//Constructor


}//Ordine
