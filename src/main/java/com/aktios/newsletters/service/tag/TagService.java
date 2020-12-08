package com.aktios.newsletters.service.tag;

import com.aktios.newsletters.model.entity.Tag;

import java.util.Set;

public interface TagService {

  /**
   * Find Tags by names
   *
   * @param names
   * @return
   */
  Set<Tag> findByNames(Set<String> names);
}
