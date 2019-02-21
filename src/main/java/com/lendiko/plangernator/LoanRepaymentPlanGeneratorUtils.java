package com.lendiko.plangernator;

import java.text.DecimalFormat;
import java.util.Map;
import java.text.ParseException;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Utility class for LoanRepaymentPlanGenerator
 *
 * @author Swathi KS.
 * @since 20/02/2019.
 */
class LoanRepaymentPlanGeneratorUtils {
    /***
     * Get the loan repayment amount items list as JSON
     * @param loanItemsList
     * @return
     * @throws ParseException
     * @throws JsonProcessingException
     */
    public static String getLoanItemsAsJson(List<RepaymentAmountItems> loanItemsList) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(loanItemsList);
    }

    /***
     * Get the loan payload items list as JSON
     * @param loanItemsMap
     * @return
     * @throws JsonProcessingException
     */
    public static String getLoanItemsAsJson(Map<String, Object> loanItemsMap) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(loanItemsMap);
    }

    /***
     * Get double value with precision set to 2
     * @param value
     * @return formatted double value
     */
    public static String getDoubleWithTwoPrecisionSet(double value) {
        DecimalFormat df2 = new DecimalFormat("##.##");
        return df2.format(value);
    }
}
