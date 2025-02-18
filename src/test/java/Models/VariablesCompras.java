package Models;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("compra")
public class VariablesCompras {
    @JsonProperty("cantidadProduct")
    private int cantidadProduct;
    @JsonProperty("valorPishing")
    private double valorPishing;

    public double getValorPishing() {
        return valorPishing;
    }

    public int getCantidadProduct() {
        return cantidadProduct;
    }
}
