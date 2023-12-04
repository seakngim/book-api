//package com.example.monumentbook.service.serviceImp;
//
//import com.example.monumentbook.model.File;
//import com.example.monumentbook.repository.FileRepository;
//import com.example.monumentbook.service.FileService;
//import org.springframework.util.StringUtils;
//import org.springframework.web.multipart.MultipartFile;
//
//public class FileServiceImp implements FileService {
//    private final FileRepository fileRepository;
//
//    public FileServiceImp(FileRepository fileRepository) {
//        this.fileRepository = fileRepository;
//    }
//
//    @Override
//    public File save(MultipartFile file) {
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        File fileObj = null;
//        fileObj= File.builder()
//                .data(fileObj.getData())
//                .name(fileName)
//                .type(fileObj.getType())
//                .build();
//
//        return fileRepository.save(fileObj);
//    }
//}
