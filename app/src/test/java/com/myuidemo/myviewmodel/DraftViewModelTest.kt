package com.myuidemo.myviewmodel

import com.myuidemo.data.model.PlayerDataModel
import org.junit.Test
import kotlin.Comparator
import kotlin.collections.ArrayList

class DraftViewModelTest {

    @Test
    fun getDataTest() {

        val player_pn = "DH,C,LH"
        val removed_position = ""
        var roster_value = "SP"
        val list_data: ArrayList<String> = ArrayList<String>(removed_position.split(",").toList())
        var list_two: ArrayList<String> = ArrayList<String>(player_pn.split(",").toList())

        list_data.retainAll(list_two)
        //System.out.println(list_data.toString())

        var comp = Comparator<PlayerDataModel>(){
o1, o2 -> o1.id.compareTo(o2.id)
        }

        var playerlist: ArrayList<PlayerDataModel> = ArrayList()
        playerlist.add(PlayerDataModel("third ", "3"))
        playerlist.add(PlayerDataModel("four 2", "6"))
        playerlist.add(PlayerDataModel("four ", "4"))
        playerlist.add(PlayerDataModel("First", "1"))
        playerlist.add(PlayerDataModel("second ", "2"))
        playerlist.add(PlayerDataModel("four 1", "5"))

     //  playerlist = playerlist.sortedWith(compareBy<PlayerDataModel>(){it.id})

        System.out.println(playerlist.toString())


    }

    @Test
    fun TestFun(){

//        var list = List<Int>(5)
    }

}