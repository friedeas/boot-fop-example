# Spring Boot MVC and Apache FOP PDF Barcode Example

### Example PDF Document
This example is using Apache FOP and the TTF font Free 3 of 9 from http://www.squaregear.net/fonts/free3of9.shtml 

Further documentation for Code 39 and example PDF URL can be found here:

* [Code 39 Documentation ](https://en.wikipedia.org/wiki/Code_39)
* [Example PDF URL](http://localhost:8080/example.pdf)

### Guides
The class org.apache.fop.fonts.apps.TTFReader was used to generate the included TrueType font metrics file. This can be done in a batch run via a script of with the shown Java code:

```
String[] arguments = new String[] { "/PATH_TO/free3of9.ttf", "/PATH_TO/free3of9.xml" };
TTFReader.main(arguments);
```

### Additional Documentation
* [TrueType Font Metrics FOP Documentation ](https://xmlgraphics.apache.org/fop/0.95/fonts.html#truetype-metrics)

