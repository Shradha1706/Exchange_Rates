@ExchangeRates
Feature: User able to open Rest API page 
  Verify REST API service for Exchange Rates is working as expected
  
Background: User launches the URL
   Given User is on the API landing page 



Scenario: Verify the response status for API
   When API is available
   Then Verify the success status of response


Scenario: Verify the response received
   When API is available
   Then Verify the response
   
Scenario: Verify the response for incorrect Url
   When API is available
   Then Verify the response for incorrect url