//package com.example.monumentbook.service.serviceImp;
//
//import com.example.monumentbook.halper.ExcelHelper;
//import com.example.monumentbook.model.Book;
//import com.example.monumentbook.model.requests.BookRequest;
//import com.example.monumentbook.repository.ExcelFileUploadRepository;
//import com.example.monumentbook.service.ExcelFileUploadService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//
//@Service
//public class ExcelFileUploadServiceImp implements ExcelFileUploadService {
//    private final ExcelFileUploadRepository repository;
//
//    public ExcelFileUploadServiceImp(ExcelFileUploadRepository repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public ResponseEntity<?> UploadFile(MultipartFile file) {
//        try {
//            List<Book> book = ExcelHelper.excelTo(file.getInputStream());
//            repository.saveAll(book);
//            } catch (IOException e) {
//            throw new RuntimeException("fail to store excel data: " + e.getMessage());
//            }
//
//        return null;
//    }
//}
