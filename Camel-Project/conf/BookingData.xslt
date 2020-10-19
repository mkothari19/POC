<?xml version="1.0" encoding="utf-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <pnr>
            <PNRid>
                  <xsl:value-of select="PNR/Identification/sourcePNRId"/>
            </PNRid>
            <recordLocator>
                <xsl:value-of select="PNR/Identification/recordLocator"/>
            </recordLocator>
            <PNR_ID> 
            <xsl:value-of select="PNR/Identification/pnrId"/>
            </PNR_ID>
			<BDUPNRID>
			<xsl:text>null</xsl:text>
			</BDUPNRID>
            <GDS>
                <xsl:value-of select="PNR/Identification/gds"/>
            </GDS>
            <platformID>
             <xsl:text>null</xsl:text>
            </platformID>
            <securityManagerID>
			 <xsl:value-of select="PNR/Identification/customerId"/>
			</securityManagerID>
            <globalCustomerNumber>
			 <xsl:value-of select="PNR/Identification/globalCustomerId"/>
			</globalCustomerNumber>
			<AgencyIataNumber>
			</AgencyIataNumber>
            <customerNumber>
                <xsl:value-of select="PNR/Identification/customerNumber"/>
            </customerNumber>
            <creationOfficeID>
			<xsl:text>null</xsl:text>
			</creationOfficeID>
            <agentSignature>
			<xsl:text>null</xsl:text>
			</agentSignature>
            <agentPCC>
			<xsl:text>null</xsl:text>
			</agentPCC>
			<EmulatedPCC>
			<xsl:text>null</xsl:text>
			</EmulatedPCC>
            <BookingDateTime>
                <xsl:value-of select="PNR/Identification/bookingDate"/>
            </BookingDateTime>
			<TicketingDateTime><xsl:text>null</xsl:text></TicketingDateTime>
			<CancelDate><xsl:text>null</xsl:text></CancelDate>
            <etrHits><xsl:text>null</xsl:text></etrHits>
            <instance>
               <xsl:if test="PNR/Identification/instance!=''"> 
        		<xsl:value-of select="PNR/Identification/instance"/>
						 </xsl:if>
			 <xsl:if test="PNR/Identification/instance =''"> 
        
				<xsl:text>Airbnb</xsl:text>
						 </xsl:if> 	

			 <xsl:if test="not(PNR/Identification/instance)"> 
        
				<xsl:text>Airbnb</xsl:text>
						 </xsl:if> 						
            </instance>
			<dataType>
			<xsl:text>Airbnb</xsl:text>
			</dataType>
			<intlFlight><xsl:text>null</xsl:text></intlFlight>
			<airPortSummary><xsl:text>null</xsl:text></airPortSummary>
          	<Last_Update_DT>
          	 <xsl:value-of select="PNR/Identification/landingtime"/>
		  </Last_Update_DT>
		  <airPortSummary><xsl:text>null</xsl:text></airPortSummary>
		  <carrierSummary><xsl:text>null</xsl:text></carrierSummary>
            <Travelers>
                <Traveler>
                    <firstName>
                        <xsl:value-of select="PNR/Travelers/Traveler/Name/firstName"/>
                    </firstName>
                    <middleName>
					<xsl:text>null</xsl:text>
                    </middleName>
                    <lastName>
                        <xsl:value-of select="PNR/Travelers/Traveler/Name/lastName"/>
                    </lastName>
                    <FrequentFlyerInfo>
                            <FrequentFlyer>
                                <frequentTravelerNum><xsl:text>null</xsl:text></frequentTravelerNum>
                                <frequentTravelerSupplier><xsl:text>null</xsl:text></frequentTravelerSupplier>
                            </FrequentFlyer>
                      
                    </FrequentFlyerInfo>
                    <MealPreferenceInfo>
                            <MealPreference>
							<mealPreferenceCode><xsl:text>null</xsl:text></mealPreferenceCode>
                             <mealPreferenceSegment><xsl:text>null</xsl:text></mealPreferenceSegment>
                            </MealPreference>
                    </MealPreferenceInfo>
                    <SeatPreferenceInfo>
                            <SeatPref>
                                <preferenceLocationCode><xsl:text>null</xsl:text></preferenceLocationCode>
                                <preferenceLocationText><xsl:text>null</xsl:text></preferenceLocationText>
                                <longFreeText><xsl:text>null</xsl:text></longFreeText>
                            </SeatPref>
                     </SeatPreferenceInfo>
                    <Seats>
					<Seat>
                        <segmentNumber><xsl:text>null</xsl:text></segmentNumber>
                         <location><xsl:text>null</xsl:text></location>
                    </Seat>
                        
                    </Seats>
                    <passengerNumber><xsl:text>null</xsl:text></passengerNumber>
                    <nameInGds>
                        <xsl:value-of select="PNR/Travelers/Traveler/Name/nameInGds"/>
                    </nameInGds>
                    <NameNumber>
					<prefix><xsl:text>null</xsl:text></prefix>
					<suffix><xsl:text>null</xsl:text></suffix>
					</NameNumber>
					
					 <Contacts>
				   <Contact>
				   <PhoneNumbers>
				 <xsl:for-each select="PNR/Contacts/PhoneNumbers/PhoneNumber">
				    <BDUPhoneNumber>
                        <number>
                            <xsl:value-of select="number"/>
                        </number>
                        <type>
                            <xsl:value-of select="type"/>
                        </type>
                        <longFreeText><xsl:text>null</xsl:text></longFreeText>
                        <cityCode><xsl:text>null</xsl:text></cityCode>
						<itemCode>
					<xsl:if test="type='Home Phone Number' or type='Home'">
					<xsl:text>17</xsl:text>
						</xsl:if>
					<xsl:if test="type='Mobile Phone Number' or type='Cellular'">
					<xsl:text>18</xsl:text>
						</xsl:if>	
					<xsl:if test="type='Work Phone Number' or type='Business'">
					<xsl:text>19</xsl:text>
						</xsl:if>		
					<xsl:if test="type='Secondary Mobile Phone Number'">
					<xsl:text>20</xsl:text>
						</xsl:if>
					<xsl:if test="type='Other Number' or type='Agent'">
					<xsl:text>21</xsl:text>
						</xsl:if>								
						</itemCode>
                    </BDUPhoneNumber>
					
					 </xsl:for-each>
					   </PhoneNumbers>
					   <EmailAddresses>
                <xsl:for-each select="PNR/Contacts/EmailAddresses/EmailAddress">
                    <EmailAddress>
                        <email>  
						<xsl:value-of select="emailAddress"/>
						</email>
                        <type>
                            <xsl:value-of select="type"/>
                        </type>
                        <email>
                            <xsl:value-of select="contents"/>
                        </email>
						<itemCode>
					<xsl:if test="type='Primary Email Address' or type='primary'">
					<xsl:text>22</xsl:text>
						</xsl:if>
					<xsl:if test="type='Secondary Email Address' or type='secondary'">
					<xsl:text>23</xsl:text>
						</xsl:if>	
					<xsl:if test="type='Other Email Address' or type='other'">
					<xsl:text>24</xsl:text>
						</xsl:if>		
												
						</itemCode>
                    </EmailAddress>
                </xsl:for-each>
            </EmailAddresses>
			<EmergencyContacts>
			<xsl:for-each select="PNR/Contacts/EmergencyContacts/EmergencyContact">
					<EmergencyContact>
						<ContactName><xsl:text>null</xsl:text></ContactName>
					  <type><xsl:text>null</xsl:text></type>
					  <itemCode><xsl:text>null</xsl:text></itemCode>
					</EmergencyContact>
					</xsl:for-each>
			</EmergencyContacts>
					 </Contact>
				  </Contacts>
                <statInfo><xsl:text>null</xsl:text></statInfo>				  
                </Traveler>
            </Travelers>

           
            <Segments>
                       <!-- HotelSegments -->
                <xsl:for-each select="PNR/Segments/Segment">
                    <xsl:choose>
                        <xsl:when test="@xsi:type = 'Hotel'" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
                            <Segment>
                            <segType>
							<xsl:text>Hotel</xsl:text>
							</segType>
								
                                <segmentNumber>
                                    <xsl:value-of select="segmentNumber"/>
                                </segmentNumber>
                                <TDSValidated>
								<xsl:text>null</xsl:text>
								</TDSValidated>
                                <isCancelled>
								<xsl:text>null</xsl:text>
								</isCancelled>
                                <isScheduleChange>
								<xsl:text>null</xsl:text>
								</isScheduleChange>
								<crs_code>
								<xsl:text>null</xsl:text>
								</crs_code>
								<SegRecordLocator>
								<xsl:text>null</xsl:text>
								</SegRecordLocator>
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
									<state>
									 <xsl:value-of select="Hotel/Address/state"/>
									</state>
                                    <cityCode>
									<xsl:text>null</xsl:text>
									</cityCode>
                                    <zip>
                                        <xsl:value-of select="Hotel/Address/zip"/>
                                    </zip>
                                    <country>
                                        <xsl:value-of select="Hotel/Address/country"/>
                                    </country>
									<longFreeText><xsl:text>null</xsl:text></longFreeText>
									<latitude>
									<xsl:value-of select="Hotel/Address/latitude"/>
									</latitude>
									<longitude>
									<xsl:value-of select="Hotel/Address/longitude"/>
									</longitude>
                                </Address>
                                <numberGuests>
                                    <xsl:value-of select="RoomInfo/guestCount"/>
                                </numberGuests>
                                <bookingSiteConfNum>
                                    <xsl:text>null</xsl:text>
                                </bookingSiteConfNum>
                                <bookingSiteURL>
								<xsl:text>null</xsl:text>
								</bookingSiteURL>
                                    <supplierConfNum>
                                 <xsl:value-of select="supplierConfNum"/>
                                </supplierConfNum>
                                <supplierName>
                                    <xsl:value-of select="supplierName"/>
                                </supplierName>
                                <supplierPhone>
								<xsl:text>null</xsl:text>
								</supplierPhone>
                                <hotelChainCode>
                                    <xsl:value-of select="Hotel/chainCode"/>
                                </hotelChainCode>
                                <numberOfRooms>
                                    <xsl:value-of select="RoomInfo/numberOfRooms"/>
                                </numberOfRooms>
                                <roomType>
								<xsl:text>null</xsl:text>
								</roomType>
                                <specialInstructions>
								<xsl:text>null</xsl:text>
								</specialInstructions>
                                <propertyCode>
                                    <xsl:value-of select="Hotel/propertyCode"/>
                                </propertyCode>
                                <creditCardGuarantee>
								<xsl:text>null</xsl:text>
								</creditCardGuarantee>
                                <bookedThrough>
								  <xsl:value-of select="bookedThrough"/>
								</bookedThrough>
                                <numberOfNights>
                                    <xsl:value-of select="Hotel/numberOfNights"/>
                                </numberOfNights>
                                <status>
                                    <xsl:value-of select="Status/Description"/>
                                </status>
								<longFreeText>
								<xsl:text>null</xsl:text>
								</longFreeText>
                                <rateCode>
								<xsl:text>null</xsl:text>
								</rateCode>
                                <Rate>
                                    <amount>
									<xsl:value-of select="Fare/amount"/>
									</amount>
                                    <currency>
									<xsl:value-of select="Fare/currency"/>
									</currency>
                                </Rate>
								   <RateChangeInfos>
								   <xsl:value-of select="RateChangeInfos"/>
								   </RateChangeInfos>
                                <corporateDiscountNumber>
                                    <xsl:value-of select="corporateDiscountNumber"/>
                                </corporateDiscountNumber>
                             <isPassive>
                                    <xsl:value-of select="isPassive"/>
                                </isPassive>
                                <RateCategory>
								<xsl:text>null</xsl:text>
								</RateCategory>
                                <ExtraPersonRate>
								<xsl:text>null</xsl:text>
								</ExtraPersonRate>
								<isMultiDateRate>
									<xsl:text>null</xsl:text>
								</isMultiDateRate>
                                <rateChangeDuringStay>	<xsl:text>null</xsl:text></rateChangeDuringStay>
                                <hotelOverview>	<xsl:text>null</xsl:text></hotelOverview>
								<RateTotal>
								<amount>
								 <xsl:value-of select="EstimatedTotalFare/amount"/>
								</amount>
								<currency>
								<xsl:value-of select="EstimatedTotalFare/currency"/>
								</currency>
								</RateTotal>
								
                            </Segment>
                        </xsl:when>
                    </xsl:choose>
                </xsl:for-each>
	<!-- 'ARNKSegment -->			
				<Segment>
				  <segmentNumber>
				  	<xsl:text>null</xsl:text>
					</segmentNumber>
				  <TDSValidated>
				  <xsl:text>null</xsl:text>
				  </TDSValidated>
				  <isCancelled>
				  <xsl:text>null</xsl:text>
				  </isCancelled>
				  <isScheduleChange>
				  <xsl:text>null</xsl:text>
				  </isScheduleChange>
                  <segType>
				<xsl:text>ARNK</xsl:text>
				 </segType>
				</Segment>
				
				<!-- 'AirSegments -->			
				<Segment>
				  <segmentNumber>
				    <xsl:text>null</xsl:text>
				  </segmentNumber>
				  <TDSValidated>
				    <xsl:text>null</xsl:text>
				  </TDSValidated>
				  <isCancelled>
				    <xsl:text>null</xsl:text>
				  </isCancelled>
				  <isScheduleChange>
				    <xsl:text>null</xsl:text>
				  </isScheduleChange>
                  <segType>
				<xsl:text>Air</xsl:text>
				 </segType>
				 <startDateTime>
				  <xsl:text>null</xsl:text>
				 </startDateTime>
				 <endDateTime>
				  <xsl:text>null</xsl:text>
				 </endDateTime>
				 <startCityName>
				 <xsl:text>null</xsl:text>
				 </startCityName>
				 <endCityName>
				  <xsl:text>null</xsl:text>
				 </endCityName>
				<marketingAirlineCode>
				 <xsl:text>null</xsl:text>
				</marketingAirlineCode>
				<marketingFlightNumber>
				 <xsl:text>null</xsl:text>
				</marketingFlightNumber>
				<operatingAirline>
				 <xsl:text>null</xsl:text>
				</operatingAirline>
				<operatingAirlineCode>
				 <xsl:text>null</xsl:text>
				</operatingAirlineCode>
				<mealCode>
				 <xsl:text>null</xsl:text>
				</mealCode>
				<mealDescription>
				 <xsl:text>null</xsl:text>
				</mealDescription>
				<bookingSiteConfNum>
				<xsl:text>null</xsl:text>
				</bookingSiteConfNum>
				<supplierConfNum>
				<xsl:text>null</xsl:text>
				</supplierConfNum>
				<equipmentCode>
				<xsl:text>null</xsl:text>
				</equipmentCode>
				<departureAirportCode>
				<xsl:text>null</xsl:text>
				</departureAirportCode>
				<arrivalAirportCode>
				<xsl:text>null</xsl:text>
				</arrivalAirportCode>
				<classOfService>
				<xsl:text>null</xsl:text>
				</classOfService>
				<classOfServiceDescription>
				<xsl:text>null</xsl:text>
				</classOfServiceDescription>
				<status>
				<xsl:text>null</xsl:text>
				</status>
				<isChangeOfGuage>
				<xsl:text>null</xsl:text>
				</isChangeOfGuage>
				<calculatedDuration>
				<xsl:text>null</xsl:text>
				</calculatedDuration>
				<numberOfStops>
				<xsl:text>null</xsl:text>
				</numberOfStops>
				<StopsCityList>
				<xsl:text>null</xsl:text>
				</StopsCityList>
				<hasSpecialMeal>
				<xsl:text>null</xsl:text>
				</hasSpecialMeal>
				<MarriedToSegments>
				<xsl:text>null</xsl:text>
				</MarriedToSegments>
				<ConnectedSegments>
				<xsl:text>null</xsl:text>
				</ConnectedSegments>
				<isCodeShare>
				<xsl:text>null</xsl:text>
				</isCodeShare>
				<codeShareComment1>
				<xsl:text>null</xsl:text>
				</codeShareComment1>
				<isFlown>
				<xsl:text>null</xsl:text>
				</isFlown>
				<isOpen>
				<xsl:text>null</xsl:text>
				</isOpen>
				<isPassive>
				<xsl:text>null</xsl:text>
				</isPassive>
				<isTicketless>
				<xsl:text>null</xsl:text>
				</isTicketless>
				<InFlightServiceCodes>
				<xsl:text>null</xsl:text>
				</InFlightServiceCodes>
				<isETicketCandidate>
				<xsl:text>null</xsl:text>
				</isETicketCandidate>
				<arrivalTerminal>
				<xsl:text>null</xsl:text>
				</arrivalTerminal>
				<flightDistance>
				<xsl:text>null</xsl:text>
				</flightDistance>
				<isTicketed>
				<xsl:text>null</xsl:text>
				</isTicketed>
				<FareBasisCode>
				<xsl:text>null</xsl:text>
				</FareBasisCode>
				<TypeCode>
				<xsl:text>null</xsl:text>
				</TypeCode>

	</Segment>
          <!-- RailSegment'-->
	<Segment>
		 <segType>
		 <xsl:text>Rail</xsl:text>
		 </segType>
		<segmentNumber>
		<xsl:text>null</xsl:text>
		</segmentNumber>
		<TDSValidated>
		<xsl:text>null</xsl:text>
		</TDSValidated>
		<isCancelled>
		<xsl:text>null</xsl:text>
		</isCancelled>
		<isScheduleChange>
		<xsl:text>null</xsl:text>
		</isScheduleChange>
		<startDateTime>
		<xsl:text>null</xsl:text>
		</startDateTime>
		<endDateTime>
		<xsl:text>null</xsl:text>
		</endDateTime>
		<StartStationAddress>
		<addr1><xsl:text>null</xsl:text> </addr1>
		<longFreeText><xsl:text>null</xsl:text></longFreeText>
		</StartStationAddress>
		<EndStationAddress>
		<addr1><xsl:text>null</xsl:text> </addr1>
		<longFreeText><xsl:text>null</xsl:text></longFreeText>
		</EndStationAddress>
		<carrierName>
		<xsl:text>null</xsl:text>
		</carrierName>
		<confirmationNum>
		<xsl:text>null</xsl:text>
		</confirmationNum>
		<bookingSiteConfNum>
		<xsl:text>null</xsl:text>
		</bookingSiteConfNum>
		<bookingSiteURL>
		<xsl:text>null</xsl:text>
		</bookingSiteURL>
		<departureStationCode>
		<xsl:text>null</xsl:text>
		</departureStationCode>
		<arrivalStationCode>
		<xsl:text>null</xsl:text>
		</arrivalStationCode>
		<trainNumber>
		<xsl:text>null</xsl:text>
		</trainNumber>
		<operator>
		<xsl:text>null</xsl:text>
		</operator>
		<classOfService>
		<xsl:text>null</xsl:text>
		</classOfService>
		<classOfServiceDescription>
		<xsl:text>null</xsl:text>
		</classOfServiceDescription>
		<status>
		<xsl:text>null</xsl:text>
		</status>
		<longFreeText>
		<xsl:text>null</xsl:text>
		</longFreeText>
		<specialInstructions>
		<xsl:text>null</xsl:text>
		</specialInstructions>
		<numberInParty>
		<xsl:text>null</xsl:text>
		</numberInParty>
		<numberOfStops>
		<xsl:text>null</xsl:text>
		</numberOfStops>
		<isPassive>
		<xsl:text>null</xsl:text>
		</isPassive>
		<RateQuote>
		<xsl:text>null</xsl:text>
		</RateQuote>
		<calculatedDuration>
		<xsl:text>null</xsl:text>
		</calculatedDuration>
		<Typecode>
		<xsl:text>null</xsl:text>
		</Typecode>
     </Segment>
          		 
<!--MiscelleneousSegment-->
<Segment>
		<segType>
		<xsl:text>Miscelleneous</xsl:text>
		</segType>
		<segmentNumber><xsl:text>null</xsl:text></segmentNumber>
		<TDSValidated><xsl:text>null</xsl:text></TDSValidated>
		<isCancelled><xsl:text>null</xsl:text></isCancelled>
		<isScheduleChange><xsl:text>null</xsl:text></isScheduleChange>
		<startDateTime><xsl:text>null</xsl:text></startDateTime>
		<endDateTime><xsl:text>null</xsl:text></endDateTime>
		<numberInParty><xsl:text>null</xsl:text></numberInParty>
		<departureCityCode><xsl:text>null</xsl:text></departureCityCode>
		<operator><xsl:text>null</xsl:text></operator>
		<status><xsl:text>null</xsl:text></status>
		<longFreeText><xsl:text>null</xsl:text></longFreeText>

</Segment>

<!-- CarSegment -->
  <Segment>
		<segType>
		<xsl:text>Car</xsl:text>
		</segType>
		<segmentNumber><xsl:text>null</xsl:text></segmentNumber>
		<TDSValidated><xsl:text>null</xsl:text></TDSValidated>
		<isCancelled><xsl:text>null</xsl:text></isCancelled>
		<isScheduleChange><xsl:text>null</xsl:text></isScheduleChange>
		<startDateTime><xsl:text>null</xsl:text></startDateTime>
		<endDateTime><xsl:text>null</xsl:text></endDateTime>
		<StartLocationAddress>
		<addr1><xsl:text>null</xsl:text></addr1>
		<addr2><xsl:text>null</xsl:text></addr2>
		<city><xsl:text>null</xsl:text></city>
		<state><xsl:text>null</xsl:text></state>
		<zip><xsl:text>null</xsl:text></zip>
		<country><xsl:text>null</xsl:text></country>
		<longFreeText><xsl:text>null</xsl:text></longFreeText>
		</StartLocationAddress>
		<EndLocationAddress>
		<addr1><xsl:text>null</xsl:text></addr1>
		<state><xsl:text>null</xsl:text></state>
		<country><xsl:text>null</xsl:text></country>
		</EndLocationAddress>
		<carType><xsl:text>null</xsl:text></carType>
		<carDescription><xsl:text>null</xsl:text></carDescription>
		<bookingSiteConfNum><xsl:text>null</xsl:text></bookingSiteConfNum>
		<bookingSiteURL><xsl:text>null</xsl:text></bookingSiteURL>
		<supplierConfNum><xsl:text>null</xsl:text></supplierConfNum>
		<supplierName><xsl:text>null</xsl:text></supplierName>
		<locationCode><xsl:text>null</xsl:text></locationCode>
		<pickupLocation><xsl:text>null</xsl:text></pickupLocation>
		<dropOffLocation><xsl:text>null</xsl:text></dropOffLocation>
		<rentalCompany><xsl:text>null</xsl:text></rentalCompany>
		<compCode><xsl:text>null</xsl:text></compCode>
		<numberOfCars><xsl:text>null</xsl:text></numberOfCars>
		<ExtraDayFee><xsl:text>null</xsl:text></ExtraDayFee>
		<ExtraHourFee>
		<amount><xsl:text>null</xsl:text></amount>
		<currency><xsl:text>null</xsl:text></currency>
		</ExtraHourFee>
			<currency><xsl:text>null</xsl:text></currency>
			<status><xsl:text>null</xsl:text></status>
			<corporateDiscountNumber><xsl:text>null</xsl:text></corporateDiscountNumber>
			<GuaranteedRate><xsl:text>null</xsl:text></GuaranteedRate>
			<BaseRate><xsl:text>null</xsl:text></BaseRate>
			<amount><xsl:text>null</xsl:text></amount>
			<currency><xsl:text>null</xsl:text></currency>
			<BaseRate><xsl:text>null</xsl:text></BaseRate>
			<EstimatedTotal>
			<amount><xsl:text>null</xsl:text></amount>
		<currency><xsl:text>null</xsl:text></currency>
			</EstimatedTotal>
			<QuotedRate>
			<amount><xsl:text>null</xsl:text></amount>
		<currency><xsl:text>null</xsl:text></currency>
			</QuotedRate>
			<DropCharge><xsl:text>null</xsl:text></DropCharge>
			<rateCode><xsl:text>null</xsl:text></rateCode>
			<bookedThrough><xsl:text>null</xsl:text></bookedThrough>
			<reservationName><xsl:text>null</xsl:text></reservationName>
			<rateCurrencyCode><xsl:text>null</xsl:text></rateCurrencyCode>
			<dcsAuxRateCode><xsl:text>null</xsl:text></dcsAuxRateCode>
			<clientIDNumber><xsl:text>null</xsl:text></clientIDNumber>
			<OneWayDropFee><xsl:text>null</xsl:text></OneWayDropFee>
			<isKilometers><xsl:text>null</xsl:text></isKilometers>
			<isManuallyBooked><xsl:text>null</xsl:text></isManuallyBooked>
			<frequentRenterID><xsl:text>null</xsl:text></frequentRenterID>
			<isPassive><xsl:text>null</xsl:text></isPassive>
			<passengerNumber><xsl:text>null</xsl:text></passengerNumber>
			<CarRateCategory><xsl:text>null</xsl:text></CarRateCategory>
			<AgentEnteredRate><xsl:text>null</xsl:text></AgentEnteredRate>
			<CarRateType><xsl:text>null</xsl:text></CarRateType>
			<mileageAllowance><xsl:text>null</xsl:text></mileageAllowance>
			<extraDayMileageAllowance><xsl:text>null</xsl:text></extraDayMileageAllowance>
			<ExtraDayMileageFee><xsl:text>null</xsl:text></ExtraDayMileageFee>
			<extraHourMileageAllowance><xsl:text>null</xsl:text></extraHourMileageAllowance>
			<ExtraHourMileageFee><xsl:text>null</xsl:text></ExtraHourMileageFee>
			<totalMandatoryCharges><xsl:text>null</xsl:text></totalMandatoryCharges>
			<ExtraWeekFee><xsl:text>null</xsl:text></ExtraWeekFee>
			<extraWeekMileageFee><xsl:text>null</xsl:text></extraWeekMileageFee>
			<numberOfDays><xsl:text>null</xsl:text></numberOfDays>
			<numberOfHours><xsl:text>null</xsl:text></numberOfHours>

		</Segment>
		
		<!--TourSegment-->
		<Segment>
		<segType>
		<xsl:text>Tour</xsl:text>
		</segType>
		<segmentNumber><xsl:text>null</xsl:text></segmentNumber>
		<TDSValidated><xsl:text>null</xsl:text></TDSValidated>
		<isCancelled><xsl:text>null</xsl:text></isCancelled>
		<isScheduleChange><xsl:text>null</xsl:text></isScheduleChange>
		<startDateTime><xsl:text>null</xsl:text></startDateTime>
		<endDateTime><xsl:text>null</xsl:text></endDateTime>
		<StartLocationAddress>
		<addr1><xsl:text>null</xsl:text></addr1>
		</StartLocationAddress>
		<EndLocationAddress>
		<addr1><xsl:text>null</xsl:text></addr1>
		</EndLocationAddress>
		<StartLocationName><xsl:text>null</xsl:text></StartLocationName>
		<EndLocationName><xsl:text>null</xsl:text></EndLocationName>
		<carrierName><xsl:text>null</xsl:text></carrierName>
		<confirmationNum><xsl:text>null</xsl:text></confirmationNum>
		<vehicleDescription><xsl:text>null</xsl:text></vehicleDescription>
		<AgencyCommission><xsl:text>null</xsl:text></AgencyCommission>
		<BalanceAmount><xsl:text>null</xsl:text></BalanceAmount>
		<CancellationFee><xsl:text>null</xsl:text></CancellationFee>
		<DepositAmountDue><xsl:text>null</xsl:text></DepositAmountDue>
		<TotalTax><xsl:text>null</xsl:text></TotalTax>
		<BasePrice><xsl:text>null</xsl:text></BasePrice>
		<cityCode><xsl:text>null</xsl:text></cityCode>
		<longFreeText><xsl:text>null</xsl:text></longFreeText>
		<TotalRate><xsl:text>null</xsl:text></TotalRate>
		<TourRate><xsl:text>null</xsl:text></TourRate>
		<numberOfNights><xsl:text>null</xsl:text></numberOfNights>
		<numberOfRooms><xsl:text>null</xsl:text></numberOfRooms>
		<tourNumber><xsl:text>null</xsl:text></tourNumber>
		<serviceEndDate><xsl:text>null</xsl:text></serviceEndDate>
		<vendorCodeAndName><xsl:text>null</xsl:text></vendorCodeAndName>
		<status><xsl:text>null</xsl:text></status>
		<tourRateFreeText><xsl:text>null</xsl:text></tourRateFreeText>

		</Segment>
            </Segments>

            <BillingAddressInfo>
                <recipient><xsl:text>null</xsl:text></recipient>
                <addr1><xsl:text>null</xsl:text></addr1>
                <addr2><xsl:text>null</xsl:text></addr2>
				<addr3><xsl:text>null</xsl:text></addr3>
                <city><xsl:text>null</xsl:text></city>
                <state><xsl:text>null</xsl:text></state>
				<country><xsl:text>null</xsl:text></country>
                <zip><xsl:text>null</xsl:text></zip>
				<longFreeTex><xsl:text>null</xsl:text></longFreeTex>
            </BillingAddressInfo>
			
            <DeliveryAddressInfo>
                 <recipient><xsl:text>null</xsl:text></recipient>
                <addr1><xsl:text>null</xsl:text></addr1>
                <addr2><xsl:text>null</xsl:text></addr2>
				<addr3><xsl:text>null</xsl:text></addr3>
                <city><xsl:text>null</xsl:text></city>
                <state><xsl:text>null</xsl:text></state>
				<country><xsl:text>null</xsl:text></country>
                <zip><xsl:text>null</xsl:text></zip>
				<longFreeTex><xsl:text>null</xsl:text></longFreeTex>
            </DeliveryAddressInfo>
            <Remarks>
                    <Remark>
                        <lineNumber><xsl:text>null</xsl:text></lineNumber>
                        <type><xsl:text>null</xsl:text></type>
                        <contents><xsl:text>null</xsl:text></contents>
						<category><xsl:text>null</xsl:text></category>
						<label><xsl:text>null</xsl:text></label>
                        <PassengerNumbers><xsl:text>null</xsl:text></PassengerNumbers>
                        <SegmentNumbers><xsl:text>null</xsl:text></SegmentNumbers>
                    </Remark>
               
            </Remarks>
            <SSRs>
                   <SSR>
                        <lineNumber><xsl:text>null</xsl:text></lineNumber>
                        <type><xsl:text>null</xsl:text></type>
                        <code><xsl:text>null</xsl:text></code>
						<status><xsl:text>null</xsl:text></status>
                        <contents><xsl:text>null</xsl:text></contents>
                        <PassengerNumbers>
                            <intElement><xsl:text>null</xsl:text></intElement>
                        </PassengerNumbers>
                        <SegmentNumbers>
                            <intElement><xsl:text>null</xsl:text></intElement>
                        </SegmentNumbers>
                    </SSR>
               
            </SSRs>
            <FormsOfPayment>
                <FormOfPayment>
                    <FormOfPayment><xsl:text>null</xsl:text></FormOfPayment>
                    <creditCardNumber><xsl:text>null</xsl:text></creditCardNumber>
                    <creditCardExpiration><xsl:text>null</xsl:text></creditCardExpiration>
                    <creditCardCompany><xsl:text>null</xsl:text></creditCardCompany>
                </FormOfPayment>
            </FormsOfPayment>

            <StoredFares>
                <StoredFare>
                    <BaseFare>
                        <amount><xsl:text>null</xsl:text></amount>
                        <currency><xsl:text>null</xsl:text></currency>
                    </BaseFare>
                    <TotalFare>
                        <amount><xsl:text>null</xsl:text></amount>
                        <currency><xsl:text>null</xsl:text></currency>
                    </TotalFare>
                    <EquivalentFare>
                        <amount><xsl:text>null</xsl:text></amount>
                        <currency><xsl:text>null</xsl:text></currency>
                    </EquivalentFare>
                    <validatingCarrier><xsl:text>null</xsl:text></validatingCarrier>
                    <privateFareAccountCode><xsl:text>null</xsl:text></privateFareAccountCode>
                    <inputMessage><xsl:text>null</xsl:text></inputMessage>
					<infoMessage><xsl:text>null</xsl:text></infoMessage>
					<endorsements><xsl:text>null</xsl:text></endorsements>
                    <fareCalculation><xsl:text>null</xsl:text></fareCalculation>
                    <commissionPercentage><xsl:text>null</xsl:text></commissionPercentage>
                    <commissionAmount><xsl:text>null</xsl:text></commissionAmount>
                    <quoteDate><xsl:text>null</xsl:text></quoteDate>
                    <lastDateToTicket><xsl:text>null</xsl:text></lastDateToTicket>
					 <CreditCard><xsl:text>null</xsl:text></CreditCard>
					 <CompareFare><xsl:text>null</xsl:text></CompareFare>
					 <FareCode><xsl:text>null</xsl:text></FareCode>
                    <StoredFareSegments>
                              <StoredFareSegment>
                                <carrierCode><xsl:text>null</xsl:text></carrierCode>
                                <departureAirportCode><xsl:text>null</xsl:text></departureAirportCode>
                                <departureDate><xsl:text>null</xsl:text></departureDate>
                                <segmentNumber><xsl:text>null</xsl:text></segmentNumber>
                                <fare><xsl:text>null</xsl:text></fare>
                                <classOfService><xsl:text>null</xsl:text></classOfService>
                                <statusCode><xsl:text>null</xsl:text></statusCode>
                                <fareBasis><xsl:text>null</xsl:text></fareBasis>
								<ticketDesignator><xsl:text>null</xsl:text></ticketDesignator>
                                <notValidBefore><xsl:text>null</xsl:text></notValidBefore>
                                <notValidAfter><xsl:text>null</xsl:text></notValidAfter>
                            </StoredFareSegment>
                        </StoredFareSegments>
                    <StoredFarePassengers>
                        <StoredFarePassenger>
                            <fareConstruction><xsl:text>null</xsl:text></fareConstruction>
                            <BaseFare>
                                <amount><xsl:text>null</xsl:text></amount>
                                <currency><xsl:text>null</xsl:text></currency>
                            </BaseFare>
                            <TotalFare>
                                <amount><xsl:text>null</xsl:text></amount>
                                <currency><xsl:text>null</xsl:text></currency>
                            </TotalFare>
                            <lastDateToTicket><xsl:text>null</xsl:text></lastDateToTicket>
                            <TicketStatus><xsl:text>null</xsl:text></TicketStatus>
                            <passengerNumber><xsl:text>null</xsl:text></passengerNumber>
                            <passengerTypeIdentifier><xsl:text>null</xsl:text></passengerTypeIdentifier>
                            <TicketType><xsl:text>null</xsl:text></TicketType>
                        </StoredFarePassenger>
                    </StoredFarePassengers>
                   
                </StoredFare>
            </StoredFares>
            <TicketRequests>
                <TicketRequest>
                    <SegmentNumbers><xsl:text>null</xsl:text></SegmentNumbers>
                    <PassengerNumbers><xsl:text>null</xsl:text></PassengerNumbers>
                    <isAlreadyTicketed><xsl:text>null</xsl:text></isAlreadyTicketed>
                    <lineNumber><xsl:text>null</xsl:text></lineNumber>
                    <requestDate><xsl:text>null</xsl:text></requestDate>
					<branchPcc><xsl:text>null</xsl:text></branchPcc>
                    <longFreeText><xsl:text>null</xsl:text></longFreeText>
                    <QueuePlacements>
					<QueuePlacement>
					<queueDate><xsl:text>null</xsl:text></queueDate>
					<queue><xsl:text>null</xsl:text></queue>
					</QueuePlacement>
					</QueuePlacements>
                </TicketRequest>
            </TicketRequests>
            <TicketInfos>
                <TicketInfo>
                    <BaseFare>
                        <amount><xsl:text>null</xsl:text></amount>
                        <currency><xsl:text>null</xsl:text></currency>
                    </BaseFare>
                    <FareTotal>
                       <amount><xsl:text>null</xsl:text></amount>
                        <currency><xsl:text>null</xsl:text></currency>
                    </FareTotal>
                    <CommissionTotal>
                       <amount><xsl:text>null</xsl:text></amount>
                        <currency><xsl:text>null</xsl:text></currency>
                    </CommissionTotal>
                    <isETicket><xsl:text>null</xsl:text></isETicket>
                    <carrier><xsl:text>null</xsl:text></carrier>
                    <ticketNum><xsl:text>null</xsl:text></ticketNum>
					<invoiceNumber><xsl:text>null</xsl:text></invoiceNumber>
					<TotalTax><xsl:text>null</xsl:text></TotalTax>
					<TaxDetails>
					<TaxDetail>
					<TaxCode><xsl:text>null</xsl:text></TaxCode>
					<TaxAmount><xsl:text>null</xsl:text></TaxAmount>
					 <Currency><xsl:text>null</xsl:text></Currency>
					</TaxDetail>
					</TaxDetails>
					
                </TicketInfo>
            </TicketInfos>
            <PhoneNumbers>
                    <PhoneNumber>
                        <number><xsl:text>null</xsl:text></number>
                        <type><xsl:text>null</xsl:text></type>
                        <longFreeText><xsl:text>null</xsl:text></longFreeText>
                        <cityCode><xsl:text>null</xsl:text></cityCode>
                    </PhoneNumber>
                
            </PhoneNumbers>
            <AdditionalInfo>
                <TravelerProfileIdentifiers>
				<GDSCompanyProfile><xsl:text>null</xsl:text></GDSCompanyProfile>
				<GDSTravelerProfile><xsl:text>null</xsl:text></GDSTravelerProfile>
				</TravelerProfileIdentifiers>
            </AdditionalInfo>
            
             <DL_XmlFileName>
                <xsl:value-of select="DL_XmlFileName"/>
            </DL_XmlFileName>
             <DL_ZipFileName>
                <xsl:value-of select="DL_ZipFileName"/>
            </DL_ZipFileName>
             <DL_XmlFileNameTS>
                <xsl:value-of select="DL_XmlFileNameTS"/>
            </DL_XmlFileNameTS>
             <DL_XmlInsertTS>
                <xsl:value-of select="DL_XmlInsertTS"/>
            </DL_XmlInsertTS>
        </pnr>

    </xsl:template>
</xsl:stylesheet>