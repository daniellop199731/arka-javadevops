package com.bancolombia.arka_javadevops.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;

    @Column(nullable = true)
    private String referenciaProducto;

    @NotBlank(message = "Debe proporcionar un nombre al producto")
    private String nombreProducto;

    @NotBlank(message = "Debe proporcionar una descripcion")
    private String descripcionProducto;

    @Positive(message = "Debe proporcionar un precio al producto")
    private double precioProducto;

    @NotNull(message = "Debe proporcionar un stock. Este puede ser cero")
    private int stockProducto;

    @NotNull(message = "Debe proporcionar un stock minimo. Este puede ser cero")
    private int stockMinimoProducto;

    @NotNull(message = "Debe proporcionar un Id de proveedor")
    private int idProveedorProducto;

    @NotNull(message = "Debe ingresas una cantidad de unidades a solicitar. Este puede ser cero")
    private int unidadesSolicitarProducto;

    @NotNull(message = "Debe proporcionar un Id de categoria")
    private int idCategoriaProducto;


}
