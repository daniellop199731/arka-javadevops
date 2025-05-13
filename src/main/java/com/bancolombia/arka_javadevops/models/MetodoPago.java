package com.bancolombia.arka_javadevops.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "metodosPago")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetodoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMetodoPago;

    @NotNull(message = "Debe proporcionar el nombre del metodo de pago")
    @NotBlank(message = "Debe proporcionar el nombre del metodo de pago")
    private String nombreMetodoPago;

    @JsonIgnore
    @OneToMany(mappedBy = "metodoPago")
    private List<MetodoPagoUsuario> metodosPagoUsuario;

}
