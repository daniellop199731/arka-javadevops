package com.bancolombia.arka_javadevops.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carritosCompraProductos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoCompraProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idCarritoCompra")
    private CarritoCompra carritoCompra;

    @NotNull(message = "Debe proporcionar un id de producto valido")
    @ManyToOne
    @JoinColumn(name = "idProducto")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Producto productoCarritoCompra;

    @NotNull(message = "Debe proporcionar las unidades del producto")
    @Positive(message = "Las unidades del producto deben ser mayores a cero")
    private int unidadesProducto;

}
