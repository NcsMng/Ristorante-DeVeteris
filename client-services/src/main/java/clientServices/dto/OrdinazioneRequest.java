package clientServices.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.List;
@Data
public class OrdinazioneRequest {
    private Integer id;
    @NonNull
    private List<PiattoOrdinazioneDto> piattiOrdinazione;
}
