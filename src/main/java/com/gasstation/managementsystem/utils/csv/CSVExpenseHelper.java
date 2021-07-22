package com.gasstation.managementsystem.utils.csv;

import com.gasstation.managementsystem.model.dto.expense.ExpenseDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.web.multipart.MultipartFile;

import java.io.PrintWriter;
import java.util.List;


public class CSVExpenseHelper {
    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }


    public static void writeExpenseToCSV(PrintWriter writer, List<ExpenseDTO> expenseDTOS) {
        try (
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("ID", "REASON", "AMOUNT", "DATE", "NOTE", "STATION", "FUEL_IMPORT"));
        ) {
            for (ExpenseDTO expense : expenseDTOS) {
                csvPrinter.printRecord(expense.getId(),
                        expense.getReason(),
                        expense.getAmount(),
                        expense.getDate(),
                        expense.getNote(),
                        expense.getStation().getName(),
                        expense.getFuelImport().getName());
            }
            csvPrinter.flush();
        } catch (Exception e) {
            System.out.println("Writing CSV error!");
            e.printStackTrace();
        }
    }

}
