package com.example.myapplicationfragmentsnav

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationfragmentsnav.Roomdbdirectery.TaskDatabase
import com.example.myapplicationfragmentsnav.Roomdbdirectery.TaskItem
import com.example.myapplicationfragmentsnav.adapter.TaskAdopter
import com.example.myapplicationfragmentsnav.databinding.FragmentListofNoteBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListofNoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListofNoteFragment : BaseFragment(), TaskAdopter.Deletetask {
    private lateinit var listofNoteBinding: FragmentListofNoteBinding
    private val binding get() = listofNoteBinding
    private lateinit var viewModel: ListofNoteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        listofNoteBinding = FragmentListofNoteBinding.inflate(inflater, container, false)
        return listofNoteBinding.root
    }


    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ListofNoteViewModel::class.java]
        binding.recycleview.apply {
            layoutManager =
                LinearLayoutManager(context?.applicationContext, RecyclerView.VERTICAL, true)
            this.setHasFixedSize(true)
        }
        // coroutine implementation
        getdatafrombd()

        binding.febAdd.setOnClickListener {
            findNavController().navigate(R.id.action_mobile_navigation_to_addNoteFragment)

        }

    }
    @SuppressLint("NotifyDataSetChanged")
    private fun getdatafrombd() {


        GlobalScope.launch {

            withContext(Dispatchers.Main) {
                context?.let {
                    if (TaskDatabase(it).TaskDao().getAllTask().isNotEmpty()) {

                        binding.recycleview.adapter = TaskAdopter(
                            requireContext(),
                            TaskDatabase(it).TaskDao().getAllTask(),
                            this@ListofNoteFragment
                       )
                        Log.e("TAG", "onViewCreated: " + TaskDatabase(requireContext()).TaskDao().getAllTask())

                    }
                }

            }
            Log.e("TAG", "onViewCreated: " + TaskDatabase(requireContext()).TaskDao().getAllTask())
        }

        binding.recycleview.adapter?.notifyDataSetChanged()
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun deletitem(taskItem: TaskItem) {

        GlobalScope.launch {
            TaskDatabase(requireContext()).TaskDao().deletitem(taskItem)
        }
        getdatafrombd()
        requireContext().toast("Data Deleted Successfully")
    }

    companion object


}