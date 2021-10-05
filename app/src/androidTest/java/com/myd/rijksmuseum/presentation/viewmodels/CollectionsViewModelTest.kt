package com.myd.rijksmuseum.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.myd.rijksmuseum.presentation.MainActivity
import com.myd.rijksmuseum.presentation.TestCoroutineRule
import com.myd.rijksmuseum.presentation.createCollection
import com.myd.rijksmuseum.usecases.GetCollectionsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CollectionsViewModelTest {
    @get:Rule
    val testRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    private val mockGetCollectionsUseCase = mockk<GetCollectionsUseCase>()

    private val tested =  CollectionsViewModel(mockGetCollectionsUseCase)

    @Before
    fun setUp() {
        tested.collectionsLiveData.observeForever {  }
    }

    @Test
    fun shouldObserveCollections() = runBlockingTest {
        // GIVEN
        val collections = listOf(createCollection("1"), createCollection("2"), createCollection("3"))
        val collectionsFlow = flow {
            this.emit(collections)
        }
        coEvery { mockGetCollectionsUseCase(any()) } answers { collectionsFlow }

        // WHEN THEN
        Assert.assertEquals(collections, tested.collectionsLiveData.value)
    }
}