package com.example.advweek4_kp_c.viewmodel

import android.app.Application
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


//// (2) tentang modelnya ??
class ListViewModel(application: Application):AndroidViewModel(application) {
    val studentsLD = MutableLiveData<ArrayList<Student>>() // memancarkan live data dalam list
    val studentLoadErrorLD =  MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleytag"
    private var queue: RequestQueue? = null


    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    fun refresh(){
        loadingLD.value = true
        studentLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://jitusolution.com/student.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Student>>() {}.type
                val result = Gson().fromJson<List<Student>>(it, sType)
                // value nya adalah ArrayList of ( Student )
                studentsLD.value = result as ArrayList<Student>
                loadingLD.value = false

            },
            {
                studentLoadErrorLD.value = true
                loadingLD.value = false


            }
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)

//        studentsLD.value = arrayListOf(
//            Student("16055","Nonie","1998/03/28","5718444778","http://dummyimage.com/75x100" +
//                    ".jpg/cc0000/ffffff"),
//            Student("13312","Rich","1994/12/14","3925444073","http://dummyimage.com/75x100" +
//                    ".jpg/5fa2dd/ffffff"),
//            Student("11204","Dinny","1994/10/07","6827808747","http://dummyimage.com/75x100.jpg/5fa2dd/ffffff1")
//        )
//
//        studentLoadErrorLD.value = false
//        loadingLD.value = false
    }
}