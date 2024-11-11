package com.talataa.ecommerce_app.controller;


import com.talataa.ecommerce_app.dto.orderDto.RequestOrderDTO;
import com.talataa.ecommerce_app.dto.orderDto.RequestUpdatedOrderDTO;
import com.talataa.ecommerce_app.dto.orderDto.ResponseOrderDTO;
import com.talataa.ecommerce_app.service.imp.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<ResponseOrderDTO> createOrder(@RequestBody RequestOrderDTO requestOrderDTO){
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.orderService.createOrder(requestOrderDTO));
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<ResponseOrderDTO>> getAllOrder(){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.orderService.findAllOrder());
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseOrderDTO> getOrderById(@PathVariable Long id){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.orderService.findOrderById(id));
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseOrderDTO> updateOrder(RequestUpdatedOrderDTO requestUpdatedOrderDTO){
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.orderService.updateOrder(requestUpdatedOrderDTO));
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        try {
            this.orderService.deleteOrderById(id);
            return ResponseEntity
                    .status(HttpStatus.OK).build();
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/status")
    public ResponseEntity<Void> updateStatus(@RequestParam Long id,@RequestParam String status){
        try {
            this.orderService.updateStatusById(id,status);
            return ResponseEntity
                    .status(HttpStatus.OK).build();
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/user/order/{userId}")
    public ResponseEntity<Iterable<ResponseOrderDTO>> getUserByOrderId(@PathVariable Long userId){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.orderService.findByUser(userId));
        }catch (RuntimeException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).build();
        }
    }

}
