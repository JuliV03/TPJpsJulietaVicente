package com.TrabajoJPA.demo.entidades;

import com.TrabajoJPA.demo.enumeraciones.FormaPagoFac;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Factura extends  BaseEntidad{
    private  int numero;
    private Date fecha;
    private double descuento;
    private FormaPagoFac formaPago;
    private int total;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getDescuento() {
        return descuento;
    }

    public FormaPagoFac getFormaPago() {
        return formaPago;
    }

    public int getNumero() {
        return numero;
    }

    public int getTotal() {
        return total;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public void setFormaPago(FormaPagoFac formaPago) {
        this.formaPago = formaPago;
    }

}

