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

import java.util.Collections;

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
			Cliente cliente1 = new Cliente();
			cliente1.setNombre("Julieta");
			cliente1.setApellido("Vicente");
			cliente1.setTelefono("2616141960");
			cliente1.setEmail("julietavic@gmail.com");

			Domicilio domicilio1 = new Domicilio();
			domicilio1.setNumero("1198");
			domicilio1.setCalle("PiedraBuena");
			domicilio1.setLocalidad("Godoy Cruz");

			Domicilio domicilio2 = new Domicilio();
			domicilio2.setNumero("2523");
			domicilio2.setCalle("San Martín");
			domicilio2.setLocalidad("Mendoza");

			cliente1.agregarDomicilio(domicilio1);
			cliente1.agregarDomicilio(domicilio2);

			Pedido pedido1 = new Pedido();
			EstadoPedido INICIADO = pedido1.getEstado();
			pedido1.setEstado(INICIADO);
			TipoPedido DELIVERY = pedido1.getEnvio();
			pedido1.setEnvio(DELIVERY);
			pedido1.setTotal(3255.30);

			Factura factura1= new Factura();
			factura1.setNumero(1);
			//factura1.setFecha(2002 / 03 / 06);
			factura1.setDescuento(0.15);
			FormaPagoFac EFECTIVO= factura1.getFormaPago();
			factura1.setFormaPago(EFECTIVO);
			 int tot= (int) (pedido1.getTotal()*factura1.getDescuento());
			factura1.setTotal(tot);
			pedido1.setFactura(factura1);

			Rubro rubro01 = new Rubro();
			rubro01.setDenominacion("Guarnición");
			Producto producto1 = new Producto();
			producto1.setReceta("Papas hervidas y freidas con aceite de girasol");
			producto1.setDenominacion("Papas Fritas");
			producto1.setPrecioVenta(20.3);
			producto1.setPrecioCompra(13.50);
			producto1.setTiempoEstimadoCocina(20);
			producto1.setUnidadVencida("No");
			producto1.setStockActual(10);
			producto1.setStockMinimo(3);
			TipoProducto INSUMO = producto1.getTipo();
			producto1.setTipo(INSUMO);
			rubro01.setProductos2(Collections.singletonList(producto1));

			Rubro rubro02 = new Rubro();
			rubro02.setDenominacion("Principal");
			Producto producto2 = new Producto();
			producto2.setReceta("Carne asada con pan, tomates, lechuga y aderesos");
			producto2.setDenominacion("Hamburguesa1");
			producto2.setPrecioVenta(1209);
			producto2.setPrecioCompra(600);
			producto2.setTiempoEstimadoCocina(30);
			producto2.setUnidadVencida("No");
			producto2.setStockActual(10);
			producto2.setStockMinimo(3);
			producto2.setTipo(INSUMO);
			rubro02.setProductos2(Collections.singletonList(producto2));

			DetallePedido detalle1= new DetallePedido();
			detalle1.setCantidad(5);
			double sub1 = (double) (producto1.getPrecioVenta()*detalle1.getCantidad());
			detalle1.setSubtotal(sub1);

			DetallePedido detalle2 = new DetallePedido();
			detalle1.setCantidad(2);
			double sub2 = (double) (producto2.getPrecioVenta()*detalle2.getCantidad());
			detalle1.setSubtotal(sub2);

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

