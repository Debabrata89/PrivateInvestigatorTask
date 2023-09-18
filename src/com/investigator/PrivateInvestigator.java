package com.investigator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;



public class PrivateInvestigator {

	public static void main(String[] args) throws IOException {
		// Read the input file.
        String inputFilePath = "input.txt";
        BufferedReader inputFile = new BufferedReader(new FileReader(inputFilePath));

        // Create a list to store the sentences.
        List<String> sentences = new ArrayList<>();

        // Read each sentence from the input file and add it to the list.
        // Group the sentences based on single changed word
        // Time complexity : Asumming each sentence generate k subsentence time complexity is : nk
        String line;
        Map<String, RelatedSentencesAndChangedWords> patternSentences = new HashMap<>();
        while ((line = inputFile.readLine()) != null) {
        	groupSimilarSentence(patternSentences,line);
            sentences.add(line);
        }
        Map<String,RelatedSentencesAndChangedWords> finalMap = getFilterdMap(patternSentences);
        // Ouput the filtered result to output.txt file
        writeResultToFile(finalMap);
        

	}
	/*
	 * Method for ouputting the final filtered map to output file
	 *
	 */
	private static void writeResultToFile(Map<String,RelatedSentencesAndChangedWords> map) throws IOException {
		String outputFilePath = "output.txt";
        FileWriter outputFile = new FileWriter(outputFilePath);
        for (Map.Entry<String,RelatedSentencesAndChangedWords> entry : map.entrySet()) {
        	RelatedSentencesAndChangedWords rS = entry.getValue();
        	for(String line : rS.getLines()) {
        		outputFile.write(line + "\n");
        	}
        	String changingWords = String.join(",",rS.getDiffWords());
        	outputFile.write("The changing word was: "+changingWords);
        	outputFile.write("\n");
           
        }
        outputFile.close();
        
		
	}
	/*
	 * Method for getFinal filter Map. If lines size in map is greater than one we collect all those result in final map
	 *
	 */

	 private static Map<String, RelatedSentencesAndChangedWords> getFilterdMap(Map<String, RelatedSentencesAndChangedWords> patternSentences){
	        Map<String,RelatedSentencesAndChangedWords> finalMap = new HashMap<>();
	        for (Map.Entry<String,RelatedSentencesAndChangedWords> entry : patternSentences.entrySet()) {
	            if (entry.getValue().getLines().size() > 1) {
	                finalMap.put(entry.getKey(), entry.getValue());
	            }
	        }
	        return finalMap;
	    }
	 /**
	  *  
	  *    Generate map key by removing each word and group together if key matches iterating over each line
	  */
	 private static void groupSimilarSentence(Map<String, RelatedSentencesAndChangedWords> patternSentences, String line) {
	        String[] words = line.split(" ");  
	        words =  Arrays.copyOfRange(words, 2, words.length);
	        for (int i = 0; i < words.length; i++) {
	            String[] prefix = Arrays.copyOfRange(words, 0, i);
	            String[] sufix = Arrays.copyOfRange(words, i+1, words.length);
	            StringJoiner sj = new StringJoiner(" ");
	            String prefixSentence = String.join(" ",prefix);
	            String sufixSentence = String.join(" ", sufix);
	            sj.add(prefixSentence).add(sufixSentence);
	            String newSentence = sj.toString().trim();
          
	            if(patternSentences.containsKey(newSentence)){
	                RelatedSentencesAndChangedWords matchingObject = patternSentences.get(newSentence);
	                matchingObject.addToDiffWords(words[i]);
	                matchingObject.addToLines(line);
	            }else{
	                patternSentences.put(newSentence,new RelatedSentencesAndChangedWords(line, words[i]));
	            }
	        }
	    }

}
