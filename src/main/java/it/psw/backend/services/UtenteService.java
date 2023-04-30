package it.psw.backend.services;

import it.psw.backend.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    //facciamo prima i metodi CRUD (CReate Update Delete)
    @Transactional //readOnly di default sta a false, lo mettiamo a true solo quadndo vogliamo leggere (es. find)
    public long saveUtente

}
