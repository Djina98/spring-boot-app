package com.silab.demo.controller;

import com.silab.demo.dto.MyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

public interface MyRestController<DTO extends MyDto, ID>{

    @ResponseBody
    ResponseEntity<List<DTO>> getAll();

    @ResponseBody
    ResponseEntity<Object> findById(@PathVariable Long ID);

    @ResponseBody
    ResponseEntity<Object> save(@RequestBody DTO dto);

    @ResponseBody
    ResponseEntity<Object> deleteById(@PathVariable Long ID);

    @ResponseBody
    ResponseEntity<Object> update(@RequestBody DTO dto);
}
