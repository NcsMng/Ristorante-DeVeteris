package permessi.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import permessi.model.Ruolo;
import permessi.model.Utente;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

public class UtenteDetails implements UserDetails {
    private Utente utente;

    public UtenteDetails(Utente utente) {
        this.utente = utente;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Ruolo> ruoli = utente.getRuoli();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Ruolo ruolo : ruoli) {
            authorities.add(new SimpleGrantedAuthority(ruolo.getNome()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return utente.getPassword();
    }

    @Override
    public String getUsername() {
        return utente.getCodiceUtente();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return utente.getAttivo();
    }
}
