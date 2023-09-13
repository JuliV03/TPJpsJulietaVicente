package com.TrabajoJPA.demo.entidades;

import com.TrabajoJPA.demo.enumeraciones.EstadoPedido;
import com.TrabajoJPA.demo.enumeraciones.TipoPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido extends BaseEntidad{
private Date fecha;
private TipoPedido envio;
private EstadoPedido estado;
private double total;

    public Date getFecha() {
        return fecha;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public double getTotal() {
        return total;
    }

    public void setEnvio(TipoPedido envio) {
        this.envio = envio;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public TipoPedido getEnvio() {
        return envio;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "factura_id")
    private Factura factura;

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name="pedido_id")
    @Builder.Default
    private List<DetallePedido> detalles = new ArrayList<>();
    public void agregarDetalles(DetallePedido deta){
        detalles.add(deta);
    }
    public void mostrarDetalles() {
        System.out.println("Detalles de " + Pedido.this);
        for (DetallePedido detalle : detalles) {
            System.out.println("Cantidad: " + detalle.getCantidad()+ ", Subtotal: " + detalle.getSubtotal());
        }

    }
}
