package com.example.mvcquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    //WHAT IS MVC ARCHITECTURE?
    //When we develop our Android app we need to follow certain rules that perhaps are not necessarily written
    //in stone meaning that if you don't do this then your application is not going to work.
    //It's not like that.
    //It's more of guides that allows us to create applications are scalable applications that are easy to
    //follow.
    //Meaning that if you are working with a team it's easier for all of you understand where things are where
    //the classes are and how the whole project is supposed to work.
    //So it's more of a guidance or guidelines that we should strive to follow.
    //But the architecture of Android development itself follows this model view controller architecture.
    //What that means is the following as we look at the structure of our application our building.
    //We noticed a very common thread here so the thread is that we have our main activity and we have our
    //layout.
    //So we talked about earlier that an Android the views or the user interface everything that users able
    //to interact with should be separate from the actual code.
    //Right.
    //In this case the Java code that we write to interface with the user interface but going further we actually
    //divide our code into three layers so the layers that we've looked at is the main activities.
    //That's what we call a controller controller because it is indeed the focus point that allows us to connect
    //everything else around so it is instead of main activity that we are able to fetch.
    //For instance the layout the Image view text view buttons whatever it's inside of our user interface
    //right.
    //And then we were able to write the code which will control our application.
    //So when users tap on the screen we want something to happen to control it takes care of all of that.
    //So the other level of our model view controller architecture here we haven't really looked at which
    //we will in the next video is the model.
    //What is the model the model is the data part of our application.
    //So in this case here our quiz will need to have some sort of data.
    //In this case will be a question.
    //So we separate this three layers model controller in view.
    //In this case just layouts.
    //So we want to make sure that each layer is responsible uniquely for one thing right now.
    //Is it possible for you to create everything in man activity.
    //Of course it is but is it scalable.
    //No it's not.
    //So this is what a gold model view controller architecture.
    //So it allows us to set up our application in such a way that any time that we want to change one layer
    //it's not really going to affect the other layer.
    //So in this case here in our model which is going to be a question class which we'll use then instead
    //of our main activity in this case controller we can change separately.
    //Somebody can create another class or add more properties in our question model the main activity doesn't
    //have to know anything about the model question just has to be able to instantiate the questions and
    //start adding them into our view.
    //All right.
    //So let's go ahead and go and create our model so we can start seeing all these three pieces of our model
    //view controller architecture working together.







    //Think of an interface class as a class that doesn't really need to be related to any other class in
    //terms of inheritance.
    //Meaning in Java we are not allowed to have multiple inheritance.
    //Meaning you cannot have a class that has two or more parents class.
    //But through interfaces through implementation you can actually bypass this limitation that we have with
    //Java not being able to inherit from two classes.
    //We can overlook that by using interfaces good interfaces are just classes that are a mold of something
    //that anybody can use but they don't have to necessarily inherit from it.
    //We have implemented it here because a notice extends App compact but then I can implement these on click
    //listener which says that now this whole class here will be able to listen to click button or click of some sort.

    private Button falseButton, trueButton;
    private ImageButton nextButton;
    private TextView textView;
    private int currentQuestionIndex = 0;


    //We are going to use Array of Objects of class Question
    //1st type of Array Declaration:       int[] array = new int[] {12,13,14,15,16};
    //2nd type of Array Declaration:       int[] array = {12,13,14,15,16};
    //we have used 1st type
    private Question[] questionsBank = new Question[]
            {
                    new Question(R.string.question_amendments, false), //correct: 27
                    new Question(R.string.question_constitution, true),
                    new Question(R.string.question_declaration, true),
                    new Question(R.string.question_independence_rights, true),
                    new Question(R.string.question_religion, true),
                    new Question(R.string.question_government, false),
                    new Question(R.string.question_government_feds, false),
                    new Question(R.string.question_government_senators, true)
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        falseButton = findViewById(R.id.falseButton);
        trueButton = findViewById(R.id.trueButton);
        nextButton = findViewById(R.id.nextButton);
        textView = findViewById(R.id.answerTextView);


//        We can do the on click listen thing for the button as shown below, But this method is not that much effective.
//        falseButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        trueButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


        //The below method is good because it doesn't involve separate declarations of onClick() method for separate buttons
        //all we have to do is to register our button to view.onClickListener

//      below two lines of code means that we are registering our buttons to view.onClickListener which is referred to as "this" in parenthesis, so that when someone clicks them, it get redirected to itself's view.onClickListener
//      and in turn it call onClick() method for that respective view v
        falseButton.setOnClickListener(this); //the parameter required here is an onClickListener interface, but we have passed "this" which is a reference to our mainActivity, because our mainActivity class has implemented the view.onClickListener interface
        trueButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);




    }


    //onClick is a method of interface onClickListener

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.falseButton:
                checkAnswer(false);
                break;

            case R.id.trueButton:
                checkAnswer(true);
                break;

            case R.id.nextButton:
                currentQuestionIndex = (currentQuestionIndex+1) % questionsBank.length; //so that  we never  exceed the array length and we go to start index again when we reach to the end
                textView.setText(questionsBank[currentQuestionIndex].getAnswerResId());
                break;
        }

    }

    private void checkAnswer(boolean userChooseCorrect)
    {
        boolean isAnswerTrue = questionsBank[currentQuestionIndex].isAnswerTrue();
        int toastMessageId = 0;

        if(userChooseCorrect == isAnswerTrue)
        {
            toastMessageId = R.string.correct_answer;
        }
        else
        {
            toastMessageId = R.string.wrong_answer;
        }

        Toast.makeText(MainActivity.this, toastMessageId, Toast.LENGTH_SHORT).show();
    }
}