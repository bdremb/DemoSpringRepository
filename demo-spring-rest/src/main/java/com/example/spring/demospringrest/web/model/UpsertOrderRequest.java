package com.example.spring.demospringrest.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertOrderRequest {

    @NotNull(message = "Not null")
    @Positive(message = "Number must be > 0")
    private Long clientId;

    @NotBlank(message = "Not blank")
    private String product;

    @NotNull(message = "Not null")
    @Positive(message = "Number must be > 0")
    private BigDecimal cost;

}
