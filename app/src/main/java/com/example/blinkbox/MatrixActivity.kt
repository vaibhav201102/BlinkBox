package com.example.blinkbox

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blinkbox.databinding.ActivityMainBinding
import com.example.blinkbox.databinding.ActivityMatrixBinding
import kotlin.math.abs

class MatrixActivity : AppCompatActivity() {

    private var _binding : ActivityMatrixBinding?= null
    private val binding get() = _binding!!
    private var matrixAdapter : MatrixAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMatrixBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val matrixvalue = intent.getIntExtra("Matrixvalue",0)
        println(matrixvalue)
        initMatrix(matrixvalue)
    }

    private fun initMatrix(matrixvalue : Int){
        val layoutManager : RecyclerView.LayoutManager = GridLayoutManager(this@MatrixActivity,matrixvalue)
        val recyclerViewItemList = binding.matrixrv
        recyclerViewItemList.layoutManager = layoutManager
        recyclerViewItemList.itemAnimator = DefaultItemAnimator()

        matrixAdapter = MatrixAdapter(matrixvalue){row,col ->
            onMatrixItemClick(row,col,matrixvalue)
        }

        recyclerViewItemList.adapter = matrixAdapter

    }

    private fun onMatrixItemClick(row : Int, col : Int, matrixvalue : Int){
        val itemcount = binding.matrixrv.childCount

        for (i in 0 until itemcount){
            val view = binding.matrixrv.getChildAt(i)
            val position = binding.matrixrv.getChildAdapterPosition(view)
            val currentRow = position / matrixvalue
            val currentCol = position % matrixvalue

            if (currentRow == row || currentCol == col || abs(currentRow - row) == abs(currentCol - col)) {
                val endColor = ContextCompat.getColor(this,R.color.blue)
                val startColor  = ContextCompat.getColor(this,R.color.yellow)

                val colorAnimator = ValueAnimator.ofObject(ArgbEvaluator(),startColor,endColor).apply {
                    duration = 200
                    addUpdateListener {
                            animator -> view.setBackgroundColor(animator.animatedValue as Int)
                    }
                }
                val reverseColorAnimator = ValueAnimator.ofObject(ArgbEvaluator(),endColor,startColor).apply {
                    duration = 200
                    addUpdateListener {
                            animator -> view.setBackgroundColor(animator.animatedValue as Int)
                    }
                }

                colorAnimator.start()
                colorAnimator.addListener(object : AnimatorListenerAdapter(){
                    override fun onAnimationEnd(animation: Animator) {
                        reverseColorAnimator.start()
                    }
                })

            }
        }
    }

}