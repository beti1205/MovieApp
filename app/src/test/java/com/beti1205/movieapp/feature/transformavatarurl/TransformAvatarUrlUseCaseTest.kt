/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.transformavatarurl

import com.beti1205.movieapp.common.AppConfig
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Before
import org.junit.Test

class TransformAvatarUrlUseCaseTest {

    private lateinit var transformAvatarUrlUseCase: TransformAvatarUrlUseCase

    @Before
    fun setup() {
        transformAvatarUrlUseCase = TransformAvatarUrlUseCaseImpl(
            AppConfig(
                baseUrl = "baseUrl",
                imageUrl = "imageUrl",
                apiKey = "apikey"
            )
        )
    }

    @Test
    fun verifyThatNullWasReturnedWhenAvatarUrlWasNull() {
        val result = transformAvatarUrlUseCase(null)

        assertNull(result)
    }

    @Test
    fun verifyThatUrlWasReturnedWhenExternalAvatarUrlWasProvided() {
        val result = transformAvatarUrlUseCase("/$EXTERNAL_URL")

        assertEquals(EXTERNAL_URL, result)
    }

    @Test
    fun verifyThatUrlWasReturnedWhenInternalAvatarUrlWasProvided() {
        val result = transformAvatarUrlUseCase(INTERNAL_URL)

        assertEquals("$IMAGE_URL$INTERNAL_URL", result)
    }

    private companion object {
        private const val EXTERNAL_URL = "https://test.com/image.png"
        private const val INTERNAL_URL = "/image.png"
        private const val IMAGE_URL = "imageUrl"
    }
}
