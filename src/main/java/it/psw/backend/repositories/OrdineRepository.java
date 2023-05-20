package it.psw.backend.repositories;

import it.psw.backend.model.Ordine;
import it.psw.backend.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long> {

    List<Ordine> findByDataAcquisto(String data);
    List<Ordine> findByAcquirente(Utente acquirente);

    @Override
    boolean existsById(Long aLong);
    boolean existsByDataAcquisto(String data);
    boolean existsByAcquirente(Utente acquirente);
}//OrdineRepository
