**Travix - Problem to be solved**

**Background:**

BusyFlights is a flights search solution which aggregates flight results initially from 2 different suppliers (CrazyAir and ToughJet). A future iteration (not part of the test) may add more suppliers. 

**What is required:**

Use this GitHub repository as a base to implement the Busy Flights service that should produce an aggregated result from both CrazyAir and ToughJet.
The result should be a JSON response which contains a list of flights ordered by fare which has the following attributes:

**Busy Flights API**

**Request**

| Name | Description |
| ------ | ------ |
| origin | 3 letter IATA code(eg. LHR, AMS) |
| destination | 3 letter IATA code(eg. LHR, AMS) |
| departureDate | ISO_LOCAL_DATE format |
| returnDate | ISO_LOCAL_DATE format |
| numberOfPassengers | Maximum 4 passengers |

**Response**

| Name | Description |
| ------ | ------ |
| airline | Name of Airline |
| supplier | Eg: CrazyAir or ToughJet |
| fare | Total price rounded to 2 decimals |
| departureAirportCode | 3 letter IATA code(eg. LHR, AMS) |
| destinationAirportCode | 3 letter IATA code(eg. LHR, AMS) |
| departureDate | ISO_DATE_TIME format |
| arrivalDate | ISO_DATE_TIME format |

The service should connect to the both the suppliers using HTTP.

**CrazyAir API**

**Request**

| Name | Description |
| ------ | ------ |
| origin | 3 letter IATA code(eg. LHR, AMS) |
| destination | 3 letter IATA code(eg. LHR, AMS) |
| departureDate | ISO_LOCAL_DATE format |
| returnDate | ISO_LOCAL_DATE format |
| passengerCount | Number of passengers |

**Response**


| Name | Description |
| ------ | ------ |
| airline | Name of the airline |
| price | Total price |
| cabinclass | E for Economy and B for Business |
| departureAirportCode | Eg: LHR |
| destinationAirportCode | Eg: LHR |
| departureDate | ISO_LOCAL_DATE_TIME format |
| arrivalDate | ISO_LOCAL_DATE_TIME format |

**ToughJet API**

**Request**

| Name | Description |
| ------ | ------ |
| from | 3 letter IATA code(eg. LHR, AMS) |
| to | 3 letter IATA code(eg. LHR, AMS) |
| outboundDate |ISO_LOCAL_DATE format |
| inboundDate | ISO_LOCAL_DATE format |
| numberOfAdults | Number of passengers |

**Response**

| Name | Description |
| ------ | ------ |
| carrier | Name of the Airline |
| basePrice | Price without tax(doesn't include discount) |
| tax | Tax which needs to be charged along with the price |
| discount | Discount which needs to be applied on the price(in percentage) |
| departureAirportName | 3 letter IATA code(eg. LHR, AMS) |
| arrivalAirportName | 3 letter IATA code(eg. LHR, AMS) |
| outboundDateTime | ISO_INSTANT format |
| inboundDateTime | ISO_INSTANT format |

**What you need to provide:**

- A solution that meets the above requirements.
- The implementation should be made as close to 'production ready' as possible within the time constraints.

It is fine to change any of the supplied application code, if you choose to do so please add comments to indicate what has changed and why.

**SOLUTION:**

Was simulated the execution of services **CrazyAir** and **ToughJet** who creates flights with random values based in the input.

Was implemented a controller (com.travix.medusa.busyflights.controller.BusyFlightsController) who gets the parameters if HTTP request using method GET. It creates a BusyFlightsRequest object and send it to the service com.travix.medusa.busyflights.services.busyflights.impl.BusyFlightsServiceImpl who will use the external services.

The result of the external services are cached in a Array List and them sorted by fare as required. Then this list will be parsed as JSON using @ResponseBody and printed in the html.

The based classes of the problem, was not modified.

**IMPORTANT:**

The implemented services is assuming that the input that is already validated.

**FOR PRODUCTION:**

- Should be implemented validations in case server side validation are required.

- Should be checked the error management.

- Functional testing of border cases.

- Write unit tests.

**TO TEST IT:**

1.- Run com.travix.medusa.busyflights.BusyFlightsApplication as java application.

2.- Enter in the url "http://localhost:8080/searchAirFare?origin=PTY&destination=AMS&departureDate=2017-12-03&returnDate=2017-12-04&numberOfPassengers=1" where:

 - origin=PTY represents the 3 letter IATA code of origin.
 - destination=AMS represents the 3 letter IATA code of destination.
 - departureDate=2017-12-03 represents the departure date in ISO_LOCAL_DATE format
 - returnDate=2017-12-04 represents the return date in ISO_LOCAL_DATE format
 - numberOfPassengers=1 represents the amount of passengers
 
 **TOTAL TIME IMPLEMENTING THIS SOLUTION: 98 MINUTES**