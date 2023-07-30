package it.psw.backend.services;

import it.psw.backend.model.ProdottoNelCarrello;
import it.psw.backend.repositories.ProdottoNelCarrelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdottoNelCarrelloService {

    @Autowired
    ProdottoNelCarrelloRepository prodottoNelCarrelloRepository;

    @Transactional
    public ProdottoNelCarrello aggiungiProdotto(ProdottoNelCarrello prodottoNelCarrello) {
        return prodottoNelCarrelloRepository.save(prodottoNelCarrello);
    }//aggiungiProdotto

    @Transactional(readOnly = true)
    public ProdottoNelCarrello getById(long id){
        return prodottoNelCarrelloRepository.getById(id);
    }

}//ProdottoNelCarrelloService
