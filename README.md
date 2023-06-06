# openLCA EcoSpold v2 API

This is a Java library for reading and writing data sets in the
[EcoSpold 2 data format](https://ecoinvent.org/the-ecoinvent-database/data-formats/ecospold2/).
It was originally part of the [openLCA modules](https://github.com/GreenDelta/olca-modules)
but was moved to a separate repository since version 2.0.0.

## Usage

Add this dependency to your project:

```xml
<dependency>
  <groupId>org.openlca</groupId>
  <artifactId>olca-ecospold-2</artifactId>
  <version>2.0.0</version>
</dependency>
```

See the [read-write-test](./src/test/java/org/openlca/ecospold/WriteReadTest.java)
for a simple example how data sets can be read and written.
