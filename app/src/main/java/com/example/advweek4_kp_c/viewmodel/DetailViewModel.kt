package com.example.advweek4_kp_c.viewmodel

import android.app.Application
import android.content.ContentValues.TAG
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.advweek4_kp_c.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()
    private var queue: RequestQueue? = null
    val TAG = "volleytag"


    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
    fun fetch(id: String?) {

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php?id=$id"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<Student>() {}.type
                val result = Gson().fromJson<Student>(it,
                    sType)
                // value nya adalah ArrayList of ( Student )
                studentLD.value = result

            },
            {
//                studentLoadErrorLD.value = true
//                loadingLD.value = false


            }
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)

//        val student1 = Student(
//            "16055", "Nonie", "1998/03/28", "5718444778",
//            "http://dummyimage.com/75x100.jpg/cc0000/ffffff"
//        )
//        studentLD.value = student1
    }
}