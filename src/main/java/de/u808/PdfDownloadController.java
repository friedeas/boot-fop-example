package de.u808;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.xmlgraphics.util.MimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PdfDownloadController implements ResourceLoaderAware {

	private ResourceLoader resourceLoader;

	@Autowired
	FopFactory fopFactory;

	@Autowired
	TransformerFactory transformerFactory;

	@GetMapping("/")
	public String indexPage() {
		return "index";
	}

	@RequestMapping(value = "/example.pdf")
	public void getPdf(HttpServletResponse response) throws FOPException, IOException, TransformerException {
		FOUserAgent userAgent = fopFactory.newFOUserAgent();// FOUserAgent can be used to set user configurable options
		Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, response.getOutputStream());

		// Start FOP processing
		transformerFactory.newTransformer().transform(new StreamSource(getTestFo()),
				new SAXResult(fop.getDefaultHandler()));
	}

	private InputStream getTestFo() throws IOException {
		Resource testFoFileResource = this.resourceLoader.getResource("classpath:fop/testfile.fo");
		return testFoFileResource.getInputStream();
	}

	@Override
	public void setResourceLoader(ResourceLoader rl) {
		this.resourceLoader = rl;
	}
}
