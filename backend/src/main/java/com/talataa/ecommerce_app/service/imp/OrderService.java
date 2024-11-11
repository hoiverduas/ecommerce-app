package com.talataa.ecommerce_app.service.imp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talataa.ecommerce_app.dto.orderDto.RequestOrderDTO;
import com.talataa.ecommerce_app.dto.orderDto.RequestUpdatedOrderDTO;
import com.talataa.ecommerce_app.dto.orderDto.ResponseOrderDTO;
import com.talataa.ecommerce_app.dto.orderProductDto.ResponseOrderProductDTO;
import com.talataa.ecommerce_app.model.Order;
import com.talataa.ecommerce_app.model.OrderProduct;
import com.talataa.ecommerce_app.model.OrderState;
import com.talataa.ecommerce_app.model.User;
import com.talataa.ecommerce_app.repository.IOrderRepository;
import com.talataa.ecommerce_app.repository.IUserRepository;
import com.talataa.ecommerce_app.service.IOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderService implements IOrderService {

    private final IOrderRepository orderRepository;
    private final IUserRepository userRepository;
    private final ObjectMapper objectMapper;

    public OrderService(IOrderRepository orderRepository, IUserRepository userRepository, ObjectMapper objectMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public ResponseOrderDTO createOrder(RequestOrderDTO requestOrderDTO) {
        // Buscar el usuario por ID
        Long userId = requestOrderDTO.getUserId();
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Crear una nueva instancia de Order y configurar sus propiedades
        Order order = new Order();
        order.setUser(user);
        order.setDateCreated(LocalDateTime.now());

        // Configurar el estado de la orden (CONFIRMED o CANCELLED)
        if (requestOrderDTO.getOrderState() != null && requestOrderDTO.getOrderState().equals(OrderState.CANCELLED)) {
            order.setOrderState(OrderState.CANCELLED);
        } else {
            order.setOrderState(OrderState.CONFIRMED);
        }

        // Convertir los productos del DTO a entidades OrderProduct y configurarlos en la orden
        List<OrderProduct> orderProducts = requestOrderDTO.getOrderProducts().stream().map(dto -> {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProductId(dto.getProductId());
            orderProduct.setQuantity(dto.getQuantity());
            orderProduct.setPrice(dto.getPrice());
            orderProduct.setOrder(order); // Asignar la orden al producto
            return orderProduct;
        }).collect(Collectors.toList());
        order.setOrderProducts(orderProducts);
        // Guardar la orden con sus productos en la base de datos
        Order savedOrder = orderRepository.save(order);

        // Mapear la entidad guardada a ResponseOrderDTO para la respuesta
        ResponseOrderDTO responseOrderDTO = mapToResponseDTO(savedOrder);
        responseOrderDTO.setTotalOrderPrice(savedOrder.getTotalOrderPrice());


        return responseOrderDTO;
    }


    @Override
    public Iterable<ResponseOrderDTO> findAllOrder() {
        List<Order> orders = (List<Order>) orderRepository.findAll();
        return orders.stream()
                .map(order -> {
                    ResponseOrderDTO dto = mapToResponseDTO(order);
                    dto.setTotalOrderPrice(order.getTotalOrderPrice()); // Asigna el precio total
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ResponseOrderDTO findOrderById(Long id) {

        Order order = orderRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Not Found"));
        return mapToResponseDTO(order);
    }

    @Override
    public ResponseOrderDTO updateOrder(RequestUpdatedOrderDTO requestUpdatedOrderDTO) {

        findOrderById(requestUpdatedOrderDTO.getId());
        Order order = orderRepository.save(mapToEntity(requestUpdatedOrderDTO));
        return mapTaDto(order);
    }

    @Override
    public void deleteOrderById(Long id) {
          findOrderById(id);
          this.orderRepository.deleteById(id);
    }



    @Override
    public void updateStatusById(Long id, String status) {

        if (status.equals(OrderState.CANCELLED)){
            this.orderRepository.updateStateById(id,OrderState.CANCELLED);
        }else {
            this.orderRepository.updateStateById(id,OrderState.CONFIRMED);
        }

    }

    @Override
    public Iterable<ResponseOrderDTO> findByUser(Long userId) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(()->new RuntimeException("Not Found"));

           List<Order> orders = (List<Order>) orderRepository.findByUser(user);


        return orders.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }


    private ResponseOrderDTO mapTaDto(Order order) {

        ResponseOrderDTO dto = this.objectMapper.convertValue(order, ResponseOrderDTO.class);

        if (order.getUser() != null) {
            dto.setUserId(order.getUser().getId());
        }
        return dto;
    }


    private Order mapToEntity(RequestOrderDTO requestProductDTO){
        return this.objectMapper
                .convertValue(requestProductDTO,Order.class);
    }

    private Order mapToEntity(RequestUpdatedOrderDTO requestProductUpdateDTO){
        return this.objectMapper
                .convertValue(requestProductUpdateDTO,Order.class);
    }


    public ResponseOrderDTO mapToResponseDTO(Order order) {
        ResponseOrderDTO dto = new ResponseOrderDTO();

        // Otros mapeos de propiedades de la orden a dto
        dto.setId(order.getId());
        dto.setOrderState(order.getOrderState());
        dto.setDateCreated(order.getDateCreated());
        dto.setTotalOrderPrice(order.getTotalOrderPrice());

        // Verifica si el usuario no es null antes de asignar el ID de usuario
        if (order.getUser() != null) {
            dto.setUserId(order.getUser().getId());
        } else {
            dto.setUserId(null); // o algún valor predeterminado o manejo según tu lógica
        }


        // Mapear los productos de la orden, si corresponde
        List<ResponseOrderProductDTO> orderProductDTOs = order.getOrderProducts().stream()
                .map(orderProduct -> {
                    ResponseOrderProductDTO productDTO = new ResponseOrderProductDTO();
                    productDTO.setProductId(orderProduct.getProductId());
                    productDTO.setQuantity(orderProduct.getQuantity());
                    productDTO.setPrice(orderProduct.getPrice());
                    return productDTO;
                })
                .collect(Collectors.toList());
        dto.setOrderProducts(orderProductDTOs);

        return dto;

    }
}
