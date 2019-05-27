package de.u808;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.xmlgraphics.util.MimeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PdfDownloadController implements ResourceLoaderAware {

	private ResourceLoader resourceLoader;

	private final static Logger LOG = LoggerFactory.getLogger(PdfDownloadController.class);

	@Autowired
	FopFactory fopFactory;

	@Autowired
	Jaxb2Marshaller marshaller;

	@Autowired
	TransformerFactory transformerFactory;

	@GetMapping("/")
	public String indexPage() {
		return "index";
	}

	@RequestMapping(value = "/example.pdf")
	public void getPdf(@RequestParam(defaultValue = "12345678", name = "code") String code,
			final HttpServletResponse response) throws FOPException, IOException, TransformerException {
		LOG.debug("Barcode value to use: " + code);

		FOUserAgent userAgent = fopFactory.newFOUserAgent();// FOUserAgent can be used to set user configurable options
		Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, response.getOutputStream());

		// Start FOP processing
		Transformer transformer = transformerFactory.newTransformer(new StreamSource(getTestXsl()));
		transformer.transform(getSource(code),
				new SAXResult(fop.getDefaultHandler()));
	}

	private InputStream getTestXsl() throws IOException {
		Resource testFoFileResource = this.resourceLoader.getResource("classpath:fop/testfile.xsl");
		return testFoFileResource.getInputStream();
	}

	private StreamSource getSource(final String codeValue) {
		StringWriter sw = new StringWriter();
		Result result = new StreamResult(sw);
		marshaller.marshal(new CustomModelObject(codeValue), result);
		final String xmlObject = sw.toString();
		LOG.debug(xmlObject);
		return new StreamSource(new StringReader(xmlObject));
	}

	@Override
	public void setResourceLoader(ResourceLoader rl) {
		this.resourceLoader = rl;
	}
}
