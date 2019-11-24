import com.fasterxml.jackson.core.JsonProcessingException;

public class MainApplication {

    public static void main(String[] args){
        co.com.mdt.helpers.Utils.printDoubleSeparator();
        co.com.mdt.helpers.Utils.printMessage("Start main");
        co.com.mdt.helpers.Utils.printSeparator();


        co.com.mdt.helpers.Utils.printMessage("Starting generate XLXS file");
        co.com.mdt.usecases.GenerateExcelUseCase generateExcel =
                new co.com.mdt.usecases.GenerateExcelUseCase();

        generateExcel.generateFromJson();



        co.com.mdt.helpers.Utils.printSeparator();
        co.com.mdt.helpers.Utils.printMessage("End main");
        co.com.mdt.helpers.Utils.printDoubleSeparator();
    }


}
