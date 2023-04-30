package it.psw.backend.repositories;

import it.psw.backend.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Utente findByEmail(String email);
    Utente findById(long id);

    boolean existsByEmail(String email);
    boolean existsById(long id);
}
