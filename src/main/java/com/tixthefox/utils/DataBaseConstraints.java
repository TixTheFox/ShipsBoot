package com.tixthefox.utils;

import java.time.LocalDate;

public class DataBaseConstraints {
  public static final int NAME_LENGTH_MAX = 50;
  public static final int PLANET_LENGTH_MAX = 50;
  public static final LocalDate DATE_MIN = LocalDate.of(2799, 12, 31);
  public static final LocalDate DATE_MAX = LocalDate.of(3020, 1, 1);
  public static final double SPEED_MIN = 0.01;
  public static final double SPEED_MAX = 0.99;
  public static final int CREW_SIZE_MIN = 1;
  public static final int CREW_SIZE_MAX = 9999;
}
