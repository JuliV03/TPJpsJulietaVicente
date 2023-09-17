package com.TrabajoJPA.demo;

import com.TrabajoJPA.demo.entidades.*;
import com.TrabajoJPA.demo.entidades.Factura;
import com.TrabajoJPA.demo.entidades.Rubro;
import com.TrabajoJPA.demo.entidades.DetallePedido;
import com.TrabajoJPA.demo.entidades.Producto;
import com.TrabajoJPA.demo.entidades.Pedido;
import com.TrabajoJPA.demo.entidades.Domicilio;
import com.TrabajoJPA.demo.entidades.Cliente;
import com.TrabajoJPA.demo.entidades.BaseEntidad;
import com.TrabajoJPA.demo.enumeraciones.EstadoPedido;
import com.TrabajoJPA.demo.enumeraciones.FormaPagoFac;
import com.TrabajoJPA.demo.enumeraciones.TipoPedido;
import com.TrabajoJPA.demo.enumeraciones.TipoProducto;
import com.TrabajoJPA.demo.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

@SpringBootApplication
public class DemoApplication {
@Autowired
ClienteRepository clienteRepository;
@Autowired
DomicilioRepository domicilioRepository;
@Autowired
	DetallePedidoRepository detallePedidoRepository;
@Autowired
	FacturaRepository facturaRepository;
@Autowired
	PedidoRepository pedidoRepository;
@Autowired
ProductoRepository productoRepository;
@Autowired
RubroRepository rubroRepository;


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Estoy funcionando");
	}

	@Bean
	CommandLineRunner init(ClienteRepository clienteRepository, RubroRepository rubroRepository){
		return args -> {
			System.out.println("----Funciono----");
			Cliente cliente1 = Cliente.builder()
					.nombre("Julieta")
					.apellido("Vicente")
					.telefono("2616141960")
					.email("julietavic@gmail.com")
					.build();

			Domicilio domicilio1 = Domicilio.builder()
					.numero("1198")
					.calle("PiedraBuena")
					.localidad("Godoy Cruz")
					.build();

			Domicilio domicilio2 = Domicilio.builder()
					.numero("2523")
					.calle("San Martín")
					.localidad("Mendoza")
					.build();

			cliente1.agregarDomicilio(domicilio1);
			cliente1.agregarDomicilio(domicilio2);

			//configuracion fecha
			SimpleDateFormat formatoFecha = new SimpleDateFormat ("yyyy-MM-dd");
			String fechaString = "2023-09-09";
			// Parsear la cadena en un objeto Date
			Date fecha = formatoFecha.parse(fechaString);

			Pedido pedido1 = Pedido.builder()
					.estado(EstadoPedido.PREPARACION)
					.envio(TipoPedido.DELIVERY)
					.fecha(fecha)
					.total(3255.30)
					.build();


			Factura factura1= Factura.builder()
					.numero(1)
					.fecha(fecha)
					.descuento(0.15)
					.formaPago(FormaPagoFac.EFECTIVO)
					.total(3530)
					.build();
			pedido1.setFactura(factura1);

			Rubro rubro01 = Rubro.builder()
					.denominacion("Guarnición")
					.build();
			Producto producto1 = Producto.builder()
					.receta("Papas hervidas y freidas con aceite de girasol")
					.denominacion("Papas Fritas")
					.precioVenta(20.3)
					.precioCompra(13.50)
					.tiempoEstimadoCocina(20)
					.unidadVencida("No")
					.stockActual(10)
					.stockMinimo(3)
					.tipo(TipoProducto.MANOFACTURADO)
					.build();
			rubro01.setProductos2(Collections.singletonList(producto1));

			Rubro rubro02 = Rubro.builder()
					.denominacion("Principal")
					.build();
			Producto producto2 = Producto.builder()
					.receta("Carne asada con pan, tomates, lechuga y aderesos")
					.denominacion("Hamburguesa1")
					.precioVenta(1209)
					.precioCompra(600)
					.tiempoEstimadoCocina(30)
					.unidadVencida("No")
					.stockActual(10)
					.stockMinimo(3)
					.tipo(TipoProducto.MANOFACTURADO)
					.build();
			rubro02.setProductos2(Collections.singletonList(producto2));

			DetallePedido detalle1= DetallePedido.builder()
					.cantidad(5)
					.subtotal(1000)
					.build();

			DetallePedido detalle2 = DetallePedido.builder()
					.cantidad(2)
					.subtotal(2500)
					.build();

			pedido1.agregarDetalles(detalle1);
			pedido1.agregarDetalles(detalle2);
			cliente1.agregarPedido(pedido1);
			rubroRepository.save(rubro01);
			rubroRepository.save(rubro02);

			clienteRepository.save(cliente1);
			Cliente clienteRecuperado = clienteRepository.findById(cliente1.getId()).orElse(null);
			if (clienteRecuperado != null) {
				System.out.println("Nombre: " + clienteRecuperado.getNombre());
				System.out.println("Apellido: " + clienteRecuperado.getApellido());
				System.out.println("Telefono: " + clienteRecuperado.getTelefono());
				System.out.println("Email: "+ clienteRecuperado.getEmail());
				clienteRecuperado.mostrarDomicilios();
				clienteRecuperado.mostrarPedidos();
			}

			Rubro rubrorecu=  rubroRepository.findById(rubro01.getId()).orElse(null);
			if (clienteRecuperado != null) {
				System.out.println("Denominación: " + rubrorecu.getDenominacion());
				rubrorecu.mostrarProductos();
			}

			Rubro rubrorecu2=  rubroRepository.findById(rubro02.getId()).orElse(null);
			if (clienteRecuperado != null) {
				System.out.println("Denominación: " + rubrorecu2.getDenominacion());
				rubrorecu2.mostrarProductos();
			}
		};
	}
}

