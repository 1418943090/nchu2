package com.love.nchu.repository;

import com.love.nchu.demain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product,Integer> {



    @Query(value = "select p from Product p order by p.position ,p.date desc")
    List<Product> getAllByPositionAndDate();

    @Modifying
    @Query("update Product  p set p.position = 4")
    void productSetInit();


    @Modifying
    @Query("update Product  p set p.position=?1 where p.id=?2")
    void updatePosition(Integer position,Integer id);

}
