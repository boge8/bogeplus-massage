package com.bogeplus.massagist.util;

public class GeoUtil {

    private static final int EARTH_RADIUS = 6371; // Radius of the Earth in kilometers

    public static double calculateDistance(String coord1, String coord2) {
        String[] coords1 = coord1.split(",");
        String[] coords2 = coord2.split(",");

        double lon1 = Double.parseDouble(coords1[0]);
        double lat1 = Double.parseDouble(coords1[1]);
        double lon2 = Double.parseDouble(coords2[0]);
        double lat2 = Double.parseDouble(coords2[1]);

        // Convert degrees to radians
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        // Haversine formula
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // Distance in kilometers
    }
}
