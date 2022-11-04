package com.example.proyectoapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoapi.adapters.GenerosAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class GenerosFragment : Fragment() {
    private val viewModel: GenerosViewModel by activityViewModels()
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: GenerosAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_generos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.myProgressBar)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.loading.collect { loading ->
                        if (loading) {
                            progressBar.visibility = View.VISIBLE
                        } else{
                            progressBar.visibility = View.GONE
                        }
                    }
                }
                launch {
                    viewModel.generoList.collect{
                        adapter.updateData(it.genres)
                    }
                }
            }
        }

        adapter = GenerosAdapter{
            DataHolder.idGenres = it.id
            findNavController().navigate(R.id.action_generosFragment_to_moviesFragment)
        }
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler)
        recyclerView.adapter = adapter
        viewModel.getGeneros()
    }
}