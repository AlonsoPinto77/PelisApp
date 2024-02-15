package com.example.domain.interactors.movies

interface GetMoviesOffline{
    suspend operator fun invoke(page: Int): List<Movie>
}

class GetMoviesOfflineImpl(private val moviesRepository: MoviesRepository): GetMoviesOffline{
    override suspend fun invoke(page: Int): List<Movie> {
        return moviesRepository.getMoviesOffline(page)
    }

}