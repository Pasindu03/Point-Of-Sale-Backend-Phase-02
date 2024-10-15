package com.example.posbe.customStatusCode;

import com.example.posbe.dto.custom.OrderDetailStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedOrderDetailErrorStatus implements OrderDetailStatus {
    private int status;
    private String statusMessage;
}
