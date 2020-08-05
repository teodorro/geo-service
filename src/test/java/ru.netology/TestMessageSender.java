package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMessageSender {
    private static final String rusIp = "172";
    private static final String usaIp = "96";


    @ParameterizedTest
    @ValueSource(ints = {5})
    public void test_MessageSenderImpl_OnlyRus_WhenIpRus(int val){
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito
                .when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Фыва олдж");

        for (int i = 0; i < val; i++) {
            for (int j = 0; j < val; j++) {
                for (int k = 0; k < val; k++) {
                    String ip = rusIp + "." + i + "." + j + "." + k;
                    GeoService geoService = Mockito.mock(GeoServiceImpl.class);
                    Mockito
                            .when(geoService.byIp(ip))
                            .thenReturn(new Location(null, Country.RUSSIA, null, 0));
                    MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

                    Map<String, String> headers = new HashMap<String, String>();
                    headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);;
                    String message = messageSender.send(headers);
                    boolean res = message.replaceAll("[ .,]", "").matches("[А-Яа-я]*");

                    Assertions.assertEquals(true, res);
                }
            }
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {5})
    public void test_MessageSenderImpl_OnlyUsa_WhenIpUsa(int val){
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito
                .when(localizationService.locale(Country.USA))
                .thenReturn("Asdf hjkl");

        for (int i = 0; i < val; i++) {
            for (int j = 0; j < val; j++) {
                for (int k = 0; k < val; k++) {
                    String ip = rusIp + "." + i + "." + j + "." + k;
                    GeoService geoService = Mockito.mock(GeoServiceImpl.class);
                    Mockito
                            .when(geoService.byIp(ip))
                            .thenReturn(new Location(null, Country.USA, null, 0));
                    MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

                    Map<String, String> headers = new HashMap<String, String>();
                    headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);;
                    String message = messageSender.send(headers);
                    boolean res = message.replaceAll("[ .,]", "").matches("[A-Za-z]*");

                    Assertions.assertEquals(true, res);
                }
            }
        }
    }


}