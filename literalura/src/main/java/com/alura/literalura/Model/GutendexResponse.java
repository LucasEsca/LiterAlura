package com.alura.literalura.Model;

import java.util.List;

public class GutendexResponse {
    private int count;
    private String next;
    private String previous;
    private List<BookFact> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<BookFact> getResults() {
        return results;
    }

    public void setResults(List<BookFact> results) {
        this.results = results;
    }
}
