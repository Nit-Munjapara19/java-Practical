MINIBANK - PURE JAVA CONSOLE BANKING APPLICATION
================================================

This project uses Java only for application logic. It uses the Java standard
library and does not require PHP, Python, JavaScript, a database server, Maven,
Gradle, or third-party libraries.

REQUIREMENTS
------------
JDK 17 or newer (JDK 21 recommended).

RUN THE PREBUILT APPLICATION
----------------------------
Open a terminal in this folder and run:
    java -jar minibank.jar

COMPILE FROM SOURCE
-------------------
Windows PowerShell / Command Prompt:
    javac -d out src\annotation\*.java src\exception\*.java src\model\*.java src\repository\*.java src\util\*.java src\concurrency\*.java src\service\*.java src\*.java
    jar cfe minibank.jar MiniBank -C out .
    java -jar minibank.jar

Linux/macOS:
    find src -name "*.java" -print0 | xargs -0 javac -d out
    jar cfe minibank.jar MiniBank -C out .
    java -jar minibank.jar

FEATURES
--------
- Open accounts, deposit, withdraw, transfer, view statements, list accounts
- Input validation and custom banking exceptions
- Generic repository and Java collections
- Account persistence using Java object serialization
- Transaction logging and end-of-day report using Java NIO
- Thread-safe account operations and transaction processing

DATA
----
Account data is stored under data/ and reports under report/.
The application creates the required folders/files as needed.

NOTE
----
This is an educational console application, not production banking software.
