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
public class Rubro extends BaseEntidad{
    private String denominacion;

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getDenominacion() {
        return denominacion;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name="rubro_id")
    @Builder.Default
    private List<Producto> productos2 = new ArrayList<>();

    public List<Producto> getProductos2() {
        return productos2;
    }

    public void setProductos2(List<Producto> productos2) {
        this.productos2 = productos2;
    }

    public void agregarProducto(Producto ped){
        productos2.add(ped);
    }
    public void mostrarProductos() {
        System.out.println("Productos de " + Rubro.this);
        for (Producto producto : productos2) {
            System.out.println("Tipo: " + producto.getTipo()+ ", Tiempo Estimado de Cosina: " + producto.getTiempoEstimadoCocina());
            System.out.println("Denominación: "+ producto.getDenominacion()+ ", Precio de Venta: "+producto.getPrecioVenta()+", Precio de Compra: "+producto.getPrecioCompra());
            System.out.println("Stock Actual: "+ producto.getStockActual()+", Stock Mínimo: "+ producto.getStockMinimo());
            System.out.println("Producto Vencida: "+ producto.getUnidadVencida()+", Receta: "+producto.getReceta());
        }

    }
}
