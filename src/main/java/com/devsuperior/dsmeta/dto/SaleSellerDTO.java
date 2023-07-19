package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

import java.time.LocalDate;

public class SaleSellerDTO extends SaleMinDTO {

  private String sellerName;

  public SaleSellerDTO(Long id, Double amount, LocalDate date, String sellerName) {
    super(id, amount, date);
    this.sellerName = sellerName;
  }

  public SaleSellerDTO(Sale entity, String sellerName) {
    super(entity);
    this.sellerName = sellerName;
  }

  public SaleSellerDTO(Sale entity) {
    super(entity);
    this.sellerName = entity.getSeller().getName();
  }

  public String getSellerName() {
    return sellerName;
  }

  public void setSellerName(String sellerName) {
    this.sellerName = sellerName;
  }
}
