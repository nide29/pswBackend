package it.psw.backend.repositories;

import it.psw.backend.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {

    List<Prodotto> findByNome(String nome);
    List<Prodotto> findByMarca(String marca);
    List<Prodotto> findByCategoria(String categoria);

    boolean existsByNome(String nome);
    boolean existsByMarca(String marca);
    boolean existsByCategoria(String categoria);

    @Override
    boolean existsById(Long id);
}
