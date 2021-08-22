package clientServices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class OrdinazioneClientRequest {
    private String note;
    private double costo;
    private List<PiattoOrdinazioneDto> piattiOrdinazione;
}
