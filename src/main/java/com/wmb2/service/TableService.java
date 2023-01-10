package com.wmb2.service;

import com.wmb2.exception.EntityExistException;
import com.wmb2.exception.NotFoundException;
import com.wmb2.model.Food;
import com.wmb2.model.Table;
import com.wmb2.model.request.FoodRequest;
import com.wmb2.model.request.TableRequest;
import com.wmb2.repository.IFoodRepository;
import com.wmb2.repository.ITableRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableService implements ITableService {

    private ITableRepository tableRepository;

    public TableService(ITableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public List<Table> list() {
        List<Table> tables = tableRepository.findAll();
        return tables;
    }

    @Override
    public Table create(TableRequest tableRequest){
        try {
            Table table = new Table();
            table.setNumber(tableRequest.getNumber());
            table.setStatus(true);
            return tableRepository.save(table);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Override
    public Table get(String id) {
        Optional<Table> table = tableRepository.findById(id);
        if (table.isEmpty()) {
            throw new NotFoundException("Food not found");
        }
        return table.get();
    }

    @Override
    public void delete(String id) {
        try {
            Table existingTable = get(id);
            tableRepository.delete(existingTable);
        } catch (NotFoundException e) {
            throw new NotFoundException("ID not found");
        }
    }

    @Override
    public Page<Table> list(Integer page, Integer size, String direction, String sortBy) throws Exception {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<Table> result = tableRepository.findAll(pageable);
        return result;
    }
}
