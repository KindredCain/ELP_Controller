package com.elp.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ASUS on 2017/7/5.
 */

@Service
public class FileService {

    public void uploadFile(String fileName, String path, MultipartFile file) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String str = sdf.format(new Date());
        File uploadFile = new File(path);
        if (!uploadFile.exists()) {
            uploadFile.mkdirs();
        }
        File localFile = new File(path + File.separator + fileName);
        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取xlsx格式的excel文件，并将读取的数据存放到List<Map>中
    public List<Map<String, String>> viewExcelFile(String fileType,MultipartFile file) {
        List<Map<String, String>> resultMap = new ArrayList<Map<String, String>>();
        try {
            if (fileType == "xlsx") {
                byte[] fileByte = file.getBytes();
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileByte);
                XSSFWorkbook wb = new XSSFWorkbook(byteArrayInputStream);
                XSSFSheet sheets = wb.getSheetAt(0);
                String item[] = new String[sheets.getRow(0).getLastCellNum()+1];
                for (int i = 0; i < sheets.getLastRowNum() + 1; i++) {
                    XSSFRow row = sheets.getRow(i);
                    Map<String, String> tempMap = new HashMap<String, String>();
                    for (int j = 0; j < row.getLastCellNum() + 1; j++) {
                        if (row.getCell(j) != null) {
                            row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                            if (i == 0) {
                                item[j] = row.getCell(j).getStringCellValue();
                            } else {
                                tempMap.put(item[j], row.getCell(j).getStringCellValue());
                            }
                        }
                    }
                    if(i != 0)
                    resultMap.add(tempMap);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return resultMap;
        }
    }
}
