package com.lendiko.plangernator;

import java.util.LinkedList;
import java.text.ParseException;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Scanner;
import java.util.Calendar;

import static com.lendiko.plangernator.LoanRepaymentPlanGeneratorUtils.getDoubleWithTwoPrecisionSet;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

/**
 * Plan generator class for loan repayment amount
 *
 * @author Swathi KS.
 * @since 20/02/2019.
 */
public class LoanRepaymentPlanGenerator {

    /***
     * Calculates the loan repayment amount plan with user input provided
     * @param loanAmount
     * @param interestRate
     * @param durationInMonths
     * @param date
     * @throws ParseException
     * @throws JsonProcessingException
     */
    public static void calculateRepaymentAmountPlan(double loanAmount, double interestRate, int durationInMonths, String date) throws ParseException, JsonProcessingException {

        List borrowerRePaymentAmountList = new LinkedList<RepaymentAmountItems>();
        double monthlyInterest, remainingOutstandingPrincipal, principal;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Calendar cal = Calendar.getInstance();
        sdf.setCalendar(cal);
        cal.setTime(sdf.parse(date));

        interestRate = interestRate / 100; //Converting % into actual decimal value
        double monthlyRate = interestRate / 12;
        double initialOutstandingPrincipal = loanAmount;
        double borrowerPaymentAmount = (loanAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -durationInMonths));

        for (int i = 1; i <= durationInMonths; i++) {
            monthlyInterest = ((interestRate * 30 * initialOutstandingPrincipal) / 360);
            principal = borrowerPaymentAmount - monthlyInterest;
            remainingOutstandingPrincipal = initialOutstandingPrincipal - principal;
            borrowerRePaymentAmountList.add(new RepaymentAmountItems(getDoubleWithTwoPrecisionSet(borrowerPaymentAmount), sdf.format(cal.getTime()), getDoubleWithTwoPrecisionSet(initialOutstandingPrincipal), getDoubleWithTwoPrecisionSet(monthlyInterest), getDoubleWithTwoPrecisionSet(principal), remainingOutstandingPrincipal > 0 ? getDoubleWithTwoPrecisionSet(remainingOutstandingPrincipal) : "0"));
            initialOutstandingPrincipal = remainingOutstandingPrincipal;
            cal.add(Calendar.MONTH, 1);
        }
        System.out.println("\nResponse : " + "\n" + LoanRepaymentPlanGeneratorUtils.getLoanItemsAsJson(borrowerRePaymentAmountList));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map payloadMap = new HashMap<String,Object>();

        System.out.print("Please enter below details for loan repayment plan calculation\n");
        System.out.print("Total loan principal amount : ");
        double loanAmount = scanner.nextDouble();
        payloadMap.put("loanAmount", String.valueOf(loanAmount));

        System.out.print("Nominal interest rate : ");
        double interestRate = scanner.nextDouble();
        payloadMap.put("nominalRate", getDoubleWithTwoPrecisionSet(interestRate));

        System.out.print("Duration(Number of instalments in months) : ");
        int durationInMonths = scanner.nextInt();
        payloadMap.put("duration", durationInMonths);

        System.out.print("Date of Disbursement/Payout : ");
        String date = scanner.next();
        payloadMap.put("startDate", date);

        try {
            System.out.println("\nPayload : " + "\n" + LoanRepaymentPlanGeneratorUtils.getLoanItemsAsJson(payloadMap));
            calculateRepaymentAmountPlan(loanAmount, interestRate, durationInMonths, date);
        } catch (ParseException | JsonProcessingException e) {
            System.err.print("Error while calculating the loan repayment amount plan");
        }
    }
}
