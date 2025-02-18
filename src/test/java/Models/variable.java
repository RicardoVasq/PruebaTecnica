package Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class variable {
    @JsonProperty("credentialsLogin")
    private VariablesCredenciales credentialsLogin;
    @JsonProperty("contactFormulario")
    private VariablesFormulario contactFormulario;
    @JsonProperty("products")
    private VariablesProductos products;
    @JsonProperty("compra")
    private VariablesCompras compra;

    public VariablesCredenciales getCredentialsLogin() {
        return credentialsLogin;
    }

    public VariablesCompras getCompra() {
        return compra;
    }

    public VariablesProductos getProducts() {
        return products;
    }

    public VariablesFormulario getContactFormulario() {
        return contactFormulario;
    }
}
