package it.psw.backend.services;

import it.psw.backend.repositories.ProdottoNelCarrelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdottoNelCarrelloService {

    @Autowired
    ProdottoNelCarrelloRepository prodottoNelCarrelloRepository;

}//ProdottoNelCarrelloService
