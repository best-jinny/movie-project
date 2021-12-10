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

        // 네이버 영화 검색 api 조회 결과 - MoviesResponseDto
        MoviesResponseDto result = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, MoviesResponseDto.class, keyword).getBody();

        for(int i = 0; i < result.getItems().length; i++) {
            // 이미지 조회 결과 값

            System.out.println("@@@@@@@@@@@GET IMAGE : " +  result.getItems()[i].getImage());



        }




        // 네이버 영화 상세 페이지 링크에 있는 영화 코드 담을 list
        List<String> movieCodes = new ArrayList<>();

        // 네이버 영화 상세페이지 링크 list
        List<String> links = new ArrayList<>();

        // 조회 결과 개수
        System.out.println("@@@@@@@@@result length : " + result.getItems().length);


        for(int i = 0; i < result.getItems().length; i++) {

            // 조회 결과 돌면서 링크 추출
            links.add(result.getItems()[i].link);

            // 링크에 있는 movieCode = 이하 코드값 추출
            int idx = links.get(i).indexOf("=");

            if ( idx >= 0 ) {
                movieCodes.add(links.get(i).substring(idx+1));
            } else {
                movieCodes.add("206641");
            }

            System.out.println("**********movies code = " + movieCodes.get(i));

        }

        // 원본이미지 request url 담을 list
        List<String> imageUrls = new ArrayList<>();

        for(int i = 0; i < movieCodes.size(); i++) {
            if(!result.getItems()[i].getImage().isEmpty()){
                imageUrls.add("https://movie.naver.com/movie/bi/mi/photoViewPopup.naver?movieCode=" + movieCodes.get(i));
            } else {
                imageUrls.add("https://movie.naver.com/movie/bi/mi/photoViewPopup.naver?movieCode=" + 206641);
            }

            System.out.println("**********imageUrls = " + imageUrls.get(i));
        }


        //List<String> crawlingUrl = new ArrayList<>();

        // 원본이미지 src list
        List<String> realImagePaths = new ArrayList<>();

        for(int i = 0; i < imageUrls.size(); i++) {

            // imageUrls html 에서 targetImage src 크롤링

                Document document = Jsoup.connect(imageUrls.get(i)).get();
                Element el = document.getElementById("targetImage");
                if(imageUrls.get(i) != "https://movie.naver.com/movie/bi/mi/photoViewPopup.naver?movieCode=206641"){
                    realImagePaths.add(el.attr("abs:src"));
                } else {
                    realImagePaths.add("https://upload.wikimedia.org/wikipedia/en/6/60/No_Picture.jpg");
                }
        }

        for(int i = 0; i < realImagePaths.size(); i++) {
            result.getItems()[i].setImage(realImagePaths.get(i));

        }

        return result;
    }




}
