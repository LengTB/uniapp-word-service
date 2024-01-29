package top.tobycold.wordservice.service;

import top.tobycold.wordservice.dto.WordDTO;
import top.tobycold.wordservice.entity.WordEntity;

import java.util.List;

public interface WordService {
    List<WordEntity> getByGroup(WordDTO wordDTO);
}
