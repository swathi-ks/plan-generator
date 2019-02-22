# plan-generator
Plan Generator is Lendiko's loan repayment calcualtor throught the lifetime of a loan which is intednted to inform borrowers about the 
final repayment schedule.

To be able to calculate a repayment plan specific input parameters are necessary: 
• duration (number of instalments in months) 
• nominal interest rate 
• total loan amount ("total principal amount")
• Date of Disbursement/Payout

These four parameters need to be input parameters. The goal is to calculate a repayment plan for an annuity loan.
Therefore the amount that the borrower has to pay back every month, consisting of principal and interest repayments, does not 
change (the last instalment might be an exception). The annuity amount has to be derived from three of the input parameters
(duration, nominal interest rate, total loan amount) before starting the plan calculation. 
(use http://financeformulas.net/Annuity_Payment_Formula.html as reference)

Sample input and output are below:
payload: { "loanAmount": "5000",
           "nominalRate": "5.0", 
           "duration": 24, 
           "startDate": "2018-01-01T00:00:01Z" 
         } 
           
response: { [ 
              { "borrowerPaymentAmount": "219.36", "date": "2018-01-01T00:00:00Z", "initialOutstandingPrincipal": "5000.00", "interest": "20.83", "principal": "198.53", "remainingOutstandingPrincipal": "4801.47", },
              { "borrowerPaymentAmount": "219.36", "date": "2018-02-01T00:00:00Z", "initialOutstandingPrincipal": "4801.47", "interest": "20.01", "principal": "199.35", "remainingOutstandingPrincipal": "4602.12", }, ... 
              { "borrowerPaymentAmount": "219.28", "date": "2020-01-01T00:00:00Z", "initialOutstandingPrincipal": "218.37", "interest": "0.91", "principal": "218.37", "remainingOutstandingPrincipal": "0", }
          ] }
         
