package com.paycraftsol.wc.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

import com.paycraftsol.wc.Model.CountResult;

@Service
public class WcService {

    public CountResult countText(InputStreamReader inputStreamReader){
        BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
        String line;
        int lines=0;
        int words=0;
        int characters=0;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                lines++;
                characters += line.length() + 1; // +1 for the newline character
                words += line.split("\\s+").length; // Count words
            }
        } catch (IOException e) {
            // Handle the exception, e.g., log it or rethrow as a runtime exception
            throw new RuntimeException("Error reading input stream", e);
        }

        return new CountResult(lines, words, characters);
    }
    
}
