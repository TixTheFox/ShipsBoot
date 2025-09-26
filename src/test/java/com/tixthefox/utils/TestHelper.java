package com.tixthefox.utils;

public class TestHelper {
  public static String NORMAL_JSON =
          "{" +
                  "\"name\": \"123456789\"," +
                  "\"planet\":\"Earth\"," +
                  "\"shipType\": \"MILITARY\"," +
                  "\"prodDate\" : 32998274577071," +
                  "\"isUsed\":false," +
                  "\"speed\":0.8," +
                  "\"crewSize\": 14" +
                  "}";

  public static String NORMAL_JSON_WITH_ID =
          "{" +
                  "\"ID\": \"23\"," +
                  "\"name\": \"123456789\"," +
                  "\"planet\":\"Earth\"," +
                  "\"shipType\": \"MILITARY\"," +
                  "\"prodDate\" : 32998274577071," +
                  "\"isUsed\":false," +
                  "\"speed\":0.8," +
                  "\"crewSize\": 14" +
                  "}";

  public static String NORMAL_JSON_WITH_RATING =
          "{" +
                  "\"name\": \"123456789\"," +
                  "\"planet\":\"Earth\"," +
                  "\"shipType\": \"MILITARY\"," +
                  "\"prodDate\" : 32998274577071," +
                  "\"isUsed\":false," +
                  "\"speed\":0.8," +
                  "\"crewSize\": 14," +
                  "\"rating\": 5.12" +
                  "}";

  public static String NO_SPEED =
          "{" +
                  "\"name\": \"123456789\"," +
                  "\"planet\":\"Earth\"," +
                  "\"shipType\": \"MILITARY\"," +
                  "\"prodDate\" : 32998274577071," +
                  "\"isUsed\":false," +
                  "\"crewSize\": 14" +
                  "}";

  public static String EMPTY_NAME =
          "{" +
                  "\"name\": \"\"," +
                  "\"planet\":\"Earth\"," +
                  "\"shipType\": \"MILITARY\"," +
                  "\"prodDate\" : 32998274577071," +
                  "\"isUsed\":false," +
                  "\"crewSize\": 14" +
                  "}";

  public static String NEGATIVE_PROD_DATE =
          "{" +
                  "\"name\": \"123456789\"," +
                  "\"planet\":\"Earth\"," +
                  "\"shipType\": \"MILITARY\"," +
                  "\"prodDate\" : -32998274577071," +
                  "\"isUsed\":false," +
                  "\"crewSize\": 14" +
                  "}";


  public static String TOO_BIG_PROD_DATE =
          "{" +
                  "\"name\": \"123456789\"," +
                  "\"planet\":\"Earth\"," +
                  "\"shipType\": \"MILITARY\"," +
                  "\"prodDate\" : 35682531451000," +
                  "\"isUsed\":false," +
                  "\"crewSize\": 14" +
                  "}";

  public static String NEGATIVE_CREW_SIZE =
          "{" +
                  "\"name\": \"123456789\"," +
                  "\"planet\":\"Earth\"," +
                  "\"shipType\": \"MILITARY\"," +
                  "\"prodDate\" : 32998274577071," +
                  "\"isUsed\":false," +
                  "\"speed\":0.8," +
                  "\"crewSize\": -14" +
                  "}";

  public static String TOO_BIG_CREW_SIZE =
          "{" +
                  "\"name\": \"123456789\"," +
                  "\"planet\":\"Earth\"," +
                  "\"shipType\": \"MILITARY\"," +
                  "\"prodDate\" : 32998274577071," +
                  "\"isUsed\":false," +
                  "\"speed\":0.8," +
                  "\"crewSize\": 100000000" +
                  "}";


  public static String TOO_BIG_PLANET_NAME =
          "{" +
                  "\"name\": \"123456789\"," +
                  "\"planet\":\"EarthEarthEarthEarthEarthEarthEarthEarthEarthEarthEarthEarthEarth\"," +
                  "\"shipType\": \"MILITARY\"," +
                  "\"prodDate\" : 32998274577071," +
                  "\"isUsed\":false," +
                  "\"speed\":0.8," +
                  "\"crewSize\": 14" +
                  "}";

  public static String NO_IS_USED =
          "{" +
                  "\"name\": \"123456789\"," +
                  "\"planet\":\"Earth\"," +
                  "\"shipType\": \"MILITARY\"," +
                  "\"prodDate\" : 32998274577071," +
                  "\"speed\":0.8," +
                  "\"crewSize\": 14" +
                  "}";

  public static String IS_USED_TRUE =
          "{" +
                  "\"name\": \"123456789\"," +
                  "\"planet\":\"Earth\"," +
                  "\"shipType\": \"MILITARY\"," +
                  "\"prodDate\" : 32998274577071," +
                  "\"isUsed\":true," +
                  "\"speed\":0.8," +
                  "\"crewSize\": 14" +
                  "}";
}
