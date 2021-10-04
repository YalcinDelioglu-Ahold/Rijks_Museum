package com.myd.rijksmuseum.usecases

import com.myd.rijksmuseum.CoroutineTestRule
import com.myd.rijksmuseum.data.DetailsRepository
import com.myd.rijksmuseum.domain.Details
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetDetailsUseCaseTest {

    @get:Rule
    var coroutineRule = CoroutineTestRule()

    private val detailsFlow = flow<Details> { }
    private val mockDetailsRepository = mockk<DetailsRepository>().apply {
        coEvery { getDetails(any()) } returns detailsFlow
    }

    private val tested = GetDetailsUseCase(mockDetailsRepository)

    @Test
    fun `should get details`() = runBlockingTest {
        // GIVEN
        val objectNumber = "1"

        // WHEN
        val actual = tested(objectNumber)

        // THEN
        assertEquals(detailsFlow, actual)
    }
}