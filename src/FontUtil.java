import java.awt.*;
import java.io.IOException;
import java.util.Objects;

/**
 * Set a pixel style font
 */
public class FontUtil {
    //reference: https://stackoverflow.com/questions/5902229/custom-fonts-in-java/5902309#5902309
    public static Font setFont(String type, int style, int size){
        try {
            Font font ;
            if (type.equalsIgnoreCase("visitor1")){
                //read .tff file
                font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("visitor1.ttf")));

                //set font style
                font = font.deriveFont(style,size);
            }else if (type.equalsIgnoreCase("visitor2")){
                font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("visitor2.ttf")));
                font = font.deriveFont(style,size);
            }else{
                //if setting a font that is not "visitor"
                font = new Font("Default",style,size);
            }
            return font;
        } catch (FontFormatException| IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
