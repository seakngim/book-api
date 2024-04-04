package com.example.monumentbook.model;

import com.example.monumentbook.model.dto.SupplierDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "supplier_tb")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Integer id;
    private String name;
    private String image;
    private LocalDate date;
    private String address;
    private String phone;
    private String email;
    @Column(name = "deleted")
    private boolean deleted = false;
    public SupplierDto toDto() {
        return new SupplierDto(this.id,this.name,this.image,this.address,this.phone,this.email);
    }

}
