package com.myuidemo

import com.myuidemo.data.model.ActiveDraftListDataModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test


import org.junit.Assert.*
import kotlin.collections.ArrayList

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(true,  8 and 2 == 2)
        assertEquals(true, StringUtils.emailValidator("c-manning4@hvcc.edu"))
    }

    @Test
    fun testActiveDraftSorting(){
        val testlist = ArrayList<ActiveDraftListDataModel>()
        testlist.add(ActiveDraftListDataModel(1,140,1))
        testlist.add(ActiveDraftListDataModel(2,110,2))
        testlist.add(ActiveDraftListDataModel(65,10,2))
        testlist.add(ActiveDraftListDataModel(4,141,4))
        testlist.add(ActiveDraftListDataModel(3,179,3))


       val dataModel =  testlist.sortedWith( compareBy<ActiveDraftListDataModel>( ActiveDraftListDataModel::status,ActiveDraftListDataModel::timeLeft ))
        val first = dataModel.filter { it -> it.status != 1 }
        (first as ArrayList).addAll(dataModel.filter { it -> it.status == 1 })
        for (activeDraftListDataModel in first) {
            println(activeDraftListDataModel.leagueId)
        }

    }

    @Test
    fun testCor() = runBlocking<Unit>{
        val job = GlobalScope.launch {
            delay(10000L)
            print("laxmikant")
        }
        println("hello")
        job.join()
        println("Bye")
    }


}
