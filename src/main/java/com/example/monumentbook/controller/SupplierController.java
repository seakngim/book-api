package com.example.monumentbook.controller;

import com.example.monumentbook.model.requests.SupplierRequest;
import com.example.monumentbook.service.SupplierService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/supplier")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;

    @PostMapping("/add")
    public ResponseEntity<?> Add(@RequestBody SupplierRequest supplierRequest) {
        return supplierService.addSupplier(supplierRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return supplierService.findAllSupplier();
    }
    @GetMapping("/byId")
    public ResponseEntity<?> getById(@Param("supplier id") Integer id) {
        return supplierService.findSupplierById(id);
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@Param("supplier id") Integer id, @RequestBody SupplierRequest supplierRequest) {
        return supplierService.updateSupplier(id, supplierRequest);
    }
    @DeleteMapping("/deleted")
    public ResponseEntity<?> delete(@Param("supplier id") Integer id) {
        return supplierService.deleteSupplier(id);
    }

}
