package com.deveteris.permessi.security;

import com.deveteris.permessi.model.Utente;
import com.deveteris.permessi.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtenteDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public UserDetails loadUserByUsername(String codiceUtente) throws UsernameNotFoundException {
        Utente utente = utenteRepository.findByCodiceUtenteEquals(codiceUtente);
//                .orElseThrow(()-> new UtenteNonTrovatoException("Utente con codice utente {} non trovato!", codiceUtente));
        return new UtenteDetails(utente);
    }
}
