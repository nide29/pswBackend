package it.psw.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.psw.backend.repositories.UtenteRepository;
import it.psw.backend.services.UtenteService;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
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
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    @Column(name = "data_acquisto", nullable = false)
    private Date dataAcquisto;

    @ManyToOne
    //@JsonBackReference
    @JoinColumn(name = "acquirente") //JOIN COLUMN STA SOLO NELLA MANY TO ONE
    private Utente acquirente;

    @OneToMany(mappedBy = "ordine")
    @JsonIgnore
    @ToString.Exclude
    private Set<ProdottoNelCarrello> prodotti;



}//Ordine
