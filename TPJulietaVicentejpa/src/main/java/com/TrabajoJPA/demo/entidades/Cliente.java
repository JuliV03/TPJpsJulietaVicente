package com.TrabajoJPA.demo.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Cliente") // Para que coincida con la entidad
public class Cliente extends BaseEntidad {
@Column(name = "Nombre")
    private String nombre;
    private String apellido;
    private String telefono;
    private  String email;

    public List<Domicilio> getDomicilios() {
        return domicilios;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDomicilios(List<Domicilio> domicilios) {
        this.domicilios = domicilios;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
@JoinColumn(name="cliente_id")
@Builder.Default
private List<Domicilio> domicilios = new ArrayList<>();
    public void agregarDomicilio(Domicilio domi){
        domicilios.add(domi);
    }
    public void mostrarDomicilios() {
        System.out.println("Domicilios de " + nombre + " " + apellido + ":");
        for (Domicilio domicilio : domicilios) {
            System.out.println("Calle: " + domicilio.getCalle() + ", Número: " + domicilio.getNumero() + " Localidad: "+domicilio.getLocalidad());
        }

    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name="cliente_id")
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList<>();
    public void agregarPedido(Pedido ped){
        pedidos.add(ped);
    }
    public void mostrarPedidos() {
        System.out.println("Domicilios de " + nombre + " " + apellido + ":");
        for (Pedido pedido : pedidos) {
            System.out.println("Estado: " + pedido.getEstado());
            System.out.println("Tipo de Envio: " + pedido.getEnvio());
            System.out.println("Fecha: " + pedido.getFecha());
            System.out.println("Total: "+ pedido.getTotal());
            System.out.println("Factura: "+ pedido.getFactura());
            pedido.mostrarDetalles();
        }

    }
}
