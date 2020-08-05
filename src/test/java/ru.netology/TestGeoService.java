package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

public class TestGeoService {
    private static final String rusIp = "172";
    private static final String usaIp = "96";

    @Test
    public void testGeoServiceImpl_RusIpMapRussia(){
        GeoService geoService = new GeoServiceImpl();

        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                for (int k = 0; k < 256; k++) {
                    String ip = rusIp + "." + i + "." + j + "." + k;

                    Location location = geoService.byIp(ip);

                    Assertions.assertEquals(Country.RUSSIA, location.getCountry());
                }
            }
        }
    }

    @Test
    public void testGeoServiceImpl_UsaIpMapUsa(){
        GeoService geoService = new GeoServiceImpl();

        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                for (int k = 0; k < 256; k++) {
                    String ip = usaIp + "." + i + "." + j + "." + k;

                    Location location = geoService.byIp(ip);

                    Assertions.assertEquals(Country.USA, location.getCountry());
                }
            }
        }
    }
}
