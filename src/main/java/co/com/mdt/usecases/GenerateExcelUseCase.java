package co.com.mdt.usecases;

import co.com.mdt.model.Price;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class GenerateExcelUseCase {

    private String FILE_NAME = "/tmp/MyExcel.xlsx";

    public void generateFromJson() {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Prices");
        co.com.mdt.helpers.Utils.printMessage("Get prices to generate XLSX file");
        List<Price> prices = getPrices();
        short df = 4;

        AtomicInteger rowNunber = new AtomicInteger();
        rowNunber.set(0);

        prices.forEach(price -> {

            Row row = sheet.createRow(rowNunber.getAndIncrement());
            sheet.setColumnWidth(1,
                    (price.getName().length() * 255) > sheet.getColumnWidth(1) ?
                            price.getName().length() * 255 : sheet.getColumnWidth(1)
            );

            Cell cell = row.createCell(0);
            CellStyle style = cell.getCellStyle();
            cell.setCellValue(price.getCode());

            cell = row.createCell(1);

            cell.setCellStyle(style);
            cell.setCellValue(price.getName());

            cell = row.createCell(2);
            sheet.setColumnWidth(2,
                    (price.getPriceBuy().toString().length() * 255) > sheet.getColumnWidth(2) ?
                            price.getPriceBuy().toString().length() * 255 : sheet.getColumnWidth(2)
            );

            style.setDataFormat(df);
            cell.setCellStyle(style);
            cell.setCellValue(price.getPriceBuy());


        });

        try {
            co.com.mdt.helpers.Utils.printMessage("Saving XLSX document");
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
            co.com.mdt.helpers.Utils.printMessage("Close XLSX document");
        } catch (FileNotFoundException e) {
            co.com.mdt.helpers.Utils.printMessage(e.getMessage());
        } catch (IOException e) {
            co.com.mdt.helpers.Utils.printMessage(e.getMessage());
        }

    }

    private List<Price> getPrices() {

        List<Price> prices = new ArrayList<>();


        prices.add(new Price(1000, "Milk", 1700d));
        prices.add(new Price(1001, "Orange Juice", 3300d));
        prices.add(new Price(1002, "Sugar 5k", 6300d));
        prices.add(new Price(1003, "Ma,rgarita snacks", 1100d));
        prices.add(new Price(2001, "Coffee 1000g", 10000d));
        prices.add(new Price(2002, "Salt 200g", 900d));
        prices.add(new Price(3002, "Corn Flakes 500g", 4230d));
        prices.add(new Price(4002, "Pringles mini", 2450d));
        prices.add(new Price(5002, "Cookies 10u", 1000d));
        prices.add(new Price(4001, "Cheese 400gr ", 1500d));
        prices.add(new Price(7001, "Asus Laptop", 999999999.23d));

        return prices;
    }

    public String getPricesToJsonString() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(getPrices());

    }
}