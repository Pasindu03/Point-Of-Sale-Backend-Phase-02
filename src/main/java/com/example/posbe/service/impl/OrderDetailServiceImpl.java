package com.example.posbe.service.impl;

import com.example.posbe.dao.OrderDetailDao;
import com.example.posbe.dto.custom.impl.OrderDetailDto;
import com.example.posbe.entity.impl.OrderDetail;
import com.example.posbe.exception.DataPersistException;
import com.example.posbe.exception.OrderDetailNotFoundException;
import com.example.posbe.service.OrderDetailService;
import com.example.posbe.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveOrderDetail(OrderDetailDto orderDetailDto) {
        OrderDetail orderDetail = mapping.toOrderDetailEntity(orderDetailDto);

        OrderDetail savedOrderDetail = orderDetailDao.save(orderDetail);
        if (savedOrderDetail == null) {
            throw new DataPersistException("OrderDetail not saved");
        }
    }

    @Override
    public OrderDetailDto getOrderDetailById(String orderDetailId) {
        Optional<OrderDetail> orderDetail = orderDetailDao.findById(orderDetailId);
        if (orderDetail.isPresent()) {
            return mapping.toOrderDetailDto(orderDetail.get());
        } else {
            throw new OrderDetailNotFoundException("OrderDetail not found");
        }
    }

    @Override
    public List<OrderDetailDto> getAllOrderDetails() {
        List<OrderDetail> allOrderDetails = orderDetailDao.findAll();
        return mapping.asOrderDetailDto(allOrderDetails);
    }

    @Override
    public void updateOrderDetail(String orderDetailId, OrderDetailDto orderDetailDto) {
        Optional<OrderDetail> orderDetailOptional = orderDetailDao.findById(orderDetailId);
        if (orderDetailOptional.isPresent()) {
            OrderDetail orderDetailToUpdate = orderDetailOptional.get();

            orderDetailToUpdate.setQuantity(orderDetailDto.getQuantity());
            orderDetailToUpdate.setUnitPrice(orderDetailDto.getUnitPrice());
            orderDetailToUpdate.setItem(mapping.toItemEntity(orderDetailDto.getItemDto()));

            orderDetailDao.save(orderDetailToUpdate);
        } else {
            throw new OrderDetailNotFoundException("OrderDetail not found for update");
        }
    }

    @Override
    public void deleteOrderDetail(String orderDetailId) {
        Optional<OrderDetail> orderDetailOptional = orderDetailDao.findById(orderDetailId);
        if (orderDetailOptional.isPresent()) {
            orderDetailDao.deleteById(orderDetailId);
        } else {
            throw new OrderDetailNotFoundException("OrderDetail not found for deletion");
        }
    }
}
