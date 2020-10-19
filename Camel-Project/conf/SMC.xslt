<?xml version="1.0" encoding="utf-8"?>

<xsl:stylesheet version="1.0" xmlns:date="http://exslt.org/dates-and-times" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
    <pnr>
            <PNRid>
                <xsl:value-of select="PNRs/PNR/PNR_ID"/>
            </PNRid>
			 <PNR_ID><xsl:text>null</xsl:text></PNR_ID>
			<BDUPNRID><xsl:text>null</xsl:text> </BDUPNRID>
            <recordLocator>
                <xsl:value-of select="PNRs/PNR/LOCATOR"/>
            </recordLocator>
            <GDS>
                <xsl:value-of select="PNRs/PNR/CRS_CODE"/>
            </GDS>
            <platformID><xsl:text>null</xsl:text></platformID>
			<securityManagerID>
			 <xsl:value-of select="PNRs/PNR/ACCOUNT_ID"/>
			</securityManagerID>
			<globalCustomerNumber><xsl:text>null</xsl:text></globalCustomerNumber>
			<AgencyIataNumber><xsl:text>null</xsl:text></AgencyIataNumber>
			<customerNumber>
                <xsl:value-of select="PNRs/PNR/ACCOUNT_NUM"/>
            </customerNumber>
			<creationOfficeID><xsl:text>null</xsl:text></creationOfficeID>
			<agentSignature>
			 <xsl:value-of select="PNRs/PNR/BOOKINGAGENT"/>
			</agentSignature>
			<agentPCC>
			 <xsl:value-of select="PNRs/PNR/BOOKINGPCC"/>
			</agentPCC>
			<EmulatedPCC>
			 <xsl:value-of select="PNRs/PNR/EMULATEPCC"/>
			</EmulatedPCC>
			   <BookingDateTime>
			   <xsl:value-of select="substring-before(PNRs/PNR/BOOKINGDATE,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(PNRs/PNR/BOOKINGDATE,'T')"/>
            </BookingDateTime>
			<TicketingDateTime><xsl:text>null</xsl:text></TicketingDateTime>
            <CancelDate><xsl:text>null</xsl:text></CancelDate>

		    <etrHits><xsl:text>null</xsl:text></etrHits>
			<instance>
			<xsl:if test="PNRs/PNR/AGENCY_ID='1087'">
					<xsl:text>AFS</xsl:text>
						</xsl:if>
					<xsl:if test="PNRs/PNR/AGENCY_ID='1083'">
					<xsl:text>Airbnb</xsl:text>
						</xsl:if>	
					<xsl:if test="PNRs/PNR/AGENCY_ID='200001'">
					<xsl:text>Balboa</xsl:text>
						</xsl:if>		
					<xsl:if test="PNRs/PNR/AGENCY_ID='1088'">
					<xsl:text>BCDBSIUK</xsl:text>
						</xsl:if>
					<xsl:if test="PNRs/PNR/AGENCY_ID='1089'">
					<xsl:text>Japan</xsl:text>
						</xsl:if>
					<xsl:if test="PNRs/PNR/AGENCY_ID='1082'">
					<xsl:text>China</xsl:text>
						</xsl:if>
					<xsl:if test="PNRs/PNR/AGENCY_ID='200000'">
					<xsl:text>Compleat</xsl:text>
						</xsl:if>	
					<xsl:if test="PNRs/PNR/AGENCY_ID='1084'">
					<xsl:text>Conlin</xsl:text>
						</xsl:if>	
					<xsl:if test="PNRs/PNR/AGENCY_ID='1090'">
					<xsl:text></xsl:text>
						</xsl:if>						
			</instance>
			<dataType>
			<xsl:if test="PNRs/PNR/AGENCY_ID='1087'">
					<xsl:text>GDS</xsl:text>
						</xsl:if>
					<xsl:if test="PNRs/PNR/AGENCY_ID='1083'">
					<xsl:text>Airbnb</xsl:text>
						</xsl:if>	
					<xsl:if test="PNRs/PNR/AGENCY_ID='200001'">
					<xsl:text>GDS</xsl:text>
						</xsl:if>		
					<xsl:if test="PNRs/PNR/AGENCY_ID='1088'">
					<xsl:text>BSI</xsl:text>
						</xsl:if>
					<xsl:if test="PNRs/PNR/AGENCY_ID='1089'">
					<xsl:text>LocalGDS</xsl:text>
						</xsl:if>
					<xsl:if test="PNRs/PNR/AGENCY_ID='1082'">
					<xsl:text>TravelSky</xsl:text>
						</xsl:if>
					<xsl:if test="PNRs/PNR/AGENCY_ID='200000'">
					<xsl:text>GDS</xsl:text>
						</xsl:if>	
					<xsl:if test="PNRs/PNR/AGENCY_ID='1084'">
					<xsl:text>GDS</xsl:text>
						</xsl:if>
						<xsl:if test="PNRs/PNR/AGENCY_ID='1090'">
					<xsl:text></xsl:text>
						</xsl:if>
			</dataType>
			<intlFlight>
			<xsl:value-of select="PNRs/PNRPNRs/PNRPNR/INTLFLIGHT"/>
			</intlFlight>
			
		<Last_Update_DT>
		<xsl:value-of select="substring-before(PNRs/PNRPNRs/PNRPNR/LASTUPDATE,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(PNRs/PNRPNRs/PNRPNR/LASTUPDATE,'T')"/>
		</Last_Update_DT>
		<airPortSummary>
		<xsl:value-of select="PNRs/PNRPNRs/PNRPNR/AIRPORTSUMMARY"/>
		</airPortSummary>
		<carrierSummary>
			<xsl:value-of select="PNRs/PNRPNRs/PNRPNR/CARRIERSUMMARY"/>
		</carrierSummary>
		<Travelers>
                <Traveler>
				
                    <firstName>
                        <xsl:value-of select="PNRs/PNRNames/PNRName/FIRSTNAME"/>
                    </firstName>
                    <middleName><xsl:text>null</xsl:text></middleName>
                    <lastName>
                       <xsl:value-of select="PNRs/PNRNames/PNRName/LASTNAME"/>
                    </lastName>
					<Contacts>
				   <Contact>
				   <PhoneNumbers>
				   <xsl:for-each select="PNRs/PNRContacts/PNRContact">
				
                  
					 <xsl:if test="ITEMTEXT='Home Phone Number'  or ITEMTEXT='Home'">
					   <BDUPhoneNumber>
                        <number>
                            <xsl:value-of select="ITEMVALUE"/>
                        </number>
                        <type>
                            <xsl:value-of select="ITEMTEXT"/>
                        </type>
                        <longFreeText><xsl:text>null</xsl:text></longFreeText>
                        <cityCode><xsl:text>null</xsl:text></cityCode>
						<itemCode>
						    <xsl:value-of select="ITEMCODE"/>
                    </itemCode>
					  </BDUPhoneNumber>
					</xsl:if>
					 <xsl:if test="ITEMTEXT='Mobile Phone Number' or ITEMTEXT='Cellular'">
                        <BDUPhoneNumber>
					   <number>
                            <xsl:value-of select="ITEMVALUE"/>
                        </number>
                        <type>
                            <xsl:value-of select="ITEMTEXT"/>
                        </type>
                        <longFreeText><xsl:text>null</xsl:text></longFreeText>
                        <cityCode><xsl:text>null</xsl:text></cityCode>
						<itemCode>
						    <xsl:value-of select="ITEMCODE"/>
                    </itemCode>
					 </BDUPhoneNumber>
					</xsl:if>
					 <xsl:if test="ITEMTEXT='Work Phone Number' or ITEMTEXT='Business'">
					  <BDUPhoneNumber>
                        <number>
                            <xsl:value-of select="ITEMVALUE"/>
                        </number>
                        <type>
                            <xsl:value-of select="ITEMTEXT"/>
                        </type>
                        <longFreeText><xsl:text>null</xsl:text></longFreeText>
                        <cityCode><xsl:text>null</xsl:text></cityCode>
						<itemCode>
						    <xsl:value-of select="ITEMCODE"/>
                    </itemCode>
					 </BDUPhoneNumber>
					</xsl:if>
					 <xsl:if test="ITEMTEXT='Secondary Mobile Phone Number'">
					  <BDUPhoneNumber>
                        <number>
                            <xsl:value-of select="ITEMVALUE"/>
                        </number>
                        <type>
                            <xsl:value-of select="ITEMTEXT"/>
                        </type>
                        <longFreeText><xsl:text>null</xsl:text></longFreeText>
                        <cityCode><xsl:text>null</xsl:text></cityCode>
						<itemCode>
						    <xsl:value-of select="ITEMCODE"/>
                    </itemCode>
					 </BDUPhoneNumber>
					</xsl:if>
					 <xsl:if test="ITEMTEXT='Other Number' or ITEMTEXT='Agent'">
					  <BDUPhoneNumber>
                        <number>
                            <xsl:value-of select="ITEMVALUE"/>
                        </number>
                        <type>
                            <xsl:value-of select="ITEMTEXT"/>
                        </type>
                        <longFreeText><xsl:text>null</xsl:text></longFreeText>
                        <cityCode><xsl:text>null</xsl:text></cityCode>
						<itemCode>
						    <xsl:value-of select="ITEMCODE"/>
                    </itemCode>
					 </BDUPhoneNumber>
					</xsl:if>
					
				</xsl:for-each>
				</PhoneNumbers>
					
	<EmailAddresses>
                <xsl:for-each select="PNRs/PNRContacts/PNRContact">

				  
				    <xsl:if test="ITEMTEXT='Primary Email Address' or ITEMTEXT='Primary'">
						 <EmailAddress>
						<type>
                            <xsl:value-of select="ITEMTEXT"/>
                        </type>
                        <email>
                             <xsl:value-of select="ITEMVALUE"/>
                        </email>
						<itemCode>
						   <xsl:value-of select="ITEMCODE"/>
						</itemCode>
						 </EmailAddress>
						</xsl:if>
						 <xsl:if test="ITEMTEXT='Secondary Email Address' or ITEMTEXT='Secondary'">
						  <EmailAddress>
						<type>
                            <xsl:value-of select="ITEMTEXT"/>
                        </type>
                        <email>
                             <xsl:value-of select="ITEMVALUE"/>
                        </email>
						<itemCode>
						   <xsl:value-of select="ITEMCODE"/>
						</itemCode>
						 </EmailAddress>
						</xsl:if>
						 <xsl:if test="ITEMTEXT='Other Email Address'">
						  <EmailAddress>
						<type>
                            <xsl:value-of select="ITEMTEXT"/>
                        </type>
                        <email>
                             <xsl:value-of select="ITEMVALUE"/>
                        </email>
						<itemCode>
						   <xsl:value-of select="ITEMCODE"/>
						</itemCode>
						 </EmailAddress>
						</xsl:if>
                </xsl:for-each>
            </EmailAddresses>
			<EmergencyContacts>
			   <xsl:for-each select="PNRs/PNRContacts/PNRContact">
			
			<xsl:if test="ITEMTEXT='Primary Emergency Contact Name'">
			<EmergencyContact>
                        <ContactName>
                            <xsl:value-of select="ITEMVALUE"/>
                        </ContactName>
                        <type>
                            <xsl:value-of select="ITEMTEXT"/>
                        </type>
                       <itemCode>
						    <xsl:value-of select="ITEMCODE"/>
                    </itemCode>
					</EmergencyContact>
					</xsl:if>
					 <xsl:if test="ITEMTEXT='Primary Emergency Contact Phone Number'">
						<EmergencyContact>
					  <ContactName>
                            <xsl:value-of select="ITEMVALUE"/>
                        </ContactName>
                        <type>
                            <xsl:value-of select="ITEMTEXT"/>
                        </type>
                       <itemCode>
						    <xsl:value-of select="ITEMCODE"/>
                    </itemCode>
					</EmergencyContact>
					</xsl:if>
					 <xsl:if test="ITEMTEXT='Primary Emergency Contact Other Number'">
					 <EmergencyContact>
                        <ContactName>
                            <xsl:value-of select="ITEMVALUE"/>
                        </ContactName>
                        <type>
                            <xsl:value-of select="ITEMTEXT"/>
                        </type>
                       <itemCode>
						    <xsl:value-of select="ITEMCODE"/>
                    </itemCode>
					</EmergencyContact>
					</xsl:if>
					 <xsl:if test="ITEMTEXT='Secondary Emergency Contact Phone Number'">
					 <EmergencyContact>
                        <ContactName>
                            <xsl:value-of select="ITEMVALUE"/>
                        </ContactName>
                        <type>
                            <xsl:value-of select="ITEMTEXT"/>
                        </type>
                        <itemCode>
						    <xsl:value-of select="ITEMCODE"/>
                    </itemCode>
					</EmergencyContact>
					</xsl:if>
			 </xsl:for-each>
			</EmergencyContacts>
			
					 </Contact>
				  </Contacts> 
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
                        <xsl:for-each select="PNRs/PNRAirs/PNRAir">
                            <Seat>
                                <segmentNumber>
                                    <xsl:value-of select="SEGNUM"/>
                                </segmentNumber>
                                <location>
                                    <xsl:value-of select="SEAT"/>
                                </location>
                            </Seat>
                        </xsl:for-each>
                    </Seats>
					<xsl:for-each select="PNRs/PNRNames/PNRName">
					 
					 <passengerNumber>
					<xsl:value-of select="substring-after(NAMENUM,'.')"/>
					</passengerNumber>
					<NameNumber>
					<prefix>
					<xsl:value-of select="substring-before(NAMENUM,'.')"/>
					</prefix>
					<suffix>
					<xsl:value-of select="substring-after(NAMENUM,'.')"/>
					</suffix>
                  </NameNumber>
            </xsl:for-each>
				 <nameInGds><xsl:text>null</xsl:text></nameInGds>		
			     
                </Traveler>
            </Travelers>

            <Segments>
			<!-- 'ARNKSegment -->			
				<Segment>
				  <segmentNumber><xsl:text>null</xsl:text></segmentNumber>
				  <TDSValidated><xsl:text>null</xsl:text></TDSValidated>
				  <isCancelled><xsl:text>null</xsl:text></isCancelled>
				  <isScheduleChange><xsl:text>null</xsl:text></isScheduleChange>
                  <segType>
				<xsl:text>ARNK</xsl:text>
				 </segType>
				</Segment>
				<!-- AirSegments -->
                <xsl:for-each select="PNRs/PNRAirs/PNRAir">
				<Segment>       
			<xsl:choose>
				<xsl:when test="TYPECODE"> 
					<segType>
					  <xsl:text>Air</xsl:text>
					  </segType>

					<segmentNumber>
				  <xsl:value-of select="SEGNUM"/>
					</segmentNumber>
					<TDSValidated><xsl:text>null</xsl:text></TDSValidated>
					<isCancelled><xsl:text>null</xsl:text></isCancelled>
					<isScheduleChange><xsl:text>null</xsl:text></isScheduleChange>
					<startDateTime>
					<xsl:value-of select="substring-before(DEPDATE,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(DEPDATE,'T')"/>
					 
					</startDateTime>
					<endDateTime>
					<xsl:value-of select="substring-before(ARRDATE,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(ARRDATE,'T')"/>
					 
					</endDateTime>
					<startCityName>
					  <xsl:value-of select="DEPCITY"/>
					</startCityName>
					<endCityName>
					  <xsl:value-of select="ARRCITY"/>
					</endCityName>
					<marketingAirlineCode>
				    <xsl:value-of select="CARRIER"/>
					</marketingAirlineCode>
					<marketingFlightNumber>
					 <xsl:value-of select="FLIGHTNUM"/>
					</marketingFlightNumber>
					<operatingAirline>
					<xsl:text>null</xsl:text>
					</operatingAirline>
					
					<operatingAirlineCode>
					<xsl:text>null</xsl:text>
					</operatingAirlineCode>
					<mealCode> <xsl:text>null</xsl:text></mealCode>
					<mealDescription><xsl:text>null</xsl:text></mealDescription>
					<bookingSiteConfNum><xsl:text>null</xsl:text></bookingSiteConfNum>
					<supplierConfNum>
					 <xsl:value-of select="NATVRECLOC"/>
					</supplierConfNum>
					<equipmentCode><xsl:text>null</xsl:text></equipmentCode>
					<departureAirportCode><xsl:text>null</xsl:text></departureAirportCode>
					<arrivalAirportCode><xsl:text>null</xsl:text></arrivalAirportCode>
					<classOfService>
					 <xsl:value-of select="CLASS"/>
					</classOfService>
					<classOfServiceDescription>
					 <xsl:value-of select="CLASSTYPE"/>
					</classOfServiceDescription>
					 <status>
                     <xsl:value-of select="STATUSCODE"/>
                       </status>
					   <isChangeOfGuage> 
					  <xsl:value-of select="COG"/>
					   </isChangeOfGuage>
					<calculatedDuration><xsl:text>null</xsl:text></calculatedDuration>
					<numberOfStops><xsl:text>null</xsl:text></numberOfStops>
					<StopsCityList> 
					<xsl:value-of select="COGCITY"/>
					</StopsCityList>
					<hasSpecialMeal><xsl:text>null</xsl:text></hasSpecialMeal>
					<MarriedToSegments><xsl:text>null</xsl:text></MarriedToSegments>
					<ConnectedSegments><xsl:text>null</xsl:text> </ConnectedSegments>
					<isCodeShare> 
					<xsl:value-of select="CODESHARE"/>
					</isCodeShare>
					<codeShareComment1> 
					<xsl:value-of select="CSOPERATOR"/>
					</codeShareComment1>
					<isFlown><xsl:text>null</xsl:text></isFlown>
					<isOpen><xsl:text>null</xsl:text></isOpen>
					<isPassive><xsl:text>null</xsl:text> </isPassive>
					<isTicketless><xsl:text>null</xsl:text></isTicketless>
					<InFlightServiceCodes><xsl:text>null</xsl:text> </InFlightServiceCodes>
					<isETicketCandidate><xsl:text>null</xsl:text></isETicketCandidate>
					<arrivalTerminal><xsl:text>null</xsl:text></arrivalTerminal>
					<flightDistance><xsl:text>null</xsl:text></flightDistance>
					<isTicketed><xsl:text>null</xsl:text></isTicketed>
					<FareBasisCode><xsl:text>null</xsl:text></FareBasisCode>
				 </xsl:when>
				 <xsl:otherwise>
				   <xsl:if test="not(STATUSCODE ='KK') and not(PNRs/PNR/AGENCY_ID = 1082)"> 
					  <segType>
					  <xsl:text>Air</xsl:text>
					  </segType>

					<segmentNumber>
					<xsl:text>Air</xsl:text>
					</segmentNumber>
					<TDSValidated>
					<xsl:text>Air</xsl:text>
					</TDSValidated>
					<isCancelled>
					<xsl:text>Air</xsl:text>
					</isCancelled>
					<isScheduleChange>
					<xsl:text>Air</xsl:text>
					</isScheduleChange>
					<startDateTime>
					<xsl:text>Air</xsl:text>
					</startDateTime>
					<endDateTime>
					<xsl:text>Air</xsl:text>
					</endDateTime>
					<startCityName>
					<xsl:text>Air</xsl:text>
					</startCityName>
					<endCityName>
					<xsl:text>Air</xsl:text>
					</endCityName>
					<marketingAirlineCode>
					<xsl:text>Air</xsl:text>
					</marketingAirlineCode>
					<marketingFlightNumber>
					<xsl:text>Air</xsl:text>
					</marketingFlightNumber>
					<operatingAirline>
					<xsl:text>Air</xsl:text>
					</operatingAirline>
					<operatingAirlineCode>
					<xsl:text>Air</xsl:text>
					</operatingAirlineCode>
					<mealCode>
					<xsl:text>Air</xsl:text>
					</mealCode>
					<mealDescription>
					<xsl:text>Air</xsl:text>
					</mealDescription>
					<bookingSiteConfNum>
					<xsl:text>Air</xsl:text>
					</bookingSiteConfNum>
					<supplierConfNum>
					<xsl:text>Air</xsl:text>
					</supplierConfNum>
					<equipmentCode>
					<xsl:text>Air</xsl:text>
					</equipmentCode>
					<departureAirportCode>
					<xsl:text>Air</xsl:text>
					</departureAirportCode>
					<arrivalAirportCode>
					<xsl:text>Air</xsl:text>
					</arrivalAirportCode>
					<classOfService>
					<xsl:text>Air</xsl:text>
					</classOfService>
					<classOfServiceDescription>
					<xsl:text>Air</xsl:text>
					</classOfServiceDescription>
					 <status>
                     <xsl:text>Air</xsl:text>
                       </status>
					   <isChangeOfGuage> <xsl:text>Air</xsl:text></isChangeOfGuage>
					<calculatedDuration>   <xsl:text>Air</xsl:text></calculatedDuration>
					<numberOfStops>   <xsl:text>Air</xsl:text></numberOfStops>
					<StopsCityList>   <xsl:text>Air</xsl:text></StopsCityList>
					<hasSpecialMeal>   <xsl:text>Air</xsl:text></hasSpecialMeal>
					<MarriedToSegments>   <xsl:text>Air</xsl:text></MarriedToSegments>
					<ConnectedSegments>   <xsl:text>Air</xsl:text></ConnectedSegments>
					<isCodeShare><xsl:text>Air</xsl:text></isCodeShare>
					<codeShareComment1><xsl:text>Air</xsl:text></codeShareComment1>
					<isFlown><xsl:text>Air</xsl:text></isFlown>
					<isOpen><xsl:text>Air</xsl:text></isOpen>
					<isPassive><xsl:text>Air</xsl:text></isPassive>
					<isTicketless><xsl:text>Air</xsl:text></isTicketless>
					<InFlightServiceCodes><xsl:text>Air</xsl:text></InFlightServiceCodes>
					<isETicketCandidate><xsl:text>Air</xsl:text></isETicketCandidate>
					<arrivalTerminal><xsl:text>Air</xsl:text></arrivalTerminal>

					<flightDistance><xsl:text>Air</xsl:text></flightDistance>
					<isTicketed><xsl:text>Air</xsl:text></isTicketed>
					<FareBasisCode><xsl:text>Air</xsl:text></FareBasisCode>


                </xsl:if>

                     </xsl:otherwise>
					</xsl:choose>
                  </Segment>
                </xsl:for-each>
				 <!-- RailSegments -->
                   <xsl:for-each select="PNRs/PNRAirs/PNRAir">
				   <xsl:if test="TYPECODE='R'">
                          <Segment>
						  <segType>
						  <xsl:text>Rail</xsl:text>
						  </segType>
                             <segmentNumber>
							 <xsl:value-of select="SEGNUM"/>
							 </segmentNumber>
					<TDSValidated><xsl:text>null</xsl:text></TDSValidated>
					<isCancelled><xsl:text>null</xsl:text></isCancelled>
					<isScheduleChange><xsl:text>null</xsl:text></isScheduleChange>
					<startDateTime>
					<xsl:value-of select="substring-before(DEPDATE,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(DEPDATE,'T')"/>
					</startDateTime>
					<endDateTime>
						<xsl:value-of select="substring-before(ARRDATE,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(ARRDATE,'T')"/>
					</endDateTime>
					<StartStationAddress>
					<addr1><xsl:text>null</xsl:text></addr1>
					<longFreeText>
					<xsl:text>null</xsl:text>
					</longFreeText>
					</StartStationAddress>
					<xsl:text>null</xsl:text>
					<EndStationAddress>
					<addr1><xsl:text>null</xsl:text></addr1>
					<longFreeText><xsl:text>null</xsl:text></longFreeText>
					</EndStationAddress>
					<carrierName>
					<xsl:value-of select="CARRIER"/>
					</carrierName>
					<confirmationNum>
					<xsl:value-of select="NATVRECLOC"/>
					</confirmationNum>
					<bookingSiteConfNum><xsl:text>null</xsl:text></bookingSiteConfNum>
					<bookingSiteURL><xsl:text>null</xsl:text></bookingSiteURL>
					<departureStationCode>
					<xsl:value-of select="DEPCITY"/>
					</departureStationCode>
					<arrivalStationCode>
					<xsl:value-of select="ARRCITY"/>
					</arrivalStationCode>
					<trainNumber>
					<xsl:value-of select="FLIGHTNUM"/>
					</trainNumber>
					<operator><xsl:text>null</xsl:text></operator>
					<classOfService>
					<xsl:value-of select="CLASS"/>
					</classOfService>
					<classOfServiceDescription>
					<xsl:value-of select="CLASSTYPE"/>
					</classOfServiceDescription>
					<status>
					<xsl:value-of select="STATUSCODE"/>
					</status>
					<longFreeText><xsl:text>null</xsl:text></longFreeText>
					<specialInstructions><xsl:text>null</xsl:text></specialInstructions>
					<numberInParty><xsl:text>null</xsl:text></numberInParty>
					<numberOfStops><xsl:text>null</xsl:text></numberOfStops>
					<isPassive><xsl:text>null</xsl:text></isPassive>
					<RateQuote><xsl:text>null</xsl:text></RateQuote>
					<calculatedDuration><xsl:text>null</xsl:text></calculatedDuration>
					<Typecode>
					<xsl:value-of select="TYPECODE"/>
					</Typecode>
				</Segment>
					</xsl:if>
					</xsl:for-each>
									
                       <!-- HotelSegments -->
                <xsl:for-each select="PNRs/PNRHotels/PNRHotel">
                          <Segment>
						  <segType>
						  <xsl:text>Hotel</xsl:text>
						  </segType>
                            <segmentNumber>
							<xsl:value-of select="SEGNUM"/>
														</segmentNumber>
							<TDSValidated><xsl:text>null</xsl:text></TDSValidated>
							<isCancelled><xsl:text>null</xsl:text></isCancelled>
							<isScheduleChange><xsl:text>null</xsl:text></isScheduleChange>
							<segCRSCode>
							<xsl:value-of select="CRS_CODE"/>
							</segCRSCode>
							<segRecordLocator>
							<xsl:value-of select="PWR_DATA_REPL_HTLS/LOCATOR"/>
							</segRecordLocator>
							<startDateTime>
							<xsl:value-of select="substring-before(INDATE,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(INDATE,'T')"/>
							</startDateTime>
							<endDateTime>
								<xsl:value-of select="substring-before(OUTDATE,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(OUTDATE,'T')"/>
							</endDateTime>
							<cityCode>
							<xsl:value-of select="CITYCODE"/>
							</cityCode>
							<longFreeText><xsl:text>null</xsl:text></longFreeText>
							<Address>
							<addr1>
							<xsl:value-of select="PWR_DATA_REPL_HTLS/PROPERTY_ADDRESS"/>,<xsl:value-of select="PWR_DATA_REPL_HTLS/PROPERTY_ADDRESS_2"/>,<xsl:value-of select="PWR_DATA_REPL_HTLS/PROPERTY_ADDRESS_3"/>,<xsl:value-of select="PWR_DATA_REPL_HTLS/PROPERTY_ADDRESS_4"/>
							</addr1>
							<city>
							<xsl:value-of select="PWR_DATA_REPL_HTLS/PROPERTY_ADDRESS"/>,<xsl:value-of select="PWR_DATA_REPL_HTLS/PROPERTY_ADDRESS_2"/>,<xsl:value-of select="PWR_DATA_REPL_HTLS/PROPERTY_ADDRESS_3"/>,<xsl:value-of select="PWR_DATA_REPL_HTLS/PROPERTY_ADDRESS_4"/>
							</city>
							<state>
							<xsl:value-of select="PWR_DATA_REPL_HTLS/PROPERTY_ADDRESS"/>,<xsl:value-of select="PWR_DATA_REPL_HTLS/PROPERTY_ADDRESS_2"/>,<xsl:value-of select="PWR_DATA_REPL_HTLS/PROPERTY_ADDRESS_3"/>,<xsl:value-of select="PWR_DATA_REPL_HTLS/PROPERTY_ADDRESS_4"/>
							</state>
							<zip>
							<xsl:value-of select="PWR_DATA_REPL_HTLS/PROPERTY_ADDRESS"/>,<xsl:value-of select="PWR_DATA_REPL_HTLS/PROPERTY_ADDRESS_2"/>,<xsl:value-of select="PWR_DATA_REPL_HTLS/PROPERTY_ADDRESS_3"/>,<xsl:value-of select="PWR_DATA_REPL_HTLS/PROPERTY_ADDRESS_4"/>
							</zip>
							<country>
							<xsl:value-of select="PWR_DATA_REPL_HTLS/PROPERTY_ADDRESS"/>,<xsl:value-of select="PWR_DATA_REPL_HTLS/PROPERTY_ADDRESS_2"/>,<xsl:value-of select="PWR_DATA_REPL_HTLS/PROPERTY_ADDRESS_3"/>,<xsl:value-of select="PWR_DATA_REPL_HTLS/PROPERTY_ADDRESS_4"/>
							</country>

							</Address>
							<numberGuests>
							<xsl:value-of select="GUESTS"/>
							</numberGuests>
							<bookingSiteConfNum><xsl:text>null</xsl:text></bookingSiteConfNum>
							<bookingSiteURL><xsl:text>null</xsl:text></bookingSiteURL>
							<supplierConfNum>
							<xsl:value-of select="CONFNUM"/>
							</supplierConfNum>
							<supplierName><xsl:text>null</xsl:text></supplierName>
							<supplierPhone><xsl:text>null</xsl:text></supplierPhone>
							<hotelChainCode>
							<xsl:value-of select="CHAINCODE"/>
							</hotelChainCode>
							<numberOfRooms>
							<xsl:value-of select="ROOMS"/>
							</numberOfRooms>
							<roomType><xsl:text>null</xsl:text></roomType>
							<propertyCode>
							<xsl:value-of select="PROPCODE"/>
							</propertyCode>
							<specialInstructions><xsl:text>null</xsl:text></specialInstructions>
							<creditCardGuarantee><xsl:text>null</xsl:text></creditCardGuarantee>
							<numberOfNights>
							<xsl:value-of select="NIGHTS"/>
							</numberOfNights>
							<status>
							<xsl:value-of select="STATUSCODE"/>
							</status>
							<longFreeText><xsl:text>null</xsl:text></longFreeText>
							<rateCode><xsl:text>null</xsl:text></rateCode>
							<Rate>
							<amount>
							<xsl:value-of select="DAILYRATE"/>
							</amount>
							<currency>
							<xsl:value-of select="CURRTYPE"/>
							</currency>
							</Rate>
							<RateChangeInfos><xsl:text>null</xsl:text></RateChangeInfos>
							<corporateDiscountNumber><xsl:text>null</xsl:text></corporateDiscountNumber>
							<isPassive><xsl:text>null</xsl:text></isPassive>
							<RateCategory><xsl:text>null</xsl:text></RateCategory>
							<ExtraPersonRate><xsl:text>null</xsl:text></ExtraPersonRate>
							<isMultiDateRate><xsl:text>null</xsl:text></isMultiDateRate>
							<rateChangeDuringStay><xsl:text>null</xsl:text></rateChangeDuringStay>
							<hotelOverview><xsl:text>null</xsl:text></hotelOverview>
							<RateTotal>
							<amount>
							<xsl:text>null</xsl:text>
							</amount>
							<currency>
							<xsl:text>null</xsl:text>
							</currency>
							</RateTotal>
							</Segment>
                      
							</xsl:for-each>
							
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
			
            <!-- CarSegments -->
                <xsl:for-each select="PNRs/PNRCars/PNRCar">
                           <Segment>
						   <segType>
						   <xsl:text>Car</xsl:text>
						   </segType>
                                    <segmentNumber>
									<xsl:value-of select="SEGNUM"/>
									</segmentNumber>
								<TDSValidated><xsl:text>null</xsl:text></TDSValidated>
								<isCancelled><xsl:text>null</xsl:text></isCancelled>
								<isScheduleChange><xsl:text>null</xsl:text></isScheduleChange>
								<startDateTime>
								<xsl:value-of select="substring-before(PICKUPDATE,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(PICKUPDATE,'T')"/>
								</startDateTime>
								<endDateTime>
								<xsl:value-of select="substring-before(DROPOFFDATE,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(DROPOFFDATE,'T')"/>
								</endDateTime>
								<StartLocationAddress>
								<addr1><xsl:text>null</xsl:text></addr1>
								<addr2><xsl:text>null</xsl:text></addr2>
								</StartLocationAddress>
								<StartLocationAddress>
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
								<carType>
								<xsl:value-of select="CARTYPE"/>
								</carType>
								<carDescription> <!-- Cynthia
								will explain--></carDescription>
								<bookingSiteConfNum><xsl:text>null</xsl:text></bookingSiteConfNum>
								<bookingSiteURL><xsl:text>null</xsl:text></bookingSiteURL>
								<supplierConfNum><xsl:text>null</xsl:text></supplierConfNum>
								<supplierName><xsl:text>null</xsl:text></supplierName>
								<locationCode><xsl:text>null</xsl:text></locationCode>
								<pickupLocation>
								<xsl:value-of select="PICKUPCITY"/>
								</pickupLocation>
								<dropOffLocation></dropOffLocation>
								<rentalCompany>
								<xsl:value-of select="COMPCODE"/>
								</rentalCompany>
								<compCode>
								<xsl:value-of select="COMPCODE"/>
								</compCode>
								<numberOfCars>
								<xsl:value-of select="NUMCARS"/>
								</numberOfCars>
								<ExtraDayFee></ExtraDayFee>
								<ExtraHourFee>
								<amount><xsl:text>null</xsl:text></amount>
								<currency><xsl:text>null</xsl:text></currency>
								</ExtraHourFee>
								<currency>
								<xsl:value-of select="CURRTYPE"/>
								</currency>
								<status>
								<xsl:value-of select="STATUSCODE"/>
								</status>
								<corporateDiscountNumber><xsl:text>null</xsl:text></corporateDiscountNumber>
								<GuaranteedRate><xsl:text>null</xsl:text></GuaranteedRate>
								<BaseRate>
								<amount>
								<xsl:value-of select="DAILYRATE"/>
								</amount>
								<currency><xsl:text>null</xsl:text></currency>
								</BaseRate>
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
								<passengerNumber>
								<xsl:value-of select="NUMPAX"/>
								</passengerNumber>
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
								<numberOfDays>
								<xsl:value-of select="NUMDAYS"/>
								</numberOfDays>
								<numberOfHours><xsl:text>null</xsl:text></numberOfHours>

									
									
									</Segment>
                     
                </xsl:for-each>
            
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
				<zip><xsl:text>null</xsl:text></zip>
				<country><xsl:text>null</xsl:text></country>
				<longFreeText><xsl:text>null</xsl:text></longFreeText>

            </BillingAddressInfo>
			 <DeliveryAddressInfo>
              <recipient/>
				<addr1><xsl:text>null</xsl:text></addr1>
				<addr2><xsl:text>null</xsl:text></addr2>
				<addr3><xsl:text>null</xsl:text></addr3>
				<city><xsl:text>null</xsl:text></city>
				<state><xsl:text>null</xsl:text></state>
				<zip><xsl:text>null</xsl:text></zip>
				<country><xsl:text>null</xsl:text></country>
				<longFreeText><xsl:text>null</xsl:text></longFreeText>

            </DeliveryAddressInfo>
            <Remarks>
                <xsl:for-each select="PNRs/PNRRemarks/PNRRemark">
                    <Remark>
					<LINETYPE><xsl:text>null</xsl:text></LINETYPE>
					<ABBREVIATION><xsl:text>null</xsl:text></ABBREVIATION>
					<lineNumber><xsl:text>null</xsl:text></lineNumber>
					<type><xsl:text>null</xsl:text></type>
					<contents>
					<xsl:value-of select="PNRLINE"/>
					</contents>
					<category><xsl:text>null</xsl:text></category>
					<label>
					<xsl:value-of select="LABEL"/>
					</label>
					<PassengerNumbers><xsl:text>null</xsl:text></PassengerNumbers>
					<SegmentNumbers><xsl:text>null</xsl:text></SegmentNumbers>
					</Remark>
                </xsl:for-each>
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
                    <FormOfPayment>
					   <xsl:value-of select="PNRs/PNRPNRs/PNRPNR/FOP"/>
					</FormOfPayment>
                    <creditCardNumber>
					<xsl:value-of select="PNRs/PNRPNRs/PNRPNR/CCNUM"/>
					</creditCardNumber>
                    <creditCardExpiration/>
                    <creditCardCompany><xsl:value-of select="PNRs/PNRPNRs/PNRPNR/CCTYPE"/></creditCardCompany>
                </FormOfPayment>
            </FormsOfPayment>
			 <Contacts>
                <xsl:for-each select="PNRs/PNRContacts/PNRContact">
                    <Contact>
					<NAMENUM>
					 <xsl:value-of select="NAMENUM"/>
					</NAMENUM>
					<ITEMCODE>
					 <xsl:value-of select="ITEMCODE"/>
					</ITEMCODE>
					<ITEMTEXT>
					 <xsl:value-of select="ITEMTEXT"/>
					</ITEMTEXT>
					<ITEMVALUE>
					 <xsl:value-of select="ITEMVALUE"/>
					</ITEMVALUE>
					</Contact>
					<PWR_DATA_REPL_TKTs>
					 <xsl:value-of select="PWR_DATA_REPL_TKTs"/>
					</PWR_DATA_REPL_TKTs>
					<PWR_DATA_REPL_HTLs>
					 <xsl:value-of select="PWR_DATA_REPL_HTLs"/>
					</PWR_DATA_REPL_HTLs>
                </xsl:for-each>
            </Contacts>
			
           
            

            <StoredFares>
                <StoredFare>
                    <BaseFare>
                        <amount>
						<xsl:text>null</xsl:text>
                        </amount>
                        <currency>
						<xsl:text>null</xsl:text>
                        </currency>
                    </BaseFare>
                    <TotalFare>
                        <amount>
                            <xsl:value-of select="PNRs/PNRPNRs/PNRPNR/TOTALFARE"/>
                        </amount>
                        <currency>
						<xsl:value-of select="PNRs/PNRPNRs/PNRPNR/CURRENCYTYPE"/>
                        </currency>
                    </TotalFare>
                   <EquivalentFare>
				     <amount>
					 <xsl:text>null</xsl:text>
                        </amount>
                        <currency>
						<xsl:text>null</xsl:text>
                        </currency>
                   </EquivalentFare>
				<validatingCarrier><xsl:text>null</xsl:text></validatingCarrier>
				<privateFareAccountCode><xsl:text>null</xsl:text></privateFareAccountCode>
				<inputMessage><xsl:text>null</xsl:text></inputMessage>
				<infoMessage><xsl:text>null</xsl:text></infoMessage>
				<endorsements><xsl:text>null</xsl:text></endorsements>
				<fareCalculation><xsl:text>null</xsl:text></fareCalculation>
				<commissionPercentage><xsl:text>null</xsl:text></commissionPercentage>
				<commissionAmount>
					<xsl:value-of select="PNRs/PNRPNRs/PNRPNR/COMMAMOUNT"/>
				</commissionAmount>
				<quoteDate><xsl:text>null</xsl:text></quoteDate>
				<lastDateToTicket><xsl:text>null</xsl:text></lastDateToTicket>
				<CreditCard><xsl:text>null</xsl:text></CreditCard>
				<CompareFare>
				<xsl:value-of select="PNRs/PNRPNRs/PNRPNR/COMPAREFARE"/>
				</CompareFare>
				<FareCode>
				<xsl:value-of select="PNRs/PNRPNRs/PNRPNR/FARECODE"/>
				</FareCode>
					<StoredFareSegments>
					<StoredFareSegment>
					<carrierCode><xsl:text>null</xsl:text></carrierCode>
					<departureAirportCode><xsl:text>null</xsl:text></departureAirportCode>
					<departureDate><xsl:text>null</xsl:text></departureDate>
					<segmentNumber><xsl:text>null</xsl:text></segmentNumber>
					<xsl:for-each select="PNRs/PNRAirs/PNRAir">
					<fare>
					<xsl:value-of select="FARE"/>
					</fare>
					</xsl:for-each>
					<classOfService><xsl:text>null</xsl:text></classOfService>
					<statusCode><xsl:text>null</xsl:text></statusCode>
					<xsl:for-each select="PNRs/PNRAirs/PNRAir">
					<fareBasis>
					<xsl:value-of select="FAREBASIS"/>
					</fareBasis>
					</xsl:for-each>
					<ticketDesignator><xsl:text>null</xsl:text></ticketDesignator>
					<notValidBefore><xsl:text>null</xsl:text></notValidBefore>
					<notValidAfter><xsl:text>null</xsl:text></notValidAfter>
					</StoredFareSegment>
					</StoredFareSegments>

					<StoredFarePassengers>
					<StoredFarePassenger>
					<fareConstruction/>
					<BaseFare>
					<amount><xsl:text>null</xsl:text></amount>
					<currency><xsl:text>null</xsl:text></currency>
					</BaseFare>
					<TotalFare>
					<amount><xsl:text>null</xsl:text></amount>
					<currency><xsl:text>null</xsl:text></currency>
					</TotalFare>
					<lastDateToTicket>
					<xsl:value-of select="substring-before(PNRs/PNR/TICKETDATE,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(PNRs/PNR/TICKETDATE,'T')"/>
					</lastDateToTicket>
					<TicketStatus>
					<xsl:value-of select="PNRs/PNR/TICKETED"/>
					</TicketStatus>
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
				<invoiceNumber><xsl:text>null</xsl:text></invoiceNumber>
				<ticketNum><xsl:text>null</xsl:text></ticketNum>
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
                        <type><xsl:text>null</xsl:text> </type>
                        <longFreeText><xsl:text>null</xsl:text> </longFreeText>
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