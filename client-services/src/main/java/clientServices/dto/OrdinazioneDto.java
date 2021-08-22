package clientServices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import notificationsmanager.enums.StatoOrdinazione;
import notificationsmanager.model.PiattoOrdinazione;

import java.util.Set;

@Data
@AllArgsConstructor
public class OrdinazioneDto {
    private Integer id;
    private String note;
    private double costo;
    private StatoOrdinazione stato;
    private Set<PiattoOrdinazione> piattiOrdinazione;
}
