# Library for Android app by Euphoria

Tired of copying code from one project to another? It is easy to fix!
This library contains useful classes with most performance for Android.
Based on [Google Guava] (https://github.com/google/guava) and [Apache Commons IO] (http://commons.apache.org/proper/commons-io/).

## Features
- Easy API to use
- Small size: (<80 KB).
- Java 6+ and Android 2.3+
- Optimized for speed.

### Usage
**Manipulation with the File System**
```java
// Read text file
String lines = FileStreams.read(temp);

// Read binary file
byte[] array = FileStreams.readBytes(getInputStream());

// Copy one file to another
FileStreams.copy(temp, another);

// and more...
```
