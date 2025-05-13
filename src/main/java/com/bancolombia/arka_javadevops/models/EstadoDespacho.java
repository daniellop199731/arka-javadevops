package com.bancolombia.arka_javadevops.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estadosDespachos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoDespacho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEstadoDespacho;

    @NotNull(message = "Debe proporcionar un nombre para el estado despacho")
    private String nombreEstadoDespacho;

    @JsonIgnore
    @OneToMany(mappedBy = "estadoDespacho")
    private List<CarritoCompra> carritoCompras;



}
