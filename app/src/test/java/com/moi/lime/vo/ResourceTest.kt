package com.moi.lime.vo

import org.junit.Assert.*
import org.junit.Test

class ResourceTest {
    private val testString = "test"
    @Test
    fun testCreateSuccessResource() {
        val resource = Resource.success(testString)
        assertEquals(Status.SUCCESS, resource.status)
        assertEquals(testString, resource.data)
        assertNull(resource.throwable)
    }

    @Test
    fun testCreateLoadingResource() {
        val resource = Resource.loading(testString)
        assertEquals(Status.LOADING, resource.status)
        assertEquals(testString, resource.data)
        assertNull(resource.throwable)
    }

    @Test
    fun testCreateErrorResource() {
        val testThrowable = Throwable()
        val resource = Resource.error(testThrowable, null)
        assertEquals(testThrowable, resource.throwable)
        assertNull(resource.data)

    }
}