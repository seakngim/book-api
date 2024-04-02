package com.example.monumentbook.service;

import com.example.monumentbook.model.requests.SupplierRequest;
import org.springframework.http.ResponseEntity;

public interface SupplierService {
    ResponseEntity<?> addSupplier(SupplierRequest supplierRequest);
    ResponseEntity<?> findAllSupplier();
    ResponseEntity<?> findSupplierById(Integer id);
    ResponseEntity<?> updateSupplier(Integer id, SupplierRequest supplierRequest);
    ResponseEntity<?> deleteSupplier(Integer id);
}
