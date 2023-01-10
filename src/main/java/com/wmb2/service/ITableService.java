package com.wmb2.service;

import com.wmb2.model.Table;
import com.wmb2.model.request.TableRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ITableService {

    List<Table> list() throws Exception;

    Table create(TableRequest tableRequest) throws Exception;

    Table get(String id) throws Exception;

    void delete(String id) throws Exception;

    Page<Table> list(Integer page, Integer size, String direction, String sortBy) throws Exception;

}
