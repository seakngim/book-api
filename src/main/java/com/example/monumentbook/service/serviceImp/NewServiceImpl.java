package com.example.monumentbook.service.serviceImp;

import com.example.monumentbook.model.Book;
import com.example.monumentbook.model.News;
import com.example.monumentbook.model.requests.NewsRequest;
import com.example.monumentbook.model.responses.ApiResponse;
import com.example.monumentbook.model.responses.BookResponse;
import com.example.monumentbook.model.responses.NewsResponse;
import com.example.monumentbook.repository.NewsRepository;
import com.example.monumentbook.service.NewsService;
import com.example.monumentbook.utilities.response.ResponseObject;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    ResponseObject res = new ResponseObject();
    @Override
    public ResponseEntity<?> addNews(NewsRequest newsRequest) {
        try{
            News news = News.builder()
                .title(newsRequest.getTitle())
                .description(newsRequest.getDescription())
                .coverImg(newsRequest.getCoverImg())
                .date(LocalDate.now())
                .delete(false)
                .build();
            newsRepository.save(news);
            res.setStatus(true);
            res.setMessage("out success!");
            res.setData(news);
            return ResponseEntity.ok(res);
        }
        catch (Exception e){
            res.setStatus(true);
            res.setMessage("out success!");
            res.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @Override
    public ResponseEntity<?> getNews(Integer page,Integer size) {
    try {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
        Page<News> pageResult = newsRepository.findByDeleteFalse(pageable);


        List<News> newsList = pageResult.getContent().stream()
                .map(news -> News.builder()
                        .id(news.getId())
                        .title(news.getTitle())
                        .coverImg(news.getCoverImg())
                        .description(news.getDescription())
                        .date(news.getDate())
                        .build())
                .collect(Collectors.toList());

        ApiResponse res = new ApiResponse(true, "Fetch news successful!", newsList, pageResult.getNumber() + 1, pageResult.getSize(), pageResult.getTotalPages(), pageResult.getTotalElements());
        return ResponseEntity.ok(res);
    }catch (Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateNews(Integer id,NewsRequest newsRequest) {
        try{
            Optional<News> newsOptional = newsRepository.findById(id);
            if(newsOptional.isPresent()){
                News news = News.builder()
                        .id(newsOptional.get().getId())
                        .title(newsRequest.getTitle())
                        .description(newsRequest.getDescription())
                        .coverImg(newsRequest.getCoverImg())
                        .date(LocalDate.now())
                        .delete(false)
                        .build();
                newsRepository.save(news);
                res.setStatus(true);
                res.setMessage("out success!");
                res.setData(news);
            }
            return ResponseEntity.ok(res);
        }catch (Exception e){
            res.setStatus(true);
            res.setMessage("out success!");
            res.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }

    }

    @Override
    public ResponseEntity<?> deleteNews(Integer id) {
        try{
            Optional<News> newsOptional = newsRepository.findById(id);
            if(newsOptional.isPresent()){
                News news = News.builder()
                        .id(newsOptional.get().getId())
                        .title(newsOptional.get().getTitle())
                        .description(newsOptional.get().getDescription())
                        .coverImg(newsOptional.get().getCoverImg())
                        .date(LocalDate.now())
                        .delete(true)
                        .build();
                newsRepository.save(news);
                res.setStatus(true);
                res.setMessage("delete success!");
                res.setData(news);
            }
            return ResponseEntity.ok(res);
        }catch (Exception e){
            res.setStatus(true);
            res.setMessage("out success!");
            res.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    @Override
    public ResponseEntity<?> getById(Integer id) {
        try{
            Optional<News> newsOptional = newsRepository.findById(id);
            if(newsOptional.isPresent()){
                News news = News.builder()
                        .id(newsOptional.get().getId())
                        .title(newsOptional.get().getTitle())
                        .description(newsOptional.get().getDescription())
                        .coverImg(newsOptional.get().getCoverImg())
                        .date(LocalDate.now())
                        .delete(false)
                        .build();
                res.setStatus(true);
                res.setMessage("out success!");
                res.setData(news);
            }
            return ResponseEntity.ok(res);
        }catch (Exception e){
            res.setStatus(true);
            res.setMessage("out success!");
            res.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    private NewsResponse get(News news) {
        return NewsResponse.builder()
                .id(news.getId())
                .title(news.getTitle())
                .coverImg(news.getCoverImg())
                .date(news.getDate())
                .build();
    }
}
