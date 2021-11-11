package edu.school21.logic.preprocessor;

public class PreProcessorToLowerImpl implements PreProcessor {
    @Override
    public String preProcess(String text) {
        return text.toLowerCase();
    }
}
