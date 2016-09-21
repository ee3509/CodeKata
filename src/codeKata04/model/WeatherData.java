/*
 * Copyright (c) 2016.
 *  This is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or any later version.
 */

package codeKata04.model;

import codeKata04.interfaces.ImportableData;
import codeKata04.interfaces.ImportableDataFactory;
import codeKata04.interfaces.ImportableDataParameter;

import java.util.ArrayList;
import java.util.List;

import static codeKata04.model.WeatherData.FIELDS.DAY;
import static codeKata04.model.WeatherData.FIELDS.MAXTMP;
import static codeKata04.model.WeatherData.FIELDS.MINTMP;


public class WeatherData implements ImportableData
{

  enum FIELDS{
    DAY,
    MAXTMP,
    MINTMP
  }

  public int getDay() {
  return _day;
}

  public int getMaxTmp() {
    return _maxTmp;
  }

  public int getMinTmp() {
    return _minTmp;
  }

  private int _day=-1;
  private int _maxTmp=-1;
  private int _minTmp=-1;

  private WeatherData(int day, int maxTmp, int minTmp) {
    _day = day;
    _maxTmp = maxTmp;
    _minTmp = minTmp;
  }

  @Override
  public String toString() {
    return _day + " " + _maxTmp + " " + _minTmp;
  }

  public static class WeatherDataFactory implements ImportableDataFactory
  {

    static String REGEX = "^ +(\\d+) +(\\d+) +(\\d+).+$";

    @Override
    public ImportableData createInstance(List<ImportableDataParameter> parameterList) {
      int day=-1;
      int maxTmp =-1;
      int minTmp =-1;

      for (ImportableDataParameter initParameter : parameterList) {
        switch (FIELDS.valueOf(initParameter.getName())) {
          case DAY : day = Integer.valueOf((String) initParameter.getValue()); break;
          case MAXTMP: maxTmp = Integer.valueOf((String) initParameter.getValue()); break;
          case MINTMP: minTmp = Integer.valueOf((String) initParameter.getValue()); break;
        }
      }

      return new WeatherData(day,maxTmp,minTmp);

    }

    @Override
    public List<ImportableDataParameter> getInitParameters() {
      List<ImportableDataParameter> list = new ArrayList<ImportableDataParameter>();

      list.add(new InitParameter(String.valueOf(DAY), 1));
      list.add(new InitParameter(String.valueOf(MAXTMP), 2));
      list.add(new InitParameter(String.valueOf(MINTMP), 3));

      return list;

    }

    @Override
    public String getRegex() {
      return REGEX;
    }
  }
}
