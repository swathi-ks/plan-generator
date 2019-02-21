package com.lendiko.plangernator;

/**
 * Repayment Amount Items
 *
 * @author Swathi KS.
 * @since 21/02/2019.
 */
public class RepaymentAmountItems {
    String borrowerPaymentAmount;
    String date;
    String initialOutstandingPrincipal;
    String interest;
    String principal;
    String remainingOutstandingPrincipal;

    public RepaymentAmountItems(String borrowerPaymentAmount, String date, String initialOutstandingPrincipal, String interest, String principal, String remainingOutstandingPrincipal) {
        this.borrowerPaymentAmount = borrowerPaymentAmount;
        this.date = date;
        this.initialOutstandingPrincipal = initialOutstandingPrincipal;
        this.interest = interest;
        this.principal = principal;
        this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
    }
}
