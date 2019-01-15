package com.cafe24.exchange.domain.currency;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Optional<Currency> findByNation(String nation);

    Optional<Currency> findByNationAndSource(String k, String source);
}
