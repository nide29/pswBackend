package it.psw.backend.services;

import it.psw.backend.model.Ordine;
import it.psw.backend.model.Prodotto;
import it.psw.backend.model.ProdottoNelCarrello;
import it.psw.backend.model.Utente;
import it.psw.backend.repositories.OrdineRepository;
import it.psw.backend.repositories.ProdottoNelCarrelloRepository;
import it.psw.backend.repositories.UtenteRepository;
import it.psw.backend.support.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Service
public class OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private ProdottoNelCarrelloRepository prodottoNelCarrelloRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {QuantitaNonDisponibileException.class})
    public Ordine creaOrdine(Ordine ordine) throws QuantitaNonDisponibileException {
       Ordine result = ordineRepository.save(new Ordine(ordine.getImporto(), ordine.getDataAcquisto(),ordine.getAcquirente(), ordine.getProdotti()));
        for(ProdottoNelCarrello pnc : ordine.getProdotti()) {
            pnc.setOrdine(result);
            prodottoNelCarrelloRepository.save(new ProdottoNelCarrello(result, pnc.getQuantita(), pnc.getProdotto()));
            Prodotto prodotto = pnc.getProdotto();
            int newQuantity = prodotto.getQuantita() - pnc.getQuantita();
            if (newQuantity < 0) {
                throw new QuantitaNonDisponibileException("Quantità non sufficiente");
            }
            prodotto.setQuantita(newQuantity);
            entityManager.merge(prodotto);
        }
        return result;
    }//creaOrdine


    @Transactional
    public Ordine aggiornaOrdine(Ordine ordine) {
        if(!ordineRepository.existsById(ordine.getId()))
            throw new OrdineNotFoundException("L'ordine non è esistente");
        return ordineRepository.save(ordine);
    }//updateOrdine

    @Transactional
    public void deleteOrdine(long idOrdine) {
        if(!ordineRepository.existsById(idOrdine)){
            throw new OrdineNotFoundException("Ordine non esistente!");
        }
        Ordine ordine = ordineRepository.getById(idOrdine);
        ordineRepository.delete(ordine);
        ordineRepository.flush();
    }//deleteOrdine


    @Transactional(readOnly = true)
    public List<Ordine> findAll() {
        return ordineRepository.findAll();
    }//findAll

    @Transactional(readOnly = true)
    public Ordine findById(long id) {
        if (!ordineRepository.existsById(id)) throw new OrdineNotFoundException("Ordine non esistente");
        if (!ordineRepository.findById(id).isPresent()) throw new OrdineNotFoundException("Ordine non trovato");

        return ordineRepository.findById(id).get();
    }//findById

    @Transactional(readOnly = true)
    public List<Ordine> findByPeriodo(Utente acquirente, Date startDate, Date endDate) {
        if (!utenteRepository.existsById(acquirente.getId())){
            throw new UtenteNonEsistenteException("L'utente non è esistente!");
        }
        if (startDate.compareTo(endDate) >= 0) {
            throw new DateWrongRangeException("Il range di date inserito non è ammisibile");
        }
        return ordineRepository.findByPeriodo(startDate, endDate, acquirente);
    }//findByPeriodo --> restituirà tutti gli ordini effettuati dal'utente passato la cui data di acquisto è compresa tra startDate e endDate

    @Transactional(readOnly = true)
    public List<Ordine> findByAcquirente(Utente acquirente) {
        return ordineRepository.findByAcquirente(acquirente);
    }//findByDAtaAcquisto



    @Transactional(readOnly = true)
    public boolean existById(long id){
        return ordineRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public boolean existByAcquirente(Utente acquirente){
        return ordineRepository.existsByAcquirente(acquirente);
    }

}//OrdineService
