<?xml version="1.0" encoding="utf-8"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">

        <pnr>
            <PNRid>
                <xsl:value-of select="PNR/Identification/pnrId"/>
            </PNRid>
            <recordLocator>
                <xsl:value-of select="PNR/Identification/recordLocator"/>
            </recordLocator>
            <GDS>
                <xsl:value-of select="PNR/Identification/gds"/>
            </GDS>
            <platformID>
                <xsl:value-of select="PNR/Identification/platformID"/>
            </platformID>
            <securityManagerID></securityManagerID>
            <globalCustomerNumber></globalCustomerNumber>
            <customerNumber>
                <xsl:value-of select="PNR/Identification/customerNumber"/>
            </customerNumber>
            <creationOfficeID></creationOfficeID>
            <agentSignature></agentSignature>
            <agentPCC></agentPCC>
            <BookingDateTime>
                <xsl:value-of select="PNR/Identification/bookingDate"/>
            </BookingDateTime>
            <etrHits></etrHits>
            <instance>
                <xsl:value-of select="PNR/Identification/instance"/>
            </instance>
            <sourcePNRid>
                <xsl:value-of select="PNR/Identification/sourcePNRId"/>
            </sourcePNRid>
            <Travelers>
                <Traveler>
                    <firstName>
                        <xsl:value-of select="PNR/Travelers/Traveler/Name/firstName"/>
                    </firstName>
                    <middleName>
                        <xsl:value-of select="PNR/Travelers/Traveler/Name/middleName"/>
                    </middleName>
                    <lastName>
                        <xsl:value-of select="PNR/Travelers/Traveler/Name/lastName"/>
                    </lastName>
                    <FrequentFlyerInfo>
                        <xsl:for-each select="PNR/Travelers/Traveler/FrequentFlyerInfo/FrequentFlyer">
                            <FrequentFlyer>
                                <frequentTravelerNum>
                                    <xsl:value-of select="frequentTravelerNum"/>
                                </frequentTravelerNum>
                                <frequentTravelerSupplier>
                                    <xsl:value-of select="frequentTravelerSupplier"/>
                                </frequentTravelerSupplier>
                            </FrequentFlyer>
                        </xsl:for-each>
                    </FrequentFlyerInfo>
                    <MealPreferenceInfo>
                        <xsl:for-each select="PNR/Travelers/Traveler/MealPreferenceInfo/MealPref">
                            <MealPreference>
                                <mealPreferenceCode>
                                    <xsl:value-of select="frequentTravelerNum"/>
                                </mealPreferenceCode>
                                <mealPreferenceSegment>
                                    <xsl:value-of select="frequentTravelerSupplier"/>
                                </mealPreferenceSegment>
                            </MealPreference>
                        </xsl:for-each>
                    </MealPreferenceInfo>
                    <SeatPreferenceInfo>
                        <xsl:for-each select="PNR/Travelers/Traveler/MealPreferenceInfo/SeatPref">
                            <SeatPreference>
                                <preferenceLocationCode>
                                    <xsl:value-of select="preferenceLocationCode"/>
                                </preferenceLocationCode>
                                <preferenceLocationText>
                                    <xsl:value-of select="preferenceLocationText"/>
                                </preferenceLocationText>
                                <longFreeText>
                                    <xsl:value-of select="notes"/>
                                </longFreeText>
                            </SeatPreference>
                        </xsl:for-each>
                    </SeatPreferenceInfo>
                    <Seats>
                        <xsl:for-each select="PNR/Travelers/Traveler/Seats/Seat">
                            <Seat>
                                <segmentNumber>
                                    <xsl:value-of select="frequentTravelerNum"/>
                                </segmentNumber>
                                <location>
                                    <xsl:value-of select="frequentTravelerNum"/>
                                </location>
                            </Seat>
                        </xsl:for-each>
                    </Seats>
                    <passengerNumber>
                        <xsl:value-of select="PNR/Travelers/Traveler/travelerNumber"/>
                    </passengerNumber>
                    <nameInGds>
                        <xsl:value-of select="PNR/Travelers/Traveler/Name/lastName"/>
                    </nameInGds>
                    <NameNumber></NameNumber>
                </Traveler>
            </Travelers>

            <!-- AirSegments -->
            <AirSegments>
                <xsl:for-each select="PNR/Segments/Segment">
                    <xsl:choose>
                        <xsl:when test="@xsi:type = 'AirSegment'" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
                            <AirSegment>
                                <segmentNumber>
                                    <xsl:value-of select="segmentNumber"/>
                                </segmentNumber>
                                <TDSValidated></TDSValidated>
                                <isCancelled></isCancelled>
                                <isScheduleChange></isScheduleChange>
                                <startDateTime>
                                    <xsl:value-of select="startDateTime"/>
                                </startDateTime>
                                <endDateTime>
                                    <xsl:value-of select="endDateTime"/>
                                </endDateTime>
                                <startCityName>
                                    <xsl:value-of select="Departure/cityName"/>
                                </startCityName>
                                <endCityName>
                                    <xsl:value-of select="Arrival/cityName"/>
                                </endCityName>
                                <marketingAirlineCode>
                                    <xsl:value-of select="MarketingFlight/airlineCode"/>
                                </marketingAirlineCode>
                                <marketingFlightNumber>
                                    <xsl:value-of select="MarketingFlight/flightNumber"/>
                                </marketingFlightNumber>
                                <operatingAirlineCode>
                                    <xsl:value-of select="OperatingFlight/airlineCode"/>
                                </operatingAirlineCode>
                                <operatingFlightNumber>
                                    <xsl:value-of select="OperatingFlight/flightNumber"/>
                                </operatingFlightNumber>
                                <mealCode>
                                    <xsl:value-of select="MealInfo/code"/>
                                </mealCode>
                                <mealDescription>
                                    <xsl:value-of select="MealInfo/description"/>
                                </mealDescription>
                                <bookingSiteConfNum>
                                    <xsl:value-of select="bookingSiteConfNum"/>
                                </bookingSiteConfNum>
                                <supplierConfNum></supplierConfNum>
                                <equipmentCode>
                                    <xsl:value-of select="equipmentCode"/>
                                </equipmentCode>
                                <departureAirportCode>
                                    <xsl:value-of select="Departure/airportCode"/>
                                </departureAirportCode>
                                <arrivalAirportCode>
                                    <xsl:value-of select="Arrival/airportCode"/>
                                </arrivalAirportCode>
                                <classOfService>
                                    <xsl:value-of select="ClassOfService/code"/>
                                </classOfService>
                                <classOfServiceDescription>
                                    <xsl:value-of select="ClassOfService/description"/>
                                </classOfServiceDescription>
                                <status>
                                    <xsl:value-of select="Status/code"/>
                                </status>
                                <isChangeOfGuage>
                                    <xsl:value-of select="isChangeOfGuage"/>
                                </isChangeOfGuage>
                                <calculatedDuration>
                                    <xsl:value-of select="calculatedDuration"/>
                                </calculatedDuration>
                                <numberOfStops>
                                    <xsl:value-of select="numberOfStops"/>
                                </numberOfStops>
                                <hasSpecialMeal>
                                    <xsl:value-of select="MealInfo/hasSpecialMeal"/>
                                </hasSpecialMeal>
                                <StopsCityList>
                                    <xsl:for-each select="StopCityList">
                                        <strElement>
                                            <xsl:value-of select="strElement"/>
                                        </strElement>
                                    </xsl:for-each>
                                </StopsCityList>
                                <MarriedToSegments>
                                    <xsl:for-each select="MarriedSegments">
                                        <intElement>
                                            <xsl:value-of select="intElement"/>
                                        </intElement>
                                    </xsl:for-each>
                                </MarriedToSegments>
                                <ConnectedSegments>
                                    <xsl:for-each select="ConnectedSegments">
                                        <intElement>
                                            <xsl:value-of select="intElement"/>
                                        </intElement>
                                    </xsl:for-each>
                                </ConnectedSegments>
                                <isFlown>
                                    <xsl:value-of select="isFlown"/>
                                </isFlown>
                                <isOpen></isOpen>
                                <isPassive>
                                    <xsl:value-of select="isPassive"/>
                                </isPassive>
                                <isFlown>
                                    <xsl:value-of select="isFlown"/>
                                </isFlown>
                                <isTicketless>
                                    <xsl:value-of select="TicketInfo/isTicketless"/>
                                </isTicketless>
                                <InFlightServiceCodes>
                                    <xsl:value-of select="InFlightServiceCodes"/>
                                </InFlightServiceCodes>
                                <isETicketCandidate>
                                    <xsl:value-of select="TicketInfo/isETicket"/>
                                </isETicketCandidate>
                                <departureTerminal>
                                    <xsl:value-of select="Departure/terminal"/>
                                </departureTerminal>
                                <arrivalTerminal>
                                    <xsl:value-of select="Arrival/terminal"/>
                                </arrivalTerminal>
                                <flightDistance>
                                    <xsl:value-of select="flightDistance"/>
                                </flightDistance>
                                <isTicketed>
                                    <xsl:value-of select="TicketInfo/isTicketed"/>
                                </isTicketed>
                                <ticketNumber>
                                    <xsl:value-of select="TicketInfo/ticketNumber"/>
                                </ticketNumber>
                            </AirSegment>
                        </xsl:when>
                    </xsl:choose>
                </xsl:for-each>
            </AirSegments>

            <!-- HotelSegments -->
            <HotelSegments>
                <xsl:for-each select="PNR/Segments/Segment">
                    <xsl:choose>
                        <xsl:when test="@xsi:type = 'Hotel'" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
                            <HotelSegment>
                                <segmentNumber>
                                    <xsl:value-of select="segmentNumber"/>
                                </segmentNumber>
                                <TDSValidated></TDSValidated>
                                <isCancelled></isCancelled>
                                <isScheduleChange></isScheduleChange>
                                <startDateTime>
                                    <xsl:value-of select="startDateTime"/>
                                </startDateTime>
                                <endDateTime>
                                    <xsl:value-of select="endDateTime"/>
                                </endDateTime>
                                <Address>
                                    <addr1>
                                        <xsl:value-of select="Hotel/Address/addr1"/>
                                    </addr1>
                                    <city>
                                        <xsl:value-of select="Hotel/Address/city"/>
                                    </city>
                                    <cityCode>
                                        <xsl:value-of select="Hotel/Address/cityCode"/>
                                    </cityCode>
                                    <zip>
                                        <xsl:value-of select="Hotel/Address/zip"/>
                                    </zip>
                                    <country>
                                        <xsl:value-of select="Hotel/Address/country"/>
                                    </country>
                                    <longFreeText></longFreeText>
                                </Address>
                                <numberGuests>
                                    <xsl:value-of select="RoomInfo/guestCount"/>
                                </numberGuests>
                                <bookingSiteConfNum>
                                    <xsl:value-of select="bookingSiteConfNum"/>
                                </bookingSiteConfNum>
                                <bookingSiteURL>
                                    <xsl:value-of select="bookingSiteURL"/>
                                </bookingSiteURL>
                                <supplierConfNum>
                                    <xsl:value-of select="confirmationNumber"/>
                                </supplierConfNum>
                                <supplierName>
                                    <xsl:value-of select="supplierName"/>
                                </supplierName>
                                <supplierPhone></supplierPhone>
                                <hotelChainCode>
                                    <xsl:value-of select="Hotel/chainCode"/>
                                </hotelChainCode>
                                <numberOfRooms>
                                    <xsl:value-of select="RoomInfo/numberOfRooms"/>
                                </numberOfRooms>
                                <roomType>
                                    <xsl:value-of select="RoomInfo/type"/>
                                </roomType>
                                <specialInstructions></specialInstructions>
                                <propertyCode>
                                    <xsl:value-of select="Hotel/propertyCode"/>
                                </propertyCode>
                                <creditCardGuarantee>
                                    <xsl:value-of select="creditCardGuarantee"/>
                                </creditCardGuarantee>
                                <bookedThrough></bookedThrough>
                                <numberOfNights>
                                    <xsl:value-of select="Hotel/numberOfNights"/>
                                </numberOfNights>
                                <status>
                                    <xsl:value-of select="Status/code"/>
                                </status>
                                <rateCode>
                                    <xsl:value-of select="rateCode"/>
                                </rateCode>
                                <Rate>
                                    <amount></amount>
                                    <currency></currency>
                                </Rate>
                                <corporateDiscountNumber>
                                    <xsl:value-of select="corporateDiscountNumber"/>
                                </corporateDiscountNumber>
                                <RateChangeInfos/>
                                <dcsAuxRequestReturnRate>
                                    <xsl:value-of select="dcsAuxRequestReturnRate"/>
                                </dcsAuxRequestReturnRate>
                                <isPassive>
                                    <xsl:value-of select="isPassive"/>
                                </isPassive>
                                <RateCategory>
                                    <xsl:value-of select="rateCategory"/>
                                </RateCategory>
                                <ExtraPersonRate/>
                                <isMultiDateRate>
                                    <xsl:value-of select="isMultiDateRate"/>
                                </isMultiDateRate>
                                <cancellationPolicy>
                                    <xsl:value-of select="cancellationPolicy"/>
                                </cancellationPolicy>
                                <rateChangeDuringStay>
                                    <xsl:value-of select="rateChangeDuringStay"/>
                                </rateChangeDuringStay>
                                <hotelOverview></hotelOverview>
                            </HotelSegment>
                        </xsl:when>
                    </xsl:choose>
                </xsl:for-each>
            </HotelSegments>

            <!-- CarSegments -->
            <CarSegments>
                <xsl:for-each select="PNR/Segments/Segment">
                    <xsl:choose>
                        <xsl:when test="@xsi:type = 'Car'" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
                            <CarSegment>
                                <segmentNumber>
                                    <xsl:value-of select="segmentNumber"/>
                                </segmentNumber>
                                <TDSValidated></TDSValidated>
                                <isCancelled></isCancelled>
                                <isScheduleChange></isScheduleChange>
                                <startDateTime>
                                    <xsl:value-of select="startDateTime"/>
                                </startDateTime>
                                <endDateTime>
                                    <xsl:value-of select="endDateTime"/>
                                </endDateTime>
                                <StartLocationAddress>
                                    <addr1>
                                        <xsl:value-of select="PickUp/addr1"/>
                                    </addr1>
                                    <addr2>
                                        <xsl:value-of select="PickUp/addr2"/>
                                    </addr2>
                                    <city>
                                        <xsl:value-of select="PickUp/city"/>
                                    </city>
                                    <state>
                                        <xsl:value-of select="PickUp/state"/>
                                    </state>
                                    <zip>
                                        <xsl:value-of select="PickUp/zip"/>
                                    </zip>
                                    <country>
                                        <xsl:value-of select="PickUp/country"/>
                                    </country>
                                    <longFreeText></longFreeText>
                                </StartLocationAddress>
                                <EndLocationAddress>
                                    <addr1>
                                        <xsl:value-of select="DropOff/addr1"/>
                                    </addr1>
                                    <addr2>
                                        <xsl:value-of select="DropOff/addr2"/>
                                    </addr2>
                                    <city>
                                        <xsl:value-of select="DropOff/city"/>
                                    </city>
                                    <state>
                                        <xsl:value-of select="DropOff/state"/>
                                    </state>
                                    <zip>
                                        <xsl:value-of select="DropOff/zip"/>
                                    </zip>
                                    <country>
                                        <xsl:value-of select="DropOff/country"/>
                                    </country>
                                    <longFreeText></longFreeText>
                                </EndLocationAddress>
                                <carDescription></carDescription>
                                <bookingSiteConfNum>
                                    <xsl:value-of select="bookingSiteConfNum"/>
                                </bookingSiteConfNum>
                                <bookingSiteURL>
                                    <xsl:value-of select="bookingSiteURL"/>
                                </bookingSiteURL>
                                <supplierConfNum></supplierConfNum>
                                <supplierName></supplierName>
                                <locationCode></locationCode>
                                <pickupLocation></pickupLocation>
                                <dropOffLocation></dropOffLocation>
                                <rentalCompany>
                                    <xsl:value-of select="rentalCompany"/>
                                </rentalCompany>
                                <numberOfCars>
                                    <xsl:value-of select="Car/numberOfCars"/>
                                </numberOfCars>
                                <ExtraDayFee>
                                    <amount>
                                        <xsl:value-of select="Fare/ExtraDayFee/amount"/>
                                    </amount>
                                    <currency>
                                        <xsl:value-of select="Fare/ExtraDayFee/currency"/>
                                    </currency>
                                </ExtraDayFee>
                                <ExtraHourFee>
                                    <amount></amount>
                                    <currency></currency>
                                </ExtraHourFee>
                                <MileCharge/>
                                <status>
                                    <xsl:value-of select="Status/code"/>
                                </status>
                                <corporateDiscountNumber>
                                    <xsl:value-of select="corporateDiscountNumber"/>
                                </corporateDiscountNumber>
                                <GuaranteedRate>
                                    <amount>
                                        <xsl:value-of select="Fare/GuaranteedRate/amount"/>
                                    </amount>
                                    <currency>
                                        <xsl:value-of select="Fare/GuaranteedRate/currency"/>
                                    </currency>
                                </GuaranteedRate>
                                <BaseRate>
                                    <amount>
                                        <xsl:value-of select="Fare/BaseRate/amount"/>
                                    </amount>
                                    <currency>
                                        <xsl:value-of select="Fare/BaseRate/currency"/>
                                    </currency>
                                </BaseRate>
                                <EstimatedTotal>
                                    <amount></amount>
                                    <currency></currency>
                                </EstimatedTotal>
                                <QuotedRate/>
                                <DropCharge>
                                    <amount>
                                        <xsl:value-of select="Fare/DropCharge/amount"/>
                                    </amount>
                                    <currency>
                                        <xsl:value-of select="Fare/DropCharge/currency"/>
                                    </currency>
                                </DropCharge>
                                <rateCode></rateCode>
                                <bookedThrough></bookedThrough>
                                <reservationName></reservationName>
                                <rateCurrencyCode></rateCurrencyCode>
                                <dcsAuxRateCode>
                                    <xsl:value-of select="Fare/dcsAuxRateCode"/>
                                </dcsAuxRateCode>
                                <clientIDNumber>
                                    <xsl:value-of select="clientIDNumber"/>
                                </clientIDNumber>
                                <OneWayDropFee/>
                                <isKilometers></isKilometers>
                                <isManuallyBooked></isManuallyBooked>
                                <frequentRenterID>
                                    <xsl:value-of select="frequentRenterID"/>
                                </frequentRenterID>
                                <isPassive>
                                    <xsl:value-of select="isPassive"/>
                                </isPassive>
                                <arrivalAirlineCode>
                                    <xsl:value-of select="ArrivalFlight/airlineCode"/>
                                </arrivalAirlineCode>
                                <arrivalFlightNumber>
                                    <xsl:value-of select="ArrivalFlight/flightNumber"/>
                                </arrivalFlightNumber>
                                <passengerNumber></passengerNumber>
                                <CarRateCategory>
                                    <xsl:value-of select="Fare/rateCategory"/>
                                </CarRateCategory>
                                <AgentEnteredRate>
                                    <amount>
                                        <xsl:value-of select="Fare/AgentEnteredRate/amount"/>
                                    </amount>
                                    <currency>
                                        <xsl:value-of select="Fare/AgentEnteredRate/currency"/>
                                    </currency>
                                </AgentEnteredRate>
                                <CarRateType>
                                    <xsl:value-of select="Fare/carRateType"/>
                                </CarRateType>
                                <mileageAllowance>
                                    <xsl:value-of select="Fare/mileageAllowance"/>
                                </mileageAllowance>
                                <extraDayMileageAllowance>
                                    <xsl:value-of select="Fare/extraDayMileageAllowance"/>
                                </extraDayMileageAllowance>
                                <ExtraDayMileageFee>
                                    <amount>
                                        <xsl:value-of select="Fare/ExtraDayMileageFee/amount"/>
                                    </amount>
                                    <currency>
                                        <xsl:value-of select="Fare/ExtraDayMileageFee/currency"/>
                                    </currency>
                                </ExtraDayMileageFee>
                                <extraHourMileageAllowance>
                                    <xsl:value-of select="Fare/extraHourMileageAllowance"/>
                                </extraHourMileageAllowance>
                                <ExtraHourMileageFee/>
                                <totalMandatoryCharges>
                                    <xsl:value-of select="Fare/totalMandatoryCharges"/>
                                </totalMandatoryCharges>
                                <ExtraWeekFee>
                                    <amount>
                                        <xsl:value-of select="Fare/ExtraWeekFee/amount"/>
                                    </amount>
                                    <currency>
                                        <xsl:value-of select="Fare/ExtraWeekFee/currency"/>
                                    </currency>
                                </ExtraWeekFee>
                                <extraWeekMileageFee></extraWeekMileageFee>
                                <numberOfDays>
                                    <xsl:value-of select="Car/numberOfDays"/>
                                </numberOfDays>
                                <numberOfHours>
                                    <xsl:value-of select="Car/numberOfHours"/>
                                </numberOfHours>
                            </CarSegment>
                        </xsl:when>
                    </xsl:choose>
                </xsl:for-each>
            </CarSegments>

            <!-- TourSegments -->
            <TourSegments>
                <xsl:for-each select="PNR/Segments/Segment">
                    <xsl:choose>
                        <xsl:when test="@xsi:type = 'Tour'" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
                            <TourSegment>
                                <segmentNumber>
                                    <xsl:value-of select="segmentNumber"/>
                                </segmentNumber>
                                <TDSValidated></TDSValidated>
                                <isCancelled></isCancelled>
                                <isScheduleChange></isScheduleChange>
                                <startDateTime>
                                    <xsl:value-of select="startDateTime"/>
                                </startDateTime>
                                <endDateTime>
                                    <xsl:value-of select="endDateTime"/>
                                </endDateTime>
                                <StartLocationAddress>
                                    <addr1>
                                        <xsl:value-of select="Departure/addr1"/>
                                    </addr1>
                                </StartLocationAddress>
                                <EndLocationAddress>
                                    <addr1>
                                        <xsl:value-of select="Arrival/addr1"/>
                                    </addr1>
                                </EndLocationAddress>
                                <startLocationName>
                                    <xsl:value-of select="Departure/name"/>
                                </startLocationName>
                                <endLocationName>
                                    <xsl:value-of select="Arrival/name"/>
                                </endLocationName>
                                <carrierName>
                                    <xsl:value-of select="Carrier/name"/>
                                </carrierName>
                                <confirmationNum></confirmationNum>
                                <vehicleDescription>
                                    <xsl:value-of select="Carrier/carInformation"/>
                                </vehicleDescription>
                                <AgencyCommission/>
                                <BalanceAmount/>
                                <CancellationFee/>
                                <DepositAmountDue/>
                                <TotalTax/>
                                <BasePrice/>
                                <cityCode></cityCode>
                                <longFreeText></longFreeText>
                                <TotalRate/>
                                <TourRate>
                                    <xsl:value-of select="tourRate"/>
                                </TourRate>
                                <numberOfNights>
                                    <xsl:value-of select="numberOfNights"/>
                                </numberOfNights>
                                <numberOfRooms>
                                    <xsl:value-of select="RoomInfo/numberOfRooms"/>
                                </numberOfRooms>
                                <tourNumber></tourNumber>
                                <serviceEndDate></serviceEndDate>
                                <vendorCodeAndName>
                                    <xsl:value-of select="vendor"/>
                                </vendorCodeAndName>
                                <status>
                                    <xsl:value-of select="Status/code"/>
                                </status>
                                <tourRateFreeText></tourRateFreeText>
                            </TourSegment>
                        </xsl:when>
                    </xsl:choose>
                </xsl:for-each>
            </TourSegments>

            <!-- RailSegments -->
            <RailSegments>
                <xsl:for-each select="PNR/Segments/Segment">
                    <xsl:choose>
                        <xsl:when test="@xsi:type = 'Rail'" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
                            <RailSegment>
                                <segmentNumber>
                                    <xsl:value-of select="segmentNumber"/>
                                </segmentNumber>
                                <TDSValidated></TDSValidated>
                                <isCancelled></isCancelled>
                                <isScheduleChange></isScheduleChange>
                                <startDateTime>
                                    <xsl:value-of select="startDateTime"/>
                                </startDateTime>
                                <endDateTime>
                                    <xsl:value-of select="endDateTime"/>
                                </endDateTime>
                                <StartStationAddress>
                                    <addr1>
                                        <xsl:value-of select="Departure/Address/addr1"/>
                                    </addr1>
                                    <longFreeText></longFreeText>
                                </StartStationAddress>
                                <EndStationAddress>
                                    <addr1>
                                        <xsl:value-of select="Arrival/Address/addr1"/>
                                    </addr1>
                                    <longFreeText></longFreeText>
                                </EndStationAddress>
                                <carrierName>
                                    <xsl:value-of select="CarrierInfo/name"/>
                                </carrierName>
                                <confirmationNum></confirmationNum>
                                <bookingSiteConfNum>
                                    <xsl:value-of select="bookingSiteConfNum"/>
                                </bookingSiteConfNum>
                                <bookingSiteURL>
                                    <xsl:value-of select="bookingSiteURL"/>
                                </bookingSiteURL>
                                <departureStationCode>
                                    <xsl:value-of select="Departure/Address/stationCode"/>
                                </departureStationCode>
                                <arrivalStationCode>
                                    <xsl:value-of select="Arrival/Address/stationCode"/>
                                </arrivalStationCode>
                                <trainNumber>
                                    <xsl:value-of select="CarrierInfo/trainNumber"/>
                                </trainNumber>
                                <operator></operator>
                                <status>
                                    <xsl:value-of select="Status/code"/>
                                </status>
                                <longFreeText></longFreeText>
                                <specialInstructions></specialInstructions>
                                <numberInParty>
                                    <xsl:value-of select="numberInParty"/>
                                </numberInParty>
                                <numberOfStops></numberOfStops>
                                <isPassive>
                                    <xsl:value-of select="isPassive"/>
                                </isPassive>
                                <RateQuote/>
                                <calculatedDuration>
                                    <xsl:value-of select="calculatedDuration"/>
                                </calculatedDuration>
                            </RailSegment>
                        </xsl:when>
                    </xsl:choose>
                </xsl:for-each>
            </RailSegments>

            <DeliveryAddressInfo>
                <recipient>
                    <xsl:value-of select="PNR/DeliveryAddress/name"/>
                </recipient>
                <addr1>
                    <xsl:value-of select="PNR/DeliveryAddress/addr1"/>
                </addr1>
                <country></country>
            </DeliveryAddressInfo>
            <BillingAddressInfo>
                <recipient>
                    <xsl:value-of select="PNR/BillingAddress/name"/>
                </recipient>
                <addr1>
                    <xsl:value-of select="PNR/BillingAddress/addr1"/>
                </addr1>
                <addr2></addr2>
                <city>
                    <xsl:value-of select="PNR/BillingAddress/city"/>
                </city>
                <state>
                    <xsl:value-of select="PNR/BillingAddress/state"/>
                </state>
                <zip>
                    <xsl:value-of select="PNR/BillingAddress/zip"/>
                </zip>
            </BillingAddressInfo>
            <Remarks>
                <xsl:for-each select="PNR/Remarks/Remark">
                    <Remark>
                        <lineNumber>
                            <xsl:value-of select="lineNumber"/>
                        </lineNumber>
                        <type>
                            <xsl:value-of select="type"/>
                        </type>
                        <contents>
                            <xsl:value-of select="contents"/>
                        </contents>
                        <PassengerNumbers>
                            <intElement></intElement>
                        </PassengerNumbers>
                        <SegmentNumbers>
                            <intElement></intElement>
                        </SegmentNumbers>
                    </Remark>
                </xsl:for-each>
            </Remarks>
            <SSRs>
                <xsl:for-each select="PNR/SSRs/SSR">
                    <SSR>
                        <lineNumber>
                            <xsl:value-of select="lineNum"/>
                        </lineNumber>
                        <type>
                            <xsl:value-of select="type"/>
                        </type>
                        <code>
                            <xsl:value-of select="code"/>
                        </code>
                        <contents>
                            <xsl:value-of select="contents"/>
                        </contents>
                        <PassengerNumbers>
                            <intElement></intElement>
                        </PassengerNumbers>
                        <SegmentNumbers>
                            <intElement></intElement>
                        </SegmentNumbers>
                    </SSR>
                </xsl:for-each>
            </SSRs>
            <FormsOfPayment>
                <FormOfPayment>
                    <FormOfPayment></FormOfPayment>
                    <creditCardNumber></creditCardNumber>
                    <creditCardExpiration></creditCardExpiration>
                    <creditCardCompany></creditCardCompany>
                </FormOfPayment>
            </FormsOfPayment>

            <StoredFares>
                <StoredFare>
                    <BaseFare>
                        <amount>
                            <xsl:value-of select="PNR/StoredFares/StoredFare/BaseFare/amount"/>
                        </amount>
                        <currency>
                            <xsl:value-of select="PNR/StoredFares/StoredFare/BaseFare/currency"/>
                        </currency>
                    </BaseFare>
                    <TotalFare>
                        <amount>
                            <xsl:value-of select="PNR/StoredFares/StoredFare/TotalFare/amount"/>
                        </amount>
                        <currency>
                            <xsl:value-of select="PNR/StoredFares/StoredFare/TotalFare/currency"/>
                        </currency>
                    </TotalFare>
                    <EquivalentFare>
                        <amount>
                            <xsl:value-of select="PNR/StoredFares/StoredFare/EquivalentFare/amount"/>
                        </amount>
                        <currency>
                            <xsl:value-of select="PNR/StoredFares/StoredFare/EquivalentFare/currency"/>
                        </currency>
                    </EquivalentFare>
                    <validatingCarrier>
                        <xsl:value-of select="PNR/StoredFares/StoredFare/validatingCarrier"/>
                    </validatingCarrier>
                    <privateFareAccountCode>
                        <xsl:value-of select="PNR/StoredFares/StoredFare/privateFareAccountCode"/>
                    </privateFareAccountCode>
                    <inputMessage>
                        <xsl:value-of select="PNR/StoredFares/StoredFare/inputMessage"/>
                    </inputMessage>
                    <fareCalculation>
                        <xsl:value-of select="PNR/StoredFares/StoredFare/fareCalculation"/>
                    </fareCalculation>
                    <commissionPercentage>
                        <xsl:value-of select="PNR/StoredFares/StoredFare/commissionPercentage"/>
                    </commissionPercentage>
                    <commissionAmount>
                        <xsl:value-of select="PNR/StoredFares/StoredFare/commissionAmount"/>
                    </commissionAmount>
                    <quoteDate>
                        <xsl:value-of select="PNR/StoredFares/StoredFare/quoteDate"/>
                    </quoteDate>
                    <lastDateToTicket>
                        <xsl:value-of select="PNR/StoredFares/StoredFare/lastDateToTicket"/>
                    </lastDateToTicket>
                    <StoredFareSegments>
                        <xsl:for-each select="PNR/StoredFares/StoredFare/StoredFareSegments/StoredFareSegment">
                            <StoredFareSegment>
                                <carrierCode>
                                    <xsl:value-of select="carrierCode"/>
                                </carrierCode>
                                <departureAirportCode>
                                    <xsl:value-of select="departureAirportCode"/>
                                </departureAirportCode>
                                <departureDate>
                                    <xsl:value-of select="departureDate"/>
                                </departureDate>
                                <segmentNumber>
                                    <xsl:value-of select="segmentNumber"/>
                                </segmentNumber>
                                <fare>
                                    <xsl:value-of select="fare"/>
                                </fare>
                                <classOfService>
                                    <xsl:value-of select="classOfService"/>
                                </classOfService>
                                <statusCode>
                                    <xsl:value-of select="statusCode"/>
                                </statusCode>
                                <fareBasis>
                                    <xsl:value-of select="fareBasis"/>
                                </fareBasis>
                                <notValidBefore></notValidBefore>
                                <notValidAfter></notValidAfter>
                            </StoredFareSegment>
                        </xsl:for-each>
                    </StoredFareSegments>
                    <StoredFarePassengers>
                        <StoredFarePassenger>
                            <fareConstruction>
                                <xsl:value-of select="PNR/StoredFarePassengers/StoredFarePassenger/fareConstruction"/>
                            </fareConstruction>
                            <BaseFare>
                                <amount>
                                    <xsl:value-of
                                            select="PNR/StoredFarePassengers/StoredFarePassenger/BaseFare/amount"/>
                                </amount>
                                <currency>
                                    <xsl:value-of
                                            select="PNR/StoredFarePassengers/StoredFarePassenger/BaseFare/currency"/>
                                </currency>
                            </BaseFare>
                            <TotalFare>
                                <amount>
                                    <xsl:value-of
                                            select="PNR/StoredFarePassengers/StoredFarePassenger/TotalFare/amount"/>
                                </amount>
                                <currency>
                                    <xsl:value-of
                                            select="PNR/StoredFarePassengers/StoredFarePassenger/TotalFare/currency"/>
                                </currency>
                            </TotalFare>
                            <lastDateToTicket>
                                <xsl:value-of select="PNR/StoredFarePassengers/StoredFarePassenger/lastDateToTicket"/>
                            </lastDateToTicket>
                            <TicketStatus>
                                <xsl:value-of select="PNR/StoredFarePassengers/StoredFarePassenger/ticketStatus"/>
                            </TicketStatus>
                            <passengerNumber></passengerNumber>
                            <passengerTypeIdentifier>
                                <xsl:value-of
                                        select="PNR/StoredFarePassengers/StoredFarePassenger/passengerTypeIdentifier"/>
                            </passengerTypeIdentifier>
                            <TicketType>
                                <xsl:value-of select="PNR/StoredFarePassengers/StoredFarePassenger/ticketType"/>
                            </TicketType>
                        </StoredFarePassenger>
                    </StoredFarePassengers>
                    <CreditCard/>
                </StoredFare>
            </StoredFares>
            <TicketRequests>
                <TicketRequest>
                    <SegmentNumbers/>
                    <PassengerNumbers/>
                    <isAlreadyTicketed>
                        <xsl:value-of select="PNR/TicketRequests/TicketRequest/isAlreadyTicketed"/>
                    </isAlreadyTicketed>
                    <lineNumber>
                        <xsl:value-of select="PNR/TicketRequests/TicketRequest/lineNumber"/>
                    </lineNumber>
                    <requestDate>
                        <xsl:value-of select="PNR/TicketRequests/TicketRequest/requestDate"/>
                    </requestDate>
                    <longFreeText>
                        <xsl:value-of select="PNR/TicketRequests/TicketRequest/longFreeText"/>
                    </longFreeText>
                    <QueuePlacements/>
                </TicketRequest>
            </TicketRequests>
            <TicketInfos>
                <TicketInfo>
                    <BaseFare>
                        <amount>
                            <xsl:value-of select="PNR/TicketInfos/TicketInfo/BaseFare/amount"/>
                        </amount>
                        <currency>
                            <xsl:value-of select="PNR/TicketInfos/TicketInfo/BaseFare/currency"/>
                        </currency>
                    </BaseFare>
                    <FareTotal>
                        <amount>
                            <xsl:value-of select="PNR/TicketInfos/TicketInfo/FareTotal/amount"/>
                        </amount>
                        <currency>
                            <xsl:value-of select="PNR/TicketInfos/TicketInfo/FareTotal/currency"/>
                        </currency>
                    </FareTotal>
                    <CommissionTotal>
                        <xsl:value-of select="PNR/TicketInfos/TicketInfo/commissionTotal"/>
                    </CommissionTotal>
                    <isETicket>
                        <xsl:value-of select="PNR/TicketInfos/TicketInfo/isETicket"/>
                    </isETicket>
                    <carrier>
                        <xsl:value-of select="PNR/TicketInfos/TicketInfo/carrier"/>
                    </carrier>
                    <ticketNum>
                        <xsl:value-of select="PNR/TicketInfos/TicketInfo/ticketNumber"/>
                    </ticketNum>
                </TicketInfo>
            </TicketInfos>
            <PhoneNumbers>
                <xsl:for-each select="PNR/Contacts/PhoneNumbers/PhoneNumber">
                    <PhoneNumber>
                        <number>
                            <xsl:value-of select="number"/>
                        </number>
                        <type>
                            <xsl:value-of select="type"/>
                        </type>
                        <longFreeText>
                            <xsl:value-of select="notes"/>
                        </longFreeText>
                        <cityCode>
                            <xsl:value-of select="cityCode"/>
                        </cityCode>
                    </PhoneNumber>
                </xsl:for-each>
            </PhoneNumbers>
            <AdditionalInfo>
                <TravelerProfileIdentifiers></TravelerProfileIdentifiers>
            </AdditionalInfo>
        </pnr>

    </xsl:template>
</xsl:stylesheet>