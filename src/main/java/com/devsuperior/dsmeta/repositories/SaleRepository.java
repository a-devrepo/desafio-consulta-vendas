package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
  @Query(
      "select obj "
          + "from Sale obj "
          + "where obj.date between :initialDate and :endDate "
          + "and upper(obj.seller.name) like upper(concat('%',:name,'%')) ")
  Page<Sale> findAll(
      LocalDate initialDate, LocalDate endDate, String name, Pageable pageable);

  @Query(
          "select new com.devsuperior.dsmeta.dto.SaleSummaryDTO(obj.seller.name, sum(obj.amount)) "
                  + "from Sale obj "
                  + "where obj.date between :initialDate and :endDate "
                  + "group by obj.seller.name")
  List<SaleSummaryDTO> getSaleSummary(
          LocalDate initialDate, LocalDate endDate);
}
