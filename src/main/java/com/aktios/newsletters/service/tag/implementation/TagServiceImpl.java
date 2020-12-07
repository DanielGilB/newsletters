package com.aktios.newsletters.service.tag.implementation;

import com.aktios.newsletters.model.entity.Tag;
import com.aktios.newsletters.repository.tag.TagRepository;
import com.aktios.newsletters.service.tag.TagService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

  private TagRepository tagRepository;

  public TagServiceImpl(TagRepository tagRepository) {
    this.tagRepository = tagRepository;
  }

  @Override
  public Optional<Tag> findByName(String name) {
    return this.tagRepository.findByName(name);
  }
}
