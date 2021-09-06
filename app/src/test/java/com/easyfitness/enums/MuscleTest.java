package com.easyfitness.enums;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import android.content.res.Resources;

import com.easyfitness.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MuscleTest {
    private static final int ABDOMINALS_NEW_ID = 0;
    private static final int BACK_NEW_ID = 1;
    private static final int TRICEPS_NEW_ID = 13;
    private static final int ENGLISH_ABDOMINALS_DATABASE_ID = 0;
    private static final int ENGLISH_BACK_DATABASE_ID = 1;
    private static final int ENGLISH_TRICEP_DATABASE_ID = 12;
    private static final int ENGLISH_TRICEP_NEWER_DATABASE_ID = 13;
    private static final int FRENCH_BACK_DATABASE_ID = 4;

    @Mock
    Resources mockEnglishResources;

    @Mock
    Resources mockFrenchResources;

    @Before
    public void setUp() throws Exception {
        when(mockEnglishResources.getString(R.string.abdominaux)).thenReturn("Abdominals");
        when(mockEnglishResources.getString(R.string.dorseaux)).thenReturn("Back");
        when(mockEnglishResources.getString(R.string.biceps)).thenReturn("Biceps");
        when(mockEnglishResources.getString(R.string.mollets)).thenReturn("Calves");
        when(mockEnglishResources.getString(R.string.pectoraux)).thenReturn("Chest");
        when(mockEnglishResources.getString(R.string.deltoids)).thenReturn("Deltoids");
        when(mockEnglishResources.getString(R.string.glutes)).thenReturn("Glutes");
        when(mockEnglishResources.getString(R.string.ischio_jambiers)).thenReturn("Hamstrings");
        when(mockEnglishResources.getString(R.string.obliques)).thenReturn("Obliques");
        when(mockEnglishResources.getString(R.string.quadriceps)).thenReturn("Quadriceps");
        when(mockEnglishResources.getString(R.string.shoulders)).thenReturn("Shoulders");
        when(mockEnglishResources.getString(R.string.adducteurs)).thenReturn("Thighs");
        when(mockEnglishResources.getString(R.string.trapezius)).thenReturn("Trapezius");
        when(mockEnglishResources.getString(R.string.triceps)).thenReturn("Triceps");

        when(mockFrenchResources.getString(R.string.abdominaux)).thenReturn("Abdominaux");
        when(mockFrenchResources.getString(R.string.dorseaux)).thenReturn("Dorseaux");
        when(mockFrenchResources.getString(R.string.biceps)).thenReturn("Biceps");
        when(mockFrenchResources.getString(R.string.mollets)).thenReturn("Mollets");
        when(mockFrenchResources.getString(R.string.pectoraux)).thenReturn("Pectoraux");
        when(mockFrenchResources.getString(R.string.deltoids)).thenReturn("Deltoids");
        when(mockFrenchResources.getString(R.string.glutes)).thenReturn("Glutes");
        when(mockFrenchResources.getString(R.string.ischio_jambiers)).thenReturn("Ischio-Jambiers");
        when(mockFrenchResources.getString(R.string.obliques)).thenReturn("Obliques");
        when(mockFrenchResources.getString(R.string.quadriceps)).thenReturn("Quadriceps");
        when(mockFrenchResources.getString(R.string.shoulders)).thenReturn("Shoulders");
        when(mockFrenchResources.getString(R.string.adducteurs)).thenReturn("Adducteurs");
        when(mockFrenchResources.getString(R.string.trapezius)).thenReturn("Trapezius");
        when(mockFrenchResources.getString(R.string.triceps)).thenReturn("Triceps");
    }

    @Test
    public void givenEnglishAbdominalDatabaseIdShouldReturnNewAbdominalId() {
        Muscle abdominals = Muscle.fromDatabaseId(ENGLISH_ABDOMINALS_DATABASE_ID, mockEnglishResources);
        assertEquals(ABDOMINALS_NEW_ID, abdominals.getNewId());
    }

    @Test
    public void givenEnglishBackDatabaseIdShouldReturnNewBackId() {
        Muscle back = Muscle.fromDatabaseId(ENGLISH_BACK_DATABASE_ID, mockEnglishResources);
        assertEquals(BACK_NEW_ID, back.getNewId());
    }

    @Test
    public void givenEnglishTricepsDatabaseIdShouldReturnNewTricepsId() {
        Muscle triceps = Muscle.fromDatabaseId(ENGLISH_TRICEP_DATABASE_ID, mockEnglishResources);
        assertEquals(TRICEPS_NEW_ID, triceps.getNewId());
    }

    @Test
    public void givenEnglishTricepsNewerDatabaseIdShouldReturnNewTricepsId() {
        Muscle triceps = Muscle.fromDatabaseId(ENGLISH_TRICEP_NEWER_DATABASE_ID, mockEnglishResources);
        assertEquals(TRICEPS_NEW_ID, triceps.getNewId());
    }

    @Test
    public void givenFrenchBackDatabaseIdShouldReturnNewBackId() {
        Muscle back = Muscle.fromDatabaseId(FRENCH_BACK_DATABASE_ID, mockFrenchResources);
        assertEquals(BACK_NEW_ID, back.getNewId());
    }

    @Test
    public void givenBackMuscleIdShouldReturnBack() {
        assertEquals(Muscle.BACK, Muscle.fromId(BACK_NEW_ID));
    }
}
