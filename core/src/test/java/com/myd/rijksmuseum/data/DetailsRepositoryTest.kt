package com.myd.rijksmuseum.data

import com.myd.rijksmuseum.CoroutineTestRule
import com.myd.rijksmuseum.createDetails
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailsRepositoryTest {

    @get:Rule
    var coroutineRule = CoroutineTestRule()

    private val detailsFromDataSource = createDetails("1")
    private val detailsFlow = flow {
        this.emit(detailsFromDataSource)
    }
    private val mockDetailsDataSource = mockk<DetailsDataSource>().apply {
        coEvery { getDetails(any()) } answers { detailsFlow }
        coEvery { updateDetails(any()) } just runs
    }
    private val detailsFromNetwork = createDetails("1")
    private val mockNetworkService = mockk<NetworkService>().apply {
        coEvery { getDetails(any()) } answers { detailsFromNetwork }
    }

    private val tested = DetailsRepository(mockDetailsDataSource, mockNetworkService)

    @Test
    fun `should get details from data source and refresh the data from network`() = runBlockingTest {
        // GIVEN
        val objectNumber = "1"

        // WHEN
        val details = tested.getDetails(objectNumber)

        // THEN
        details.collect {
            assertEquals(detailsFromDataSource, it)
        }
        coVerify { mockDetailsDataSource.updateDetails(detailsFromNetwork) }
    }
}