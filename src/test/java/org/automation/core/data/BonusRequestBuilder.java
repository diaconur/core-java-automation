package org.automation.core.data;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BonusRequestBuilder {

        String bonusName;
        String bonusType;
        String startTime;
        String endTime;
}
