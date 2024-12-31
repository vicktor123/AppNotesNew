package com.example.myapplicationfragmentsnav.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationfragmentsnav.ListofNoteFragment
import com.example.myapplicationfragmentsnav.Roomdbdirectery.TaskItem
import com.example.myapplicationfragmentsnav.databinding.TasklistitemBinding

class TaskAdopter (
    private val listofNoteFragment: Context,
    private val getallItemlist: List<TaskItem>,
    private val listofNoteFragment1: ListofNoteFragment): RecyclerView.Adapter<TaskAdopter.MyViewHolder>(){
    class MyViewHolder(val binding: TasklistitemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


}