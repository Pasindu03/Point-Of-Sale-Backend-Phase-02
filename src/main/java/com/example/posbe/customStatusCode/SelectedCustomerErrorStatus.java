package com.example.posbe.customStatusCode;

import com.example.posbe.dto.custom.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedCustomerErrorStatus extends Throwable implements CustomerStatus {
    private int status;
    private String statusMessage;
}
