package Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("products")
public class VariablesProductos {
    @JsonProperty("ordenProducto")
    private String ordenProducto;


    public String getOrdenProducto() {
        return ordenProducto;
    }
}

