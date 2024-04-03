package com.example.monumentbook.model.requests;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class SupplierRequest {
    private String name;
    private String phone;
    private String email;
    private String image;
    private String address;
}
