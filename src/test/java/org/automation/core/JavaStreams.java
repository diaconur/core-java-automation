package org.automation.core;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.automation.core.data.BonusResponse;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class JavaStreams extends BaseSetup {

    @Test
    public void getAllWelcomeBonusesNoStreams() {
        BonusResponse welcomeEntry = null;
        for (BonusResponse bonusResponse : bonusList) {
            if (bonusResponse.getBonusName().equals("WELCOME")) {
                welcomeEntry = bonusResponse;
                break;
            }
        }
        log.info("Welcome entries are: {}", welcomeEntry);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(welcomeEntry.getBonusName()).isEqualTo("WELCOME");
        softAssertions.assertThat(welcomeEntry.getStartTime()).isEqualTo("2020-03-01");
        softAssertions.assertAll();

    }

    @Test
    public void getAllWelcomeBonuses() {
        List<BonusResponse> welcomeEntries = bonusList.stream()
                .filter(bonus -> bonus.getBonusName().equals("WELCOME"))
                .collect(Collectors.toList());

        log.info("Welcome entries are: {}", welcomeEntries);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(welcomeEntries.size()).isEqualTo(1);
        softAssertions.assertThat(welcomeEntries.get(0).getBonusName()).isEqualTo("WELCOME");
        softAssertions.assertThat(welcomeEntries.get(0).getStartTime()).isEqualTo("2020-03-01");
        softAssertions.assertAll();
    }

    @Test
    public void checkNumberOfBonuses() {
        Map bonusesMap = bonusList.stream()
                .collect(Collectors.groupingBy(BonusResponse::getBonusType, Collectors.counting()));

        log.info("Bonuses received are: {}" + bonusesMap);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(bonusesMap.get("LAPTOP")).isEqualTo(1);
        softAssertions.assertThat(bonusesMap.get("JOIN_US")).isEqualTo(1);
        softAssertions.assertThat(bonusesMap.get("HEADPHONES")).isEqualTo(1);
        softAssertions.assertThat(bonusesMap.get("HOLIDAY")).isEqualTo(3);
        softAssertions.assertThat(bonusesMap.get("ANUAL_RAISE")).isEqualTo(2);
        softAssertions.assertThat(bonusesMap.get("MID_RAISE")).isEqualTo(1);
        softAssertions.assertAll();
    }

    @Test
    public void checkHardwareStartTime() {

        // In case you have duplicate keys you will receive an exception
        // For example filtering by key ANUAL_RAISE :
        //  java.lang.IllegalStateException:
        //  Duplicate key ANUAL_RAISE (attempted merging values 2021-01-01 and 2022-01-01)

        Map bonusesMap = bonusList.stream()
                .filter(val -> val.getBonusName().equals("HARDWARE"))
                .collect(Collectors.toMap(val -> val.getBonusType(),
                        BonusResponse::getStartTime));


        log.info("Bonuses received are: " + bonusesMap);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(bonusesMap.get("LAPTOP")).isEqualTo("2024-01-05");
        softAssertions.assertThat(bonusesMap.get("HEADPHONES")).isEqualTo("2024-02-05");
        softAssertions.assertAll();
    }
}
