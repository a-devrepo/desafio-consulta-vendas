package com.devsuperior.dsmeta.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {
  @Query(
      "select obj "
          + "from Sale obj "
          + "where obj.date between :initialDate and :endDate "
          + "and upper(obj.seller.name) like upper(concat('%',:name,'%')) ")
  Page<Sale> findAll(
      LocalDate initialDate, LocalDate endDate, String name, Pageable pageable);
}
