package it.psw.backend.services;
import it.psw.backend.exceptions.DeleteException;
import it.psw.backend.model.Prodotto;
import it.psw.backend.repositories.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Transactional
    public long save(Prodotto prodotto) {
        Prodotto p = prodottoRepository.save(prodotto);
        return p.getId();
    }//save
    @Transactional

    public void update(Prodotto prodotto) {
        prodottoRepository.save(prodotto);
    }//update
    @Transactional

    public void delete(long idProdotto) {
        try {
            prodottoRepository.deleteById(idProdotto);
            prodottoRepository.flush();
        } catch (Exception e) {throw new DeleteException("Eliminazione non riuscita!");}
    }//delete

    @Transactional(readOnly = true)
    public List<Prodotto> findByNome(String nome) {
        return prodottoRepository.findByNome(nome);
    }//findByNome

    @Transactional(readOnly = true)
    public List<Prodotto> findByMarca(String marca) {
        return prodottoRepository.findByMarca(marca);
    }//findByMarca

    @Transactional(readOnly = true)
    public List<Prodotto> findByCategoria(String categoria){
        return prodottoRepository.findByCategoria(categoria);
    }//findByMarca

    @Transactional(readOnly = true)
    public boolean existsByNome(String nome) {
        return prodottoRepository.existsByNome(nome);
    }
    @Transactional(readOnly = true)

    public boolean existsByMarca(String marca) {
        return prodottoRepository.existsByMarca(marca);
    }
    @Transactional(readOnly = true)

    public boolean existsByCategoria(String categoria) {
        return prodottoRepository.existsByCategoria(categoria);
    }

    @Transactional(readOnly = true)
    public boolean existsById(long id){
        return prodottoRepository.existsById(id);
    }

}//ProdottoService
