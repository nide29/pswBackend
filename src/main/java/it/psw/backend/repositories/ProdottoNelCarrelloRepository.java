package it.psw.backend.repositories;

import it.psw.backend.model.ProdottoNelCarrello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoNelCarrelloRepository extends JpaRepository<ProdottoNelCarrello, Long> {

}//ProdottoNelCarrelloRepository
