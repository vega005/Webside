package com.example.Projekt;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GameRepo extends CrudRepository<GameItem, Long> {

    List<GameItem> findByType(Type type);

}
