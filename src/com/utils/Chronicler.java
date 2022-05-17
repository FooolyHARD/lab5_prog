package com.utils;

public class Chronicler {
    private String[] history;
     public Chronicler(){
         this.history = new String[13];
     }

    public String[] getHistory() {
        return history;
    }
    public void addInHistory(String newCommand){
         for (int i = 0; i<12;i++){
             history[i] = history[i+1];
         }
         history[12] = newCommand;
    }
}
