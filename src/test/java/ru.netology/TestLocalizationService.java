package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

public class TestLocalizationService {

    @Test
    public void testLocalizationServiceImp_Rus(){
        LocalizationService localizationService = new LocalizationServiceImpl();

        String message = localizationService.locale(Country.RUSSIA);

        boolean res = message.replaceAll("[ .,]", "").matches("[А-Яа-я]*");
        Assertions.assertEquals(true, res);
    }

    @Test
    public void testLocalizationServiceImp_Usa(){
        LocalizationService localizationService = new LocalizationServiceImpl();

        String message = localizationService.locale(Country.USA);

        boolean res = message.replaceAll("[ .,]", "").matches("[A-Za-z]*");
        Assertions.assertEquals(true, res);
    }
}
