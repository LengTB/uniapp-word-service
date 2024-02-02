package top.tobycold.wordservice.service;

import top.tobycold.wordservice.entity.StudyWordEntity;
import top.tobycold.wordservice.entity.WordEntity;

import java.util.List;
import java.util.Set;

public interface WordService {
    Set<StudyWordEntity> getGroupWord();

    boolean pushWord(String word);
}
