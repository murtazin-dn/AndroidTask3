package com.example.androidtask3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.min
import kotlin.random.Random

class MyView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private var counter = 0
    private val paint = Paint()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = 500
        val desiredHeight = 200

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val width = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.AT_MOST -> min(desiredWidth, widthSize)
            else -> desiredWidth
        }

        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.AT_MOST -> min(desiredHeight, heightSize)
            else -> desiredHeight
        }

        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.WHITE)

        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE

        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)

        paint.color = randomColor()
        paint.style = Paint.Style.FILL

        canvas.drawRect(0f, 0f, ((width / 10f) * counter), height.toFloat(), paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                return true
            }
            MotionEvent.ACTION_UP -> {
                if(counter >= 10) counter = 0 else counter++
                invalidate()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

}

fun randomColor() = Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
