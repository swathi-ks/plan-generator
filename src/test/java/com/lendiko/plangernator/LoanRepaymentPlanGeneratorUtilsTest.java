package com.lendiko.plangernator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Swathi KS.
 * @since 21/02/2019.
 */

public class LoanRepaymentPlanGeneratorUtilsTest {
    @Test
    public void tesGetLoanRepaymentItemsAsJson() throws Exception {
        RepaymentAmountItems actualRepaymentAmountItems = new RepaymentAmountItems("219.36", "2018-01-01T00:00:01Z", "5000.00", "20.83", "198.52", "4801.48");
        List<RepaymentAmountItems> itemList = new LinkedList<>();
        itemList.add(actualRepaymentAmountItems);
        String actuaRepaymentAmountItemsJSON = LoanRepaymentPlanGeneratorUtils.getLoanItemsAsJson(itemList);
        Assert.assertNotNull(actuaRepaymentAmountItemsJSON);
        System.out.println("");
    }

    @Test
    public void getDoubleWithTwoPrecisionSet() {
        double amount = 15000.2343545;
        String actualAmount = LoanRepaymentPlanGeneratorUtils.getDoubleWithTwoPrecisionSet(amount);
        Assert.assertEquals("15000.23", actualAmount);
    }

    @Test
    public void tesGetLoanPayloadMapAsJson() throws Exception {
        Map<String, Object> payloadMap = new HashMap();
        payloadMap.put("loanAmount", "5000");
        payloadMap.put("nominalRate", "5.0");
        payloadMap.put("duration", 24);
        payloadMap.put("startDate", "2018-01-01T00:00:01Z");

        String actuaRepaymentAmountItemsJSON = LoanRepaymentPlanGeneratorUtils.getLoanItemsAsJson(payloadMap);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> actualPayloadMap = mapper.readValue(actuaRepaymentAmountItemsJSON, new TypeReference<Map<String, Object>>() {
        });
        String expectedLoanAmount = "5000";

        Assert.assertEquals(expectedLoanAmount, actualPayloadMap.get("loanAmount"));
    }
}
