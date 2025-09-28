package com.tixthefox.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class LocalDateToLongConverter {
  public static LocalDate toLocalDate(Long dateInMillis) {
    return Instant.ofEpochMilli(dateInMillis).atZone(ZoneId.systemDefault()).toLocalDate();
  }

  public static Long toLong(LocalDate dateLocal) {
    return dateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
  }
}
