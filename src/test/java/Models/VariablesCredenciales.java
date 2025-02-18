package Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("credentialsLogin")
public class VariablesCredenciales {
    @JsonProperty("usuario")
    private String usuario;
    @JsonProperty("contra")
    private String contra;
    @JsonProperty("usuarioInvalido")
    private String usuarioInvalido;
    @JsonProperty("contraInvalida")
    private String contraInvalida;


    public String getUsuario() {
        return usuario;
    }

    public String getUsuarioInvalido() {
        return usuarioInvalido;
    }

    public String getContra() {
        return contra;
    }

    public String getContraInvalida() {
        return contraInvalida;
    }
}