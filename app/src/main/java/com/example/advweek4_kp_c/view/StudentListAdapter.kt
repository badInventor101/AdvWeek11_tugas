package com.example.advweek4_kp_c.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek4_kp_c.R
import com.example.advweek4_kp_c.databinding.StudentListItemBinding
import com.example.advweek4_kp_c.model.Student
import com.example.advweek4_kp_c.util.loadImage

/// (1)
// adapter untuk recyclerView
class StudentListAdapter(val StudentList:ArrayList<Student>):
    RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), ButtonDetailClickListener{
    class StudentViewHolder(var view: StudentListItemBinding) : RecyclerView.ViewHolder(view.root)

    // untuk student list item (layout yang mau di tampilkan secara mennrun)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.student_list_item, parent, false)
        val view = DataBindingUtil.inflate<StudentListItemBinding>(inflater, R.layout.student_list_item, parent, false)

        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return StudentList.size
    }

    // ikat viewholdernya dengan ArrayList studentList yang ada di parameter
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.view.student = StudentList[position]
        // instatiate the listener
        holder.view.listener = this
//        holder.view.findViewById<TextView>(R.id.txtID).text = StudentList[position].id
//        holder.view.findViewById<TextView>(R.id.txtName).text = StudentList[position].name
//        holder.view.findViewById<Button>(R.id.btnDetail).setOnClickListener {
//            val action = StudentListFragmentDirections.actionStudentDetail(StudentList[position].id.toString())
//            Navigation.findNavController(it).navigate(action)
//        }
//
//        var imageview = holder.view.findViewById<ImageView>(R.id.imageView)
//        var progress = holder.view.findViewById<ProgressBar>(R.id.progressBar)
//        imageview.loadImage(StudentList[position].photoUrl, progress)

    }

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        StudentList.clear()
        StudentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    // implement the listener
    override fun onButtonDetailClick(v: View) {
        val action = StudentListFragmentDirections.actionStudentDetail(v.tag.toString()) // di passingkan lewat tag
        Navigation.findNavController(v).navigate(action)
    }

}