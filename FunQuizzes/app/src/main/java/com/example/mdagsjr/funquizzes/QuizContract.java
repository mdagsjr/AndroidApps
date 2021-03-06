package com.example.mdagsjr.funquizzes;

import android.provider.BaseColumns;

/* Credits given to Code in Flow youtube channel
   playlist Multiple Choice Quiz App with SQLite Integration
   This app created by Michael Dagostino is an adaptation of that app from the youtube videos
   Link: https://www.youtube.com/watch?v=PiCZQg4mhBQ&list=PLrnPJCHvNZuDCyg4Usq2gHMzz6_CiyQO7
 */

public final class QuizContract {

    private QuizContract() {}

    public static class QuestionsTable implements BaseColumns{
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4 = "option4";
        public static final String COLUMN_ANSWER_NUM = "answer_num";
        public static final String COLUMN_CATEGORY = "category";

    }
}
