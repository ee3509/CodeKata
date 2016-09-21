/*
 * Copyright (c) 2016.
 *  This is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or any later version.
 */

package codeKata04.model;

import codeKata04.interfaces.ImportableDataParameter;

public class InitParameter implements ImportableDataParameter
{
  private String _name;
  private Object _value;
  private int _idx;

  @Override
  public String getName() {
    return _name;
  }

  @Override
  public Object getValue() {
    return _value;
  }

  @Override
  public void setValue(Object value) {
    _value = value;
  }

  @Override
  public int getIdx() {
    return _idx;
  }

  public InitParameter(String name, int idx) {
    _name = name;
    _idx = idx;
  }
}
