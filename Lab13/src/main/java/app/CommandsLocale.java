package app;

import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.lang.System.out;

public class CommandsLocale
{
    private static final String baseName = "Messages";
    private static Locale locale;
    private static ResourceBundle resourceBundle;

    public static String message(String key, String ... arguments) {
        String pattern = resourceBundle.getString(key);
        String message = new MessageFormat(pattern).format(arguments);
        return message;
    }
    public static void setLocale(String languageTag) {
        Locale.setDefault(Locale.forLanguageTag(languageTag));
        CommandsLocale.locale = Locale.forLanguageTag(languageTag);
        CommandsLocale.resourceBundle = ResourceBundle.getBundle(baseName, locale);
        message("locale.set", languageTag);
    }

    public static void displayLocales() {
        out.println(CommandsLocale.message("locales"));
        for (Locale locale : Locale.getAvailableLocales())
        {
            out.println(locale.toString());
        }
    }
    public static void localeInfo() {
        out.println(CommandsLocale.message("locale.set", CommandsLocale.getLocale().getDisplayName()));
        Currency currency  = Currency.getInstance(CommandsLocale.getLocale());
        DateFormatSymbols locale = DateFormatSymbols.getInstance(CommandsLocale.getLocale());
        out.println("Country: " + CommandsLocale.getLocale().getCountry());
        out.println("Language: " + CommandsLocale.getLocale().getLanguage());
        out.println("Currency: " + currency.getCurrencyCode());
        out.print("Week Days: ");
        for (String string : locale.getWeekdays()) {
            out.print(string + " ");
        }
        out.println();
        out.print("Months: ");
        for (String string : locale.getMonths()) {
            out.print(string + " ");
        }
        out.println();
        out.print("Today: ");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy", CommandsLocale.getLocale());
        LocalDateTime now = LocalDateTime.now();
        out.println(dtf.format(now));
    }

    public static Locale getLocale()
    {
        return CommandsLocale.locale;
    }
}