package com.myd.rijksmuseum.data

import com.myd.rijksmuseum.CoroutineTestRule
import com.myd.rijksmuseum.createCollection
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CollectionRepositoryTest {
    @get:Rule
    var coroutineRule = CoroutineTestRule()

    private val listFromDataSource = listOf(createCollection("1"))
    private val collectionsFlow = flow {
        this.emit(listFromDataSource)
    }
    private val mockCollectionDataSource = mockk<CollectionDataSource>().apply {
        coEvery { getCollections(any()) } answers { collectionsFlow }
        coEvery { updateCollections(any()) } just runs
    }
    private val listFromNetwork = listOf(createCollection("1"), createCollection("2"))
    private val mockNetworkService = mockk<NetworkService>().apply {
        coEvery { fetchCollections(any()) } answers { listFromNetwork }
    }

    private val tested = CollectionRepository(mockCollectionDataSource, mockNetworkService)

    @Test
    fun `should get collections from data source and refresh the data from network`() = runBlockingTest {
        // GIVEN
        val pageNumber = 1

        // WHEN
        val collections = tested.getCollections(pageNumber)

        // THEN
        collections.collect {
            Assert.assertEquals(listFromDataSource, it)
        }
        coVerify { mockCollectionDataSource.updateCollections(listFromNetwork) }
    }
}