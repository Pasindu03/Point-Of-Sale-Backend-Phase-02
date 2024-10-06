package com.example.posbe.dao;

import com.example.posbe.entity.impl.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDao extends JpaRepository<Item, String> {
}
