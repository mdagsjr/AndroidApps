package com.example.mdagsjr.funquizzes;

import android.os.Parcel;
import android.os.Parcelable;

/* Credits given to Code in Flow youtube channel
   playlist Multiple Choice Quiz App with SQLite Integration
   This app created by Michael Dagostino is an adaptation of that app from the youtube videos
   Link: https://www.youtube.com/watch?v=PiCZQg4mhBQ&list=PLrnPJCHvNZuDCyg4Usq2gHMzz6_CiyQO7
 */

public class Question implements Parcelable{

   // public static final String CATEGORY_COMPUTERS = "Computers";
   // public static final String CATEGORY_SPORTS = "Sports";
    //public static final String CATEGORY_FACTS = "Facts";


    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int answerNum;
    //private String category;

    public Question(){

    }

    public Question(String question, String option1, String option2, String option3, String option4, int answerNum) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answerNum = answerNum;
        //this.category = category;
    }

    protected Question(Parcel in) {
        question = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        option4 = in.readString();
        answerNum = in.readInt();
       // category = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(option1);
        dest.writeString(option2);
        dest.writeString(option3);
        dest.writeString(option4);
        dest.writeInt(answerNum);
        //dest.writeString(category);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public int getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(int answerNum) {
        this.answerNum = answerNum;
    }
/*
    public String getCategory() {
        return category;
    }
*/
/*
    public void setCategory(String category) {
        this.category = category;
    }
    */
/*
    public static String[] getAllCategories()
    {
        return new String[]{
                CATEGORY_COMPUTERS,
                CATEGORY_SPORTS,
                CATEGORY_FACTS
        };
    }
    */
}
