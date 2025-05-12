package com.bancolombia.arka_javadevops.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "metodosPagoUsuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetodoPagoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Debe proporcionar un id de usuario valido")
    @ManyToOne
    @JoinColumn(name = "idUsuarioMetodoPago")
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
    private Usuario usuario;

    @NotNull(message = "Debe proporcionar un id de metodo de pago valido")
    @ManyToOne
    @JoinColumn(name = "idMetodoPago")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private MetodoPago metodoPago;

    @NotNull(message = "Debe de proporcionar un valor de cuenta")
    @Positive(message = "El valor de la cuenta debe ser mayor a cero")
    private double valorCuentaMetodoPago;

}
