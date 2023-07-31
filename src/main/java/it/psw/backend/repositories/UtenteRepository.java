package it.psw.backend.repositories;

import it.psw.backend.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    Utente findByEmail(String email);
    Utente findById(long id);
    List<Utente> findByNome(String nome);
    List<Utente> findByCognome(String cognome);

    boolean existsByEmail(String email);
    boolean existsById(long id);
    boolean existsByNome(String nome);
    boolean existsByCognome(String conome);

}//UtenteRepository
