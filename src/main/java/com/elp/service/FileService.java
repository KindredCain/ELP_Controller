package com.elp.service;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ASUS on 2017/7/5.
 */
public class FileService {

    public void uploadFile(String fileName,String path,MultipartFile file){
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd-HH-mm-ss" );
        String str = sdf.format(new Date());
        File uploadFile = new File(path);
        if(!uploadFile.exists()){
            uploadFile.mkdirs();
        }
        File localFile = new File(path+File.separator+fileName);
        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //
    public List<Map<String,String>> viewExcelFile(String filePath,String fileType){
        List<Map<String,String>> resultMap = new ArrayList<Map<String,String>>();
        String item[] = new String[20];
        try {
            if (fileType == "xlsx"){
                XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(ResourceUtils.getFile(filePath)));
                XSSFSheet sheets = wb.getSheetAt(0);
                for (int i=0;i<sheets.getLastRowNum()+1;i++){
                    XSSFRow row = sheets.getRow(i);
                    Map<String,String> tempMap = new HashMap<String,String>();
                    for(int j=0;j<row.getLastCellNum()+1;j++){
                        if (row.getCell(j) != null) {
                            row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                            if (i == 0){
                               item[j] = row.getCell(j).getStringCellValue();
                            } else {

                            }
                        }
                    }
                }
            }else {
                HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(ResourceUtils.getFile(filePath)));
                HSSFSheet sheets = wb.getSheetAt(0);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
