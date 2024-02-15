package com.example.pelisapp.ui.catalogue

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.common.Resource
import com.example.pelisapp.R
import com.example.pelisapp.databinding.FragmentCatalogueBinding
import com.example.pelisapp.ui.adapters.MovieRowAdapter
import org.koin.android.ext.android.inject

class CatalogueFragment: Fragment(R.layout.fragment_catalogue) {
    private val catalogueViewModel: CatalogueViewModel by inject()
    private val pager= 1
    private lateinit var binding: FragmentCatalogueBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatalogueBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerMoviesList.layoutManager = LinearLayoutManager(requireContext())

        catalogueViewModel.getMovies(pager)
        observeMovieState()
    }

    private fun observeMovieState(){
        catalogueViewModel.getMoviesViewState.observe(viewLifecycleOwner){state->
            when(state){
                is Resource.Loading-> {
                    //todo agregar pantalla de carga
                }
                is Resource.Success->{
                    val adapter = MovieRowAdapter(state.data!!)
                    binding.recyclerMoviesList.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
                is Resource.Error ->{
                    Toast.makeText(requireContext(), "Error al ingresar: ${state.message}", Toast.LENGTH_SHORT).show()
                    Log.e(ContentValues.TAG, state.message!!)
                }
            }

        }
    }
}