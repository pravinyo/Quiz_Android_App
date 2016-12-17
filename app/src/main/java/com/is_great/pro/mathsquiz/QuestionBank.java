package com.is_great.pro.mathsquiz;

/**
 * Created by Pravin on 12/4/2016.
 */

public class QuestionBank {
    private int question; // question id from the resource
    private boolean solution;

    public QuestionBank(int question,boolean solution)
    {
        this.question=question;
        this.solution=solution;
    }
    public int getQuestion()
    {
        return question;
    }
    public void setQuestion(int question)
    {
        this.question=question;
    }
    public boolean isTrueQuestion()
    {
        return solution;
    }
    public void setTrueQuestion(boolean truequestion)
    {
        this.solution=truequestion;
    }
}
