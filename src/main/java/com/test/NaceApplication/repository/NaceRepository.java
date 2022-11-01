package com.test.NaceApplication.repository;

import com.test.NaceApplication.model.Nace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NaceRepository extends JpaRepository<Nace, Integer> {
    //Nace findByOrder(Long order);
}
