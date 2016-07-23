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

## License
    The MIT License (MIT)
    
    Copyright (c) 2014-2016 Euphoria Dev Community.
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.   
