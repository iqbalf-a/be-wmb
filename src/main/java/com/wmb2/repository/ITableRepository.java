package com.wmb2.repository;

import com.wmb2.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITableRepository extends JpaRepository<Table, String> {
}
