package com.example.garima.quizapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.button;

public class MainActivity extends AppCompatActivity  {
    private ArrayList<QuestionModel> questionModelsList = new ArrayList<>();
    private TextView questionNumberTextView;
    private TextView questionTextView;
    private TextView previousTextView;
    private TextView nextTextView;
    private Button choiceButton1;
    private Button choiceButton2;
    private Button choiceButton3;
    private Button choiceButton4;
    private RadioGroup radioGroup;
    private  int size;
    private int count=0 ;
    private QuestionModel questionModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createData();
        getLayoutsId();
        size = questionModelsList.size();
        questionModel= questionModelsList.get(count);
        updateValue(questionModel);
        setClickListener();

    }

    private void createData() {
        QuestionModel questionModel;
        questionModel = new QuestionModel();
        questionModel.setQuestion("How to get a response from an activity in Android?");
        questionModel.setChoice1("startActivityToResult()");
        questionModel.setChoice2("startActivityForResult()");
        questionModel.setChoice3("Bundle()");
        questionModel.setChoice4("None");
        questionModelsList.add(questionModel);

        questionModel = new QuestionModel();
        questionModel.setQuestion("How many sizes are supported by Android?");
        questionModel.setChoice1("Android supported all sizes");
        questionModel.setChoice2("Android does not support all sizes");
        questionModel.setChoice3("Android supports small,normal, large and extra-large sizes");
        questionModel.setChoice4("Size is undefined in android");
        questionModelsList.add(questionModel);

        questionModel = new QuestionModel();
        questionModel.setQuestion("What is the difference between services and thread in android?");
        questionModel.setChoice1("Services performs functionalities in the background. By default services run on main thread only");
        questionModel.setChoice2("Thread and services are having same functionalities.");
        questionModel.setChoice3("Thread works on services");
        questionModel.setChoice4(" None of the above");
        questionModelsList.add(questionModel);

        questionModel = new QuestionModel();
        questionModel.setQuestion(" What is the difference between content values and cursor in android SQlite?");
        questionModel.setChoice1("Content values are key pair values, which are updated or inserted in the database");
        questionModel.setChoice2("Cursor is used to store the temporary result.");
        questionModel.setChoice3("A & B");
        questionModel.setChoice4("Cursor is used to store data permanently.");
        questionModelsList.add(questionModel);

        questionModel = new QuestionModel();
        questionModel.setQuestion("Which features are considered while creating android application?");
        questionModel.setChoice1("Screen Size");
        questionModel.setChoice2(" Input configuration");
        questionModel.setChoice3(" Platform Version");
        questionModel.setChoice4("All of above");
        questionModelsList.add(questionModel);


    }

    private void getLayoutsId() {
        questionNumberTextView = (TextView) findViewById(R.id.questionNumberTextView);
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        previousTextView = (TextView) findViewById(R.id.previousTextView);
        nextTextView = (TextView) findViewById(R.id.nextTextView);
        choiceButton1=(Button)findViewById(R.id.choiceButton1);
        choiceButton2=(Button)findViewById(R.id.choiceButton2);
        choiceButton3=(Button)findViewById(R.id.choiceButton3);
        choiceButton4=(Button)findViewById(R.id.choiceButton4);
        clearBackground();




    }
    private void setClickListener()
    {
        View.OnClickListener  clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();
                switch (id)
                {
                    case R.id.previousTextView:
                    {
                        if(count>0)
                        {

                            count--;
                           questionModel = questionModelsList.get(count);
                            setPreviousSelectedChoice();
                            updateValue(questionModel);


                        }
                        else {
                           previousTextView.setEnabled(false);

                        }
                    }

                        break;
                    case R.id.nextTextView:
                        clearBackground();
                        if(count<size-1)
                        {
                            count++;
                            questionModel = questionModelsList.get(count);
                            updateValue(questionModel);
                            previousTextView.setEnabled(true);
                        }
                        else
                        {

                           showDialog();
                        }
                        break;
                    case R.id.choiceButton1:
                        choiceButton1.setBackgroundDrawable(getResources().getDrawable(R.color.choiceColor));
                        choiceButton2.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
                        choiceButton3.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
                        choiceButton4.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
                        questionModel.setUserAnswer(choiceButton1.getText().toString());
                        questionModel.setUserChoice(1);
                        questionModelsList.set(count,questionModel);
                        break;
                    case R.id.choiceButton2:
                        choiceButton2.setBackgroundDrawable(getResources().getDrawable(R.color.choiceColor));
                        choiceButton1.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
                        choiceButton3.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
                        choiceButton4.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
                        questionModel.setUserAnswer(choiceButton2.getText().toString());
                        questionModel.setUserChoice(2);
                        questionModelsList.set(count,questionModel);

                        break;
                    case R.id.choiceButton3:
                        choiceButton3.setBackgroundDrawable(getResources().getDrawable(R.color.choiceColor));
                        choiceButton2.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
                        choiceButton4.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
                        choiceButton1.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
                        questionModel.setUserAnswer(choiceButton3.getText().toString());
                        questionModel.setUserChoice(3);
                        questionModelsList.set(count,questionModel);

                        break;
                    case R.id.choiceButton4:
                        choiceButton4.setBackgroundDrawable(getResources().getDrawable(R.color.choiceColor));
                        choiceButton2.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
                        choiceButton3.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
                        choiceButton1.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
                        questionModel.setUserAnswer(choiceButton4.getText().toString());
                        questionModel.setUserChoice(4);
                        questionModelsList.set(count,questionModel);


                        break;
                }

            }
        };
        previousTextView.setOnClickListener(clickListener);
        nextTextView.setOnClickListener(clickListener);
        choiceButton1.setOnClickListener(clickListener);
        choiceButton2.setOnClickListener(clickListener);
        choiceButton3.setOnClickListener(clickListener);
        choiceButton4.setOnClickListener(clickListener);
    }
    private void updateValue(QuestionModel questionModel)
    {
        int selectedQuestion = count;
        questionNumberTextView.setText(selectedQuestion+1+ "/"+size);
        questionTextView.setText(questionModel.getQuestion());
        choiceButton1.setText(questionModel.getChoice1());
        choiceButton2.setText(questionModel.getChoice2());
        choiceButton3.setText(questionModel.getChoice3());
        choiceButton4.setText(questionModel.getChoice4());

    }
    private void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setMessage("Your score is "+5 +"Do You Want Play Again");
        alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    private void clearBackground()
    {
        choiceButton1.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
        choiceButton2.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
        choiceButton3.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
        choiceButton4.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));

    }
    private  void setPreviousSelectedChoice()
    {
        int selectedChoice = questionModel.getUserChoice();
        switch (selectedChoice)
        {
            case 1:
                choiceButton1.setBackgroundDrawable(getResources().getDrawable(R.color.choiceColor));
                choiceButton2.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
                choiceButton3.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
                choiceButton4.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
                questionModel.setUserAnswer(choiceButton1.getText().toString());
                questionModel.setUserChoice(1);
                questionModelsList.set(count,questionModel);

                break;
            case 2:
                choiceButton2.setBackgroundDrawable(getResources().getDrawable(R.color.choiceColor));
                choiceButton1.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
                choiceButton3.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
                choiceButton4.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
                questionModel.setUserAnswer(choiceButton2.getText().toString());
                questionModel.setUserChoice(2);
                questionModelsList.set(count,questionModel);
                break;
            case 3:
                choiceButton3.setBackgroundDrawable(getResources().getDrawable(R.color.choiceColor));
                choiceButton2.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
                choiceButton4.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
                choiceButton1.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
                questionModel.setUserAnswer(choiceButton3.getText().toString());
                questionModel.setUserChoice(3);
                questionModelsList.set(count,questionModel);
                break;
            case 4:
                choiceButton4.setBackgroundDrawable(getResources().getDrawable(R.color.choiceColor));
                choiceButton2.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
                choiceButton3.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
                choiceButton1.setBackgroundDrawable(getResources().getDrawable(android.R.color.darker_gray));
                questionModel.setUserAnswer(choiceButton4.getText().toString());
                questionModel.setUserChoice(4);
                questionModelsList.set(count,questionModel);
                break;
            default:
                clearBackground();
                break;

        }

    }
 }




