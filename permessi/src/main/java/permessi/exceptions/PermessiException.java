package permessi.exceptions;

import org.apache.commons.lang3.RegExUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PermessiException extends RuntimeException{
    private final String message;

    public PermessiException(String message, Object... pars) {
        for (Object par: pars) {
            if(par != null) {
                message = RegExUtils.replaceFirst(message, Pattern.quote("{}"), Matcher.quoteReplacement(par.toString()));
            } else {
                message = RegExUtils.replaceFirst(message, Pattern.quote("{}"), Matcher.quoteReplacement(""));
            }
        }
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
