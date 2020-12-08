package com.aktios.newsletters.service.tag.implementation;

import com.aktios.newsletters.model.entity.Tag;
import com.aktios.newsletters.repository.tag.TagRepository;
import com.aktios.newsletters.service.tag.TagService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TagServiceImpl implements TagService {

  private TagRepository tagRepository;

  public TagServiceImpl(TagRepository tagRepository) {
    this.tagRepository = tagRepository;
  }

  @Override
  public Set<Tag> findByNames(Set<String> names) {
    return this.tagRepository.findByNameIn(names);
  }
}
