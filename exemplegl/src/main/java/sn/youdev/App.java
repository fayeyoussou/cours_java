package sn.youdev;

import com.ibm.icu.text.RuleBasedNumberFormat;
import sn.youdev.model.Etudiant;

import java.time.LocalDate;
import java.util.Locale;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        RuleBasedNumberFormat formatter =
                new RuleBasedNumberFormat(Locale.FRENCH, RuleBasedNumberFormat.SPELLOUT);
        System.out.println(formatter.format(230000000 )+"Frs CFA");
    }
}
