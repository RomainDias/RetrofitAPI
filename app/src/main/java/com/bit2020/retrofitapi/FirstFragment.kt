package com.bit2020.retrofitapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.bit2020.retrofitapi.adapters.PhotoAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState

                binding.recyclerView.LayoutManager = LinearLayoutManager(view.context)
                val photos = retrofitAPIHandler.getPhotos()

        photos.enqueue(object : CallBack<List<Photo>> {

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
            val photosBody = response.body()
            val adapter = PhotoAdapter(photosBody!!, this,{position->onListItemClick(position)})
                binding.recyclerView.adapter = adapter



            }
        }



        )

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}