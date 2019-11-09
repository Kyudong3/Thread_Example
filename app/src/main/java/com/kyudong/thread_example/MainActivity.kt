package com.kyudong.thread_example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var mainNum = 0
    private var threadNum = 0
    private var runnableNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Thread를 상속한 SubThread 실행
        val subThread = SubThread()
        subThread.isDaemon = true
        subThread.start()

        // Runnable을 implements한 SubRunnable 실행
        val subRunnable = SubRunnable()
        val runnableThread = Thread(subRunnable)
        runnableThread.isDaemon = true
        runnableThread.start()

        btn_click.setOnClickListener {
            mainNum++
            btn_click_num.text = "$mainNum"
            thread_num.text = "$threadNum"
            runnable_num.text = "$runnableNum"
        }

    }

    inner class SubThread : Thread() {
        override fun run() {
            while (true) {
                threadNum++

                try {
                    sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }

    inner class SubRunnable : Runnable {
        override fun run() {
            while (true) {
                runnableNum++

                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }


        }

    }
}
