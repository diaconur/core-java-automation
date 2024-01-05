package org.automation.core.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@RequiredArgsConstructor
public enum BonusValues {

    BONUS0("WELCOME", "JOIN_US","2020-03-01", "2020-03-01"),
    BONUS1("RAISE", "ANUAL_RAISE","2021-01-01", "2021-12-30"),
    BONUS2("RAISE", "ANUAL_RAISE","2022-01-01", "2022-12-30"),
    BONUS3("RAISE", "MID_RAISE","2022-01-01", "2022-06-30"),
    BONUS4("BIRTHDAY", "HOLIDAY","2023-06-06", "2023-06-06"),
    BONUS5("NAME_BIRTHDAY", "HOLIDAY","2023-10-06", "2023-10-06"),
    BONUS6("CHILD_BIRTH", "HOLIDAY","2023-10-06", "2023-10-06"),
    BONUS7("HARDWARE", "LAPTOP","2024-01-05", "2024-01-05"),
    BONUS8("HARDWARE", "HEADPHONES","2024-01-05", "2024-01-05");

    private final String bonusName;
    private final String bonusType;
    private final String startTime;
    private final String endTime;
}
