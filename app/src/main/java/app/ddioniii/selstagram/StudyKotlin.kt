package app.ddioniii.selstagram

import android.util.Log
import org.json.JSONObject

fun main(){
    // 과제
    var arrayTest = mutableListOf<JSONObject>()

    var testObj1 = JSONObject()
    testObj1.put("name", "gildong")

    var testObj2 = JSONObject()
    testObj2.put("name", "hello")

    var testObj3 = JSONObject()
    testObj3.put("name", "Kotlin")

    arrayTest.add(0, testObj1)
    arrayTest.add(1, testObj2)
    arrayTest.add(2, testObj3)

    for(item in arrayTest){
        Log.d("STUDY_KT", item.getString("name"))
    }

//    var json = JSONObject()
//
//    json.put("name", "jiwon")
//    json.put("age", "23")
//
//    var list = mutableListOf<String>() // mutable: 조정이 가능한, 그냥 ListOf도 있음
//    list.add("1")
//    list.add("2")
//
//    var listOfJson = mutableListOf<JSONObject>()
//    listOfJson.add(json)
}