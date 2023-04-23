package com.maetdori.ottention.search.application.service.in;

import com.maetdori.ottention.search.adapter.web.dto.*;
import com.maetdori.ottention.search.adapter.web.dto.movie.SearchMovieRequest;
import com.maetdori.ottention.search.adapter.web.dto.movie.SearchMovieResponse;
import com.maetdori.ottention.search.adapter.web.dto.multi.SearchMultiRequest;
import com.maetdori.ottention.search.adapter.web.dto.multi.SearchMultiResponse;
import com.maetdori.ottention.search.adapter.web.dto.tv.SearchTvRequest;
import com.maetdori.ottention.search.adapter.web.dto.tv.SearchTvResponse;
import com.maetdori.ottention.search.application.port.in.SearchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SearchService implements SearchUseCase {
    private final SearchHttpRequestService searchHttpRequestService;
    private final Environment env;

    @Override
    public SearchMultiResponse searchMulti(SearchMultiRequest searchMultiRequest) {
        SearchQueryParams queryParams = new SearchQueryParams(env, searchMultiRequest);

        return searchHttpRequestService.httpGet("/multi", queryParams)
                .bodyToMono(SearchMultiResponse.class)
                .block();
    }

    @Override
    public SearchMovieResponse searchMovie(SearchMovieRequest searchMovieRequest) {
        SearchQueryParams queryParams = new SearchQueryParams(env, searchMovieRequest);
        queryParams.add("year", searchMovieRequest.getYear());
        queryParams.add("primary_release_year", searchMovieRequest.getPrimaryReleaseYear());

        return searchHttpRequestService.httpGet("/movie", queryParams)
                .bodyToMono(SearchMovieResponse.class)
                .block();
    }

    @Override
    public SearchTvResponse searchTv(SearchTvRequest searchTvRequest) {
        SearchQueryParams queryParams = new SearchQueryParams(env, searchTvRequest);
        queryParams.add("first_air_date_year", searchTvRequest.getFirstAirDateYear());

        return searchHttpRequestService.httpGet("/tv", queryParams)
                .bodyToMono(SearchTvResponse.class)
                .block();
    }
}
