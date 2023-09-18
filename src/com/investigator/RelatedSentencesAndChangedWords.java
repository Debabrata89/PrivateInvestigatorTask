package com.investigator;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class RelatedSentencesAndChangedWords {

    private List<String> lines;
    private Set<String> diffWords;

    public RelatedSentencesAndChangedWords( String line,String diffWord) {
        lines = new ArrayList<>();
        diffWords = new HashSet<>();
        addToLines(line);
        if (!diffWord.isEmpty()){
            addToDiffWords(diffWord);
        }
    }

    public void addToLines(String line){
        this.lines.add(line);
    }

    public void addToDiffWords(String diffWord){
        this.diffWords.add(diffWord);
    }
    public List<String> getLines() {
        return lines;
    }

    public Set<String> getDiffWords() {
        return diffWords;
    }

    private void addLines(List<String> lines) {
        this.lines.addAll(lines);
    }

    private void addDiffWords(Set<String> diffWords){
        this.diffWords.addAll(diffWords);
    }
}
