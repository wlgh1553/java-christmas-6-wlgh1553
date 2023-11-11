package christmas.domain;

import java.util.Arrays;

public enum Badge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NULL("없음", 0);

    private final String badgeName;
    private final Integer referenceCost;

    Badge(String name, Integer cost) {
        badgeName = name;
        referenceCost = cost;
    }

    public static Badge getBadge(Integer cost) {
        return Arrays.stream(Badge.values())
                .filter(badge -> cost >= badge.referenceCost)
                .findFirst()
                .orElse(NULL);
    }

    public String getName() {
        return badgeName;
    }
}
