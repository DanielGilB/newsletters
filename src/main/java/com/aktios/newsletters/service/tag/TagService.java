package com.aktios.newsletters.service.tag;

import com.aktios.newsletters.model.entity.Tag;

import java.util.Optional;

public interface TagService {

  /**
   * Find Tag by name
   *
   * @param name
   * @return
   */
  Optional<Tag> findByName(String name);
}
