package com.bancolombia.arka_javadevops.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proveedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProveedor;

    @NotBlank(message = "Debe proporcionar una identificacion de proveedor")
    @NotNull(message = "Debe proporcionar una identificacion de proveedor")
    private String identificacionProveedor;

    @NotBlank(message = "Debe proporcionar un nombre de proveedor")
    @NotNull(message = "Debe proporcionar un nombre de proveedor")    
    private String nombreProveedor;

    @NotBlank(message = "Debe proporcionar un telefono de proveedor")
    @NotNull(message = "Debe proporcionar un telefono de proveedor")    
    private String telefonoProveedor;

    @Email(message = "Debe proporcionar una direccion de correo valida")
    @NotBlank(message = "Debe proporcionar un correo electronico de proveedor")
    @NotNull(message = "Debe proporcionar un correo electronico de proveedor")    
    private String correoElectronicoProveedor;

    @JsonBackReference
    @OneToMany(mappedBy = "proveedor")
    private List<Producto> productos;
    
}
