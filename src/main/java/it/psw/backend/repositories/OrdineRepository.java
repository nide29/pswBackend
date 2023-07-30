package it.psw.backend.repositories;

import it.psw.backend.model.Ordine;
import it.psw.backend.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long> {

    List<Ordine> findByDataAcquisto(Date data);
    List<Ordine> findByAcquirente(Utente acquirente);

    @Query("select o from Ordine o where o.dataAcquisto > ?1 and o.dataAcquisto < ?2 and o.acquirente = ?3")
    List<Ordine> findByPeriodo(Date startDate, Date endDate, Utente acquirente);

    @Override
    boolean existsById(Long aLong);
    boolean existsByDataAcquisto(Date data);
    boolean existsByAcquirente(Utente acquirente);
}//OrdineRepository
