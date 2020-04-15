package de.balvi.ope.core.integerexample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntValuePlanningEntityTest {

    IntValuePlanningEntity sut;

    @BeforeEach
    void setUp() {
        sut = new IntValuePlanningEntity(1);
    }

    @Test
    void check_IntValuePlanningEntity_is_1() {
        assertEquals(sut.value, 1);
    }
}