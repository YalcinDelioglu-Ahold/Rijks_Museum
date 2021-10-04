package com.myd.rijksmuseum.usecases

import com.myd.rijksmuseum.CoroutineTestRule
import com.myd.rijksmuseum.data.CollectionRepository
import com.myd.rijksmuseum.domain.Collection
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetCollectionsUseCaseTest {

    @get:Rule
    var coroutineRule = CoroutineTestRule()

    private val collectionsFlow = flow<List<Collection>> { }
    private val mockCollectionRepository = mockk<CollectionRepository>().apply {
        coEvery { getCollections(any()) } returns collectionsFlow
    }

    private val tested = GetCollectionsUseCase(mockCollectionRepository)

    @Test
    fun `should get collections`() = runBlockingTest {
        // GIVEN
        val pageNumber = 1

        // WHEN
        val actual = tested(pageNumber)

        // THEN
        Assert.assertEquals(collectionsFlow, actual)
    }
}