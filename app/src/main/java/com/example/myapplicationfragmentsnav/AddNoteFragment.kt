package com.example.myapplicationfragmentsnav

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplicationfragmentsnav.Roomdbdirectery.TaskDatabase
import com.example.myapplicationfragmentsnav.Roomdbdirectery.TaskItem
import com.example.myapplicationfragmentsnav.databinding.FragmentAddNoteBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddNoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddNoteFragment : BaseFragment() {
    private var utaskItem: TaskItem? = null

    companion object {
        fun newInstance() = AddNoteFragment()
    }

    // TODO: Rename and change types of parameters
    private lateinit var addNoteBinding: FragmentAddNoteBinding
    private val binding get() = addNoteBinding

    private lateinit var viewModel: AddNoteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        addNoteBinding= FragmentAddNoteBinding.inflate(inflater, container, false)
        return addNoteBinding.root
    }

    fun Fragment.makeToast(text: String,duration: Int = Toast.LENGTH_LONG) {
        activity?.let {
            Toast.makeText(it, text, duration).show()
        }
    }
    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[AddNoteViewModel::class.java]
       // arguments?.let {
       //     val data  = AddNoteFragmentArgs.fromBundle(it).taskItem
          // Toast.makeText(activity,"text",Toast.LENGTH_LONG).show()
         //   makeToast(data.toString())
       // }

        arguments?.let {
            utaskItem = AddNoteFragmentArgs.fromBundle(it).taskItem
            binding.etName.setText(utaskItem?.taskname)
            binding.etDescreptiuon.setText(utaskItem?.TaskDescription)
        }

        binding.btnAdd.setOnClickListener(View.OnClickListener {
                if (validation()) {
                    GlobalScope.launch {
                        context?.let {
                            val taskItem = TaskItem(
                                binding.etName.text.toString(),
                                binding.etDescreptiuon.text.toString(),
                                0
                            );
                            if (utaskItem == null) {

                                TaskDatabase(it).TaskDao().inserttaskItem(taskItem)

                            } else {
                                taskItem.id= utaskItem?.id!!
                                TaskDatabase(it).TaskDao().updatetak(taskItem)

                            }
                        }
                    }
                    if(utaskItem==null){
                        context?.toast("Data Insert Successfully")
                    }else{
                        context?.toast("Data Updated Successfully")
                    }
                    findNavController().navigate(R.id.action_addNoteFragment_to_home_fragment)


                }
            })

            // val data = "Hello, Destination Fragment!"
           // findNavController().navigate(R.id.action_addNoteFragment_to_home_fragment)




    }


    private fun validation(): Boolean {
        if (TextUtils.isEmpty(binding.etName.text)) {
            context?.toast("Please enter task title")
            return false
        } else if (TextUtils.isEmpty(binding.etDescreptiuon.text)) {
            context?.toast("Please enter task description")
            return false
        }
        return true
    }
}