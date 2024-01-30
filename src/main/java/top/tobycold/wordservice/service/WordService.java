package top.tobycold.wordservice.service;

import top.tobycold.wordservice.entity.WordEntity;

import java.util.List;
import java.util.Set;

public interface WordService {
    void initStudyWord();
    Set<WordEntity> getGroupWord();

    boolean pushWord(String word);
}
