package com.kodilla.servicefrontend.service;

import com.kodilla.servicefrontend.domain.Hikingtrail;
import com.kodilla.servicefrontend.domain.HikingtrailsType;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class HikingtrailService {

    private final Set hikingtrails;
    private static HikingtrailService hikingtrailService;

    private HikingtrailService() {
        this.hikingtrails = exampleData();
    }

    public static HikingtrailService getInstance() {
        if (hikingtrailService == null) {
            hikingtrailService = new HikingtrailService();
        }
        return hikingtrailService;
    }

    public Set getHikingtrails() {
        return new HashSet<>(hikingtrails);
    }

    public void addHikingTrail(Hikingtrail hikingtrail) {
        this.hikingtrails.add(hikingtrail);
    }

    private Set exampleData() {
        Set hikingtrails = new HashSet<>();
        hikingtrails.add(new Hikingtrail("Szlak wygasłych wulkanów", "Legnickie Pole", "Złotoryja",  "85 km", HikingtrailsType.YELLOW));
        hikingtrails.add(new Hikingtrail("Szlak Tysiąclecia Turystyki", "Bytom (Dworzec PKP)", "Zawiercie, Skarżyce", "101 km", HikingtrailsType.GREEN));
        hikingtrails.add(new Hikingtrail("Szlak graniczny", "Słonecznik", "Tłumaczów", "101 km", HikingtrailsType.GREEN));
        hikingtrails.add(new Hikingtrail("Główny szlak świętokrzyski", "Gołoszyce", "Kuźniaki", "105 km", HikingtrailsType.RED));
        hikingtrails.add(new Hikingtrail("Szlak Wałbrzych - Szklarska Poręba", "Wałbrzych (Dworzec PKP)", "Szklarska Poręba Górna", "105 km", HikingtrailsType.GREEN));
        hikingtrails.add(new Hikingtrail("Szlak Trzech Pogórz", "Siedliska", "Dynów", "120 km", HikingtrailsType.YELLOW));
        hikingtrails.add(new Hikingtrail("Mały Szlak Beskidzki", "Bielsko-Biała, Straconka", "Luboń Wielki", "137 km", HikingtrailsType.RED));
        hikingtrails.add(new Hikingtrail("Szlak Brzeźnica - Kacwin", "Brzeźnica (okolice Krakowa)", "Kacwin", "137 km", HikingtrailsType.BLUE));
        hikingtrails.add(new Hikingtrail("Dookoła Kotliny Jeleniogórskiej", "Szklarska Poręba", "Szklarska Poręba", "137 km", HikingtrailsType.DIFFERENT));
        hikingtrails.add(new Hikingtrail("Szlak Zamków Piastowskich", "Zamek Grodno", "Zamek Grodziec", "146 km", HikingtrailsType.GREEN));
        hikingtrails.add(new Hikingtrail("Szlak Warowni Jurajskich", "Mstów", "Rudawa", "150 km", HikingtrailsType.BLUE));
        hikingtrails.add(new Hikingtrail("Szlak Orlich Gniazd", "Kraków, Krowodrza", "Częstochowa", "164 km", HikingtrailsType.RED));
        hikingtrails.add(new Hikingtrail("Szlak Tarnów - Wielki Rogacz", "Tarnów", "Wielki Rogacz", "184 km", HikingtrailsType.BLUE));
        hikingtrails.add(new Hikingtrail("Szlak Przełęcz Puchacza - Mokrzyce", "Przełęcz Puchacza", "Mokrzyce", "217 km", HikingtrailsType.YELLOW));
        hikingtrails.add(new Hikingtrail("Główny Szlak Beskidu Wyspowego", "Limanowa", "Limanowa", "320 km", HikingtrailsType.DIFFERENT));
        hikingtrails.add(new Hikingtrail("Niebieski Szlak Sudecki", "Szklarska Poręba, Dolna", "Pasterka (schronisko)", "368 km", HikingtrailsType.BLUE));
        hikingtrails.add(new Hikingtrail("Szlak Nadmorski", "Świnoujście", "Żarnowiec", "380 km", HikingtrailsType.RED));
        hikingtrails.add(new Hikingtrail("Główny Szlak Sudecki", "Świeradów-Zdrój", "Prudnik", "440 km", HikingtrailsType.RED));
        hikingtrails.add(new Hikingtrail("Szlak Karpacki", "Rzeszów, Biała", "Grybów", "445 km", HikingtrailsType.BLUE));
        hikingtrails.add(new Hikingtrail("Główny Szlak Beskidzki", "Ustroń", "Wołosate", "519 km", HikingtrailsType.RED));
        return hikingtrails;
    }

    public Set findByName(String name) {
        return (Set) hikingtrails.stream().filter(hikingtrail ->  hikingtrail.getClass().getName().contains(name)).collect(Collectors.toSet());
    }


    public void save(Hikingtrail hikingtrail) {
        this.hikingtrails.add(hikingtrail);
    }

    public void delete(Hikingtrail hikingtrail) {
        this.hikingtrails.remove(hikingtrail);
    }
}