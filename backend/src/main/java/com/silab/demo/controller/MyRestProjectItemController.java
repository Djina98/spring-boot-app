package com.silab.demo.controller;

import com.silab.demo.dto.MyDto;
import com.silab.demo.entity.impl.ProjectItemIdentity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface MyRestProjectItemController<DTO extends MyDto, id> {

    @ResponseBody
    ResponseEntity<List<DTO>> getAll();

    @ResponseBody
    ResponseEntity<Object> findById(@PathVariable ProjectItemIdentity id);

    @ResponseBody
    ResponseEntity<List<DTO>> findProjectItemsByEmployeeId(@PathVariable Long id);

    @ResponseBody
    ResponseEntity<List<DTO>> findProjectItemsByProjectId(@PathVariable Long id);

    @ResponseBody
    ResponseEntity<Object> save(@RequestBody DTO dto);

    @ResponseBody
    ResponseEntity<Object> deleteById(@RequestBody ProjectItemIdentity id);

    @ResponseBody
    ResponseEntity<Object> update(@RequestBody DTO dto);
}
