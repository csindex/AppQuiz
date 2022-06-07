package com.example.appquiz.classes;

public class Test {
    int question_image;
    String answer,question;
    String choice1,choice2;

    public Test(int question_image, String answer, String question, String choice1, String choice2) {
        this.question_image = question_image;
        this.answer = answer;
        this.question = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
    }

    public int getQuestion_image() {
        return question_image;
    }

    public void setQuestion_image(int question_image) {
        this.question_image = question_image;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }
}
