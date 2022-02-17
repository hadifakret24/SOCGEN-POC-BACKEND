package com.socgen.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socgen.dto.Transfer;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {

}
