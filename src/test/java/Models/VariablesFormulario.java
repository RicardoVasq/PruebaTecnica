package Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("contactFormulario")
public class VariablesFormulario {
    @JsonProperty("subjectOpcion")
    int subjectOpcion;
    @JsonProperty("emailAddress")
    String emailAddress;
    @JsonProperty("message")
    String message;

    public int getSubjectOpcion() {
        return subjectOpcion;
    }

    public String getMessage() {
        return message;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
