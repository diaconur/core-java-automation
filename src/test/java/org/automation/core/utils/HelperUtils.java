package org.automation.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.automation.core.data.BonusRequestBuilder;
import org.automation.core.data.BonusValues;

public class HelperUtils {

    public static String buildBodyRequest(int val) throws JsonProcessingException {
        BonusValues bonusEntry = BonusValues.valueOf("BONUS" + val);
        ObjectMapper mapper = new ObjectMapper();
        BonusRequestBuilder bonusDto = BonusRequestBuilder.builder()
                .bonusName(bonusEntry.getBonusName())
                .bonusType(bonusEntry.getBonusType())
                .startTime(bonusEntry.getStartTime())
                .endTime(bonusEntry.getEndTime())
                .build();
        return mapper.writeValueAsString(bonusDto);
    }
}
