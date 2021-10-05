package test.spotify.web;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import test.spotify.mobile.pages.WebLandingScreenRegistrationsPage;

import java.util.regex.Pattern;

public class SpotifyWebUi {

    public static void main(String [] args) {
        String a = "Preheat the oven for 10 minutes at 180C. Grease a rectangular tin for baking the cake and keep it aside. Take a big vessel and sift the two kinds of flour with baking powder, coffee, powder, cinnamon powder and salt for at least three times.\n" +
                "    Take another vessel and beat powdered sugar and oil till it is light and fluffy , for about 10 minutes.\n" +
                "    Add the curd, coffee decoction, vanilla essence and vinegar in the sugar and oil mixture and stir it thoroughly. Keep 1 tsp. of sugar aside.\n" +
                "    Gently mix in the liquid mix in the dry flour. Mix the whole batter very lightly till it resembles a uniform paste. Do not over mix, just 10-12 strokes will do.\n" +
                "    Pour the prepared batter in the baking tin and sprinkle 1 tsp. of sugar over it. Bake it at 180C for about 25-30 minutes or till done.\n" +
                "    Insert a clean knife and take it out, if it comes clean the cinnamon tea cake is ready, else bake it for few more minutes.\n" +
                "    Take out of the oven and let it cool on a wire rack. Cut it in desired shape and serve with tea or coffee. Stays good for a week.";

        String regex = a.replaceAll("\\.\\s?","\\.\n");
        System.out.println(regex);
    }


    public WebLandingScreenRegistrationsPage landingScreenRegistrationsPage;
    public SpotifyWebUi(WebDriver driver) {
        this.landingScreenRegistrationsPage = new WebLandingScreenRegistrationsPage(driver);
    }
}
