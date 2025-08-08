package com.james.store.dtos;

import lombok.Data;

@Data
public class ChangePasswordRequest {
  private String oldPassword;
  private String newPassword;
}
