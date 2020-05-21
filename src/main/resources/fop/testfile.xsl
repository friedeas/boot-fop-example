<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<xsl:output method="xml" indent="yes" />
	<xsl:template match="/">
		<fo:root>

			<fo:layout-master-set>

				<fo:simple-page-master master-name="simple"
					page-height="29.7cm" page-width="21cm" margin-top="1cm"
					margin-bottom="2cm" margin-left="2.5cm" margin-right="2.5cm">
					<fo:region-body margin-top="3cm" />
					<fo:region-before extent="3cm" />
					<fo:region-after extent="1.5cm" />
				</fo:simple-page-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="simple">

				<fo:flow flow-name="xsl-region-body">

					<fo:block font-size="18pt" font-family="sans-serif"
						line-height="24pt" space-after.optimum="15pt" background-color="blue"
						color="white" text-align="center" padding-top="3pt">
						Barcode Example
					</fo:block>
					
					<fo:table table-layout="fixed">					   
						<fo:table-column column-width="proportional-column-width(1)"/>
					    <fo:table-body line-height="16pt">
					        <fo:table-row height="20pt">
					            <fo:table-cell text-align="center">
					                <fo:block-container >
					                    <fo:block font-family="New" font-size="36pt">
											*<xsl:value-of select="customModelObject/codeValue"/>*
										</fo:block>
					                </fo:block-container>					                
					            </fo:table-cell>
					        </fo:table-row>
					        <fo:table-row>
					        	<fo:table-cell text-align="center">
					        		<fo:block-container>
					                    <fo:block font-size="9pt" letter-spacing="9pt">*<xsl:value-of select="customModelObject/codeValue"/>*</fo:block>
					                </fo:block-container>
					        	</fo:table-cell>
					        </fo:table-row>
					    </fo:table-body>
					</fo:table>

				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>