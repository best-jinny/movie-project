package com.movie.springboot.api;

import com.movie.springboot.web.dto.MoviesResponseDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieApiClient {
    @Autowired
    private final RestTemplate restTemplate;

    private final String CLIENT_ID = "Xg4LDBHlVyETj_25Cn0T";
    private final String CLIENT_SECRET = "Wc87Jz8T08";

    private final String apiUrl = "https://openapi.naver.com/v1/search/movie.json?query={keyword}";

    public MoviesResponseDto requestMovie(String keyword) throws IOException {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", CLIENT_ID);
        headers.set("X-Naver-Client-Secret", CLIENT_SECRET);

        final HttpEntity<String> entity = new HttpEntity<>(headers);


        MoviesResponseDto result = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, MoviesResponseDto.class, keyword).getBody();

        List<String> movieCodes = new ArrayList<>();
        List<String> links = new ArrayList<>();

        System.out.println("@@@@@@@@@result length : " + result.getItems().length);

        for(int i = 0; i < result.getItems().length; i++) {


            links.add(result.getItems()[i].link);

            int idx = links.get(i).indexOf("=");

            if ( idx >= 0)
            {
                movieCodes.add(links.get(i).substring(idx+1));

            } else {
                movieCodes.add("206641");
            }

            System.out.println("**********movies code = " + movieCodes.get(i));

        }

        List<String> imageUrls = new ArrayList<>();



        for(int i = 0; i < movieCodes.size(); i++) {
            imageUrls.add("https://movie.naver.com/movie/bi/mi/photoViewPopup.naver?movieCode=" + movieCodes.get(i));
            System.out.println("**********imageUrls = " + imageUrls.get(i));
        }

        List<String> crawlingUrl = new ArrayList<>();
        List<String> realImagePaths = new ArrayList<>();

        for(int i = 0; i < imageUrls.size(); i++) {
            crawlingUrl.add(imageUrls.get(i));
            System.out.println("**********crawlingUrls = " + crawlingUrl.get(i));

            if(crawlingUrl.get(i) != null) {
                Document document = Jsoup.connect(crawlingUrl.get(i)).get();
                Element el = document.getElementById("targetImage");
                if(el.attr("abs:src") == null){
                    realImagePaths.add("https://movie-phinf.pstatic.net/20111223_122/1324650523645suE10_JPEG/movie_image.jpg");

                } else {
                    realImagePaths.add(el.attr("abs:src"));
                }

            } else {
                realImagePaths.add("https://movie-phinf.pstatic.net/20111223_122/1324650523645suE10_JPEG/movie_image.jpg");
            }


        }

        for(int i = 0; i < realImagePaths.size(); i++) {
            result.getItems()[i].setImage(realImagePaths.get(i));

        }

        return result;
    }




}
