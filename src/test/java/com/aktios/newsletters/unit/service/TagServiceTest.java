package com.aktios.newsletters.unit.service;

import com.aktios.newsletters.Utils;
import com.aktios.newsletters.model.entity.Tag;
import com.aktios.newsletters.repository.tag.TagRepository;
import com.aktios.newsletters.service.tag.TagService;
import com.aktios.newsletters.service.tag.implementation.TagServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

@SpringBootTest
public class TagServiceTest {

  @Mock private TagRepository tagRepository;

  @InjectMocks private TagService tagService = new TagServiceImpl(tagRepository);

  @Test
  void givenExistingTags_whenFindByNames_shouldReturnTags() {
    Set<Tag> expectedTag = Utils.fakeTags();
    Set<String> tagNames = expectedTag.stream().map(Tag::getName).collect(Collectors.toSet());

    when(tagRepository.findByNameIn(tagNames)).thenReturn(expectedTag);
    Set<Tag> tags = tagService.findByNames(tagNames);

    verify(tagRepository, times(1)).findByNameIn(tagNames);
    Assertions.assertIterableEquals(
        tagNames, tags.stream().map(Tag::getName).collect(Collectors.toSet()));
  }

  @Test
  void givenNotExistingsTag_whenFindByNames_shouldReturnEmpty() {
    Set<String> notExistingTagNames = Set.of("noexistingTag", "anotherNoExistingTag");

    when(tagRepository.findByNameIn(notExistingTagNames)).thenReturn(Set.of());
    Set<Tag> tags = tagService.findByNames(notExistingTagNames);

    verify(tagRepository, times(1)).findByNameIn(notExistingTagNames);
    Assertions.assertIterableEquals(tags, Set.of());
  }
}
