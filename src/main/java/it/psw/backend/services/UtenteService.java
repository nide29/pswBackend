package it.psw.backend.services;

import it.psw.backend.exceptions.UtenteEsistenteException;
import it.psw.backend.exceptions.UtenteNonEsistenteException;
import it.psw.backend.model.Utente;
import it.psw.backend.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    //facciamo prima i metodi CRUD (CReate Update Delete)
   // @Transactional //readOnly di default sta a false, lo mettiamo a true solo quadndo vogliamo leggere (es. find)
    //public long saveUtente()

    @Transactional
    public long saveUtente(Utente utente) {
        if (utenteRepository.existsById(utente.getId())) {
            throw new UtenteEsistenteException("Utente già esistente!");
        }
        utenteRepository.save(utente);
        return utente.getId();
    }//saveUtente


    @Transactional
    public void update(Utente utente) {
        if (utenteRepository.existsById(utente.getId())) {
            throw new UtenteNonEsistenteException("Utente non esistente!");
        }
        utenteRepository.save(utente);
    }
    

}
