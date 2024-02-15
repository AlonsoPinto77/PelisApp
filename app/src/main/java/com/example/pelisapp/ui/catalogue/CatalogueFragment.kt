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
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.common.Resource
import com.example.domain.interactors.movies.Movie
import com.example.pelisapp.R
import com.example.pelisapp.databinding.FragmentCatalogueBinding
import com.example.pelisapp.ui.adapters.MovieRowAdapter
import org.koin.android.ext.android.inject

class CatalogueFragment: Fragment(R.layout.fragment_catalogue) {
    private val catalogueViewModel: CatalogueViewModel by inject()
    private val pager= 1
    private lateinit var binding: FragmentCatalogueBinding
    private lateinit var adapter: MovieRowAdapter
    private var counter =0
    private var movieArray =  ArrayList<Movie>()
    //Page
    var targetPage = 1
    var pageSize = 20
    var stateScroll = true
    var numTasks = 0
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

        adapter = MovieRowAdapter(movieArray, requireActivity())
        binding.recyclerMoviesList.adapter = adapter

        catalogueViewModel.getMovies(pager)

        binding.recyclerMoviesList.addOnScrollListener(
            object :
                RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    if (dy > 0) {
                        val visibleItemCount = layoutManager.childCount
                        val totalItemCount = layoutManager.itemCount
                        val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                        val stateScroll2 = numTasks != totalItemCount
                        Log.i("RecyclerListenerStatScroll", "$stateScroll, $stateScroll2")
                        if (stateScroll && stateScroll2) {
                            Log.i("RecyclerScrollListenerVar", "$numTasks, $stateScroll2, $stateScroll")
                            if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                                stateScroll = false
                                targetPage += 1
                                Log.i("RecyclerScrollTargetPage", "$targetPage")
                                //displayWaiting()
                                catalogueViewModel.getMovies(targetPage)
                                }
                            }
                        }
                    }
                }
        )
        observeMovieState()
    }

    private fun observeMovieState(){
        catalogueViewModel.getMoviesViewState.observe(viewLifecycleOwner){state->
            stateScroll = true
            when(state){
                is Resource.Loading-> {
                    //todo agregar pantalla de carga
                    Toast.makeText(requireContext(), "Cargando...", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success->{
                    if(counter == 0){
                        numTasks = state.data?.get(0)?.totalResults ?: 0
                        counter++
                    }
                    val startIndex = movieArray.size // Índice inicial de los nuevos elementos
                    val itemCount = state.data!!.count() // Cantidad de nuevos elementos agregados
                    movieArray.addAll(state.data!!)
                    Log.i("ObserveMovieStateVars", "$numTasks, $startIndex, $itemCount")
                    Log.i("ObserveMovieStateData", "${state.data}")

                    adapter.notifyItemRangeInserted(startIndex, itemCount)
                }
                is Resource.Error ->{
                    Toast.makeText(requireContext(), "Error al ingresar: ${state.message}", Toast.LENGTH_SHORT).show()
                    Log.e(ContentValues.TAG, state.message!!)
                }
            }

        }
    }
}