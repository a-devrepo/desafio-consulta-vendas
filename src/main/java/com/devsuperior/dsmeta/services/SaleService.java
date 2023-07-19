package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleSellerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

  @Autowired private SaleRepository repository;

  public SaleMinDTO findById(Long id) {
    Optional<Sale> result = repository.findById(id);
    Sale entity = result.get();
    return new SaleMinDTO(entity);
  }

  public Page<SaleSellerDTO> findAll(String minDate, String maxDate, String name, Pageable pageable) {
    LocalDate ini;
    LocalDate end;
    if (maxDate.isBlank()) {
      end = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
    } else {
      end = LocalDate.parse(maxDate);
    }
    if (minDate.isBlank()) {
      ini = end.minusYears(1L);
    } else {
      ini = LocalDate.parse(minDate);
    }
    Page<Sale> result = repository.findAll(ini, end, name,pageable);
    return result.map(x -> new SaleSellerDTO(x));
  }
}
