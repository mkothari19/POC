<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output indent="no" />
    <xsl:strip-space elements="*"/>
    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>
    <xsl:template match="*">
        <xsl:element name="{local-name()}">
            <xsl:apply-templates select="@*[not(name()='xsi:type')]|node()"/>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>
