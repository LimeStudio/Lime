package com.moi.lime.util

import android.util.Log

enum class Logger {
    INS {
        private var isDebug = true
        private var tag = "Logger"
        private var isTest = false
        fun setupLogger(isDebug: Boolean, tag: String, isTest: Boolean = false) {
            this.isDebug = isDebug
            this.tag = tag
            this.isTest = isTest
        }

        fun d(message: String) {
            check {
                Log.d(tag, message)
            }
        }

        fun i(message: String) {
            check {
                Log.i(tag, message)
            }
        }

        fun e(message: String) {
            check {
                Log.e(tag, message)
            }
        }

        private fun check(block: () -> Unit) {
            if (isDebug && !isTest) {
                block()
            }
        }
    };


}