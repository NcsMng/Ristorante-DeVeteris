package permessi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import permessi.exceptions.UtenteNonTrovatoException;
import permessi.model.Utente;
import permessi.repository.UtenteRepository;

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
