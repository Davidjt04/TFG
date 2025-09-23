package com.david.tfg.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.david.tfg.entities.OrderDetail;
import com.david.tfg.services.OrderDetailService;

@CrossOrigin(origins = "*")
@RestController
public class OrderDetailRestController {

     //Inyectamos el servicio
    private final OrderDetailService service;

    public OrderDetailRestController(OrderDetailService service) {
        this.service = service;
    }

    //se muestran todos los detalles de pedido
    @GetMapping("/detaPedido/lista")
    public List<OrderDetail> lista(){
        //va a sacar una lista de detalles de pedido 
        return this.service.findAll();
    }

    @GetMapping("/detaPedido/borrar/{id}")
    public ResponseEntity<OrderDetail> borrar(@PathVariable Integer id){
        //va a borrar un detalle de pedido
        if(service.existsById(id)){  
          service.deleteById(id);
          //noContent la operacion se hizo bien pero no hay contenido en el cuerpo
            return ResponseEntity.noContent().build(); 
        }
        //noFound no se ha encontrado, codigo de error 404
        return ResponseEntity.notFound().build();
    }

    //editar
    @GetMapping("/detaPedido/editar/{id}")
    public ResponseEntity<OrderDetail> editar(@PathVariable Integer id) {
    Optional<OrderDetail> detallePedidoOpt = service.findById(id);
        if (detallePedidoOpt.isPresent()) {
            OrderDetail detallePedido = detallePedidoOpt.get();
            return ResponseEntity.ok(detallePedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //crear
    @PostMapping("/detaPedido/crear")
    public ResponseEntity<OrderDetail> crear(){
        //va a crear un producto
        return ResponseEntity.ok(new OrderDetail());
    }

    //guardar
    @PostMapping("/detaPedido/guardar")
    public ResponseEntity<OrderDetail> guardar(@RequestBody OrderDetail detallePedido){
        //parte de creacion de un detalle de pedido 
        if(detallePedido.getIdDetalle_Pedido() == 0){
            service.save(detallePedido);
            return ResponseEntity.ok(detallePedido);
        }else{
            //parte de modificacin de un detalle de pedido 
            Optional<OrderDetail> detallePedidoSinActu = service.findById(detallePedido.getIdDetalle_Pedido());
            //cogemos el objeto del optional 
            OrderDetail detallePedidoActu = detallePedidoSinActu.get();
            detallePedidoActu.setPrecio_Unitario(detallePedido.getPrecio_Unitario());
            detallePedidoActu.setCantidad_Unitaria(detallePedido.getCantidad_Unitaria());

            service.save(detallePedidoActu);
            return ResponseEntity.ok(detallePedidoActu);  
        }
            
    }
}
