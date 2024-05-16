package com.example.firstproject.repository;

import com.example.firstproject.entity.Members;
import org.springframework.data.repository.CrudRepository;

public interface MembersRepository extends CrudRepository<Members, Long> {
}
