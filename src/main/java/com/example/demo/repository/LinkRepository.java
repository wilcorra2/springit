package com.example.demo.repository;

import com.example.demo.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository  extends JpaRepository<Link, Long> {

}
