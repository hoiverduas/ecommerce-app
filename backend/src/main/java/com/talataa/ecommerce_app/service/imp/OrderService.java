package com.talataa.ecommerce_app.service.imp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talataa.ecommerce_app.dto.orderDto.RequestOrderDTO;
import com.talataa.ecommerce_app.dto.orderDto.RequestUpdatedOrderDTO;
import com.talataa.ecommerce_app.dto.orderDto.ResponseOrderDTO;
import com.talataa.ecommerce_app.model.Order;
import com.talataa.ecommerce_app.model.OrderState;
import com.talataa.ecommerce_app.model.User;
import com.talataa.ecommerce_app.repository.IOrderRepository;
import com.talataa.ecommerce_app.repository.IUserRepository;
import com.talataa.ecommerce_app.service.IOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

        Long userId = requestOrderDTO.getUserId();
        User user = userRepository
                .findById(userId)
                .orElseThrow(()->new RuntimeException("Not Found"));

        Order productOrder = mapToEntity(requestOrderDTO);
        productOrder.setDateCreated(LocalDateTime.now());

        productOrder.getOrderProducts()
                .forEach(orderProduct -> orderProduct.setOrder(productOrder));

        this.orderRepository.save(productOrder);

        ResponseOrderDTO orderDTO = mapTaDto(productOrder);
        orderDTO.setDateCreated(productOrder.getDateCreated());

        return orderDTO;
    }

    @Override
    public Iterable<ResponseOrderDTO> findAllOrder() {
        List<Order> orders = (List<Order>) orderRepository.findAll();
        return orders.stream()
                .map(this::mapTaDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseOrderDTO findOrderById(Long id) {

        Order order = orderRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Not Found"));
        return mapTaDto(order);
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
    public Iterable<ResponseOrderDTO> findByUser(User user) {

        List<Order>orders = (List<Order>) orderRepository.findByUser(user);
        return orders.stream()
                .map(this::mapTaDto)
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


}
