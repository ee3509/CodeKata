/*
 * Copyright (c) 2016.
 *  This is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or any later version.
 */

package codeKata04;

import codeKata04.interfaces.ImportableData;
import codeKata04.model.LeagueTableEntry;
import codeKata04.model.WeatherData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Main
{
  public static void main(String[] args) {
    TreeMap spreadMap = new TreeMap<>();
    List<ImportableData> list = new ArrayList<ImportableData>();
    DataImporter dataImporter = new DataImporter();

    //// Weather

    dataImporter.importDataToList(new File("data/weather.dat"),
                                  list,
                                  new WeatherData.WeatherDataFactory());

    for (ImportableData weatherData : list) {
      spreadMap.put(findSpread((WeatherData) weatherData), weatherData);
    }

    System.out.println("Min Spread :" + spreadMap.firstEntry());

    // Weather

    spreadMap.clear();
    list.clear();

    dataImporter.importDataToList(new File("data/football.dat"),
                                  list,
                                  new LeagueTableEntry.LeagueTableEntryFactory());

    for (ImportableData importableData : list) {
      spreadMap.put(Math.abs(findGoalDifference((LeagueTableEntry) importableData)),
                    importableData);
    }

    System.out.println("Min Goal Diff :" + spreadMap.firstEntry());


  }

  static int findSpread(WeatherData data) {
    return data.getMaxTmp() - data.getMinTmp();
  }

  static int findGoalDifference(LeagueTableEntry tableEntry) {
    return tableEntry.getForGoals() - tableEntry.getAgainstGoals();
  }





}
