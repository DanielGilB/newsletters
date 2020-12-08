package com.aktios.newsletters.repository.tag;

import com.aktios.newsletters.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    Set<Tag> findByNameIn(Set<String> names);
}
