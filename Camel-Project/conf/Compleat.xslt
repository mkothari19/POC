<?xml version="1.0" encoding="utf-8"?>

<xsl:stylesheet version="1.0"  xmlns:compleat="http://gdsx.com/PnrDataPush.xsd"  xmlns:xsl="http://www.w3.org/1999/XSL/Transform" exclude-result-prefixes="compleat">

    <xsl:template match="/">
        <pnr>
            <PNRid>
                <xsl:value-of select="//compleat:pnr/compleat:PNRid"/>
            </PNRid>
            <PNR_ID>
			<xsl:text>null</xsl:text>
            </PNR_ID>  
			<BDUPNRID><xsl:text>null</xsl:text></BDUPNRID>
			 <recordLocator>
                <xsl:value-of select="//compleat:pnr/compleat:recordLocator"/>
            </recordLocator>
            <GDS>
                <xsl:value-of select="//compleat:pnr/compleat:GDS"/>
            </GDS>
            <platformID>
                <xsl:value-of select="//compleat:pnr/compleat:platformID"/>
            </platformID>
            <securityManagerID> 
			<xsl:value-of select="//compleat:pnr/compleat:securityManagerID"/>
			</securityManagerID>
            <globalCustomerNumber>
				<xsl:value-of select="//compleat:pnr/compleat:globalCustomerNumber"/>
			</globalCustomerNumber>
			<AgencyIataNumber>
			<xsl:text>null</xsl:text>
			</AgencyIataNumber>
            <customerNumber>
                <xsl:value-of select="//compleat:pnr/compleat:customerNumber"/>
            </customerNumber>
            <creationOfficeID>
			   <xsl:value-of select="//compleat:pnr/compleat:creationOfficeID"/>
			</creationOfficeID>
            <agentSignature>
			<xsl:value-of select="//compleat:pnr/compleat:agentSignature"/>
			</agentSignature>
            <agentPCC>
			<xsl:value-of select="//compleat:pnr/compleat:agentPCC"/>
			</agentPCC>
			<EmulatedPCC>	
			<xsl:text>null</xsl:text>
			</EmulatedPCC>
            <BookingDateTime>
                <xsl:value-of select="compleat:pnr/compleat:BookingDateTime"/>
            </BookingDateTime>
			<TicketingDateTime>
			<xsl:text>null</xsl:text>
			</TicketingDateTime>
			<CancelDate>
			<xsl:text>null</xsl:text>
			</CancelDate>
			<etrHits>
			 <xsl:value-of select="compleat:pnr/compleat:etrHits"/>
			</etrHits>
			<instance>
			<xsl:text>COMPLEAT</xsl:text>
			</instance>
			<dataType>
			<xsl:text>GDS</xsl:text>
			</dataType>
             <intlFlight>
			 <xsl:text>null</xsl:text>
			 </intlFlight>
			 <Last_Update_DT></Last_Update_DT>
			 
			 <airPortSummary>
			 
              <xsl:for-each select="//compleat:Segments/compleat:Segment">
                    <xsl:choose>
                        <xsl:when test="@xsi:type = 'AirSegment'" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
						<xsl:value-of select="compleat:departureAirportCode"/>	<xsl:text>-</xsl:text> <xsl:value-of select="compleat:arrivalAirportCode"/>	<xsl:text>/</xsl:text> 
						 </xsl:when>
                    </xsl:choose>
                </xsl:for-each>
			 </airPortSummary>

			 <carrierSummary>
			 <xsl:for-each select="//compleat:Segments/compleat:Segment">
                    <xsl:choose>
                        <xsl:when test="@xsi:type = 'AirSegment'" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
						<xsl:choose>
				<xsl:when test="compleat:carrier"> 
						<xsl:value-of select="compleat:carrier"/>	<xsl:text>-</xsl:text> 
						 </xsl:when>
                    </xsl:choose>
						 </xsl:when>
                    </xsl:choose>
                </xsl:for-each>
			 </carrierSummary>

            <Travelers>
                <Traveler>
                    <firstName>
                        <xsl:value-of select="//compleat:Traveler/compleat:firstName"/>
                    </firstName>
                    <middleName>
                        <xsl:value-of select="//compleat:Traveler/compleat:middleName"/>
                    </middleName>
                    <lastName>
                        <xsl:value-of select="//compleat:Traveler/compleat:lastName"/>
                    </lastName>
					
					<Contacts>
				   <Contact>
				   <PhoneNumbers>
				    <xsl:for-each select="//compleat:PhoneNumbers/compleat:PhoneNumber">
				   <BDUPhoneNumber>
                        <number>
                            <xsl:value-of select="compleat:number"/>
                        </number>
                        <type>
                            <xsl:value-of select="compleat:type"/>
                        </type>
                        <longFreeText>
                            <xsl:value-of select="compleat:longFreeText"/>
                        </longFreeText>
                        <cityCode>
                            <xsl:value-of select="compleat:cityCode"/>
                        </cityCode>
							<itemCode>
							<xsl:if test="compleat:type='Home Phone Number' or compleat:type='Home'">
					<xsl:text>17</xsl:text>
						</xsl:if>
					<xsl:if test="compleat:type='Mobile Phone Number' or compleat:type='Cellular'">
					<xsl:text>18</xsl:text>
						</xsl:if>	
					<xsl:if test="compleat:type='Work Phone Number' or compleat:type='Business'">
					<xsl:text>19</xsl:text>
						</xsl:if>		
					<xsl:if test="compleat:type='Secondary Mobile Phone Number'">
					<xsl:text>20</xsl:text>
						</xsl:if>
					<xsl:if test="compleat:type='Other Number' or compleat:type='Agent'">
					<xsl:text>21</xsl:text>
						</xsl:if>	
					
						</itemCode>
                    </BDUPhoneNumber>
					
					 </xsl:for-each>
					</PhoneNumbers>
					
					<EmailAddresses>
				 <xsl:for-each select="//compleat:Remarks/compleat:Remark">
				 	<xsl:if test="compleat:type='3' or compleat:type='10' or compleat:type='11'">
                    <EmailAddress>
                       
                        <type>
				     
						<xsl:if test="contains(compleat:contents, 'TO')">
						<xsl:text>Primary</xsl:text> 
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'CC')">
						<xsl:text>Secondary</xsl:text>     
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-T')">
						<xsl:text>Travel Agent</xsl:text>    
                            
							</xsl:if>
							
							<xsl:if test="contains(compleat:contents, 'EMAIL-A')">
						<xsl:text>Travel Agent</xsl:text>    
                           
							</xsl:if>
							
							<xsl:if test="contains(compleat:contents, 'EMAIL-BK')">
						 <xsl:text>Booker</xsl:text>    
                             </xsl:if>
							
							<xsl:if test="contains(compleat:contents, 'EMAIL-M')">
						 <xsl:text>Manager</xsl:text>    
                           
							</xsl:if>
							
							<xsl:if test="contains(compleat:contents, 'EMAIL-FD')">
						<xsl:text>Finance Dept</xsl:text>    
                             
                            
							</xsl:if>
							
							<xsl:if test="contains(compleat:contents, 'EMAIL-TM')">
						<xsl:text>Travel Manager </xsl:text>    
                               
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-GM')">
						<xsl:text>Global Travel Manager</xsl:text>    
                               
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-CS')">
						 <xsl:text>Corporate Security</xsl:text>    
                               
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-HP')">
						<xsl:text>Hotel Property</xsl:text>    
                               
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-A1')">
						 <xsl:text>Approver1</xsl:text>    
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-A2')">
						 <xsl:text>Approver2</xsl:text>    
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-MG')">
						 <xsl:text>Missing In XREF Document</xsl:text>    
                               
							</xsl:if>
							
                         
                        </type>
						
						
                        <email>
						<xsl:if test="contains(compleat:contents, 'TO')">
						 <xsl:value-of select="substring-after(compleat:contents,'TO')"/>  
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'CC')">
						 <xsl:value-of select="substring-after(compleat:contents,'CC')"/>  
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, '*EMAIL-T:')">
						 <xsl:value-of select="substring-after(compleat:contents,'*EMAIL-T:')"/>  
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-T-')">
						 <xsl:value-of select="substring-after(compleat:contents,'EMAIL-T-')"/>  
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, '*EMAIL-T-')">
						 <xsl:value-of select="substring-after(compleat:contents,'*EMAIL-T-')"/>  
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-A:')">
						 <xsl:value-of select="substring-after(compleat:contents,'EMAIL-A:')"/>  
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, '%EMAIL-A-%')">
						 <xsl:value-of select="substring-after(compleat:contents,'EMAIL-A-')"/>  
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, '%EMAIL-BK:%')">
						 <xsl:value-of select="substring-after(compleat:contents,'EMAIL-BK:')"/>  
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, '%EMAIL-BK-%')">
						 <xsl:value-of select="substring-after(compleat:contents,'EMAIL-BK-')"/>  
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-M:')">
						 <xsl:value-of select="substring-after(compleat:contents,'EMAIL-M:')"/>  
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-M-')">
						 <xsl:value-of select="substring-after(compleat:contents,'EMAIL-M-')"/>  
                            
							</xsl:if>
							
							<xsl:if test="contains(compleat:contents, 'EMAIL-FD:')">
						 <xsl:value-of select="substring-after(compleat:contents,'EMAIL-FD:')"/>  
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-FD-')">
						 <xsl:value-of select="substring-after(compleat:contents,'EMAIL-FD-')"/>  
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-TM:')">
						 <xsl:value-of select="substring-after(compleat:contents,'EMAIL-TM:')"/>  
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-TM-')">
						 <xsl:value-of select="substring-after(compleat:contents,'EMAIL-TM-')"/>  
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-GM:')">
						 <xsl:value-of select="substring-after(compleat:contents,'EMAIL-GM:')"/>  
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-GM-')">
						 <xsl:value-of select="substring-after(compleat:contents,'EMAIL-GM-')"/>  
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-CS:')">
						 <xsl:value-of select="substring-after(compleat:contents,'EMAIL-CS:')"/>  
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-CS-')">
						 <xsl:value-of select="substring-after(compleat:contents,'EMAIL-CS-')"/>  
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-HP:')">
						 <xsl:value-of select="substring-after(compleat:contents,'EMAIL-HP:')"/>  
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-HP-')">
						 <xsl:value-of select="substring-after(compleat:contents,'EMAIL-HP-')"/>  
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-A1:')">
						 <xsl:value-of select="substring-after(compleat:contents,'EMAIL-A1:')"/>  
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-A1-')">
						 <xsl:value-of select="substring-after(compleat:contents,'EMAIL-A1-')"/>  
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-A2:')">
						 <xsl:value-of select="substring-after(compleat:contents,'EMAIL-A2:')"/>  
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-A2-')">
						 <xsl:value-of select="substring-after(compleat:contents,'EMAIL-A2-')"/>  
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-MG')">
						 <xsl:value-of select="substring-after(compleat:contents,'EMAIL-MG')"/>  
                            
							</xsl:if>
							
                        </email>
						
						<itemCode>
						  
						<xsl:if test="contains(compleat:contents, 'TO')">
						<xsl:text>22</xsl:text> 
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'CC')">
						<xsl:text>23</xsl:text>     
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-T')">
						<xsl:text>25</xsl:text>    
                            
							</xsl:if>
							
							<xsl:if test="contains(compleat:contents, 'EMAIL-A')">
						<xsl:text>29</xsl:text>    
                            
							</xsl:if>
							
							<xsl:if test="contains(compleat:contents, 'EMAIL-BK')">
						 <xsl:text>32</xsl:text>    
                             </xsl:if>
							
							<xsl:if test="contains(compleat:contents, 'EMAIL-M')">
						 <xsl:text>26</xsl:text>    
                            </xsl:if>
							
							<xsl:if test="contains(compleat:contents, 'EMAIL-FD')">
						<xsl:text>33</xsl:text>    
                             </xsl:if>
							
							<xsl:if test="contains(compleat:contents, 'EMAIL-TM')">
						<xsl:text>27</xsl:text>    
                               
							</xsl:if>
							
							<xsl:if test="contains(compleat:contents, 'EMAIL-GM')">
						<xsl:text>28</xsl:text>    
                               
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-CS')">
						 <xsl:text>34</xsl:text>    
                               
							</xsl:if>
							
							<xsl:if test="contains(compleat:contents, 'EMAIL-HP')">
						<xsl:text>35</xsl:text>    
                               
							</xsl:if>
							
							<xsl:if test="contains(compleat:contents, 'EMAIL-A1')">
						 <xsl:text>30</xsl:text>    
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-A2')">
						 <xsl:text>31</xsl:text>    
                            
							</xsl:if>
							<xsl:if test="contains(compleat:contents, 'EMAIL-MG')">
						 <xsl:text>0</xsl:text>    
                               
							</xsl:if>
							
                        
						</itemCode>
						
                    </EmailAddress>
					 </xsl:if>
                </xsl:for-each>
				</EmailAddresses>
			<EmergencyContacts>
				<EmergencyContact>
			<ContactName>
			<xsl:text>null</xsl:text>
			</ContactName>
			<type>
			<xsl:text>null</xsl:text>
			</type>
			<itemCode>
			<xsl:text>null</xsl:text>
			</itemCode>
			</EmergencyContact>
			</EmergencyContacts>
					
                   
					</Contact>
				  </Contacts>

			<FrequentFlyerInfo>
                         <xsl:for-each select="//compleat:Travelers/compleat:Traveler/compleat:FrequentFlyerInfo/compleat:FrequentFlyer">
                           <FrequentFlyer>
                                <frequentTravelerNum>
                                    <xsl:value-of select="compleat:frequentTravelerNum"/>
                                </frequentTravelerNum>
                                <frequentTravelerSupplier>
                                    <xsl:value-of select="compleat:frequentTravelerSupplier"/>
                                </frequentTravelerSupplier>
                            </FrequentFlyer>
							</xsl:for-each>
							</FrequentFlyerInfo>
							
                    <MealPreferenceInfo>
					<xsl:for-each select="//compleat:Travelers/compleat:Traveler/compleat:MealPreferenceInfo/compleat:MealPreference">
                            <MealPreference>
                                <mealPreferenceCode>
                                    <xsl:value-of select="compleat:mealPreferenceCode"/>
                                </mealPreferenceCode>
                                <mealPreferenceSegment>
                                    <xsl:value-of select="compleat:mealPreferenceSegment"/>
                                </mealPreferenceSegment>
                            </MealPreference>
                        </xsl:for-each>
                       </MealPreferenceInfo>
                    <SeatPreferenceInfo>
                        <xsl:for-each select="//compleat:Travelers/compleat:Traveler/compleat:SeatPreferenceInfo/compleat:SeatPref">
                            <SeatPref>
                                <preferenceLocationCode>
                                    <xsl:value-of select="compleat:preferenceLocationCode"/>
                                </preferenceLocationCode>
                                <preferenceLocationText>
                                    <xsl:value-of select="compleat:preferenceLocationText"/>
                                </preferenceLocationText>
                                <longFreeText>
                                    <xsl:value-of select="compleat:longFreeText"/>
                                </longFreeText>
                            </SeatPref>
                        </xsl:for-each>
                    </SeatPreferenceInfo>
					
                    <Seats>
					<xsl:for-each select="//compleat:Travelers/compleat:Traveler/compleat:Seats/compleat:Seat">
                            <Seat>
                                <segmentNumber>
                                    <xsl:value-of select="compleat:segmentNumber"/>
                                </segmentNumber>
                                <location>
                                    <xsl:value-of select="compleat:location"/>
                                </location>
                            </Seat>
                        </xsl:for-each>
                       
                        
                    </Seats>
                    <passengerNumber>
                        <xsl:value-of select="//compleat:Travelers/compleat:Traveler/compleat:passengerNumber"/>
                    </passengerNumber>
                    <nameInGds>
                        <xsl:value-of select="//compleat:Travelers/compleat:Traveler/compleat:nameInGds"/>
                    </nameInGds>
                    <NameNumber>
					 <prefix>
					   <xsl:value-of select="//compleat:Travelers/compleat:Traveler/compleat:prefix"/>
					 </prefix>
					 <suffix>
					 <xsl:value-of select="//compleat:Travelers/compleat:Traveler/compleat:suffix"/>
					 </suffix>
					</NameNumber>
				</Traveler>
								  
            </Travelers>
			  <BillingAddressInfo>
                <recipient>
                    <xsl:value-of select="//compleat:BillingAddressInfo/compleat:recipient"/>
                </recipient>
              
			   <addr1>
				 
                    <xsl:value-of select="//compleat:BillingAddressInfo/compleat:addr1"/>
                </addr1>
				
                <addr2> 
				<xsl:value-of select="//compleat:BillingAddressInfo/compleat:BillingAddressInfo/addr2"/>
				</addr2>
				<addr3>
				<xsl:value-of select="//compleat:BillingAddressInfo/compleat:addr3"/>
				</addr3>
                <city>
                    <xsl:value-of select="//compleat:BillingAddressInfo/compleat:city"/>
                </city>
                <state>
                    <xsl:value-of select="//compleat:BillingAddressInfo/compleat:state"/>
                </state>
                <zip>
                    <xsl:value-of select="//compleat:BillingAddressInfo/compleat:zip"/>
                </zip>
				<country>
				 <xsl:value-of select="//compleat:BillingAddressInfo/compleat:country"/>
				</country>
				<longFreeText>
				 <xsl:value-of select="//compleat:BillingAddressInfo/compleat:longFreeText"/>
				</longFreeText>
            </BillingAddressInfo>
			
            <DeliveryAddressInfo>
                <recipient>
                    <xsl:value-of select="//compleat:DeliveryAddressInfo/compleat:recipient"/>
                </recipient>
                 <addr1>
                    <xsl:value-of select="//compleat:DeliveryAddressInfo/compleat:addr1"/>
                </addr1>
                <addr2> 
				<xsl:value-of select="//compleat:DeliveryAddressInfo/compleat:addr2"/>
				</addr2>
				<addr3>
				<xsl:value-of select="//compleat:DeliveryAddressInfo/compleat:addr3"/>
				</addr3>
                  <city>
                    <xsl:value-of select="//compleat:DeliveryAddressInfo/compleat:city"/>
                </city>
                <state>
                    <xsl:value-of select="//compleat:DeliveryAddressInfo/compleat:state"/>
                </state>
                <zip>
                    <xsl:value-of select="//compleat:DeliveryAddressInfo/compleat:zip"/>
                </zip>
				<country>
				 <xsl:value-of select="//compleat:DeliveryAddressInfo/compleat:country"/>
				</country>
				<longFreeText>
				 <xsl:value-of select="//compleat:DeliveryAddressInfo/compleat:longFreeText"/>
				</longFreeText>
            </DeliveryAddressInfo>
			
			 <Remarks>
               <xsl:for-each select="//compleat:Remarks/compleat:Remark">
                    <Remark>
                        <lineNumber>
                         <xsl:text>null</xsl:text>
                        </lineNumber>
                        <type>
                          <xsl:text>null</xsl:text> 
                        </type>
                        <contents>
                            <xsl:value-of select="compleat:contents"/>
                        </contents>
						<category>
						 <xsl:value-of select="compleat:category"/>
						</category>
						<label>
						<xsl:text>null</xsl:text>
						</label>
                        <PassengerNumbers>
                             <xsl:value-of select="compleat:PassengerNumbers"/>
                        </PassengerNumbers>
                        <SegmentNumbers>
                             <xsl:value-of select="compleat:SegmentNumbers"/>
                        </SegmentNumbers>
                    </Remark>
                </xsl:for-each>
                
            </Remarks>
			<SSRs>
                <xsl:for-each select="//compleat:SSRs/compleat:SSR">
                    <SSR>
                        <lineNumber>
                            <xsl:value-of select="compleat:lineNum"/>
                        </lineNumber>
                        <type>
                            <xsl:value-of select="compleat:type"/>
                        </type>
                        <code>
                            <xsl:value-of select="compleat:code"/>
                        </code>
						<status>
						 <xsl:value-of select="compleat:status"/>
						</status>
                        <contents>
                            <xsl:value-of select="compleat:contents"/>
                        </contents>
                        <PassengerNumbers>
                            <intElement><xsl:value-of select="compleat:PassengerNumbers/compleat:intElement"/></intElement>
                        </PassengerNumbers>
                        <SegmentNumbers>
                            <intElement><xsl:value-of select="compleat:SegmentNumbers/compleat:intElement"/></intElement>
                        </SegmentNumbers>
                    </SSR>
                </xsl:for-each>
               </SSRs>
			    <FormsOfPayment>
             <xsl:for-each select="//compleat:FormsOfPayment/compleat:FormOfPayment">
                <FormOfPayment>
                    <FormOfPayment><xsl:value-of select="compleat:FormOfPayment"/></FormOfPayment>
                    <creditCardNumber><xsl:value-of select="compleat:creditCardNumber"/></creditCardNumber>
                    <creditCardExpiration><xsl:value-of select="compleat:creditCardExpiration"/></creditCardExpiration>
                    <creditCardCompany><xsl:value-of select="compleat:creditCardCompany"/></creditCardCompany>
                </FormOfPayment>
				</xsl:for-each>
				
            </FormsOfPayment>
			
			            <StoredFares>
                <StoredFare>
                <xsl:for-each select="//compleat:StoredFares/compleat:StoredFare/compleat:BaseFare">
                      <BaseFare>
                        <amount>
                            <xsl:value-of select="compleat:amount"/>
                        </amount>
                        <currency>
                            <xsl:value-of select="compleat:currency"/>
                        </currency>
                    </BaseFare>
					</xsl:for-each>
					
						  <xsl:for-each select="//compleat:StoredFares/compleat:StoredFare/compleat:TotalFare">
                    <TotalFare>
                         <amount>
                            <xsl:value-of select="//compleat:StoredFares/compleat:StoredFare/compleat:TotalFare/compleat:amount"/>
                        </amount>
                        <currency>
                            <xsl:value-of select="//compleat:StoredFares/compleat:StoredFare/compleat:TotalFare/compleat:currency"/>
                        </currency>
                    </TotalFare>
					</xsl:for-each>
					
					 	 <xsl:for-each select="//compleat:StoredFares/compleat:StoredFare/compleat:EquivalentFare">
                    <EquivalentFare>
                       <amount>
                            <xsl:value-of select="//compleat:StoredFares/compleat:StoredFare/compleat:EquivalentFare/compleat:amount"/>
                        </amount>
                        <currency>
                            <xsl:value-of select="//compleat:StoredFares/compleat:StoredFare/compleat:EquivalentFare/compleat:currency"/>
                        </currency>
                    </EquivalentFare>
					</xsl:for-each>
					
					
                    <validatingCarrier>
                        <xsl:value-of select="//compleat:StoredFares/compleat:StoredFare/compleat:validatingCarrier"/>
                    </validatingCarrier>
                    <privateFareAccountCode>
                        <xsl:value-of select="//compleat:StoredFares/compleat:StoredFare/compleat:privateFareAccountCode"/>
                    </privateFareAccountCode>
                    <inputMessage>
                        <xsl:value-of select="//compleat:StoredFares/compleat:StoredFare/compleat:inputMessage"/>
                    </inputMessage>
					<infoMessage>
					<xsl:value-of select="//compleat:StoredFares/compleat:StoredFare/compleat:infoMessage"/>
					</infoMessage>
					<endorsements>
					<xsl:value-of select="//compleat:StoredFares/compleat:StoredFare/compleat:endorsements"/>
					</endorsements>
                    <fareCalculation>
                        <xsl:value-of select="//compleat:StoredFares/compleat:StoredFare/compleat:fareCalculation"/>
                    </fareCalculation>
                    <commissionPercentage>
                        <xsl:value-of select="//compleat:StoredFares/compleat:StoredFare/compleat:commissionPercentage"/>
                    </commissionPercentage>
                    <commissionAmount>
                        <xsl:value-of select="//compleat:StoredFares/compleat:StoredFare/compleat:commissionAmount"/>
                    </commissionAmount>
					
                    <quoteDate>
                        <xsl:value-of select="substring-before(//compleat:StoredFares/compleat:StoredFare/compleat:quoteDate,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(//compleat:StoredFares/compleat:StoredFare/compleat:quoteDate,'T')"/>
                    </quoteDate>
                    <lastDateToTicket>
					<xsl:value-of select="substring-before(//compleat:StoredFares/compleat:StoredFare/compleat:lastDateToTicket,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(//compleat:StoredFares/compleat:StoredFare/compleat:lastDateToTicket,'T')"/>
                    </lastDateToTicket>
					<CreditCard>
					  <xsl:value-of select="//compleat:StoredFares/compleat:StoredFare/compleat:CreditCard"/>
					</CreditCard>
					<CompareFare>
					<xsl:text>null</xsl:text>
					</CompareFare>
					<FareCode>
					<xsl:text>null</xsl:text>
					</FareCode>

                    <StoredFareSegments>
                    <xsl:for-each select="//compleat:StoredFares/compleat:StoredFare/compleat:StoredFareSegments/compleat:StoredFareSegment">
                            <StoredFareSegment>
                                <carrierCode>
                                    <xsl:value-of select="compleat:carrierCode"/>
                                </carrierCode>
                                <departureAirportCode>
                                    <xsl:value-of select="compleat:departureAirportCode"/>
                                </departureAirportCode>
                                <departureDate>
								<xsl:value-of select="substring-before(compleat:departureDate,'T')"/>  
									<xsl:text> </xsl:text>
									<xsl:value-of select="substring-after(compleat:departureDate,'T')"/>
                                     </departureDate>
                                <segmentNumber>
                                    <xsl:value-of select="compleat:segmentNumber"/>
                                </segmentNumber>
                                <fare>
                                    <xsl:value-of select="compleat:fare"/>
                                </fare>
                                <classOfService>
                                    <xsl:value-of select="compleat:classOfService"/>
                                </classOfService>
                                <statusCode>
                                    <xsl:value-of select="compleat:statusCode"/>
                                </statusCode>
                                <fareBasis>
                                    <xsl:value-of select="compleat:fareBasis"/>
                                </fareBasis>
                                <notValidBefore>
								 <xsl:value-of select="substring-before(compleat:notValidBefore,'T')"/>  
									<xsl:text> </xsl:text>
								<xsl:value-of select="substring-after(compleat:notValidBefore,'T')"/>
								</notValidBefore>
                                <notValidAfter>
								 <xsl:value-of select="substring-before(compleat:notValidAfter,'T')"/>  
									<xsl:text> </xsl:text>
								<xsl:value-of select="substring-after(compleat:notValidAfter,'T')"/>
								</notValidAfter>
								<ticketDesignator>
								<xsl:value-of select="compleat:ticketDesignator"/>
								</ticketDesignator>
								</StoredFareSegment>
								</xsl:for-each>
								</StoredFareSegments>
								<StoredFarePassengers>

								  <xsl:for-each select="//compleat:StoredFares/compleat:StoredFare/compleat:StoredFarePassengers/compleat:StoredFarePassenger">
								  <StoredFarePassenger>
                            <fareConstruction>
                                <xsl:value-of select="compleat:fareConstruction"/>
                            </fareConstruction>
							  <BaseFare>
                                <amount>
                                    <xsl:value-of select="compleat:amount"/>
                                </amount>
                                <currency>
                                    <xsl:value-of select="compleat:currency"/>
                                </currency>
                            </BaseFare>
							<TotalFare>
                                 <amount>
                                    <xsl:value-of select="compleat:amount"/>
                                </amount>
                                <currency>
                                    <xsl:value-of select="compleat:currency"/>
                                </currency>
                            </TotalFare>
							
                            <lastDateToTicket>
							 <xsl:value-of select="substring-before(compleat:lastDateToTicket,'T')"/>  
									<xsl:text> </xsl:text>
								<xsl:value-of select="substring-after(compleat:lastDateToTicket,'T')"/>
                               </lastDateToTicket>
                            <TicketStatus>
                                <xsl:value-of select="compleat:TicketStatus"/>
                            </TicketStatus>
                            <passengerNumber>  
                            <xsl:value-of select="compleat:passengerNumber"/>
                            </passengerNumber>
                            <passengerTypeIdentifier>
                                <xsl:value-of
                                        select="compleat:passengerTypeIdentifier"/>
                            </passengerTypeIdentifier>
                            <TicketType>
                                <xsl:value-of select="compleat:TicketType"/>
                            </TicketType>
                        </StoredFarePassenger>
						</xsl:for-each>
					</StoredFarePassengers>
                </StoredFare>
            </StoredFares>
			
			 <TicketRequests>
				 <xsl:for-each select="//compleat:TicketRequests/compleat:TicketRequest">
                <TicketRequest>
                    <SegmentNumbers>
					 <xsl:value-of select="compleat:SegmentNumbers"/>
					</SegmentNumbers>
                    <PassengerNumbers>
					 <xsl:value-of select="compleat:PassengerNumbers"/>
					</PassengerNumbers>
                    <isAlreadyTicketed>
                        <xsl:value-of select="compleat:isAlreadyTicketed"/>
                    </isAlreadyTicketed>
                    <lineNumber>
                        <xsl:value-of select="compleat:lineNumber"/>
                    </lineNumber>
                    <requestDate>
					<xsl:value-of select="substring-before(compleat:requestDate,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(compleat:requestDate,'T')"/>
                       
                    </requestDate>
                    <longFreeText>
                        <xsl:value-of select="compleat:longFreeText"/>
                    </longFreeText>
					<branchPcc>
					 <xsl:value-of select="compleat:branchPcc"/>
					</branchPcc>
                    <QueuePlacements>
					 <xsl:for-each select="//compleat:TicketRequests/compleat:TicketRequest/compleat:QueuePlacements/compleat:QueuePlacement">
					<QueuePlacement>
					<queueDate>
					<xsl:value-of select="substring-before(compleat:queueDate,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(compleat:queueDate,'T')"/>
					
					</queueDate>
					<queue>
					 <xsl:value-of select="compleat:queue"/>
					</queue>
					</QueuePlacement>
					</xsl:for-each>
					</QueuePlacements>
                </TicketRequest>
				</xsl:for-each>
				
            </TicketRequests>
			
			 
            <TicketInfos>
			<xsl:for-each select="//compleat:TicketInfos/compleat:TicketInfo">
                <TicketInfo>
				<xsl:for-each select="//compleat:TicketInfos/compleat:TicketInfo/compleat:BaseFare">
                    <BaseFare>
                        <amount>
                            <xsl:value-of select="compleat:amount"/>
                        </amount>
                        <currency>
                            <xsl:value-of select="compleat:currency"/>
                        </currency>
                    </BaseFare>
					</xsl:for-each>
					
					 <xsl:for-each select="//compleat:TicketInfos/compleat:TicketInfo/compleat:FareTotal">
                    <FareTotal>
                        <amount>
                            <xsl:value-of select="compleat:amount"/>
                        </amount>
                        <currency>
                            <xsl:value-of select="compleat:currency"/>
							 </currency>
                    </FareTotal>
					</xsl:for-each>
					 <xsl:for-each select="//compleat:TicketInfos/compleat:TicketInfo/compleat:CommissionTotal">
                    <CommissionTotal>
                        <amount>
                            <xsl:value-of select="compleat:amount"/>
                        </amount>
                        <currency>
                            <xsl:value-of select="compleat:currency"/>
							 </currency>
                    </CommissionTotal>
					</xsl:for-each>
					
					
                    <isETicket>
                        <xsl:value-of select="compleat:isETicket"/>
                    </isETicket>
                    <carrier>
                        <xsl:value-of select="compleat:carrier"/>
                    </carrier>
                    <ticketNum>
                        <xsl:value-of select="compleat:ticketNumber"/>
                    </ticketNum>
					<invoiceNumber>
					 <xsl:value-of select="compleat:invoiceNumber"/>
					</invoiceNumber>
					
					<TotalTax/>
				<TaxDetails>
				<TaxDetail>
				<TaxCode/>
					<TaxAmount>
					<xsl:text>null</xsl:text>
					</TaxAmount>
					<Currency>
					<xsl:text>null</xsl:text>
					</Currency>
					</TaxDetail>
					</TaxDetails>
                </TicketInfo>
				</xsl:for-each>
				
            </TicketInfos>
			    <PhoneNumbers>
			     <xsl:for-each select="//compleat:PhoneNumbers/compleat:PhoneNumber">
                    <PhoneNumber>
                        <number>
                            <xsl:value-of select="compleat:number"/>
                        </number>
                        <type>
                            <xsl:value-of select="compleat:type"/>
                        </type>
                        <longFreeText>
                            <xsl:value-of select="compleat:longFreeText"/>
                        </longFreeText>
                        <cityCode>
                            <xsl:value-of select="compleat:cityCode"/>
                        </cityCode>
                    </PhoneNumber>
                </xsl:for-each>
				
            </PhoneNumbers>
            <AdditionalInfo>
			 
                <TravelerProfileIdentifiers>
				<GDSCompanyProfile>
				 <xsl:value-of select="//compleat:TravelerProfileIdentifiers/compleat:GDSCompanyProfile"/>
				 </GDSCompanyProfile>
				 <GDSTravelerProfile>
				  <xsl:value-of select="//compleat:TravelerProfileIdentifiers/compleat:GDSTravelerProfile"/>
				 </GDSTravelerProfile>
				</TravelerProfileIdentifiers>
            </AdditionalInfo>
		 <Segments>
			 <!-- ARNKSegment  -->
			  <xsl:for-each select="//compleat:Segments/compleat:Segment">
			    <xsl:choose>
                        <xsl:when test="@xsi:type = 'ARNKSegment'" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
			  			
				<Segment>
				<segType>
				<xsl:text>ARNKS</xsl:text>
				</segType>
			         <segmentNumber> 
					   <xsl:value-of select="compleat:segmentNumber"/>
					 </segmentNumber>
                     <TDSValidated>
					 <xsl:value-of select="compleat:TDSValidated"/>
					 </TDSValidated>
					<isCancelled> 
					<xsl:value-of select="compleat:isCancelled"/>
					</isCancelled>
					<isScheduleChange>
					<xsl:value-of select="compleat:isScheduleChange"/>
					</isScheduleChange>
               </Segment>
                   </xsl:when>
                    </xsl:choose>
					
		

                </xsl:for-each>
            <!-- AirSegments -->
           
              <xsl:for-each select="//compleat:Segments/compleat:Segment">
                    <xsl:choose>
                        <xsl:when test="@xsi:type = 'AirSegment'" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
                            <Segment>
							
							<segType>
							<xsl:text>Air</xsl:text>
							</segType>
                                <segmentNumber>
                                    <xsl:value-of select="compleat:segmentNumber"/>
                                </segmentNumber>
                                <TDSValidated>
								   <xsl:value-of select="compleat:TDSValidated"/>
								</TDSValidated>
                                <isCancelled>
								 <xsl:value-of select="compleat:isCancelled"/>
								</isCancelled>
                                <isScheduleChange>
								 <xsl:value-of select="compleat:isScheduleChange"/>
								</isScheduleChange>
                                <startDateTime>
								<xsl:value-of select="substring-before(compleat:startDateTime,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(compleat:startDateTime,'T')"/>
                                   
                                </startDateTime>
                                <endDateTime>
								<xsl:value-of select="substring-before(compleat:endDateTime,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(compleat:endDateTime,'T')"/>
                                
                                </endDateTime>
                                <startCityName>
                                    <xsl:value-of select="compleat:startCityName"/>
                                </startCityName>
                                <endCityName>
                                    <xsl:value-of select="compleat:endCityName"/>
                                </endCityName>
                                <marketingAirlineCode>
                                    <xsl:value-of select="compleat:marketingAirlineCode"/>
                                </marketingAirlineCode>
                                <marketingFlightNumber>
                                    <xsl:value-of select="compleat:marketingFlightNumber"/>
                                </marketingFlightNumber>
								<operatingAirline>
								<xsl:value-of select="compleat:operatingAirline"/>
								</operatingAirline>
                                <operatingAirlineCode>
                                   <xsl:value-of select="compleat:operatingAirlineCode"/>
                                </operatingAirlineCode>
                                <mealCode>
                                    <xsl:value-of select="compleat:mealCode"/>
                                </mealCode>
                                <mealDescription>
                                        <xsl:value-of select="compleat:mealDescription"/>
                                </mealDescription>
                                <bookingSiteConfNum>
                                    <xsl:value-of select="compleat:bookingSiteConfNum"/>
                                </bookingSiteConfNum>
                                <supplierConfNum>
								<xsl:value-of select="compleat:supplierConfNum"/>
								</supplierConfNum>
                                <equipmentCode>
                                   <xsl:value-of select="compleat:equipmentCode"/>
                                </equipmentCode>
                                <departureAirportCode>
                                     <xsl:value-of select="compleat:departureAirportCode"/>
                                </departureAirportCode>
                                <arrivalAirportCode>
                                    <xsl:value-of select="compleat:arrivalAirportCode"/>
                                </arrivalAirportCode>
                                <classOfService>
                                  <xsl:value-of select="compleat:classOfService"/>
                                </classOfService>
                                <classOfServiceDescription>
                                    <xsl:value-of select="compleat:classOfServiceDescription"/>
                                </classOfServiceDescription>
                                <status>
                                   <xsl:value-of select="compleat:status"/>
                                </status>
                                <isChangeOfGuage>
                                      <xsl:value-of select="compleat:isChangeOfGuage"/>
                                </isChangeOfGuage>
                                <calculatedDuration>
                                  <xsl:value-of select="compleat:calculatedDuration"/>
                                </calculatedDuration>
                                <numberOfStops>
                                    <xsl:value-of select="compleat:numberOfStops"/>
                                </numberOfStops>
                                <hasSpecialMeal>
                                     <xsl:value-of select="compleat:hasSpecialMeal"/>
                                </hasSpecialMeal>
                                <StopsCityList>
								<xsl:value-of select="compleat:StopCityList"/>
                                  </StopsCityList>
                                <MarriedToSegments>
                                      <xsl:value-of select="compleat:MarriedToSegments"/>
                              </MarriedToSegments>
                                <ConnectedSegments>
                                        <xsl:value-of select="compleat:ConnectedSegments"/>
                                       
                                </ConnectedSegments>
								<isCodeShare>
								<xsl:choose>
						<xsl:when test="codeShareComment1"> 
						<xsl:text>1</xsl:text>
				                 </xsl:when>
								  <xsl:otherwise>
								  <xsl:text>0</xsl:text>
								  </xsl:otherwise>
				                 </xsl:choose>
 								</isCodeShare>
								<codeShareComment1>
								 <xsl:value-of select="compleat:codeShareComment1"/>
								</codeShareComment1>
                                <isFlown>
                                    <xsl:value-of select="compleat:isFlown"/>
                                </isFlown>
                                <isOpen>
								<xsl:value-of select="compleat:isOpen"/>
								</isOpen>
                                <isPassive>
                                    <xsl:value-of select="compleat:isPassive"/>
                                </isPassive>
                                <isFlown>
                                    <xsl:value-of select="compleat:isFlown"/>
                                </isFlown>
                                <isTicketless>
                                    <xsl:value-of select="compleat:isTicketless"/>
                                </isTicketless>
                                <InFlightServiceCodes>
                                    <xsl:value-of select="compleat:InFlightServiceCodes"/>
                                </InFlightServiceCodes>
                                <isETicketCandidate>
                                    <xsl:value-of select="compleat:isETicketCandidate"/>
                                </isETicketCandidate>
                                <arrivalTerminal>
                                   <xsl:value-of select="compleat:arrivalTerminal"/>
                                </arrivalTerminal>
                                <flightDistance>
                                    <xsl:value-of select="compleat:flightDistance"/>
                                </flightDistance>
                                <isTicketed>
                                    <xsl:value-of select="compleat:isTicketed"/>
                                </isTicketed>
                                <FareBasisCode/>
                            </Segment>
                        </xsl:when>
                    </xsl:choose>
                </xsl:for-each>
				
				   <!-- RailSegments -->
                <xsl:for-each select="compleat:Segments/compleat:Segment">
                    <xsl:choose>
                        <xsl:when test="@xsi:type = 'RailSegment'" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
                            <Segment>
							<segType>
						<xsl:text>Rail</xsl:text>
						</segType>
                                <segmentNumber>
                                    <xsl:value-of select="compleat:segmentNumber"/>
                                </segmentNumber>
                                <TDSValidated>
								<xsl:value-of select="compleat:TDSValidated"/>
								</TDSValidated>
                                <isCancelled>
								<xsl:value-of select="compleat:isCancelled"/>
								</isCancelled>
                                <isScheduleChange>
								<xsl:value-of select="compleat:isScheduleChange"/>
								</isScheduleChange>
                                <startDateTime>
								<xsl:value-of select="substring-before(compleat:startDateTime,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(compleat:startDateTime,'T')"/>
                                   
                                </startDateTime>
                                <endDateTime>
								<xsl:value-of select="substring-before(compleat:endDateTime,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(compleat:endDateTime,'T')"/>
								</endDateTime>
                                <StartStationAddress>
                                    <addr1>
                                        <xsl:value-of select="compleat:StartStationAddress/compleat:addr1"/>
                                    </addr1>
                                    <longFreeText>
									  <xsl:value-of select="compleat:StartStationAddress/compleat:longFreeText"/>
									</longFreeText>
                                </StartStationAddress>
                                <EndStationAddress>
                                   <addr1>
                                        <xsl:value-of select="compleat:EndStationAddress/compleat:addr1"/>
                                    </addr1>
                                    <longFreeText>
									  <xsl:value-of select="compleat:EndStationAddress/compleat:longFreeText"/>
									</longFreeText>
                                </EndStationAddress>
                                <carrierName>
                                    <xsl:value-of select="compleat:carrierName"/>
                                </carrierName>
                                <confirmationNum>
								<xsl:value-of select="compleat:confirmationNum"/>
								</confirmationNum>
                                <bookingSiteConfNum>
                                    <xsl:value-of select="compleat:bookingSiteConfNum"/>
                                </bookingSiteConfNum>
                                <bookingSiteURL>
                                    <xsl:value-of select="compleat:bookingSiteURL"/>
                                </bookingSiteURL>
                                <departureStationCode>
                                    <xsl:value-of select="compleat:departureStationCode"/>
                                </departureStationCode>
                                <arrivalStationCode>
                                    <xsl:value-of select="compleat:arrivalStationCode"/>
                                </arrivalStationCode>
                                <trainNumber>
                                    <xsl:value-of select="compleat:trainNumber"/>
                                </trainNumber>
                                <operator>
								  <xsl:value-of select="compleat:operator"/>
								</operator>
								<classOfService/>
								<classOfServiceDescription/>
                                <status>
                                    <xsl:value-of select="compleat:status"/>
                                </status>
                                <longFreeText>
								<xsl:value-of select="compleat:longFreeText"/>
								</longFreeText>
                                <specialInstructions>
								<xsl:value-of select="compleat:specialInstructions"/>
								</specialInstructions>
                                <numberInParty>
                                    <xsl:value-of select="compleat:numberInParty"/>
                                </numberInParty>
                                <numberOfStops>
								  <xsl:value-of select="compleat:numberOfStops"/>
								</numberOfStops>
                                <isPassive>
                                    <xsl:value-of select="compleat:isPassive"/>
                                </isPassive>
                                <RateQuote>
								  <xsl:value-of select="compleat:RateQuote"/>
								</RateQuote>
                                <calculatedDuration>
                                    <xsl:value-of select="compleat:calculatedDuration"/>
                                </calculatedDuration>
								<Typecode><xsl:text>null</xsl:text></Typecode>
                            </Segment>
                        </xsl:when>
                    </xsl:choose>
                </xsl:for-each>
                       <!-- HotelSegments -->
                <xsl:for-each select="//compleat:Segments/compleat:Segment">
                    <xsl:choose>
                        <xsl:when test="@xsi:type = 'HotelSegment'" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
                            <Segment>
							<segType>
							<xsl:text>Hotel</xsl:text>
							</segType>
                                <segmentNumber>
                                    <xsl:value-of select="compleat:segmentNumber"/>
                                </segmentNumber>
                                <TDSValidated>
								  <xsl:value-of select="compleat:TDSValidated"/>
								</TDSValidated>
                                <isCancelled>
								 <xsl:value-of select="compleat:isCancelled"/>
								</isCancelled>
                                <isScheduleChange>
								 <xsl:value-of select="compleat:isScheduleChange"/>
								</isScheduleChange>
								<crs_code>
								<xsl:text>null</xsl:text>
								</crs_code>
								<SegRecordLocator>
								<xsl:text>null</xsl:text>
								</SegRecordLocator>
                               <startDateTime>
								<xsl:value-of select="substring-before(compleat:startDateTime,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(compleat:startDateTime,'T')"/>
                                   
                                </startDateTime>
                                <endDateTime>
								<xsl:value-of select="substring-before(compleat:endDateTime,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(compleat:endDateTime,'T')"/>
                                </endDateTime>
								
                                <Address>
								 <cityCode>
                                         <xsl:value-of select="compleat:Address/compleat:cityCode"/>
                                    </cityCode>
									<longFreeText>
									 <xsl:value-of select="compleat:Address/compleat:longFreeText"/>
									</longFreeText>
                                    <addr1>
                                       <xsl:value-of select="compleat:Address/compleat:addr1"/>
                                    </addr1>
                                    <city>
                                        <xsl:value-of select="compleat:Address/compleat:city"/>
                                    </city>
									<state>
									  <xsl:value-of select="compleat:Address/compleat:state"/>
									</state>
                                   
                                    <zip>
                                         <xsl:value-of select="compleat:Address/compleat:zip"/>
                                    </zip>
                                    <country>
                                           <xsl:value-of select="compleat:Address/compleat:country"/>
                                    </country>
									<latitude>
									<xsl:text>null</xsl:text>
									</latitude>
									<longitude>
									<xsl:text>null</xsl:text>
									</longitude>
                                    
                                </Address>
								
                                <numberGuests>
                                 <xsl:value-of select="compleat:numberGuests"/>
                                </numberGuests>
                                <bookingSiteConfNum>
                                    <xsl:value-of select="compleat:bookingSiteConfNum"/>
                                </bookingSiteConfNum>
                                <bookingSiteURL>
                                    <xsl:value-of select="compleat:bookingSiteURL"/>
                                </bookingSiteURL>
                                <supplierConfNum>
                                    <xsl:value-of select="compleat:supplierConfNum"/>
                                </supplierConfNum>
                                <supplierName>
                                    <xsl:value-of select="compleat:supplierName"/>
                                </supplierName>
                                <supplierPhone>
								<xsl:value-of select="compleat:supplierPhone"/>
								</supplierPhone>
                                <hotelChainCode>
                                    <xsl:value-of select="compleat:hotelChainCode"/>
                                </hotelChainCode>
                                <numberOfRooms>
                                     <xsl:value-of select="compleat:numberOfRooms"/>
                                </numberOfRooms>
                                <roomType>
                                     <xsl:value-of select="compleat:roomType"/>
                                </roomType>
                                <specialInstructions>
								    <xsl:value-of select="compleat:specialInstructions"/>
								</specialInstructions>
                                <propertyCode>
                                     <xsl:value-of select="compleat:propertyCode"/>
                                </propertyCode>
                                <creditCardGuarantee>
                                    <xsl:value-of select="compleat:creditCardGuarantee"/>
                                </creditCardGuarantee>
                                
                                <numberOfNights>
                                    <xsl:value-of select="compleat:numberOfNights"/>
                                </numberOfNights>
                                <status>
                                       <xsl:value-of select="compleat:status"/>
                                </status>
                                <rateCode>
                                     <xsl:value-of select="compleat:rateCode"/>
                                </rateCode>
								<longFreeText>
								 <xsl:value-of select="compleat:longFreeText"/>
								</longFreeText>
                                <Rate>
                                    <amount>
						          <xsl:value-of select="compleat:Rate/compleat:amount"/>
									</amount>
                                    <currency>
									 <xsl:value-of select="compleat:Rate/compleat:currency"/>
									</currency>
                                </Rate>
                                <corporateDiscountNumber>
                                    <xsl:text>null</xsl:text>
                                </corporateDiscountNumber>
                                <RateChangeInfos>
								 <xsl:value-of select="compleat:RateChangeInfos"/>
								</RateChangeInfos>
                                <dcsAuxRequestReturnRate>
                                    <xsl:value-of select="compleat:dcsAuxRequestReturnRate"/>
                                </dcsAuxRequestReturnRate>
                                <isPassive>
                                    <xsl:value-of select="compleat:isPassive"/>
                                </isPassive>
                                <RateCategory>
                                    <xsl:value-of select="compleat:RateCategory"/>
                                </RateCategory>
                                <ExtraPersonRate>
								  <xsl:value-of select="compleat:ExtraPersonRate"/>
								</ExtraPersonRate>
                                <isMultiDateRate>
                                    <xsl:value-of select="compleat:isMultiDateRate"/>
                                </isMultiDateRate>
                                <cancellationPolicy>
                                    <xsl:value-of select="compleat:cancellationPolicy"/>
                                </cancellationPolicy>
                                <rateChangeDuringStay>
                                    <xsl:value-of select="compleat:rateChangeDuringStay"/>
                                </rateChangeDuringStay>
                                <hotelOverview>
								 <xsl:value-of select="compleat:hotelOverview"/>
								</hotelOverview>
								<RateTotal>
								 <amount>
						          <xsl:value-of select="compleat:RateTotal/compleat:amount"/>
									</amount>
                                    <currency>
									 <xsl:value-of select="compleat:RateTotal/compleat:currency"/>
									</currency>
								</RateTotal>
                            </Segment>
                        </xsl:when>
                    </xsl:choose>
                </xsl:for-each>
				
				<!-- MiscellaneousSegment -->
             <xsl:for-each select="//compleat:Segments/compleat:Segment">
                    <xsl:choose>
                        <xsl:when test="@xsi:type = 'MiscellaneousSegment'" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
                      <Segment>
					  <segType>
							<xsl:text>Miscellaneous</xsl:text>
							</segType>
						<segmentNumber>
						 <xsl:value-of select="compleat:segmentNumber"/>
						</segmentNumber>
                        <TDSValidated>
						 <xsl:value-of select="compleat:TDSValidated"/>
						</TDSValidated>
						<isCancelled>
						  <xsl:value-of select="compleat:isCancelled"/>
						</isCancelled>
						<isScheduleChange>
						  <xsl:value-of select="compleat:isScheduleChange"/>
						</isScheduleChange>
						<startDateTime>
								<xsl:value-of select="substring-before(compleat:startDateTime,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(compleat:startDateTime,'T')"/>
                                   
                                </startDateTime>
                                <endDateTime>
								<xsl:value-of select="substring-before(compleat:endDateTime,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(compleat:endDateTime,'T')"/>
                                
                                </endDateTime>
						<numberInParty>
						 <xsl:value-of select="compleat:numberInParty"/>
						</numberInParty>
						<departureCityCode>
						 <xsl:value-of select="compleat:departureCityCode"/>
						</departureCityCode>
						<operator>
						  <xsl:value-of select="compleat:operator"/>
						</operator>
						<status>
						 <xsl:value-of select="compleat:status"/>
						</status>
						<longFreeText>
						 <xsl:value-of select="compleat:longFreeText"/>
						</longFreeText>
	
						 </Segment>
                        </xsl:when>
                    </xsl:choose>
                </xsl:for-each>
			
            <!-- CarSegments -->
                <xsl:for-each select="//compleat:Segments/compleat:Segment">
                    <xsl:choose>
                        <xsl:when test="@xsi:type = 'CarSegment'" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
                            <Segment>
							<segType>
						<xsl:text>Car</xsl:text>
						</segType>
                                <segmentNumber>
                                    <xsl:value-of select="compleat:segmentNumber"/>
                                </segmentNumber>
                                <TDSValidated>
								   <xsl:value-of select="compleat:TDSValidated"/>
								</TDSValidated>
                                <isCancelled>
								 <xsl:value-of select="compleat:isCancelled"/>
								</isCancelled>
                                <isScheduleChange>
								 <xsl:value-of select="compleat:isScheduleChange"/>
								</isScheduleChange>
                                <startDateTime>
								<xsl:value-of select="substring-before(compleat:startDateTime,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(compleat:startDateTime,'T')"/>
                                   
                                </startDateTime>
                                <endDateTime>
								<xsl:value-of select="substring-before(compleat:endDateTime,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(compleat:endDateTime,'T')"/>
                                
                                </endDateTime>
                                <StartLocationAddress>
                                    <addr1>
                                        <xsl:value-of select="compleat:StartLocationAddress/compleat:addr1"/>
                                    </addr1>
                                    <addr2>
                                        <xsl:value-of select="compleat:StartLocationAddress/compleat:addr2"/>
                                    </addr2>
                                    <city>
                                        <xsl:value-of select="compleat:StartLocationAddress/compleat:city"/>
                                    </city>
                                    <state>
                                        <xsl:value-of select="compleat:StartLocationAddress/compleat:state"/>
                                    </state>
                                    <zip>
                                        <xsl:value-of select="compleat:StartLocationAddress/compleat:zip"/>
                                    </zip>
                                    <country>
                                        <xsl:value-of select="compleat:StartLocationAddress/compleat:country"/>
                                    </country>
                                    <longFreeText>
									 <xsl:value-of select="compleat:StartLocationAddress/compleat:longFreeText"/>
									</longFreeText>
                                </StartLocationAddress>
                                <EndLocationAddress>
                                     <addr1>
                                        <xsl:value-of select="compleat:EndLocationAddress/compleat:addr1"/>
                                    </addr1>
                                    <state>
                                        <xsl:value-of select="compleat:EndLocationAddress/compleat:state"/>
                                    </state>
                                    <country>
                                        <xsl:value-of select="compleat:EndLocationAddress/compleat:country"/>
                                    </country>
                                   
                                </EndLocationAddress>
								<carType>
								<xsl:text>null</xsl:text>
								</carType>
                                <carDescription>  
								<xsl:value-of select="compleat:carDescription"/>
								</carDescription>
                                <bookingSiteConfNum>
                                    <xsl:value-of select="compleat:bookingSiteConfNum"/>
                                </bookingSiteConfNum>
                                <bookingSiteURL>
                                    <xsl:value-of select="compleat:bookingSiteURL"/>
                                </bookingSiteURL>
                                <supplierConfNum>
								<xsl:value-of select="compleat:supplierConfNum"/>
								</supplierConfNum>
                                <supplierName>
								<xsl:value-of select="compleat:supplierName"/>
								</supplierName>
                                <locationCode>
								<xsl:value-of select="compleat:locationCode"/>
								</locationCode>
                                <pickupLocation>
								<xsl:value-of select="compleat:pickupLocation"/>
								</pickupLocation>
                                <dropOffLocation>
								<xsl:value-of select="compleat:dropOffLocation"/>
								</dropOffLocation>
                                <rentalCompany>
                                    <xsl:value-of select="compleat:rentalCompany"/>
                                </rentalCompany>
                                <numberOfCars>
                                    <xsl:value-of select="compleat:numberOfCars"/>
                                </numberOfCars>
								<compCode>
								<xsl:text>null</xsl:text>
								</compCode>
                                <ExtraDayFee>
                                    <amount>
                                        <xsl:value-of select="compleat:ExtraDayFee/compleat:amount"/>
                                    </amount>
                                    <currency>
                                         <xsl:value-of select="compleat:ExtraDayFee/compleat:currency"/>
                                    </currency>
                                </ExtraDayFee>
								<currency>
								 <xsl:value-of select="compleat:currency"/>
								</currency>
								<status>
								 <xsl:value-of select="compleat:status"/>
								</status>
                                <ExtraHourFee>
                                    <amount>
                                        <xsl:value-of select="compleat:ExtraHourFee/compleat:amount"/>
                                    </amount>
                                    <currency>
                                         <xsl:value-of select="compleat:ExtraHourFee/compleat:currency"/>
                                    </currency>
                                </ExtraHourFee>
                                <MileCharge/>
                                <status>
                                    <xsl:value-of select="compleat:status"/>
                                </status>
                                <corporateDiscountNumber>
                                   <xsl:value-of select="compleat:corporateDiscountNumber"/>
                                </corporateDiscountNumber>
                                <GuaranteedRate>
                                    <xsl:value-of select="compleat:GuaranteedRate"/>
                                </GuaranteedRate>
                                <BaseRate>
                                     <amount>
                                        <xsl:value-of select="compleat:BaseRate/compleat:amount"/>
                                    </amount>
                                    <currency>
                                         <xsl:value-of select="compleat:BaseRate/compleat:currency"/>
                                    </currency>
                                </BaseRate>
                                <EstimatedTotal>
                                     <amount>
                                        <xsl:value-of select="compleat:EstimatedTotal/compleat:amount"/>
                                    </amount>
                                    <currency>
                                         <xsl:value-of select="compleat:EstimatedTotal/compleat:currency"/>
                                    </currency>
                                </EstimatedTotal>
                                <QuotedRate>
								 <amount>
                                        <xsl:value-of select="compleat:QuotedRate/compleat:amount"/>
                                    </amount>
                                    <currency>
                                         <xsl:value-of select="compleat:QuotedRate/compleat:currency"/>
                                    </currency>
								</QuotedRate>
                                <DropCharge>
                                   <xsl:value-of select="compleat:QuotedRate/compleat:DropCharge"/>
                                </DropCharge>
                                <rateCode>
								     <xsl:value-of select="compleat:rateCode"/>
								</rateCode>
                                <bookedThrough>
								  <xsl:value-of select="compleat:bookedThrough"/>
								</bookedThrough>
                                <reservationName>
								  <xsl:value-of select="compleat:reservationName"/>
								</reservationName>
                                <rateCurrencyCode>
								  <xsl:value-of select="compleat:rateCurrencyCode"/>
								</rateCurrencyCode>
                                <dcsAuxRateCode>
                                    <xsl:value-of select="compleat:dcsAuxRateCode"/>
                                </dcsAuxRateCode>
                                <clientIDNumber>
                                    <xsl:value-of select="compleat:clientIDNumber"/>
                                </clientIDNumber>
                                <OneWayDropFee>
								<xsl:value-of select="compleat:OneWayDropFee"/>
								</OneWayDropFee>
                                <isKilometers>
								<xsl:value-of select="compleat:isKilometers"/>
								</isKilometers>
                                <isManuallyBooked>
								<xsl:value-of select="compleat:isManuallyBooked"/>
								</isManuallyBooked>
                                <frequentRenterID>
                                 <xsl:value-of select="compleat:frequentRenterID"/>
                                </frequentRenterID>
                                <isPassive>
                                   <xsl:value-of select="compleat:isPassive"/>
                                </isPassive>
                                <passengerNumber>
								  <xsl:value-of select="compleat:passengerNumber"/>
								</passengerNumber>
                                <CarRateCategory>
                                    <xsl:value-of select="compleat:CarRateCategory"/>
                                </CarRateCategory>
                                <AgentEnteredRate>
                                      <xsl:value-of select="compleat:AgentEnteredRate"/>
                                </AgentEnteredRate>
                                <CarRateType>
                                       <xsl:value-of select="compleat:CarRateType"/>
                                </CarRateType>
                                <mileageAllowance>
                                      <xsl:value-of select="compleat:mileageAllowance"/>
                                </mileageAllowance>
                                <extraDayMileageAllowance>
                                    <xsl:value-of select="compleat:extraDayMileageAllowance"/>
                                </extraDayMileageAllowance>
                                <ExtraDayMileageFee>
                                      <xsl:value-of select="compleat:ExtraDayMileageFee"/>
                                </ExtraDayMileageFee>
                                <extraHourMileageAllowance>
                                    <xsl:value-of select="compleat:extraHourMileageAllowance"/>
                                </extraHourMileageAllowance>
                                <ExtraHourMileageFee/>
                                <totalMandatoryCharges>
                                    <xsl:value-of select="compleat:totalMandatoryCharges"/>
                                </totalMandatoryCharges>
                                <ExtraWeekFee>
                                        <xsl:value-of select="compleat:ExtraWeekFee"/>
                                </ExtraWeekFee>
                                <extraWeekMileageFee>
								   <xsl:value-of select="compleat:extraWeekMileageFee"/>
								</extraWeekMileageFee>
                                <numberOfDays>
                                  <xsl:value-of select="compleat:numberOfDays"/>
                                </numberOfDays>
                                <numberOfHours>
                                    <xsl:value-of select="compleat:numberOfHours"/>
                                </numberOfHours>
								<compCode>
								<xsl:text>null</xsl:text>
								</compCode>
                            </Segment>
                        </xsl:when>
                    </xsl:choose>
                </xsl:for-each>
           
		   
        
				
				 <!-- TourSegments -->
                <xsl:for-each select="//compleat:Segments/compleat:Segment">
                    <xsl:choose>
                        <xsl:when test="@xsi:type = 'TourSegment'" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
                            <Segment>
							<segType>
						<xsl:text>Tour</xsl:text>
						</segType>
                                <segmentNumber>
                                    <xsl:value-of select="compleat:segmentNumber"/>
                                </segmentNumber>
                                <TDSValidated>
								  <xsl:value-of select="compleat:TDSValidated"/>
								</TDSValidated>
                                <isCancelled>
								  <xsl:value-of select="compleat:isCancelled"/>
								</isCancelled>
                                <isScheduleChange>
								  <xsl:value-of select="compleat:isScheduleChange"/>
								</isScheduleChange>
                                <startDateTime>
								<xsl:value-of select="substring-before(compleat:startDateTime,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(compleat:startDateTime,'T')"/>
                                   
                                </startDateTime>
                                <endDateTime>
								<xsl:value-of select="substring-before(compleat:endDateTime,'T')"/>  
						<xsl:text> </xsl:text>
						<xsl:value-of select="substring-after(compleat:endDateTime,'T')"/>
                                  
                                </endDateTime>
                                <StartLocationAddress>
                                    <addr1>
                                        <xsl:value-of select="compleat:StartLocationAddress/compleat:addr1"/>
                                    </addr1>
                                </StartLocationAddress>
                                <EndLocationAddress>
                                    <addr1>
                                       <xsl:value-of select="compleat:EndLocationAddress/compleat:addr1"/>
                                    </addr1>
                                </EndLocationAddress>
                                <startLocationName>
                                    <xsl:value-of select="compleat:startLocationName"/>
                                </startLocationName>
                                <endLocationName>
                                   <xsl:value-of select="compleat:endLocationName"/>
                                </endLocationName>
                                <carrierName>
                                     <xsl:value-of select="compleat:carrierName"/>
                                </carrierName>
                                <confirmationNum>
								 <xsl:value-of select="compleat:confirmationNum"/>
								</confirmationNum>
                                <vehicleDescription>
                                    <xsl:value-of select="compleat:vehicleDescription"/>
                                </vehicleDescription>
                                <AgencyCommission>
								 <xsl:value-of select="compleat:AgencyCommission"/>
								</AgencyCommission>
                                <BalanceAmount>
								 <xsl:value-of select="compleat:BalanceAmount"/>
								</BalanceAmount>
                                <CancellationFee>
								<xsl:value-of select="compleat:CancellationFee"/>
								</CancellationFee>
                                <DepositAmountDue>
									<xsl:value-of select="compleat:DepositAmountDue"/>
								</DepositAmountDue>
                                <TotalTax>
								<xsl:value-of select="compleat:TotalTax"/>
								</TotalTax>
                                <BasePrice>
								<xsl:value-of select="compleat:BasePrice"/>
								</BasePrice>
                                <cityCode>
								<xsl:value-of select="compleat:cityCode"/>
								</cityCode>
                                <longFreeText>
								<xsl:value-of select="compleat:longFreeText"/>
								</longFreeText>
                                <TotalRate>
								<xsl:value-of select="compleat:TotalRate"/>
								</TotalRate>
                                <TourRate>
                                    <xsl:value-of select="compleat:TourRate"/>
                                </TourRate>
                                <numberOfNights>
                                    <xsl:value-of select="compleat:numberOfNights"/>
                                </numberOfNights>
                                <numberOfRooms>
                                    <xsl:value-of select="compleat:numberOfRooms"/>
                                </numberOfRooms>
                                <tourNumber>
								 <xsl:value-of select="compleat:tourNumber"/>
								</tourNumber>
                                <serviceEndDate>
								 <xsl:value-of select="compleat:serviceEndDate"/>
								</serviceEndDate>
                                <vendorCodeAndName>
                                    <xsl:value-of select="compleat:vendorCodeAndName"/>
                                </vendorCodeAndName>
                                <status>
                                   <xsl:value-of select="compleat:status"/>
                                </status>
                                <tourRateFreeText>
								  <xsl:value-of select="compleat:tourRateFreeText"/>
								</tourRateFreeText>
                            </Segment>
                        </xsl:when>
                    </xsl:choose>
                </xsl:for-each>

            </Segments>
  
             <DL_XmlFileName>
                <xsl:value-of select="//compleat:pnr/compleat:DL_XmlFileName"/>
            </DL_XmlFileName>
             <DL_ZipFileName>
                <xsl:value-of select="//compleat:pnr/compleat:DL_ZipFileName"/>
            </DL_ZipFileName>
             <DL_XmlFileNameTS>
                <xsl:value-of select="//compleat:pnr/compleat:DL_XmlFileNameTS"/>
            </DL_XmlFileNameTS>
             <DL_XmlInsertTS>
                <xsl:value-of select="//compleat:pnr/compleat:DL_XmlInsertTS"/>
            </DL_XmlInsertTS>
            
        </pnr>

    </xsl:template>
</xsl:stylesheet>