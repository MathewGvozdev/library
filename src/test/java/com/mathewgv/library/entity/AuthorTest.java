package com.mathewgv.library.entity;

import com.mathewgv.library.entity.Author;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    private static final Author IVAN = new Author(1, "Иван", "Иванов");
    private static final Author PETR = new Author(2, "Петр", "Петров");

    @Test
    void getId() {
        var ivanId = IVAN.getId();
        var petrId = PETR.getId();
        assertEquals(ivanId, 1);
        assertEquals(petrId, 2);
    }

    @Test
    void setId() {
        IVAN.setId(11);
        PETR.setId(12);
        var ivanId = IVAN.getId();
        var petrId = PETR.getId();
        assertEquals(ivanId, 11);
        assertEquals(petrId, 12);
    }

    @Test
    void getFirstName() {
        var ivanFirstName = IVAN.getFirstName();
        var petrFirstName = PETR.getFirstName();
        assertEquals(ivanFirstName, "Иван");
        assertEquals(petrFirstName, "Петр");
    }

    @Test
    void setFirstName() {
        IVAN.setFirstName("Иванка");
        PETR.setFirstName("Петра");
        var ivanFirstName = IVAN.getFirstName();
        var petrFirstName = PETR.getFirstName();
        assertEquals(ivanFirstName, "Иванка");
        assertEquals(petrFirstName, "Петра");
    }

    @Test
    void getSurname() {
        var ivanSurname = IVAN.getSurname();
        var petrSurname = PETR.getSurname();
        assertEquals(ivanSurname, "Иванов");
        assertEquals(petrSurname, "Петров");
    }

    @Test
    void setSurname() {
        IVAN.setSurname("Афанасенков");
        PETR.setSurname("Сумбуров");
        var ivanSurname = IVAN.getSurname();
        var petrSurname = PETR.getSurname();
        assertEquals(ivanSurname, "Афанасенков");
        assertEquals(petrSurname, "Сумбуров");
    }
}