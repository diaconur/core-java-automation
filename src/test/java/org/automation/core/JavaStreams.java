package org.automation.core;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.automation.core.data.BonusResponse;
import org.testng.annotations.Test;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class JavaStreams extends BaseSetup{

    @Test
    public void getAllWelcomeBonusesNoStreams(){
        BonusResponse welcomeEntry = null;
        for(BonusResponse bonusResponse : bonusList){
            if(bonusResponse.getBonusName().equals("WELCOME")){
                welcomeEntry = bonusResponse;
                break;
            }
        }
        log.info("Welcome entries are: {}", welcomeEntry);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(welcomeEntry.getBonusName()).isEqualTo("WELCOME");
        softAssertions.assertAll();

    }
    @Test
    public void getAllWelcomeBonuses() {
        List<BonusResponse> welcomeEntries = bonusList.stream().filter(v -> v.getBonusName().equals("WELCOME"))
                .collect(Collectors.toList());

        log.info("Welcome entries are: {}", welcomeEntries);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(welcomeEntries.size()).isEqualTo(1);
        softAssertions.assertThat(welcomeEntries.get(0).getBonusName()).isEqualTo("WELCOME");
        softAssertions.assertAll();
    }
}
