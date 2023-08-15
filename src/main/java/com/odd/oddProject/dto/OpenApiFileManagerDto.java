package com.odd.oddProject.dto;

import com.odd.oddProject.cmn.OddException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.formula.atp.Switch;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class OpenApiFileManagerDto {
    @Value("${file.path}")
    String PATH;

    public List<LocationDto> getOpenApiFileFetch() throws IOException, CsvValidationException, OddException, URISyntaxException, ParseException {
        // 테스트 코드에서 PATH에서는 SpringBoot 접근 애매하여 설정 (수정해야할 코드)
        if(PATH==null) PATH = "/Users/han/oddProjectBack/src/main/resources/files";
        File dir = new File(PATH);
        FilenameFilter xlsxFilter = new FilenameFilter() {
            @Override
            public boolean accept(File f, String name) {
                return name.contains(".xlsx");
            }
        };
        File xlsxFiles[] = dir.listFiles(xlsxFilter);
        List locationList = new ArrayList<LocationDto>();
        List rtnLocationList = new ArrayList<LocationDto>();
        /* 엑셀 파일을 읽어오는 부분 */
        for(File xlsxFile : xlsxFiles){
            XSSFWorkbook workBook = new XSSFWorkbook(new FileInputStream(xlsxFile));
            System.out.println("workBook => " + workBook);

            // 하나의 시트수 만 존재하기 때문에 0으로 줌.
            XSSFSheet sheet = workBook.getSheetAt(0);
            System.out.println("sheet => " + sheet);

            int rows = sheet.getPhysicalNumberOfRows();
            System.out.println("rows => " +rows);

            int rowNo = 0;
            int addressIndex=0;
            for(rowNo = 0; rowNo < rows;rowNo++)
            {
                XSSFRow row = sheet.getRow(rowNo);
                if(row != null){
                    int cells = row.getPhysicalNumberOfCells(); // 해당 Row에 사용자가 입력한 셀의 수를 가져온다.

                    if(rowNo==0) {
                        addressIndex = searchHeaderAddressExcelIndex(row);
                        continue;
                    }
                    XSSFCell cell = row.getCell(addressIndex);
                    String value = "";
                    if (cell == null)  continue;
                    switch(cell.getCellType()) {
                                    case STRING:
                                        value = cell.getStringCellValue() + "";
                                        break;
                                    case NUMERIC:
                                        value = cell.getNumericCellValue() + "";
                                        break;
                                    case FORMULA:
                                        value = cell.getCellFormula();
                                        break;
                                    case BOOLEAN:
                                        value = cell.getBooleanCellValue() + "";
                                        break;
                        }
                        locationList.add(value);
                    }
                }
            }
            KakaoApiManagerDto kakaoApiManagerDto = new KakaoApiManagerDto();
        rtnLocationList = kakaoApiManagerDto.KakaoOpenApifetch(locationList);
        return rtnLocationList;
    }
    /* 엑셀의 주소위치의 인덱스 찾기*/
    public int searchHeaderAddressExcelIndex(XSSFRow row) {
       int cellIndex = 0;
        String headerName ="";
       do{
           XSSFCell cell = row.getCell(cellIndex);
           headerName = cell.getStringCellValue() + "";
           cellIndex++;

       }while(!headerName.contains("주소"));
        System.out.println("cellIndex >> " + cellIndex);
       return cellIndex-1;
    }
}
