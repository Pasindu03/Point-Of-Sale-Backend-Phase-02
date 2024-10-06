package com.example.posbe.dao;

import com.example.posbe.entity.impl.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailDao extends JpaRepository<OrderDetail, String> {
}
