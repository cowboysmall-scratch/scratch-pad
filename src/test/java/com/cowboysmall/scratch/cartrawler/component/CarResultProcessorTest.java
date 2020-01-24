package com.cowboysmall.scratch.cartrawler.component;

import com.cowboysmall.scratch.cartrawler.model.CarResult;
import com.cowboysmall.scratch.cartrawler.model.FuelPolicy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CarResultProcessorTest {

    public static final CarResult CAR_RESULT_01 =
            new CarResult("Volkswagen Polo", "NIZA", "EDMR", 12.81d, FuelPolicy.FULLEMPTY);
    public static final CarResult CAR_RESULT_02 =
            new CarResult("Ford C-Max Diesel", "NIZA", "CMMD", 22.04d, FuelPolicy.FULLEMPTY);
    public static final CarResult CAR_RESULT_03 =
            new CarResult("Volkswagen Up", "NIZA", "MDMR", 9.78d, FuelPolicy.FULLEMPTY);
    public static final CarResult CAR_RESULT_04 =
            new CarResult("Renault Scenic Diesel", "NIZA", "JGAD", 93.67d, FuelPolicy.FULLEMPTY);
    public static final CarResult CAR_RESULT_05 =
            new CarResult("Ford Focus Estate", "AVIS", "CWMR", 291.28d, FuelPolicy.FULLFULL);
    public static final CarResult CAR_RESULT_06 =
            new CarResult("Volkswagen Polo", "AVIS", "EDMR", 160.73d, FuelPolicy.FULLFULL);
    public static final CarResult CAR_RESULT_07 =
            new CarResult("Peugeot 107", "AVIS", "MCMR", 145.52d, FuelPolicy.FULLFULL);
    public static final CarResult CAR_RESULT_08 =
            new CarResult("Toyota Avensis", "AVIS", "IDAR", 465.34d, FuelPolicy.FULLFULL);

    public static final CarResult CAR_RESULT_09 =
            new CarResult("Ford Galaxy Diesel", "AVIS", "FVMD", 520.49d, FuelPolicy.FULLFULL);
    public static final CarResult CAR_RESULT_10 =
            new CarResult("Mercedes A Class", "AVIS", "ICAV", 310.56d, FuelPolicy.FULLFULL);


    @Test
    public void testCarResultProcessor_ProcessCarResults_Partitioning_Corporate() {

        List<CarResult> list = new ArrayList<>(
                asList(
                        CAR_RESULT_01,
                        CAR_RESULT_02,
                        CAR_RESULT_03,
                        CAR_RESULT_04,
                        CAR_RESULT_05,
                        CAR_RESULT_06,
                        CAR_RESULT_07,
                        CAR_RESULT_08
                )
        );

        List<CarResult> carResults = new CarResultProcessorImpl(false).processCarResults(list);

        assertThat(carResults.size(), is(8));

        assertThat(carResults.get(0).isCorporate(), is(true));
        assertThat(carResults.get(1).isCorporate(), is(true));
        assertThat(carResults.get(2).isCorporate(), is(true));
        assertThat(carResults.get(3).isCorporate(), is(true));

        assertThat(carResults.get(4).isCorporate(), is(false));
        assertThat(carResults.get(5).isCorporate(), is(false));
        assertThat(carResults.get(6).isCorporate(), is(false));
        assertThat(carResults.get(7).isCorporate(), is(false));
    }

    @Test
    public void testCarResultProcessor_ProcessCarResults_Partitioning_SIPP() {

        List<CarResult> list = new ArrayList<>(
                asList(
                        CAR_RESULT_01,
                        CAR_RESULT_02,
                        CAR_RESULT_03,
                        CAR_RESULT_04,
                        CAR_RESULT_05,
                        CAR_RESULT_06,
                        CAR_RESULT_07,
                        CAR_RESULT_08
                )
        );

        List<CarResult> carResults = new CarResultProcessorImpl(false).processCarResults(list);

        assertThat(carResults.size(), is(8));

        assertThat(carResults.get(0).isMini(), is(true));
        assertThat(carResults.get(1).isEconomy(), is(true));
        assertThat(carResults.get(2).isCompact(), is(true));
        assertThat(carResults.get(3).isOther(), is(true));

        assertThat(carResults.get(4).isMini(), is(true));
        assertThat(carResults.get(5).isEconomy(), is(true));
        assertThat(carResults.get(6).isCompact(), is(true));
        assertThat(carResults.get(7).isOther(), is(true));
    }

    @Test
    public void testCarResultProcessor_ProcessCarResults_NoMedian() {

        List<CarResult> list = new ArrayList<>(
                asList(
                        CAR_RESULT_01,
                        CAR_RESULT_02,
                        CAR_RESULT_03,
                        CAR_RESULT_04,
                        CAR_RESULT_05,
                        CAR_RESULT_06,
                        CAR_RESULT_07,
                        CAR_RESULT_08,
                        CAR_RESULT_09,
                        CAR_RESULT_10
                )
        );

        List<CarResult> carResults = new CarResultProcessorImpl(false).processCarResults(list);

        assertThat(carResults.size(), is(10));

        assertThat(carResults.get(0).isMini(), is(true));
        assertThat(carResults.get(1).isEconomy(), is(true));
        assertThat(carResults.get(2).isCompact(), is(true));
        assertThat(carResults.get(3).isOther(), is(true));
        assertThat(carResults.get(4).isOther(), is(true));
        assertThat(carResults.get(5).isOther(), is(true));

        assertThat(carResults.get(6).isMini(), is(true));
        assertThat(carResults.get(7).isEconomy(), is(true));
        assertThat(carResults.get(8).isCompact(), is(true));
        assertThat(carResults.get(9).isOther(), is(true));
    }

    @Test
    public void testCarResultProcessor_ProcessCarResults_Median() {

        List<CarResult> list = new ArrayList<>(
                asList(
                        CAR_RESULT_01,
                        CAR_RESULT_02,
                        CAR_RESULT_03,
                        CAR_RESULT_04,
                        CAR_RESULT_05,
                        CAR_RESULT_06,
                        CAR_RESULT_07,
                        CAR_RESULT_08,
                        CAR_RESULT_09,
                        CAR_RESULT_10
                )
        );

        List<CarResult> carResults = new CarResultProcessorImpl(true).processCarResults(list);

        assertThat(carResults.size(), is(9));

        assertThat(carResults.get(0).isMini(), is(true));
        assertThat(carResults.get(1).isEconomy(), is(true));
        assertThat(carResults.get(2).isCompact(), is(true));
        assertThat(carResults.get(3).isOther(), is(true));
        assertThat(carResults.get(4).isOther(), is(true));

        assertThat(carResults.get(5).isMini(), is(true));
        assertThat(carResults.get(6).isEconomy(), is(true));
        assertThat(carResults.get(7).isCompact(), is(true));
        assertThat(carResults.get(8).isOther(), is(true));
    }
}
