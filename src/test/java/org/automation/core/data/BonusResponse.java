package org.automation.core.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
public class BonusResponse {

        @JsonProperty("id")
        int id;
        @JsonProperty("bonusName")
        private String bonusName;
        @JsonProperty("bonusType")
        private String bonusType;
        @JsonProperty("startTime")
        private String startTime;
        @JsonProperty("endTime")
        private String endTime;
}
