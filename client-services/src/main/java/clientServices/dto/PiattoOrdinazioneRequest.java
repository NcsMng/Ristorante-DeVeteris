package clientServices.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class PiattoOrdinazioneRequest {
    @NonNull
    private String codicePiatto;
    @NonNull
    private Integer quantita;

    private String note;
}
