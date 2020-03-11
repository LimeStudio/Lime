package com.moi.lime.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.MethodRule
import org.junit.runners.model.FrameworkMethod
import org.junit.runners.model.Statement

@ExperimentalCoroutinesApi
class TestDispatchersRule : MethodRule {

    val testDispatcher = TestCoroutineDispatcher()
    val testScope = TestCoroutineScope(testDispatcher)

    override fun apply(base: Statement?, method: FrameworkMethod?, target: Any?): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                try {
                    Dispatchers.setMain(testDispatcher)
                    base?.evaluate()
                } finally {
                    Dispatchers.resetMain()
                    testScope.cleanupTestCoroutines()
                }
            }
        }
    }

}