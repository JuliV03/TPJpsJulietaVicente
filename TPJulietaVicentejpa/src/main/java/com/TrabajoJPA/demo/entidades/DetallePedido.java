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

public class DetallePedido extends BaseEntidad {
private int cantidad;
private  double subtotal;

    public double getSubtotal() {
        return subtotal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name="producto_id")
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();
    public void agregarProducto(Producto ped){
        productos.add(ped);
    }
    public void mostrarProductos() {
        System.out.println("Productos de: " + DetallePedido.this);
        for (Producto producto : productos) {
            System.out.println("Tipo: " + producto.getTipo() + ", Tiempo Estimado de Cosina: " + producto.getTiempoEstimadoCocina());
            System.out.println("Denominación: " + producto.getDenominacion() + ", Precio de Venta: " + producto.getPrecioVenta() + ", Precio de Compra: " + producto.getPrecioCompra());
            System.out.println("Stock Actual: " + producto.getStockActual() + ", Stock Mínimo: " + producto.getStockMinimo());
            System.out.println("Producto Vencida: " + producto.getUnidadVencida() + ", Receta: " + producto.getReceta());
        }

    }
}
