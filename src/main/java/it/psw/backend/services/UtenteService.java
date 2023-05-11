package it.psw.backend.services;

import it.psw.backend.support.ResponseMessage;
import it.psw.backend.support.exceptions.UtenteEsistenteException;
import it.psw.backend.support.exceptions.UtenteNonEsistenteException;
import it.psw.backend.model.Utente;
import it.psw.backend.repositories.UtenteRepository;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    @Value("${keycloak.auth-server-url}")
    private String serverUrl;
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${clientid}")
    private String clientId;
    //@Value("${clientsecret}")
    private String clientSecret="";
    @Value("${usernameadmin}")
    private String username_admin;
    @Value("${passwordadmin}")
    private String password_admin;


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Utente creaUtente(Utente utente) {
        if (utenteRepository.existsByEmail(utente.getEmail())) {
            throw new UtenteEsistenteException("Utente già esistente!");
        }
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .username(username_admin)
                .password(password_admin)
                .build();

        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(utente.getEmail());
        user.setEmail(utente.getEmail());
        user.setAttributes(Collections.singletonMap("origin", Arrays.asList("demo")));
        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersResource = realmResource.users();
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(utente.getPassword());
        List<CredentialRepresentation> l = new LinkedList<>();
        l.add(passwordCred);
        user.setCredentials(l);
        Response response = usersResource.create(user);
        String userId = CreatedResponseUtil.getCreatedId(response);
        usersResource = keycloak.realm(realm).users();
        UserResource userResource = usersResource.get(userId);

        //getting client
        ClientRepresentation clientRepresentation = keycloak.realm(realm).clients().findAll().stream().filter(client -> client.getClientId().equals(clientId)).collect(Collectors.toList()).get(0);
        ClientResource clientResource = keycloak.realm(realm).clients().get(clientRepresentation.getId());
        //getting role
        RoleRepresentation roleRepresentation = clientResource.roles().list().stream().filter(element -> element.getName().equals("user2")).collect(Collectors.toList()).get(0);
        //assigning to user
        userResource.roles().clientLevel(clientRepresentation.getId()).add(Collections.singletonList(roleRepresentation));

        return utenteRepository.save(utente);

    }//creaUtente


    @Transactional
    public void aggiornaUtente(Utente utente) {
        if (utenteRepository.existsById(utente.getId())) {
            throw new UtenteNonEsistenteException("Utente non esistente!");
        }
        utenteRepository.save(utente);
    }//updateUtente

    @Transactional(readOnly = true)
    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }//findAll


    @Transactional(readOnly = true)
    public Utente findByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }//findByEmail

    @Transactional(readOnly = true)
    public Utente findById(long id) {
        return utenteRepository.findById(id);
    }//findById

    @Transactional(readOnly = true)
    public List<Utente> findByNome(String nome) {
        return utenteRepository.findByNome(nome);
    }//findByNome

    @Transactional(readOnly = true)
    public List<Utente> findByCognome(String cognome) {
        return utenteRepository.findByCognome(cognome);
    }//findByCognome

    @Transactional(readOnly = true)
    public boolean existsById(long id){
        return utenteRepository.existsById(id);
    }//existByID

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email){
        return utenteRepository.existsByEmail(email);
    }//existByEmail

    @Transactional(readOnly = true)
    public boolean existsByNome(String nome){
        return utenteRepository.existsByNome(nome);
    }//existByNome

    @Transactional(readOnly = true)
    public boolean existsByCognome(String cognome){
        return utenteRepository.existsByCognome(cognome);
    }//existByCognome


}//UtenteService
