package ristorante.de.veteris.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ristorante.de.veteris.exceptions.UtenteNonTrovatoException;
import ristorante.de.veteris.model.Utente;
import ristorante.de.veteris.repository.UtenteRepository;

@Service
public class UtenteDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public UserDetails loadUserByUsername(String codiceUtente) throws UsernameNotFoundException {
        Utente utente = utenteRepository.getByCodiceUtenteEquals(codiceUtente).orElseThrow(()-> new UtenteNonTrovatoException("Utente con codice utente {} non trovato!", codiceUtente));
        return new UtenteDetails(utente);
    }
}
