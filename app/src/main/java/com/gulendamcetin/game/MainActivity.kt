package com.gulendamcetin.game

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.gulendamcetin.game.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var score=0
    var runnable=Runnable{}
    var handler=Handler(Looper.getMainLooper())

    var imageArray= ArrayList<ImageView>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        imageArray.add(binding.imageView2)
        imageArray.add(binding.imageView3)
        imageArray.add(binding.imageView4)
        imageArray.add(binding.imageView5)
        imageArray.add(binding.imageView6)
        imageArray.add(binding.imageView7)
        imageArray.add(binding.imageView8)
        imageArray.add(binding.imageView9)
        imageArray.add(binding.imageView10)
hideImages()
        object:CountDownTimer(15500,1000){
            override fun onTick(p0: Long) {
                binding.time.text="Time: ${p0/1000}"
            }
            override fun onFinish() {
                binding.time.text = "Time:0"
                handler.removeCallbacks(runnable)
                for (image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("GAME OVER")
                alert.setMessage("Restart the game?")
                alert.setPositiveButton(
                    "yes",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                 val intent1=intent
                        finish()
                        startActivity(intent1)
                    })
                alert.setNegativeButton(
                    "No",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        Toast.makeText(this@MainActivity, "GAME OVER", Toast.LENGTH_LONG).show()
                    })
                alert.show()
            }
        }.start()
    }
    fun hideImages(){
        runnable=object:Runnable{
            override fun run() {
                for(image in imageArray){
                    image.visibility=View.INVISIBLE
                }
                val random=Random()
                val randomIndex=random.nextInt(9)
                imageArray[randomIndex].visibility=View.VISIBLE

                handler.postDelayed(runnable,500)
            }
            }
handler.post(runnable)
        }

    fun tweety(view: View){
        score=score+1
        binding.score.text="Score:${score}"

    }
}