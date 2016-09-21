/*
 * Copyright (c) 2016.
 *  This is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or any later version.
 */

package codeKata04;

import codeKata04.interfaces.ImportableData;
import codeKata04.interfaces.ImportableDataFactory;
import codeKata04.interfaces.ImportableDataParameter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataImporter
{

  /**
   * Imports data into the targetlist
   * @param file the file holding the data
   * @param targetList the target list of the import
   * @param factory the factory with the information of init parameters and creating new instances
   */
  void importDataToList(File file, List<ImportableData> targetList, ImportableDataFactory factory) {
    try {

      List<String> lines = FileUtils.readLines(file);
      Pattern pattern = Pattern.compile(factory.getRegex());
      Matcher matcher;

      List<ImportableDataParameter> initParameters = factory.getInitParameters();

      for (String line : lines) {
        matcher = pattern.matcher(line);
        if (matcher.find()) {

          for (ImportableDataParameter initParameter : initParameters) {
            initParameter.setValue(matcher.group(initParameter.getIdx()));
          }

          ImportableData instance = factory.createInstance(initParameters);
          targetList.add(instance);

        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
