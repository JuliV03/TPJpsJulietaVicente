package com.TrabajoJPA.demo.entidades;

import com.TrabajoJPA.demo.enumeraciones.TipoProducto;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto extends BaseEntidad {
   private int tiempoEstimadoCocina;
   private String denominacion;
   private double precioVenta;
   private double precioCompra;
   private  int stockActual;
   private  int stockMinimo;
   private  String unidadVencida;
   private String receta;
   private TipoProducto tipo;

    public double getPrecioCompra() {
        return precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public int getTiempoEstimadoCocina() {
        return tiempoEstimadoCocina;
    }

    public int getStockActual() {
        return stockActual;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public String getUnidadVencida() {
        return unidadVencida;
    }

    public String getReceta() {
        return receta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public void setTiempoEstimadoCocina(int tiempoEstimadoCocina) {
        this.tiempoEstimadoCocina = tiempoEstimadoCocina;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setReceta(String receta) {
        this.receta = receta;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    public void setUnidadVencida(String unidadVencida) {
        this.unidadVencida = unidadVencida;
    }
}
