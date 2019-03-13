package com.sys.common.dataconver;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.util.JSONUtils;

public class DefaultDefaultValueProcessor
  implements DefaultValueProcessor
{
  public Object getDefaultValue(Class type)
  {
    if (JSONUtils.isArray(type))
      return new JSONArray();
    if (JSONUtils.isNumber(type)) {
      if (JSONUtils.isDouble(type)) {
        return new Double(0.0D);
      }
      return new Integer(0);
    }
    if (JSONUtils.isBoolean(type))
      return Boolean.FALSE;
    if (JSONUtils.isString(type)) {
      return "";
    }
    return JSONNull.getInstance();
  }
}

