package com.example.connectingworld.utils;

public class Constants {
    private Constants() {
    }

    public static final String NEWS_REQUEST_URL = "https://content.guardianapis.com/search";

    public static final String FORMAT = "json";

    public static final String SHOW_TAGS = "contributor";

    // Parameters
    public static final String QUERY_PARAM = "q";
    public static final String ORDER_BY_PARAM = "order-by";
    public static final String PAGE_SIZE_PARAM = "page-size";
    public static final String ORDER_DATE_PARAM = "order-date";
    public static final String FROM_DATE_PARAM = "from-date";
    public static final String SHOW_FIELDS_PARAM = "show-fields";
    public static final String FORMAT_PARAM = "format";
    public static final String SHOW_TAGS_PARAM = "show-tags";
    public static final String API_KEY_PARAM = "api-key";
    public static final String SECTION_PARAM = "section";
    public static final String SHOW_FIELDS = "thumbnail,trailText";

    // API Key
    public static final String API_KEY = "14f1fe0f-2a5b-47f1-ac16-f4e590fc9e3b"; // Use your API Key when API rate limit exceeded

    // Constants value for each fragment
    public static final int HOME = 0;
    public static final int WORLD = 1;
    public static final int SCIENCE = 2;
    public static final int TECHNOLOGY = 3;
    public static final int TRAVEL = 4;
    public static final int SPORT = 5;
    public static final int ENVIRONMENT = 6;
    public static final int SOCIETY = 7;
    public static final int FASHION = 8;
    public static final int BUSINESS = 9;
    public static final int CULTURE = 10;
    public static final int FOOD = 11;
    public static final int ARTANDDESIGN = 12;

}
