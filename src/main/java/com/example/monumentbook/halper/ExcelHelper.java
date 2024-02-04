//package com.example.monumentbook.halper;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Iterator;
//import java.util.List;
//
//import com.example.monumentbook.model.Book;
//import com.example.monumentbook.model.requests.BookRequest;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.web.multipart.MultipartFile;
//public class ExcelHelper {
//    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
//    static String[] HEADERs = { "Id", "Title", "Description", "Published" };
//    static String SHEET = "Tutorials";
//
//    public static boolean hasExcelFormat(MultipartFile file) {
//
//        if (!TYPE.equals(file.getContentType())) {
//            return false;
//        }
//
//        return true;
//    }
//
//    public static List<Book> excelTo(InputStream is) {
//        try {
//            Workbook workbook = new XSSFWorkbook(is);
//
//            Sheet sheet = workbook.getSheet(SHEET);
//            Iterator<Row> rows = sheet.iterator();
//
//            List<Book> books = new ArrayList<>();
//
//            int rowNumber = 0;
//            while (rows.hasNext()) {
//                Row currentRow = rows.next();
//
//                // skip header
//                if (rowNumber == 0) {
//                    rowNumber++;
//                    continue;
//                }
//
//                Iterator<Cell> cellsInRow = currentRow.iterator();
//
//                Book book = new Book();
//
//                int cellIdx = 0;
//                while (cellsInRow.hasNext()) {
//                    Cell currentCell = cellsInRow.next();
//
//                    switch (cellIdx) {
//                        case 1:
//                            book.setIsbn(currentCell.getStringCellValue());
//                            break;
//
//                        case 2:
//                            book.setTitle(currentCell.getStringCellValue());
//                            break;
//                        case 3:
////                            book.setCategoryId((int) currentCell.getNumericCellValue());
//                            break;
//
//                        case 4:
//                            book.setPublisher(currentCell.getStringCellValue());
//                            break;
//                        case 5:
//                            book.setCoverImg(currentCell.getStringCellValue());
//                            break;
//                        case 6:
////                            book.setPublisherDate(currentCell.getStringCellValue());
//                            break;
//                        case 7:
//                            book.setPrice(currentCell.getNumericCellValue());
//                            break;
//                        case 8:
//                            book.setQty((int) currentCell.getNumericCellValue());
//                            break;
//                        case 9:
//                            book.setDescription(currentCell.getStringCellValue());
//                            break;
//                        case 10:
////                            book.setAuthorsId(Collections.singletonList((int) currentCell.getNumericCellValue()));
//                            break;
//                        default:
//                            break;
//                    }
//
//                    cellIdx++;
//                }
//
//                books.add(book);;
//            }
//
//            workbook.close();
//            return books;
//        } catch (IOException e) {
//            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
//        }
//    }
//}
