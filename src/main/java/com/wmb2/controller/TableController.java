package com.wmb2.controller;

import com.wmb2.model.Table;
import com.wmb2.model.request.TableRequest;
import com.wmb2.model.response.PagingResponse;
import com.wmb2.model.response.SuccessResponse;
import com.wmb2.service.ITableService;
import com.wmb2.utils.UrlMapping;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlMapping.TABLES)
@Validated
public class TableController {

    private ModelMapper modelMapper;
    private ITableService tableService;

    public TableController(ModelMapper modelMapper, ITableService tableService) {
        this.modelMapper = modelMapper;
        this.tableService = tableService;
    }

    @GetMapping
    public ResponseEntity getAllTables(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "DESC") String direction,
            @RequestParam(defaultValue = "tableId") String sortBy
    ) throws Exception {
        Page<Table> result = tableService.list(page, size, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get table list", result));
    }

    @PostMapping
    public ResponseEntity createTable(TableRequest tableRequest) throws Exception {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Table result = tableService.create(tableRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success add table", result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") String id) throws Exception {
        tableService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success delete table", null));
    }


}
