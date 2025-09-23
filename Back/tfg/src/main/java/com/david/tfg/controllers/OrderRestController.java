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

import com.david.tfg.entities.Order;
import com.david.tfg.services.OrderService;

@CrossOrigin(origins = "*")
@RestController
public class OrderRestController {
 //Inyectamos el servicio
    private final OrderService service;

    public OrderRestController(OrderService service) {
        this.service = service;
    }

    //se muestran todos los pedidos
    @GetMapping("/pedido/lista")
    public List<Order> lista(){
        //va a sacar una lista de pedidos 
        return this.service.findAll();
    }

    @GetMapping("/pedido/borrar/{id}")
    public ResponseEntity<Order> borrar(@PathVariable Integer id){
        //va a borrar un pedido
        if(service.existsById(id)){  
          service.deleteById(id);
          //noContent la operacion se hizo bien pero no hay contenido en el cuerpo
            return ResponseEntity.noContent().build(); 
        }
        //noFound no se ha encontrado, codigo de error 404
        return ResponseEntity.notFound().build();
    }

    //editar
    @GetMapping("/pedido/editar/{id}")
    public ResponseEntity<Order> editar(@PathVariable Integer id) {
    Optional<Order> pedidoOpt = service.findById(id);
        if (pedidoOpt.isPresent()) {
            Order pedido = pedidoOpt.get();
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //crear
    @PostMapping("/pedido/crear")
    public ResponseEntity<Order> crear(){
        //va a crear un producto
        return ResponseEntity.ok(new Order());
    }

    //guardar
    @PostMapping("/pedido/guardar")
    public ResponseEntity<Order> guardar(@RequestBody Order pedido){
        //parte de creacion de un articulo 
        if(pedido.getIdPedido() == 0){
        //    return service.save(arbitro);
            service.save(pedido);
            return ResponseEntity.ok(pedido);
        }else{
            //parte de modificacin de un arbitro 
            Optional<Order> pedidoSinActu = service.findById(pedido.getIdPedido());
            //cogemos el objeto del optional 
            Order pedidoActu = pedidoSinActu.get();
            pedidoActu.setPrecio_Total(pedido.getPrecio_Total());
            pedidoActu.setFecha_Realizacion(pedido.getFecha_Realizacion());
            pedidoActu.setEstado(pedido.getEstado());

            //meter los partidos asociados 
            service.save(pedidoActu);
            return ResponseEntity.ok(pedidoActu);  
        }
            
    }
}
