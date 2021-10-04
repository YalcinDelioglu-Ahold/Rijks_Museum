package com.myd.rijksmuseum.framework.datasource

import com.myd.rijksmuseum.CoroutineTestRule
import com.myd.rijksmuseum.createDetails
import com.myd.rijksmuseum.framework.db.dao.DetailsDao
import com.myd.rijksmuseum.framework.db.entity.DetailsEntity
import com.myd.rijksmuseum.framework.db.entity.toDetailEntity
import com.myd.rijksmuseum.framework.db.entity.toDetails
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RoomDetailsDataSourceTest {

    @get:Rule
    var coroutineRule = CoroutineTestRule()

    private val detailsEntity = createDetails("1").toDetailEntity()
    private val detailsFlow = flow<DetailsEntity> { detailsEntity }
    private val mockDetailsDao = mockk<DetailsDao> {
        coEvery { getDetails(any()) } returns detailsFlow
    }

    private val tested = RoomDetailsDataSource(mockDetailsDao)

    @Test
    fun `should get details from db`() = runBlockingTest {
        // GIVEN
        val objectNumber = "1"

        // WHEN
        val details = tested.getDetails(objectNumber)

        // THEN
        details.collect {
            Assert.assertEquals(detailsEntity.toDetails().id, it.id)
        }
    }

    @Test
    fun `should update details in db`() = runBlockingTest {
        // GIVEN
        val details = detailsEntity.toDetails()
        val slotDetailsEntity = slot<DetailsEntity>()
        mockDetailsDao.apply {
            coEvery { updateDetails(capture(slotDetailsEntity)) } just runs
        }

        // WHEN
        tested.updateDetails(details)

        // THEN
        Assert.assertTrue(slotDetailsEntity.isCaptured)
        Assert.assertEquals(detailsEntity.id, slotDetailsEntity.captured.id)
    }
}