package clientServices.exception;

public class OrdinazioneNonTrovataException extends ClientException{
    public OrdinazioneNonTrovataException(String message, Object... pars) {
        super(message, pars);
    }
}
