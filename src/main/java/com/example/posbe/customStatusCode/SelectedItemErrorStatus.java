package com.example.posbe.customStatusCode;

import com.example.posbe.dto.custom.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedItemErrorStatus implements ItemStatus {
    private int status;
    private String statusMessage;
}
