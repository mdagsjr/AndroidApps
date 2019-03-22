package com.example.mdagsjr.funquizzes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/* Credits given to Code in Flow youtube channel
   playlist Multiple Choice Quiz App with SQLite Integration
   This app created by Michael Dagostino is an adaptation of that app from the youtube videos
   Link: https://www.youtube.com/watch?v=PiCZQg4mhBQ&list=PLrnPJCHvNZuDCyg4Usq2gHMzz6_CiyQO7
 */

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="FunQuizzes.db";
    private static final int DATABASE_VERSION = 13;

    private SQLiteDatabase dataBase;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.dataBase=sqLiteDatabase;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_ANSWER_NUM + " INTEGER" +
                ")";

        dataBase.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        dataBase.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    private void fillQuestionsTable()
    {

        Question question1 = new Question("What does HTML stand for?", "Hugetext Markup Language", "Hyperlinktext Markup Language","Hypertext Markup Language", "None of the above",3);
        addQuestion(question1);
        Question question2 = new Question("Who won the 2017 World Series?", "Astros", "Yankees","Indians", "Dodgers",1);
        addQuestion(question2);
        Question question3 = new Question("Who created HTML?", "Alan Turing", "Linus Torvalds","Edsger Dijkstra", "Tim Berners Lee",4);
        addQuestion(question3);
        Question question4 = new Question("Who won the 2018 Super Bowl?", "Steelers", "Eagles","Cowboys", "Patriots",2);
        addQuestion(question4);
        Question question5 = new Question("What is Java?", "A programing Language", "Coffee","A Country", "Nothing I care about",1);
        addQuestion(question5);
        Question question6 = new Question("What city the Home of Kelloggs?", "Battle Creek MI", "New York NY","Boston MA", "I don't give a crap",1);
        addQuestion(question6);
        Question question7 = new Question("What is the worst case efficiency of QuickSort ?",
                "logn", "nlogn","1", "n^2",4);
        addQuestion(question7);
        Question question8 = new Question("What is the best sorting algorithms to use when you care about efficiency ?",
                "QuickSort", "Insertion Sort","Heap Sort", "Bubble Sort",3);
        addQuestion(question8);
        Question question9 = new Question("What does CPU stand for ?",
                "Central Power Unit", "Central Processing Unit","Computer", "None of the Above",2);
        addQuestion(question9);

        Question question10 = new Question("How many World Series Championships have the Yankees won ?",
                "25", "24","26", "27",4);
        addQuestion(question10);

        Question question11 = new Question("How many states does the USA have ?",
                "51", "50","13", "49",2);
        addQuestion(question11);

        Question question12 = new Question("What are the  three different names for towns in the USA ?",
                "Town, Township, Parish ", "Town, City, Suburb","No Clue", "This Question is stupid",1);
        addQuestion(question12);




    }

    private void addQuestion(Question question)
    {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NUM, question.getAnswerNum());
        //cv.put(QuizContract.QuestionsTable.COLUMN_CATEGORY, question.getCategory());
        dataBase.insert(QuizContract.QuestionsTable.TABLE_NAME,null, cv);
    }

    public ArrayList<Question>getAllQuestions()
    {
        ArrayList<Question> questionList = new ArrayList<>();
        dataBase = getReadableDatabase();
        Cursor c = dataBase.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME, null);

        if(c.moveToFirst())
        {
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNum(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NUM)));
                //question.setCategory(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_CATEGORY)));
                questionList.add(question);

            }while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
    /*
    public ArrayList<Question>getQuestions(String category)
    {
        ArrayList<Question> questionList = new ArrayList<>();
        dataBase = getReadableDatabase();

        String[] selectionArgs = new String[]{category};
        Cursor c = dataBase.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME +
                " WHERE " + QuizContract.QuestionsTable.COLUMN_CATEGORY + " = ?", selectionArgs);

        if(c.moveToFirst())
        {
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION4)));
                question.setCategory(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_CATEGORY)));

                questionList.add(question);

            }while (c.moveToNext());
        }
        c.close();
        return questionList;
        */
    }

