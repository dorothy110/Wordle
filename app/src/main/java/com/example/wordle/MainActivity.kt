package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.view.View
import android.graphics.Color
import android.widget.Toast

val wordToGuess = FourLetterWordList.getRandomFourLetterWord()
//val wordToGuess = "TEST"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val submitGuess = findViewById<Button>(R.id.submitGuess)
        val guessEntered = findViewById<EditText>(R.id.guessEntered)
        val endMessage = findViewById<TextView>(R.id.endMessage)

        val correct = findViewById<TextView>(R.id.correctAnswer)
        correct.text = wordToGuess

        var guessText = findViewById<TextView>(R.id.textView)
        var guessCheck = findViewById<TextView>(R.id.textView1)
        var counter = 1

        submitGuess.setOnClickListener{
            val guess = guessEntered.text.toString() //to sting, otherwise the next line hard crashes and comparisons don't work
            guessEntered.text.clear()
            //Toast.makeText(it.context, guess, Toast.LENGTH_SHORT).show()
            val guessResult = checkGuess(guess)
            when (counter) {
                1 -> {
                    counter++

                    guessText.visibility = View.VISIBLE //makes the "Guess 1" text visible
                    guessText = findViewById(R.id.guess1) //sets the var to the guess display on the right-hand side
                    guessText.text = guess //sets the text to the correct value

                    //same as above but for the next line down
                    guessCheck.visibility = View.VISIBLE
                    guessCheck = findViewById(R.id.guessCheck1)
                    guessCheck.text = guessResult

                    //sets the right-hand side fields visible
                    guessText.visibility = View.VISIBLE
                    guessCheck.visibility = View.VISIBLE

                    //moves to the next set of guess and guess checks
                    guessText = findViewById(R.id.textView2)
                    guessCheck = findViewById(R.id.textView3)

                    if(guess == wordToGuess){
                        //disable and grey-out the button
                        submitGuess.isEnabled = false
                        submitGuess.isClickable = false
                        submitGuess.setBackgroundColor(Color.GRAY)

                        correct.visibility = View.VISIBLE //display the correct word
                        endMessage.text = "Congratulations!" //modify the text to a victory message...
                        endMessage.visibility = View.VISIBLE //...and make it visible
                    }
                }
                2 -> {
                    counter++
                    //the code here is basically the same as the code in the "1" block
                    guessText.visibility = View.VISIBLE
                    guessText = findViewById(R.id.guess2)
                    guessText.text = guess

                    guessCheck.visibility = View.VISIBLE
                    guessCheck = findViewById(R.id.guessCheck2)
                    guessCheck.text = guessResult

                    guessText.visibility = View.VISIBLE
                    guessCheck.visibility = View.VISIBLE

                    guessText = findViewById(R.id.textView4)
                    guessCheck = findViewById(R.id.textView5)
                    if(guess == wordToGuess){
                        submitGuess.isEnabled = false
                        submitGuess.isClickable = false
                        submitGuess.setBackgroundColor(Color.GRAY)

                        correct.visibility = View.VISIBLE
                        endMessage.text = "Congratulations!"
                        endMessage.visibility = View.VISIBLE
                    }
                }
                3 -> {
                    //code here is mostly the same as above
                    guessText.visibility = View.VISIBLE
                    guessText = findViewById(R.id.guess3)
                    guessText.text = guess

                    guessCheck.visibility = View.VISIBLE
                    guessCheck = findViewById(R.id.guessCheck3)
                    guessCheck.text = guessResult

                    guessText.visibility = View.VISIBLE
                    guessCheck.visibility = View.VISIBLE

                    //always disable the button, as there are no more guesses
                    submitGuess.isEnabled = false
                    submitGuess.isClickable = false
                    submitGuess.setBackgroundColor(Color.GRAY)

                    //ditto
                    correct.visibility = View.VISIBLE
                    if(guess == wordToGuess){
                        endMessage.text = "Congratulations!"
                        endMessage.visibility = View.VISIBLE
                    }
                    else{
                        endMessage.visibility = View.VISIBLE //if you never guessed right, no need to edit the message
                    }
                }
            }
        }
    }



    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     */
    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) { //for each letter in guess, add O, +, or X, depending on its accuracy
            if (guess[i] == wordToGuess[i]) {
                result += "O" //right letter, right place
            }
            else if (guess[i] in wordToGuess) {
                result += "+" //right letter, wrong place
            }
            else {
                result += "X" //wrong letter
            }
        }
        return result
    }
}