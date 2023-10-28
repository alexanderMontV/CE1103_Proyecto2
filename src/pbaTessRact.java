import java.io.File;

import net.sourceforge.tess4j.*;

public class pbaTessRact {
    public static void main(String[] args) {
        Tesseract tesseract = new Tesseract();
        try {
            tesseract.setDatapath("./tessdata/");
            tesseract.setLanguage("eng");

            // the path of your tess data folder
            // inside the extracted file
            String text= tesseract.doOCR(new File("./assets/scanTemp2.jpg"));

            // path of your image file
            System.out.print(text);

            for (int i=0;i<text.length();i++){
                Character c=text.charAt(i);
                System.out.println(c);
            }
        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }
}
