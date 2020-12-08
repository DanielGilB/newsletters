package com.aktios.newsletters.unit.service;

import com.aktios.newsletters.Utils;
import com.aktios.newsletters.model.entity.Tag;
import com.aktios.newsletters.repository.tag.TagRepository;
import com.aktios.newsletters.service.tag.TagService;
import com.aktios.newsletters.service.tag.implementation.TagServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class TagServiceTest {

  @Mock private TagRepository tagRepository;

  @InjectMocks private TagService tagService = new TagServiceImpl(tagRepository);

  @Test
  void givenExistingTag_whenFindByName_shouldReturnTag() {
    Tag tag = Utils.fakeTag();

    when(tagRepository.findByName(tag.getName())).thenReturn(Optional.of(tag));
    tagService.findByName(tag.getName());

    verify(tagRepository, times(1)).findByName(tag.getName());
  }

  @Test
  void givenNotExistingTag_whenFindByName_shouldReturnEmpty() {
    Tag tag = Utils.fakeTag();
    String unexistingTagName = "fake";

    when(tagRepository.findByName(unexistingTagName)).thenReturn(Optional.empty());
    tagService.findByName(unexistingTagName);

    verify(tagRepository, times(1)).findByName(unexistingTagName);
  }
}
