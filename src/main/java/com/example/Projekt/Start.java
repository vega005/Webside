package com.example.Projekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Start {

    GameRepo gameRepo;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public Start(GameRepo gameRepo, JdbcTemplate jdbcTemplate) {
        this.gameRepo = gameRepo;
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener(ApplicationReadyEvent.class)
    void run() {

        initDb();

        GameItem game1 = new GameItem(1L, "Mass Effect", Type.SCIENCE_FICTION, "BioWare", "Fabuła gry " +
                "osadzona jest w Drodze Mlecznej w 2183 roku, kiedy ludzkość poznała już tajniki lotów międzygwiezdnych, " +
                "możliwych dzięki urządzeniom znanym jako przekaźniki masy, będącymi pozostałością po wymarłej rasie " +
                "obcych – protean.");
        gameRepo.save(game1);


        GameItem game2 = new GameItem(2L, "Unreal Tournament", Type.FPP, " Epic Games", "Unreal Tournament " +
                "to strzelanka pierwszoosobowa na arenie, w której głównym celem są bezpośrednie pojedynki na śmierć i życie " +
                "w trybie wieloosobowym. Kampania dla pojedynczego gracza to seria meczy na arenie rozgrywanych z botami, w " +
                "których gracz rywalizuje o tytuł Grand Championa.");
        gameRepo.save(game2);

        GameItem game3 = new GameItem(3L, "Wiedzmin", Type.RPG, "CD Projekt Red", "Wiedźmin " +
                "jest fabularną grą akcji z otwartym światem. Gracz steruje postacią Geralta z Rivii z perspektywy trzeciej " +
                "osoby. W niektórych fragmentach gry gracz wciela się w postać Ciri. Poza poruszaniem się po lądzie można " +
                "także pływać zarówno na jak i pod powierzchnią wody.");
        gameRepo.save(game3);

        GameItem game4 = new GameItem(4L, "Dying Light", Type.HORROR, "Techland", "Głównym bohaterem gry " +
                "jest Kyle Crane, który na polecenie fikcyjnej organizacji GRE (Globalny Resort Epidemiologiczny) trafia do " +
                "miasta Harran. Na miejscu okazuje się, że mieszkańcy zostali zarażeni chorobą, która zmieniła ich w zombie. " +
                "Z opresji ratują go nieznani wybawcy, jak się okazuje należący do grupy ocalałych. Celem bohatera staje się " +
                "odnalezienie pliku zawierającego informacje o potencjalnym lekarstwie.");
        gameRepo.save(game4);

        GameItem game5 = new GameItem(5L, "Syberia ", Type.ADVENTURE, "Microïds", "Gracz kieruje amerykańską " +
                "prawniczką Kate Walker, która odwiedza kolejne lokacje, prowadzi rozmowy z napotkanymi postaciami, odnajduje " +
                "potrzebne przedmioty i rozwiązuje zagadki logiczne. W czasie dialogów z postaciami gracz ma do wyboru różne " +
                "tematy rozmów. Bohaterka posługuje się też telefonem komórkowym, aby wykonywać rozmowy lub odbierać połączenia.");
        gameRepo.save(game5);

    }

    /**
     * Utworzenie tabeli.
     */
    private void initDb() {
        System.out.println(String.format("****** Creating table: %s, and Inserting test data ******", "Game"));

        String sqlStatements[] = {
                "drop table game if exists",
                "create table game(id serial,type varchar(50), title varchar(255),production varchar(255), description text)",
        };

        Arrays.asList(sqlStatements).stream().forEach(sql -> {
            System.out.println(sql);
            jdbcTemplate.execute(sql);
        });
    }
}
