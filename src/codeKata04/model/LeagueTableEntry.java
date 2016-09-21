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

import static codeKata04.model.LeagueTableEntry.FIELDS.*;

public class LeagueTableEntry implements ImportableData
{
  enum FIELDS{
    TEAM,
    FOR,
    AGAINST
  }

  private String _teamName = "";
  private int _forGoals = -1;
  private int _againstGoals=-1;


  public LeagueTableEntry(String teamName, int forGoals, int againstGoals) {

    _teamName = teamName;
    _forGoals = forGoals;
    _againstGoals = againstGoals;
  }

  public String getTeamName() {
    return _teamName;
  }

  public int getForGoals() {
    return _forGoals;
  }

  public int getAgainstGoals() {
    return _againstGoals;
  }

  @Override
  public String toString() {
    return _teamName + " : " + _forGoals + " : "+ _againstGoals;
  }

  public static class LeagueTableEntryFactory implements ImportableDataFactory
  {

    @Override
    public ImportableData createInstance(List<ImportableDataParameter> parameterList) {
      String teamName ="";
      int forGoals =-1;
      int againstGoals =-1;

      for (ImportableDataParameter initParameter : parameterList) {
        switch (FIELDS.valueOf(initParameter.getName())) {
          case TEAM : teamName = (String) initParameter.getValue(); break;
          case FOR : forGoals = Integer.valueOf((String) initParameter.getValue()); break;
          case AGAINST: againstGoals = Integer.valueOf((String) initParameter.getValue()); break;
        }
      }

      return new LeagueTableEntry(teamName, forGoals, againstGoals);
    }

    @Override
    public String getRegex() {
      return "^\\s+\\d+.\\s+(\\w+)\\s+\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s+(\\d+)\\s+-\\s+(\\d+)+\\s+\\d+$";
    }

    @Override
    public List<ImportableDataParameter> getInitParameters() {
      List<ImportableDataParameter> list = new ArrayList<ImportableDataParameter>();

      list.add(new InitParameter(String.valueOf(TEAM), 1));
      list.add(new InitParameter(String.valueOf(FOR), 2));
      list.add(new InitParameter(String.valueOf(AGAINST), 3));

      return list;
    }
  }

}
