package com.example.bitlab_spring1_task2.Repository;

import com.example.bitlab_spring1_task2.DBconnection.ApplicationRequest;
import com.example.bitlab_spring1_task2.controller.HomeController;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationRequest,Long> {
    @Query("SELECT ar FROM  ApplicationRequest ar WHERE ar.handled = ?1")
    List<ApplicationRequest> findByHandled(boolean handled);
}
