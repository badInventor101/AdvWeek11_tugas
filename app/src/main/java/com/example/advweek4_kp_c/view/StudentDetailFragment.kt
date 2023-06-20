package com.example.advweek4_kp_c.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek4_kp_c.R
import com.example.advweek4_kp_c.databinding.FragmentStudentDetailBinding
import com.example.advweek4_kp_c.util.loadImage
import com.example.advweek4_kp_c.viewmodel.DetailViewModel
import com.example.advweek4_kp_c.viewmodel.ListViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel // list modelnya
    private lateinit var dataBinding:FragmentStudentDetailBinding





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_student_detail, container, false)
        dataBinding = DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater, R.layout.fragment_student_detail, container, false)
        return dataBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val stud_id = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId // mengambil parameter ID
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(stud_id) // passingkan ID

        // dan akhirnya di observe datanya setelah di fetch dari DetailViewModel
        observeViewModel()


    }


    fun observeViewModel() {   /// di lihat dan di amati
                               /// bisa juga di ambil datanya
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            dataBinding.studentD = it

//            var student = it
//
//            val btnNotif = view?.findViewById<Button>(R.id.btnNotif)
//
//
//            btnNotif?.setOnClickListener {
//                Observable.timer(5, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        Log.d("Messages", "five seconds")
//                        MainActivity.showNotification(
//                            student.name.toString(),
//                            "A new notification created",
//                            R.drawable.baseline_error_24
//                        )
//                    }
//            }
//            val id = view?.findViewById<TextInputEditText>(R.id.txtIntID)
//            val name = view?.findViewById<TextInputEditText>(R.id.txtIntName)
//            val bod = view?.findViewById<TextInputEditText>(R.id.txtIntBod)
//            val phone = view?.findViewById<TextInputEditText>(R.id.txtIntPhone)
//            val imgV = view?.findViewById<ImageView>(R.id.imageView2)
//            val prog = view?.findViewById<ProgressBar>(R.id.progressBar2)
//
//
//
//            id?.setText(viewModel.studentLD.value?.id)
//            name?.setText(viewModel.studentLD.value?.name)
//            bod?.setText(viewModel.studentLD.value?.dob)
//            phone?.setText(viewModel.studentLD.value?.phone)
//            imgV?.loadImage(viewModel.studentLD.value?.photoUrl, prog!!)
        })





    }


}