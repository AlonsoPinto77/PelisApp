package com.example.domain.interactors.movies

interface GetMovies{
    suspend operator fun invoke(page : Int) : List<Movie>
}

class GetMoviesImpl(private val moviesRepository: MoviesRepository) : GetMovies{
    override suspend fun invoke(page: Int): List<Movie> {
        return moviesRepository.getMovies(page)
    }

}